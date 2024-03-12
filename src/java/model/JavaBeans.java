package model;

/**
 *
 * @author Airosa
 */
public class JavaBeans {
    //Variaveis de acrodo que tem no BD
    private String idcon;
    private String nome;
    private String fone;
    private String email;

    /**
     *
     */
    public JavaBeans() {
        super();
    }
    
    /**
     *
     * @param idcon
     * @param nome
     * @param fone
     * @param email
     */
    public JavaBeans(String idcon, String nome, String fone, String email) {
        this.idcon = idcon;
        this.nome = nome;
        this.fone = fone;
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getIdcon() {
        return idcon;
    }

    /**
     *
     * @param idcon
     */
    public void setIdcon(String idcon) {
        this.idcon = idcon;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return 
     */
    public String getFone() {
        return fone;
    }

    /**
     *
     * @param fone
     */
    public void setFone(String fone) {
        this.fone = fone;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }    
}
