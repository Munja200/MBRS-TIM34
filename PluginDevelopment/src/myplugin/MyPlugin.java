package myplugin;

import java.io.File;

import javax.swing.JOptionPane;

import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;


import com.nomagic.actions.NMAction;
import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;

/** MagicDraw plugin that performes code generation */
public class MyPlugin extends com.nomagic.magicdraw.plugins.Plugin {
	
	String pluginDir = null; 
	
	public void init() {
		JOptionPane.showMessageDialog( null, "My Plugin init");
		
		pluginDir = getDescriptor().getPluginDirectory().getPath();
		
		// Creating submenu in the MagicDraw main menu 	
		ActionsConfiguratorsManager manager = ActionsConfiguratorsManager.getInstance();		
		manager.addMainMenuConfigurator(new MainMenuConfigurator(getSubmenuActions()));
		
		/** @Todo: load project options (@see myplugin.generator.options.ProjectOptions) from 
		 * ProjectOptions.xml and take ejb generator options */
		
		modelOptions();
		repositoryOptions();
		indexPageOptions();
		createPageOptions();
		editPageOptions();
		listPageOptions();
		detailsPageOptions();
		
		//for test purpose only:
		GeneratorOptions ejbOptions = new GeneratorOptions("c:/temp", "ejbclass", "templates", "{0}.java", true, "ejb"); 				
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("EJBGenerator", ejbOptions);
				
		ejbOptions.setTemplateDir(pluginDir + File.separator + ejbOptions.getTemplateDir()); //apsolutna putanja
	}
	
	private void modelOptions() {
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/java", "jpa_model", "templates", "{0}.java", true, "uns.ftn.mbrs.model");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("ModelLayerGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}
	
	private void repositoryOptions() {
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/java", "repository", "templates", "{0}Repository.java", true, "uns.ftn.mbrs.repository");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("RepositoryGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}
	
	private void createPageOptions() {
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/resources", "create_page", "templates", "Create{0}.ftlh", true, "web");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("CreatePageGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}

	private void editPageOptions() {
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/resources", "edit_page", "templates", "Edit{0}.ftlh", true, "web");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("EditPageGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}
	
	private void indexPageOptions() {
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/resources", "index_page", "templates", "{0}.ftlh", true, "webapp");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("IndexPageGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}
	
	private void listPageOptions() {
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/resources", "list_page", "templates", "{0}s.ftlh", true, "webapp");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("ListPageGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}
	
	private void detailsPageOptions() {
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/resources", "details_page", "templates", "{0}.ftlh", true, "web");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("DetailPageGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}
	
	private NMAction[] getSubmenuActions()
	{
	   return new NMAction[]{
			new GenerateAction("Generate"),
	   };
	}
	
	public boolean close() {
		return true;
	}
	
	public boolean isSupported() {				
		return true;
	}
}


