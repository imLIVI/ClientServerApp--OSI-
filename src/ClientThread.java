import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread extends Thread {
    private final int port = 8080;
    private final String host = "netology.homework";
    private String response = null;
    private int numOfClient;

    public ClientThread(int numOfClient){
        this.numOfClient = numOfClient;
    }

    @Override
    public void run() {
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            // 0. Client sends his name to server
            // 3. Client gets the string with name and prints it
            if (numOfClient == 0) {
                out.println("y");
                response = in.readLine();
                System.out.println(response);

                out.println("anonym@gmail.com");
                response = in.readLine();
                System.out.println(response);

                out.println("anonym123");
                response = in.readLine();
                System.out.println(response);

                response = in.readLine();
                System.out.println(response);

                out.println("y");
                response = in.readLine();
                System.out.println(response);

                out.println("lily@gmail.com");
                response = in.readLine();
                System.out.println(response);

                out.println("harry934");
                response = in.readLine();
                System.out.println(response);

                response = in.readLine();
                System.out.println(response);

                numOfClient++;
            } else if (numOfClient == 1) {
                out.println("n");
                response = in.readLine();
                System.out.println(response);

                response = in.readLine();
                System.out.println(response);

                out.println("Kate");
                response = in.readLine();
                System.out.println(response);

                out.println("21");
                response = in.readLine();
                System.out.println(response);

                out.println("kate@gmail.com");
                response = in.readLine();
                System.out.println(response);

                out.println("kate431");
                response = in.readLine();
                System.out.println(response);

                response = in.readLine();
                System.out.println(response);

                numOfClient++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
