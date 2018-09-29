package pl.sdacademy.javaktw7.chat.client;


import pl.sdacademy.javaktw7.chat.model.ChatMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.util.Scanner;

public class ClientTerminal implements Runnable{


    private final Socket socket;

    public ClientTerminal() throws IOException {
        socket = new Socket("adres na który będziemy się łączyć", 5567);
    }

    @Override
    public void run() {
        try (ObjectOutputStream connection =
            new ObjectOutputStream(socket.getOutputStream())) {


            Scanner scanner = new Scanner(System.in);
            System.out.println("Your Username: ");

            String userName = scanner.nextLine();
            String messsage = "";

            while (!messsage.equalsIgnoreCase("exit")) {
                System.out.println(">");
                messsage = scanner.nextLine();

                ChatMessage messageToSent = new ChatMessage(userName, messsage);
                connection.writeObject(messageToSent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
