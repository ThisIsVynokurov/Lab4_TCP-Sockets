package system;

import cmd.PayOperation;
import cmd.RegisterOperation;
import data.MetroUser;
import data.MetroUserCard;
import net.TCPClient;
import rules.Operation;
import rules.RegForm;
import rules.Service;
import rules.Sex;

import java.io.IOException;
import java.text.ParseException;

public class MetroTCPClient implements Service {
    private final TCPClient client;
    private final Thread thread;

    public MetroTCPClient(TCPClient client){
        this.client = client;
        this.thread = new Thread (this.client);
    }

    public MetroTCPClient(String host, int port) throws IOException {
        this.client = new TCPClient(host, port);
        this.thread = new Thread (this.client);
    }

    public void setOperation (Operation command){
        this.client.setOperation(command);
    }

    public Thread getThread(){
        return thread;
    }

    public TCPClient getClient(){
        return this.client;
    }

    @Override
    public void stratService(){
        thread.start();
    }

    @Override
    public void stoptService(){
        this.thread.interrupt();
    }

    public static void main(String[] args){
        final int NUM = 10;
        try{
            MetroTCPClient service = null;
            for (int i = 0; i < NUM; i++){
                TCPClient client = new TCPClient("localhost", 9300);
                service = new MetroTCPClient(client);
                RegForm form = new MetroUserCard("12XJD34" + i, new MetroUser("Stepan", "Kogutenko", Sex.MAN, "01.01.2001"), "Org");
                Operation command  = new RegisterOperation(form);
                System.out.println("Command: " + command);
                service.setOperation(command);
                service.stratService();
                System.out.println("The client has been started");
                try{
                    service.getThread().join();
                    System.out.println("The client finished his work");
                    System.out.println(service.getClient().getResult().getStatus());
                    System.out.println(service.getClient().getResult().getResult());
                    System.out.println();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            TCPClient client = new TCPClient("localhost", 9300);
            service = new MetroTCPClient(client);
            Operation command = new PayOperation("12XJD342", 50);
            System.out.println("Command: " + command);
            service.setOperation(command);
            service.stratService();
            System.out.println("The client has been started");
            try{
                service.getThread().join();
                System.out.println("The client finished his work");
                System.out.println(service.getClient().getResult().getStatus());
                System.out.println(service.getClient().getResult().getResult());
                System.out.println();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        } catch (ParseException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
