<html>
    <head>
        <title>${titulo}</title>
    </head>
    <body>
        <h1>${titulo}</h1>

        <p>${nomeRelatorio}</p>
        <br>

        <table border="1" style="border-collapse: collapse; width: 100%;">
            <tbody>
                <tr>
                    <td>Matrícula</td>
                    <td>Estudante</td>
                    <td>Instituição</td>
                    <td>Dias da Semana</td>
                </tr>

                <#list estudantes as estudante>
                    <tr>
                        <td>${estudante.idMatricula}</td>
                        <td>${estudante.nome}</td>
                        <td>${estudante.instituicao}</td>
                        <td>${estudante.diaSemana}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </body>
</html>