package business;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import controller.Identity;
import dao.MatriculaDAO;
import exception.MatriculaBusinessException;
import freemarker.FreemarkerUtils;
import freemarker.relatorio.matricula.EstudanteFM;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import model.Edital;
import org.primefaces.model.DefaultStreamedContent;

import javax.inject.Inject;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RelatorioMatriculaBusiness implements Serializable {

    private static final long serialVersionUID = 3318053777525893197L;

    @Inject
    private MatriculaDAO matriculaDAO;

    @Inject
    private InstituicaoBusiness instituicaoBusiness;

    @Inject
    private EditalBusiness editalBusiness;

    @Inject
    private Identity identity;

    public String gerarRelatorioHtml(Long idEdital, Long idInstituicao) throws MatriculaBusinessException {
        List<EstudanteFM> estudantes = matriculaDAO.obterRelatorioMatricula(idEdital, idInstituicao);

        for (EstudanteFM estudante : estudantes) {
            estudante.setDiasSemana(matriculaDAO.getDiasSemana(estudante.getIdMatricula(), idInstituicao));
        }

        if (estudantes.isEmpty())
            throw new MatriculaBusinessException("Não há estudantes matriculados para a seleção atual!");

        Map<String, Object> templateData = new HashMap<>();

        templateData.put("estudantes", estudantes);

        templateData.put("edital", editalBusiness.listarEdital(idEdital).getTitulo());
        templateData.put("instituicao", instituicaoBusiness.obterInstituicao(idInstituicao));
        templateData.put("titulo", "Matriculas");
        templateData.put("nomeRelatorio", "Relatório de Matrículas");

        try {
            Template template = FreemarkerUtils.getTemplate("relatorio_matriculas.ftl");

            StringWriter stringWriter = new StringWriter();
            template.process(templateData, stringWriter);

            return stringWriter.toString();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            throw new MatriculaBusinessException("Erro ao gerar o relatório!");
        }
    }

    public DefaultStreamedContent gerarRelatorioPdf(Long idEdital, Long idInstituicao) throws MatriculaBusinessException {
        try {
            String html = gerarRelatorioHtml(idEdital, idInstituicao);
            String arquivo = idEdital + "_" + idInstituicao + "_relatorio_matricula.pdf";

            ByteArrayOutputStream os = new ByteArrayOutputStream();

            Document document = new Document(PageSize.LETTER);
            PdfWriter.getInstance(document, os);
            document.open();
            document.addAuthor("AARF");
            document.addCreator(identity.obterNome());
            document.addCreationDate();
            document.addTitle("Relatório de Matrícula");

            HTMLWorker htmlWorker = new HTMLWorker(document);
            htmlWorker.parse(new StringReader(html));
            document.close();

            InputStream is = new ByteArrayInputStream(os.toByteArray());

            return new DefaultStreamedContent(is, "application/pdf", arquivo);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
