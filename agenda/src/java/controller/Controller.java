package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import model.DAO;
import model.JavaBeans;
import java.util.ArrayList;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author Airosa
 */
public class Controller extends HttpServlet {
    DAO dao = new DAO();
    JavaBeans contatoJB = new JavaBeans();

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controller</title>");
            out.println("<link rel=\"stylesheet\" href=\"style.css\">");
            out.println("<link rel=\"icon\" href=\"imagens/flip-phone.png\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller at " + request.getContextPath() + "</h1>");
            out.println("<a href=\"index.html\" class=\"botao\">Voltar</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    /*
        Metodo que recebe requisições das páginas e formularios...
        Nesse caso no web.xml esse servlet mapea o /main eo /insert
        a pagina que redirecionar para /main, chamara esse servlet
        ou o formulario que usar action="insert", tambem estara
        chamando esse servlet, estando aqui no servlet, o servlet
        recaminha a request para onde ele quiser dando um response
        atravez de recalcular a rota dessa requisição, copmom exe:
        redirecionar a outra pagina de html ou etc...
    */

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chama metodo acima que tem o codigo HTML
        //processRequest(request, response);

        // Aparece no console/terminal a conexão
        dao.testeConexao();
        
        // Armazena o caminho da requisição
        String action = request.getServletPath();
        System.out.println(action);
        // Se action for igual a /main, execute esse bloco
        if (action.equals("/main")) {
            // Formas de fazer um redirect para pag HTML
            // index acessa esse SEREVLET, e esse SERVLET
            // redireciona a pag .JSP
            
            // Assim ou criando um metodo
            //response.sendRedirect("agenda.jsp");
            contatos(request, response);
        } else if(action.equals("/insert")) {
            // Servlet recebe requisições do FORM atravez do action="insert"
            
            // Se o conteudo da variavel action for igual a /insert
            // redireciona ao metodo responsavel, por encaminhar 
            // esta requisição a camada MODEL           
            novoContato(request, response);
        } else if(action.equals("/select")) {
            // Servlet recebe requisições da tag A atravez do href="select"
            
            // Se o conteudo da variavel action for igual a /select
            // redireciona ao metodo responsavel, por encaminhar 
            // esta requisição a camada MODEL           
            listarContato(request, response);
        } else if(action.equals("/update")) {
            // Servlet recebe requisições da FORM atravez do action="update"
            
            // Se o conteudo da variavel action for igual a /update
            // redireciona ao metodo responsavel, por encaminhar 
            // esta requisição a camada MODEL           
            editarContato(request, response);
        } else if(action.equals("/delete")) {
            // Servlet recebe requisições do href dentro do confirmador.js
            // atravez do window.loaction.href="delete?..."
            
            // Se o conteudo da variavel action for igual a /update
            // redireciona ao metodo responsavel, por encaminhar 
            // esta requisição a camada MODEL           
            deletarContato(request, response);
        } else if(action.equals("/report")) {
            // Servlet recebe requisições da tag A atravez do href="report"
            
            // Se o conteudo da variavel action for igual a /report
            // redireciona ao metodo responsavel, por encaminhar 
            // esta requisição a camada MODEL           
            gerarRelatorio(request, response);
        } else {
            // Caso o Servlet receba uma requisição e desconheça
            // ele ira redirecionar para a pag principal da aplicação
            response.sendRedirect("index.html");
        }        
    }
    // Lista Contatos

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void contatos(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        // Criando um objeto que ira receber os dados do JavaBeans
        ArrayList<JavaBeans> lista = dao.listarContatos();
        
        // Verifica no console se os dados foram obitidos
        for(JavaBeans dados: lista) {
            System.out.format("Nome: %s\nFone: %s\nEmail: %s\n\n",
                    dados.getNome(), dados.getFone(), dados.getEmail());
        }
        
        // Seta o conteudo da Lista para o atributo "contatos"/criado e
        // encaminha o "contato"(tem os dados da lista) para a pag agenda.jsp
        request.setAttribute("contatos", lista);
        RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
        rd.forward(request, response);
        
        // Redireciona a pag agenda.jsp
        response.sendRedirect("agenda.jsp");
    }
    
    // Novo Contato

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void novoContato(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        // Imprime variaveis do FORMULARIO no console
        System.out.println(request.getParameter("nome"));
        System.out.println(request.getParameter("fone"));
        System.out.println(request.getParameter("email"));
        
        // Armazena/seta os dados do formulario nas variaveis do 
        // objeto JAVABEANS temporariamente JB -> DAO -> BD
        contatoJB.setNome(request.getParameter("nome"));
        contatoJB.setFone(request.getParameter("fone"));
        contatoJB.setEmail(request.getParameter("email"));
        
        // Invoca no Model na classe DAO o metodo
        // responsavel por fazer a inserção dos dados no BD
        dao.inserirContato(contatoJB);
        
        // Depois de inserir os dados do JB no DAO, eo DAO no BD
        // redireciona a pag agenda.jsp
        response.sendRedirect("main");
    }
    
