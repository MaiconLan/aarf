<html>
    <body style="font-size: 15px">
        <h4>${titulo}</h4>

        <p style="font-size: 8px">${nomeRelatorio}</p>

        <center>
            <p style="font-size: 8px">Edital ${edital} - ${instituicao}</p>
        </center>
        <br>

        <table border="1" style="table-layout:fixed; border-collapse: collapse; width: 100%; font-size: 8px;">
            <tbody>
                <tr>
                    <td style="width: 30px; font-weight: bold">Matrícula</td>
                    <td style="font-weight: bold">Estudante</td>
                    <td style="font-weight: bold">Segunda</td>
                    <td style="font-weight: bold">Terça</td>
                    <td style="font-weight: bold">Quarta</td>
                    <td style="font-weight: bold">Quinta</td>
                    <td style="font-weight: bold">Sexta</td>
                </tr>

                <#list estudantes as estudante>
                    <tr>
                        <td>${estudante.idMatricula}</td>
                        <td>${estudante.nome}</td>
                        <td>
                            <#if estudante.diasSemana?seq_contains("Segunda-Feira")>[_____]<#else>---------------------------</#if>
                        </td>
                        <td>
                            <#if estudante.diasSemana?seq_contains("Terça-Feira")>[_____]<#else>---------------------------</#if>
                        </td>
                        <td>
                            <#if estudante.diasSemana?seq_contains("Quarta-Feira")>[_____]<#else>---------------------------</#if>
                        </td>
                        <td>
                            <#if estudante.diasSemana?seq_contains("Quinta-Feira")>[_____]<#else>---------------------------</#if>
                        </td>
                        <td>
                            <#if estudante.diasSemana?seq_contains("Sexta-Feira")>[_____]<#else>---------------------------</#if>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </body>
</html>