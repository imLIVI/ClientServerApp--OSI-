import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
    public final String FORMAT = "%-60s %-20s";
    private final int port = 8080;
    // Create a database of registered users
    private DataBase dataBase = new DataBase();

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                // Waiting for connection
                Socket clientSocket = serverSocket.accept();
                // For data to client
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                //For data from client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                System.out.printf("Connection accepted!\n");
                System.out.println("[SYSTEM INFO] For further work, you need to LOG IN or SIGN UP");

                boolean logIn = true;
                String answer = in.readLine();
                out.println(String.format(FORMAT, "[SERVER <-> CLIENT] Are you registered in the system? (y/n):", answer));
                if (answer.equals("y")) {
                    do {
                        String login = in.readLine();
                        out.println(String.format(FORMAT, "[SERVER <-> CLIENT] Email (login): ", login));
                        String password = in.readLine();
                        out.println(String.format(FORMAT, "[SERVER <-> CLIENT] Password: ", password));
                        if (dataBase.checkInDataBase(login, password)) {
                            logIn = true;
                            out.println(String.format("[SYSTEM INFO] Welcome to service! :)"));
                        } else {
                            logIn = false;
                            out.println(String.format("[SYSTEM INFO] Authorization failed :("));
                            answer = in.readLine();
                            out.println(String.format(FORMAT, "[SYSTEM INFO] Do you want try again? (y/n)", answer));
                            if (answer.equals("n"))
                                break;
                        }
                    } while (!logIn);
                } else if (answer.equals("n")) {
                    //Registration
                    out.println(String.format("[SYSTEM INFO] Let's register!"));
                    String name = in.readLine();
                    out.println(String.format(FORMAT, "[SERVER <-> CLIENT] Enter your name: ", name));
                    String age = in.readLine();
                    out.println(String.format(FORMAT, "[SERVER <-> CLIENT] Enter your age: ", age));
                    String login = in.readLine();
                    out.println(String.format(FORMAT, "[SERVER <-> CLIENT] Enter an email (login): ", login));
                    String password = in.readLine();
                    out.println(String.format(FORMAT, "[SERVER <-> CLIENT] Enter the password: ", password));
                    if (!dataBase.checkInDataBase(login, password)) {
                        dataBase.addUser(name, Integer.valueOf(age), login, password);
                    } else {
                        out.println(String.format("[SYSTEM INFO] A user with this username already exists!"));
                    }
                    out.println(String.format("[SYSTEM INFO] Registration is successful!"));
                } else {
                    out.println(String.format("[ERROR] Incorrect input data!"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
