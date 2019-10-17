package freemarker;

import freemarker.relatorio.matricula.EstudanteFM;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import model.Estudante;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreemarkerUtils {

    public static void main(String[] args) {
        try {

            Map<String, Object> templateData = new HashMap<>();

            List<EstudanteFM> list = new ArrayList<>();

            EstudanteFM estudante = new EstudanteFM();
            estudante.setIdMatricula(1L);
            estudante.setNome("Maicon");
            estudante.setInstituicao("Unisul");
            estudante.setDiaSemana("Segunda");
            list.add(estudante);

            templateData.put("estudantes", list);

            templateData.put("titulo", "Matriculas");
            templateData.put("nomeRelatorio", "Rel Matricula");

            Template template = getTemplate("relatorio_matriculas.ftl");

            templateData.put("estudantes", list);

            try (StringWriter out = new StringWriter()) {

                template.process(templateData, out);
                System.out.println(out.getBuffer().toString());

                out.flush();
            } catch (TemplateException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Template getTemplate(String arquivo) throws IOException {
        Configuration cfg = new Configuration(Configuration.getVersion());

        cfg.setClassForTemplateLoading(FreemarkerUtils.class, "/freemarker");
        cfg.setDefaultEncoding("UTF-8");

        return cfg.getTemplate(arquivo);
    }

}
