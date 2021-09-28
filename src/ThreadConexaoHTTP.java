import java.io.BufferedReader;
import java.io.File;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.TimeZone;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadConexaoHTTP implements Runnable {

    private final Socket socket;
    private boolean conectado;

    public ThreadConexaoHTTP(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        this.conectado = true;

        System.out.println("IP Address: " + this.socket.getInetAddress());

        while(this.conectado) {
            try {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                System.out.println("Requisição: ");

                String linha = buffer.readLine();
                System.out.println(linha);

                String[] dadosReq = linha.split(" ");
                String metodo = dadosReq[0];
                String caminhoArquivo = dadosReq[1];
                String protocolo = dadosReq[2];

                while (!linha.isEmpty()) {
                    System.out.println(linha);
                    linha = buffer.readLine();
                }

                if (caminhoArquivo.equals("/")) {
                    caminhoArquivo = "index.html";
                }
                
                File arquivo = new File("app/" +caminhoArquivo);

                String status = protocolo + " 200\r\n";

                if (!arquivo.exists()) {
                    status = protocolo + "404 Not Found\r\n";
                    arquivo = new File("app/404.html");
                }

                byte[] conteudo = Files.readAllBytes(arquivo.toPath());

                SimpleDateFormat formatador = new SimpleDateFormat("E, dd MMM YYYY hh:mm:ss", Locale.ENGLISH);
                formatador.setTimeZone(TimeZone.getTimeZone("GMT"));
                String dataFormatada = formatador.toString();

                String header = status
                    + "Location: http://localhost:8000/\r\n"
                    + "Date: " + dataFormatada + "\r\n"
                    + "Server: MeuServidor/1.0\r\n"
                    + "Content-Type: text/html\r\n"
                    + "Content-Length: " + conteudo.length + "\r\n"
                    + "Connection: close\r\n"
                    + "\r\n";
                
                OutputStream resposta = socket.getOutputStream();
                resposta.write(header.getBytes());
                resposta.write(conteudo);
                resposta.flush();
            } catch (IOException ex) {
                
                if (ex instanceof SocketTimeoutException) {
                    try {
                        this.conectado = false;
                        socket.close();
                    } catch (IOException ex1) {
                        Logger.getLogger(ThreadConexao.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            }
        }
        
    }
    
    // Sets e Gets

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public Socket getSocket() {
        return socket;
    }
}
