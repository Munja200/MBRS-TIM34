package myplugin;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import myplugin.analyzer.AnalyzeException;
import myplugin.analyzer.ModelAnalyzer;
import myplugin.generator.CreatePageGenerator;
import myplugin.generator.DetailsPageGenerator;
import myplugin.generator.EditPageGenerator;
import myplugin.generator.IndexPageGenerator;
import myplugin.generator.ListPageGenerator;
import myplugin.generator.ModelGenerator;
import myplugin.generator.RepositoryGenerator;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;

/** Action that activate code generation */
@SuppressWarnings("serial")
class GenerateAction extends MDAction{


	public GenerateAction(String name) {
		super("", name, null, null);
	}

	public void actionPerformed(ActionEvent evt) {

		if (Application.getInstance().getProject() == null) return;
		Package root = Application.getInstance().getProject().getModel();

		if (root == null) return;

		ModelAnalyzer analyzer = null;
		GeneratorOptions generatorOptions = null;

		String packageName = choosePackageName();
		String outputPath = chooseOutputPath();
		String javaOutputPath = outputPath + "/src/main/java";
		String templatesOutputpath = outputPath + "/src/main/resources";
		
		try {
			generateModel(analyzer, root, generatorOptions, packageName, javaOutputPath);
			generateRepositories(analyzer, root, generatorOptions, packageName, javaOutputPath);
			generateIndexPage(analyzer, root, generatorOptions, templatesOutputpath);
			generateCreatePage(analyzer, root, generatorOptions, templatesOutputpath);
			generateEditPage(analyzer, root, generatorOptions, templatesOutputpath);
			generateListPage(analyzer, root, generatorOptions, templatesOutputpath);
			generateDetailsPage(analyzer, root, generatorOptions, templatesOutputpath);
			
			JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
					+ outputPath + ", package: " + packageName);
		} 
		catch (AnalyzeException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}

	private String choosePackageName() {
		return JOptionPane.showInputDialog("Enter package name");
	}

	private String chooseOutputPath() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(null);
		String path = "";

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			path = chooser.getSelectedFile().getAbsolutePath();
		}

		return path;
	}

	private void generateModel(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions, String packageName, String outputPath)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, packageName + ".model");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ModelLayerGenerator");
		ModelGenerator generator = new ModelGenerator(generatorOptions, outputPath);
		generator.generate();
	}

	
	private void generateRepositories(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions, String packageName, String outputPath)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, packageName + ".repository");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("RepositoryGenerator");
		RepositoryGenerator repositoryGenerator = new RepositoryGenerator(generatorOptions, outputPath);
		repositoryGenerator.generate();

	}

	private void generateCreatePage(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions, String outputPath)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "templates");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("CreatePageGenerator");
		CreatePageGenerator createPageGenerator = new CreatePageGenerator(generatorOptions, outputPath);
		createPageGenerator.generate();
	}
	
	private void generateEditPage(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions, String outputPath)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "templates");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("EditPageGenerator");
		EditPageGenerator generator = new EditPageGenerator(generatorOptions, outputPath);
		generator.generate();
	}
	
	private void generateIndexPage(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions, String outputPath)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "templates");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("IndexPageGenerator");
		IndexPageGenerator generator = new IndexPageGenerator(generatorOptions, outputPath);
		generator.generate();
	}
	
	private void generateListPage(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions, String outputPath)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "templates");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ListPageGenerator");
		ListPageGenerator generator = new ListPageGenerator(generatorOptions, outputPath);
		generator.generate();
	}
	
	private void generateDetailsPage(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions, String outputPath)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "templates");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("DetailsPageGenerator");
		DetailsPageGenerator generator = new DetailsPageGenerator(generatorOptions, outputPath);
		generator.generate();
	}
	
	private void exportToXml() {
		if (JOptionPane.showConfirmDialog(null, "Do you want to save FM Model?") ==
				JOptionPane.OK_OPTION)
		{
			JFileChooser jfc = new JFileChooser();
			if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				String fileName = jfc.getSelectedFile().getAbsolutePath();

				XStream xstream = new XStream(new DomDriver());
				BufferedWriter out;
				try {
					out = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(fileName), "UTF8"));
					xstream.toXML(FMModel.getInstance().getClasses(), out);
					xstream.toXML(FMModel.getInstance().getEnumerations(), out);

				} catch (UnsupportedEncodingException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
	}

}
