/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*
 * Validação de formulario
 */

function validar() {
    /*
     * variavel nome, vai receber do formulario frmContato, 
     * do campo(input) nome, o seu valor(value)
     */
    let nome = frmContato.nome.value;
    let fone = frmContato.fone.value;
    let email = frmContato.email.value;
    
    // Verifica se os campos estao preenchidos
    if(nome === "") {
        alert('Preencha o campo Nome');
        // Posiciona o cursor novamente ao campo
        frmContato.nome.focus();
        // Retorna sera falso, se o usuario nao preencher esse campo
        // Nao enviara as info para camada controller, que ira add ao BD
        return false;
    } else if (fone === "") {
        alert('Preencha o campo Fone');
        frmContato.fone.focus();
        return false;
    } else {
        /*
         * Se todas condições de cima for falso, indica que,
         * os campos obrigatorios estao preenchidos, entao,
         * o envio dos dados ficara liberado
         * 
         * No documento, o formulario="frmContatos", envie ele(submit)/aceite
         */
        document.forms["frmContato"].submit();
    }
}


