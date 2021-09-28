import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws Exception {

        ServerSocket socket = new ServerSocket(8000);

        try {
            ExecutorService pool = Executors.newFixedThreadPool(20);

            while(true) {
                pool.execute(new ThreadConexaoHTTP(socket.accept()));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            socket.close();
        }
    }
}
