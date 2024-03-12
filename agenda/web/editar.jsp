<%-- 
    Document   : editar
    Created on : 8 de mar. de 2024, 11:50:20
    Author     : Airosa
--%>

<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edição</title>
        <link rel="stylesheet" href="style.css">
        <link rel="icon" href="imagens/flip-phone.png">
    </head>
    <body>
        <h1>Editar contato</h1>
        <%-- action="" Ação que vai enviar o formulario depois de validado e
            editado, para a camada Controller --%>
        <form name="frmContato" action="update">
            <table>
                <tr> <%-- Recupera o parametro "idcon" criado no Contrroler --%>
                    <td><input type="text" name="idcon" readonly="" 
                        value="<%out.print(request.getAttribute("idcon"));%>" 
                        class="caixa"></td>
                </tr>
                <tr>
                    <td><input type="text" name="nome" 
                        value="<%out.print(request.getAttribute("nome"));%>" 
                        class="caixa"></td>
                </tr>
                <tr>
                    <td><input type="text" name="fone" 
                        value="<%out.print(request.getAttribute("fone"));%>" 
                        class="caixa"></td>
                </tr>
                <tr>
                    <td><input type="text" name="email" 
                        value="<%out.print(request.getAttribute("email"));%>" 
                        class="caixa"></td>
                </tr>
            </table>
            <input type="button" value="Salvar" class="botao caixa" 
                   id="btn" onclick="validar()">
        </form>
        <script src="scripts/validador.js"></script>
    </body>
</html>