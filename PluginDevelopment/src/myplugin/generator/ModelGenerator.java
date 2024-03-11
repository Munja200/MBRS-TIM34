package myplugin.generator;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import freemarker.template.TemplateException;
import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.GeneratorOptions;

public class ModelGenerator extends BasicGenerator {

	public ModelGenerator(GeneratorOptions generatorOptions, String outputPath) {
		super(generatorOptions);
		super.setOutputPath(outputPath);
	}

	public void generate() {
		try {
			super.generate();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		List<FMClass> classes = FMModel.getInstance().getClasses();

		boolean mainAdded = false;
		for (int i = 0; i < classes.size(); i++) {
			FMClass cl = classes.get(i);
			Writer out;

			Map<String, Object> context = new HashMap<String, Object>();
			ArrayList<String> imports = new ArrayList<>();

			String import_str = "";
			try {
				if(!mainAdded) {
					FMClass mainClass = new FMClass("MbrsGeneratedApplication", cl.getTypePackage().substring(0, cl.getTypePackage().indexOf(".")), "true");
					out = getWriter(mainClass.getName(), mainClass.getTypePackage());
					if (out != null) {
						context.clear();
						context.put("class", mainClass);
						getTemplate().process(context, out);
						out.flush();
					}
					mainAdded = true;
				}
				out = getWriter(cl.getName(), cl.getTypePackage());
				if (out != null) {
					context.clear();
					context.put("class", cl);
					context.put("properties", cl.getProperties());
					context.put("importedPackages", cl.getImportedPackages());
					getTemplate().process(context, out);
					out.flush();
				}

			} catch (TemplateException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

}
