package pl.sdacademy.javaktw7.chat.server;

import pl.sdacademy.javaktw7.chat.model.ChatMessage;
import pl.sdacademy.javaktw7.chat.model.DatedChatMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChatLog {

    private Map<Socket, ObjectOutputStream> clientConnections;
    private SimpleDateFormat dateFormatter;

    public ChatLog(){
        clientConnections = new LinkedHashMap<>();
       dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    public boolean register(Socket newClient) {
        try {
            ObjectOutputStream newClientStream = new ObjectOutputStream(newClient.getOutputStream());
            clientConnections.put(newClient, newClientStream);
        } catch (IOException e) {
            System.out.println("### Someone tried to connect, but was rejected: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean unregister(Socket existingClient) {
        if (clientConnections.containsKey(existingClient)) {
            ObjectOutputStream connectionToBeClose = clientConnections.remove(existingClient);
            try {
                connectionToBeClose.close();
            } catch (IOException e) {
            }
            return true;
        }
        return false;
    }

    public void acceptMessage(ChatMessage message) {
        DatedChatMessage datedChatMessage = new DatedChatMessage(message);
        String formattedDate = dateFormatter.format(datedChatMessage.getReceiveDate());

        System.out.println("Data: " + datedChatMessage.getReceiveDate() + " Autor: " + datedChatMessage.getAuthor()
                + "Wiadomość: " + datedChatMessage.getMessage());



    }

    private void updateObservers(DatedChatMessage datedChatMessage){
        for (ObjectOutputStream objectOutputStream : clientConnections.values()) {
            try {
                objectOutputStream.writeObject(datedChatMessage);
                objectOutputStream.flush();
            } catch (IOException e) {
                System.out.println("### could not sent message to client" + e.getMessage());
            }
        }

        }
    }


