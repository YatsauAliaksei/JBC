package by.mrj.network;

import by.mrj.domain.types.Command;
import by.mrj.service.BasicOperations;
import by.mrj.util.NetUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import com.google.common.collect.Sets;

public class NetworkService {

    private final Set<String> servers = Sets.newHashSet("127.0.0.1"); // todo
    private final Set<String> network;
    private final int DEFAULT_PORT = 8338;
    private final BasicOperations basicOperations; // DI + abstraction todo

    public NetworkService(BasicOperations basicOperations) {
        this.network = createNetwork();
        this.basicOperations = basicOperations;
        listening();
    }

    private Set<String> createNetwork() { // todo
        try {
            for (String address : servers) {
                if (network != null && network.size() == 8)
                    break;

                askPeers(address);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Sets.newHashSet("127.0.0.1"); // todo
    }

    private void askPeers(String address) throws IOException { // todo
//        Socket socket = new Socket(address, DEFAULT_PORT);
//        socket.getInputStream();
    }

    private void listening() {
        try (ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                CompletableFuture.runAsync(() -> {
                    try {
                        Message message = NetUtils.deserialize(clientSocket.getInputStream());
                        System.out.println(message);

                        initialMsgProcessing(message);
                    } catch (IOException e) {
                        throw new RuntimeException(e); // todo
                    }
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e); // todo
        }

    }

    private void initialMsgProcessing(Message message) {
        Command command = message.getCommand();
        switch (command) {
            case HANDSHAKE:
                System.out.println(command);
                basicOperations.handshake(message);
                break;
            default:
                throw new IllegalStateException(); // todo
        }
    }
}
