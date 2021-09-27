import java.util.ArrayList;

public class Usuario {
    
    private String endereco;
    private String nome;
    private String senha;
    private ArrayList<Integer> emails;

    public Usuario(String endereco, String nome, String senha) {
        this.setEndereco(endereco);
        this.setNome(nome);
        this.setSenha(senha);
        this.emails = new ArrayList<>();
    }

    // Sets e Gets

    public ArrayList<Integer> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<Integer> emails) {
        this.emails = emails;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