    // Listar Contato

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void listarContato(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        // Recebe o parametro que esta vindo da tag A
        String idcon = request.getParameter("idcon");
        System.out.println(idcon);
        
        // Setar/armazena na variavel idcon do JAVABEANS
        // = contatoJB.setIdcon(request.getParameter("idcon"));
        contatoJB.setIdcon(idcon);

        
        // Executa o metodo selecionarContato() da classe DAO
        dao.selecionarContato(contatoJB);
        
        // Seta os atributos do formulario com o conteudo JavaBeans
        // Cria uma variavel e preenche ela com dados contido na classe 
        // Javabeans, e que posteriomente pode ser invocado na pagina editar.jsp
        // utilizando o nome da variavel criado exp: "editar", ao ser invocado
        // ela pode preenhcer a tag input com o conteudo que ela armazenou
        request.setAttribute("idcon", contatoJB.getIdcon());
        request.setAttribute("nome", contatoJB.getNome());
        request.setAttribute("fone", contatoJB.getFone());
        request.setAttribute("email", contatoJB.getEmail());
        
        // Encaminhar ao documento editar.jsp
        RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
        rd.forward(request, response);
    }
    
    // Editar Contato

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void editarContato(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        // Teste de recebimento
        System.out.println(request.getParameter("idcon"));
        System.out.println(request.getParameter("nome"));
        System.out.println(request.getParameter("fone"));
        System.out.println(request.getParameter("email"));
        
        // Setar/armazenar nas variaveis do JAVABEANS
        contatoJB.setIdcon(request.getParameter("idcon"));
        contatoJB.setNome(request.getParameter("nome"));
        contatoJB.setFone(request.getParameter("fone"));
        contatoJB.setEmail(request.getParameter("email"));
        
        // Executa o metodo alteraContato() da classe DAO
        dao.alterarContato(contatoJB);
        
        // Redireciona diretamente ao /main - para ja aparecer os
        // dados ja atualizados na agenda 
        response.sendRedirect("main");
    }
    
    // Excluir Contato

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deletarContato(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        // Recebendo o ID do contato a ser excluido
        String idcon = request.getParameter("idcon");
        System.out.println(idcon);
        
        // Setar/armazenar na varaivel idcon do JavaBeans
        // = contatoJB.setIdcon(request.getParameter("idcon"));
        contatoJB.setIdcon(idcon);
        
        // Executando o metodo deletarContato() da classe DAO
        dao.deletarContato(contatoJB);
        
        // Redireciona diretamente ao /main - para ja aparecer os
        // dados ja atualizados na agenda 
        response.sendRedirect("main");
    }

    // Gerar Relatorio

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        // Criando o objeto - reposnavel pela geração do relatorio em PDF,
        // o obj acessa atributos e metodos da biblioteca ITEXTPDF
        Document documento = new Document();
        
        try {
            // Tipo de conteudo - tipo de resposta sera um documento PDF
            response.setContentType("application/pdf");
            
            // Nome para o documento PDF
            response.addHeader("content-Disposition", "inline; "
                    + "filename=contatos.pdf");
            
            // Criar o documento PDF
            PdfWriter.getInstance(documento, response.getOutputStream());
                                                     
            // Criando um Cabeçalho
            PdfPCell coluna1 = new PdfPCell(new Paragraph("Nome"));
            PdfPCell coluna2 = new PdfPCell(new Paragraph("Fone"));
            PdfPCell coluna3 = new PdfPCell(new Paragraph("E-mail"));
            
            // Criando uma tabela e add o Cabeçalho + Dados
            PdfPTable tabela = new PdfPTable(3); // 3 Colunas
            tabela.addCell(coluna1);
            tabela.addCell(coluna2);
            tabela.addCell(coluna3);
            
            ArrayList<JavaBeans> lista = dao.listarContatos();
            for(JavaBeans dados: lista) {    
                tabela.addCell(dados.getNome());
                tabela.addCell(dados.getFone());
                tabela.addCell(dados.getEmail());
            }
            
            // Montagem
            // Abrir/write o documento PDF - e escreve nele "Lista de Contatos"
            // + Tabela - A tabela tem o cabeçalho+dados
            documento.open();
            documento.add(new Paragraph("Lista de Contatos:"));
            documento.add(new Paragraph(" ")); // Quebra uma linha do doc PDF
            documento.add(tabela);
            
            // Fechando documento
            documento.close();
            
        } catch (Exception e) {
            System.out.println(e);
            documento.close();
        }
        
        // Redireciona diretamente ao /main - para ja aparecer os
        // dados ja atualizados na agenda 
        response.sendRedirect("main");
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
