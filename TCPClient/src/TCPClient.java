import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter the server IP address: ");
        String serverIP = inFromUser.readLine();

        final String[] sentence = new String[1];
        final String[] modifiedSentence = new String[1];

        Socket clientSocket = new Socket(serverIP, 6666);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        sentence[0] = inFromUser.readLine();
        outToServer.writeBytes(sentence[0] + '\n');
        outToServer.flush();

        modifiedSentence[0] = inFromServer.readLine();

        if (modifiedSentence[0].equalsIgnoreCase("CONNECT")) {
            System.out.println("FROM SERVER: The connection is established.");
            Thread sendThread = new Thread(() -> {
                try {
                    while (true) {
                        sentence[0] = inFromUser.readLine();
                        outToServer.writeBytes(sentence[0] + '\n');
                        outToServer.flush();

                        if (sentence[0].equalsIgnoreCase("quit")) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Thread receiveThread = new Thread(() -> {
                try {
                    while (true) {
                        modifiedSentence[0] = inFromServer.readLine();
                        System.out.println("FROM SERVER: " + modifiedSentence[0]);

                        if (modifiedSentence[0].equalsIgnoreCase("quit")) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            sendThread.start();
            receiveThread.start();
            sendThread.join();
            receiveThread.join();
        }

        clientSocket.close();
    }
}
