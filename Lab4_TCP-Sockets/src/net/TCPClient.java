package net;

import rules.Operation;
import rules.Result;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPClient implements Runnable{
    private final Socket client;
    private Operation command;
    private Result result;

    public TCPClient (String host, int port) throws IOException {
        this.client = new Socket(host, port);
    }

    public Result getResult(){
        return result;
    }

    public void setOperation(Operation command){
        this.command = command;
    }

    @Override
    public void run(){
        try{
            System.out.println("Connected to server\n");
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            out.writeObject(command);
            out.flush();
            System.out.println("Submitted a command for execution\n");
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            result = (Result)in.readObject();
            System.out.println("result = " + result);
        } catch (IOException | ClassNotFoundException e){
            throw new RuntimeException("Problem in client run", e);
        } finally{
            try{
                if (client != null){
                    client.close();
                }
            } catch (IOException e) {}
        }
    }
}
