package myplugin.generator;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import freemarker.template.TemplateException;
import myplugin.generator.options.GeneratorOptions;

public class CssGenerator extends BasicGenerator {

    public CssGenerator(GeneratorOptions generatorOptions, String outputPath) {
        super(generatorOptions);
        super.setOutputPath(outputPath);
    }

    public void generate() {

        try {
            super.generate();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        Map<String, Object> context = new HashMap<String, Object>();
        try {
            Writer out = getWriter("common", "templates");
            getTemplate().process(context, out);
            out.flush();
        } catch (TemplateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}