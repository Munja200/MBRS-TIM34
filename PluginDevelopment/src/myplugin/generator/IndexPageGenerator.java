package myplugin.generator;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;
import freemarker.template.TemplateException;
import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.GeneratorOptions;

public class IndexPageGenerator extends BasicGenerator {

    public IndexPageGenerator(GeneratorOptions generatorOptions, String outputPath) {
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
        List<FMClass> pageClasses = new ArrayList<FMClass>();
        for (FMClass fmClass: classes ) {
            if (fmClass.getPage()!=null) {
                pageClasses.add(fmClass);
            }
        }
        Map<String, Object> context = new HashMap<String, Object>();
        try {
            context.put("allClasses", pageClasses);
            Writer out = getWriter("index", "templates");
            getTemplate().process(context, out);
            out.flush();
        } catch (TemplateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}