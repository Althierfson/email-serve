import java.util.Date;

public class Email {
    
    private int id;
    private String remetente;
    private String destinario;
    private String mensagem;
    private Date dataEvento;

    public Email(int id, String remetente, String destinario, String mensagem, Date dataEvento) {
        this.setId(id);
        this.setRemetente(remetente);
        this.setDestinario(destinario);
        this.setMensagem(mensagem);
        this.setDataEvento(dataEvento);
    }

    // Sets e gests

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDestinario() {
        return destinario;
    }

    public void setDestinario(String destinario) {
        this.destinario = destinario;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
