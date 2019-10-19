package freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;

public class FreemarkerUtils {

    public static Template getTemplate(String arquivo) throws IOException {
        Configuration cfg = new Configuration(Configuration.getVersion());

        cfg.setClassForTemplateLoading(FreemarkerUtils.class, "/freemarker");
        cfg.setDefaultEncoding("UTF-8");

        return cfg.getTemplate(arquivo);
    }

}
