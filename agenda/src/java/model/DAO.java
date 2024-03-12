package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Airosa
 */
public class DAO {
    /*
    Driver: Tipo de banco de dados
    URL: ip ou dominio do servidor
    Banco: nome do banco de dados
    Autewnticação: usuario e senha 
    
    |-------JDBC-------|
    |                  |
    |                  |
    classe         interface
    DriverManager  Connection
    */
    
    
    /* Modulo de Conexão */
    // Parametros de Conexão
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?"+
            "useTimezone=true&serverTimeZone=UTC";
    private String user = "root";
    private String password = "";   

    // Metodo de Conexão
    private Connection conectar() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    // Teste de Conexão

    /**
     *
     */
    public void testeConexao(){
        try {
            Connection con = conectar();
            System.out.println(con);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /* CRUD CREATE */
    // Insere novos contatos

    /**
     *
     * @param contato
     */
    public void inserirContato(JavaBeans contato) {
        String create = "insert into contatos (nome,fone,email)"
                      + " values (?,?,?)";
        
        try {
            // Abri a conexão com o BD
            Connection con = conectar();
            
            // Prepara a query para ser executada no BD
            PreparedStatement pst = con.prepareStatement(create);
            
            // Substitui os ? pelos valores contido nas variaveis do JB
            pst.setString(1, contato.getNome());
            pst.setString(2, contato.getFone());
            pst.setString(3, contato.getEmail());
            
            // Executa a query
            pst.executeUpdate();
            
            // Encerra a conexão com o BD
            con.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /* CRUD READ */
    // Vetor dinamico, que so aceita retorno de objetos do tipo JavaBeans
    // Lista contatos

    /**
     *
     * @return
     */
    public ArrayList<JavaBeans> listarContatos() {
        ArrayList<JavaBeans> contatos = new ArrayList<>();
        
        String read = "select * from contatos order by nome";
        
        try {
            // Abri a conexão com o BD
            Connection con = conectar();
            
            // Prepara a query para ser executada no BD
            PreparedStatement pst = con.prepareStatement(read);
            
            // Executa a query, e recebe os dados do BD
            ResultSet rs = pst.executeQuery();
            
            // O laço sera executado enquanto houver dados
            while(rs.next()) {
                // Variaveis de apoio que recebem os dados do BD
                String idcon = rs.getString(1);
                String nome = rs.getString(2);
                String fone = rs.getString(3);
                String email = rs.getString(4);
                
                // Armazena no JavaBeans
                contatos.add(new JavaBeans(idcon,nome,fone,email));               
            }         
            
            // Encerra a conexão com o BD
            con.close();
            
            // Retorna o array do tipo JavaBeans com os 
            // dados obtidos dobd
            return contatos;
                        
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }       
    }
    
    /* CRUD UPDADE */
    // Um seleciona contato, o outro para efetivamente alterar o contato
    // Seleciona contato

    /**
     *
     * @param contato
     */
    public void selecionarContato(JavaBeans contato) {
        String read = "select * from contatos where idcon = ?";
        
        try {
            // Abri a conexão com o BD
            Connection con = conectar();
            
            // Prepara a query para ser executada no BD
            PreparedStatement pst = con.prepareStatement(read);
            
            // Substitui os ? pelos valores contido nas variaveis do JB
            pst.setString(1, contato.getIdcon());
            
            // Executa a query, e recebo as info do contato
            ResultSet rs = pst.executeQuery();
            
            // O laço sera executado enquanto houver dados
            while(rs.next()) {       
                // Seta/armazena as variaveis do JAVABEANS
                contato.setIdcon(rs.getString(1));  
                contato.setNome(rs.getString(2)); 
                contato.setFone(rs.getString(3)); 
                contato.setEmail(rs.getString(4)); 
            }
            // Teste de recebimentos do BD para o JavaBeans/contato
            System.out.println(contato.getIdcon());
            System.out.println(contato.getNome());
            System.out.println(contato.getFone());
            System.out.println(contato.getEmail());
            
            
            // Encerra a conexão com o BD
            con.close();                   
                        
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
    // Update - atualiza o contato

    /**
     *
     * @param contato
     */
    public void alterarContato(JavaBeans contato) {
        String update = "update contatos set nome=?, fone=?, "
                + "email=? where idcon=?";
        
        try {
            // Abri a conexão com o BD
            Connection con = conectar();
            
            // Prepara a query para ser executada no BD
            PreparedStatement pst = con.prepareStatement(update);
            
            // Substitui os ? pelos valores contido nas variaveis do JB
            pst.setString(1, contato.getNome());
            pst.setString(2, contato.getFone());
            pst.setString(3, contato.getEmail());
            pst.setString(4, contato.getIdcon());
            
            // Executa a query
            pst.executeUpdate();
            
            // Encerra a conexão com o BD
            con.close();         
                       
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /* CRUD DELETE */

    /**
     *
     * @param contato
     */

    public void deletarContato(JavaBeans contato) {
        String delete = "delete from contatos where idcon=?";
        
        try {
            // Abri a conexão com o BD
            Connection con = conectar();
            
            // Prepara a query para ser executada no BD
            PreparedStatement pst = con.prepareStatement(delete);
            
            // Substitui os ? pelos valores contido nas variaveis do JB
            pst.setString(1, contato.getIdcon());
            
            // Executa a query
            pst.executeUpdate();
            
            // Encerra a conexão com o BD
            con.close();         
                       
        } catch (Exception e) {
            System.out.println(e);
        }
    }    
}
