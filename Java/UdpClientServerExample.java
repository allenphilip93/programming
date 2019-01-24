import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class UdpClientServerExample {
    public static void main(String[] args) {
        int port = 50001;
        UdpUnicastServer server = new UdpUnicastServer(port);
        UdpUnicastClient client = new UdpUnicastClient(port);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(client);
        executorService.submit(server);
    }
}