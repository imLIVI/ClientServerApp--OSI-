public class Main {
    public static final int NUMBER_OF_CLIENTS = 2;

    public static void main(String[] args) {
        ServerThread serverThread = new ServerThread();
        serverThread.start();

        for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
            ClientThread clientThread = new ClientThread(i);
            clientThread.start();
        }
    }
}
