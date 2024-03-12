<%-- 
    Document   : agenda
    Created on : 4 de mar. de 2024, 12:56:05
    Author     : Airosa

    PASSOS de criação de novo contato
    0: Request - index.html + css -> Controller/controller-main (redirect) ->
    1: -> agenda.jsp + css (new contato) -> novo.html + css ->
    2: Recebe os dados, novo.html + css (request)-> valida.js ->
    3: -> Valida os dados, valida.js, Repassa para -> Controller/Servlet ->
    
    4: Controller recebe os dados validados
    5: Controller/Servlet -> Armazena na classe JavaBeans
    6: Controller/Servlet -> Invoca na camada MODEL, DAO - metodo de inserir c..
    7: Model/DAO - Metodo, faz um request na classe JavaBeans sobre os dados
    8: JavaBeans - Response/retorna os dados para o metodo da classe DAO
    9: Model/DAO/metodo - Insere no BD os dados
    10: Controller - redireciona para pagina agenda.jsp
--%>

<%--
    Editar Contato

    PASSOS de edição de um contato
    0: Request - Botao de ediçaõ - encaminha o numero de ID do contato
    que sera editado
    1: O SERVLET - encaminha essa requisição a camada model, aramzenando
    na classe JAVABEANS, o numero do ID do contato que sera alterado
    2: Apos o armazenamento do numero do ID na classe JAVABEANS, o SERVLET
    executa o METODO da classe DAO, responsavel por fazer um select do 
    contato que sera editado
    3: Metodo que sera responsavel, pela seleção do contato a ser editado,
    requisita da classe JAVABEANS, o ID do contato 
    4: Tendo o ID do contato o metodo, executa um select do contato especifico
    no banco de dados
    5: O banco de dados atualiza e retorna o contato para o metodo
    6: O metodo seta as variaveis do JAVABEANS com os dados do contato
    7: A classe JAVABEANS, encaminha os dados do contato ao SERVLET
    8: O SERVLET redireciona os dados ao documento editar.jsp, os campos
    desse formulario sao preenchidos automaticamente com os dados do contato
    que sera editado
    9: Apos a edição dos dados do contato, no formulario ao precionar o 
    botao SALVAR, os campos obrigatorios são validados no documento javascript
    e apos a validação os dados do contato são encaminhados para o SERVLET
    10: O SERVLET seta as variaveis JAVABEANS com os dados editados do contato
    11: Apos o armazenamento dos dados do contato na classe JAVABEANS o SERVLET
    executa o metodo responsavel pela edição do contato no BD
    12: O novo metodo responsavel pela edição do contato, requisita da classe
    JAVABEANS os dados do contato
    13: Tendo obtido os dados do contato, o metodo executa o update no BD
    14: Apos a edição do contato, o SERVLET faz um redirecionamento ao
    documento agenda.jsp, exibindo a listagem de todos os contatos incluindo
    a atualização do contato que foi editado
--%>

<%--
    Deletar contato
    0: Request - Botao de excluir - encaminha o numero do ID do contato
    que sera excluido, para o confirmado.js
    1: Confirmador.js - Vai pedir a confirmação de exclusão do contato,
    e vai encaminhar uma requisição ao SERVLET, enviando o ID do contato
    que sera excluido
    2: O Servlet armazena na classe JAVABEANS o ID do contato que sera 
    exluido
    3: Apos o envio do ID o contato a ser excluido pelo Servlet, O Servlet
    executa o metodo da classe DAO, responsavel por excluir o contato
    4: O metodo responsavel pela exclusão do contato, requisita o ID da
    classe JavaBeans
    5: Sabendo o ID do contato a ser excluido, o metodo executa o delete
    no BD
    6: Apos a exclusão no BD, o Servlet encaminha a resposta(response)
    para o documento agenda.jsp, atualizando a lista de contatos
--%>

<%--
    Gerar relatorio PDF 
    0: Request - Botao de PDF - encaminha ao SERVLET
    1: O Sevlet, cria o documento e executa o metodo listarContatos() da 
    classe DAO
    2: O metodo é o mesmo para listar todos os contatos de agenda.jsp,
    ele executa um select no BD
    3: O BD encaminha a listagem de todos os contatos para o metodo listar 
    contatos
    4: O metodo encaminha atravez do vetor dinamico os dados de todos
    os contatos para a classe JavaBeans
    5: A classe JavaBeans encaminha atravez de um vetor dinamico a listagem
    de contatos para o Servlet
    6: O servlet finaliza a criação do documento PDF com a listagem dos 
    contatos, exibindo o documento PDF que pode ser salvo ou impresso
--%>

<%--
    Documentação do Projeto
    0: Documentação do BD - Passo a passo esta no script sql do BD
    1: Revisão do codigo-fonte - ela remove os comentarios que foram inseridos
    no codigo-fonte para questao didaticas
    2: Gera a documentação especificas das classes JAVA, para isso necessita
    instalar um pluguin e tambem gerar a documentação no formato HTML usando
    o JavaDoc
    3: Enviar a documentação para o GITHUB
--%>

<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%-- Extrair o conteudo do obejto tipo ListaJavaBeans --%>
<%@page import="model.JavaBeans"%>
<%@page import="java.util.ArrayList"%>

<%--// Trabalhando com a Lista --%>
<%
    // @SupressWarnings("unchacked") - ignora aviso de erro
    // Obtem os dados inseridos no atributo "contatos", 
    // que foi criado no SERVLET
    ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>)
            request.getAttribute("contatos");
    
    // Verifica na pag se os dados foram obitidos
    /*
    for(JavaBeans dados: lista) {    
        out.println(dados.getIdcon());
        out.println(dados.getNome());
        out.println(dados.getFone());
        out.println(dados.getEmail());
    }    
    */
%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda de Contatos</title>
        <link rel="stylesheet" href="style.css">
        <link rel="icon" href="imagens/flip-phone.png">
    </head>
    <body>
        <h1>Agenda de Contatos</h1>
        <a href="novo.html" class="botao">Novo Contato</a>
        <a href="report" class="botao" id="relatorio">Relatorio</a>
        <table id="tabela">
            <thead>
                <tr> <%--// Cabeçalho - tr = linha, th = coluna  --%>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Fone</th>
                    <th>E-mail</th>
                    <th colspan="2">Opções</th>
                </tr>
            </thead>
            <tbody>
                <%for(JavaBeans dados: lista) { %>   
                    <tr>
                        <td><%=dados.getIdcon()%></td>
                        <td><%=dados.getNome()%></td>
                        <td><%=dados.getFone()%></td>
                        <td><%=dados.getEmail()%></td>
                        <td><a href="select?idcon=<%=dados.getIdcon()%>" 
                               class="editar">
                                Editar
                        </a></td>
                        <td><a class="editar" id="excluir" onclick=
                            "confirmar(<%=dados.getIdcon()%>)">
                                Excluir
                        </a></td>  
                        <%-- Ou
                            <td><a id="ed" href=
                                "javascript: confirmar(<%=dados.getIdcon()%>)">
                                Excluir
                            </a></td>
                        --%>
                    </tr>
                <%} %>
            </tbody>
            <script src="scripts/confirmador.js"></script>
        </table>
    </body>
</html>
