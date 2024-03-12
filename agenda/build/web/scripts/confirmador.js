/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*
 * COnfirmação de exclusão de contato
 */

function confirmar(idcon) {
    let resposta = confirm("Confirma a exclusão deste contato ?");
    if(resposta) {
        // alert(idcon);
        // É usado para fazer um redirecionamento
        // Faz com que o usuario saia desse documento, e seje encaminhado
        // para outro local, no caso aqui para "delete", e quem trata as
        // requisições, é o SERVLET, la no Servlet vai receber essa request
        window.window.location.href = "delete?idcon=" + idcon;
    }
}
