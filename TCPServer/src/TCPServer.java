import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        String clientSentence;
        boolean flag = true;

        ServerSocket welcomeSocket = new ServerSocket(6666);

        while (flag) {
            Socket connectionSocket = welcomeSocket.accept();

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            clientSentence = inFromClient.readLine();
            if (clientSentence != null && clientSentence.equalsIgnoreCase("connect")) {
                String capitalizeSentence = clientSentence.toUpperCase() + '\n';
                outToClient.writeBytes(capitalizeSentence);
                outToClient.flush();
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(System.in));

                while (true) {
                    if (inFromClient.ready()) {
                        clientSentence = inFromClient.readLine();
                        if (clientSentence.equalsIgnoreCase("quit")) {
                            break;
                        }
                        System.out.println("FROM CLIENT: " + clientSentence);
                    }

                    if (inFromServer.ready()) {
                        String serverSentence = inFromServer.readLine();
                        if (serverSentence.equalsIgnoreCase("quit")) {
                            outToClient.writeBytes(serverSentence + '\n');
                            outToClient.flush();
                            break;
                        }
                        outToClient.writeBytes(serverSentence + '\n');
                        outToClient.flush();
                    }
                }

                connectionSocket.close();
            }
        }
        welcomeSocket.close();
    }
}