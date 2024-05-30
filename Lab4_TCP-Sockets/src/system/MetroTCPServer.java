package system;

import data.MetroUser;
import data.MetroUserBank;
import data.MetroUserCard;
import net.TCPServer;
import rules.RegUsers;
import rules.Service;
import rules.Sex;

import java.io.IOException;
import java.text.ParseException;

public class MetroTCPServer implements Service {
    private final TCPServer server;
    private Thread thread = null;

    public MetroTCPServer(TCPServer server){
        this.server = server;
    }

    public MetroTCPServer(RegUsers users, int serverPort) throws IOException{
        this.server = new TCPServer(users, serverPort);
    }

    public TCPServer getServer(){
        return server;
    }

    @Override
    public void stratService(){
        this.thread = new Thread(server);
        this.thread.start();
    }

    @Override
    public void stoptService(){
        this.server.stop();
    }


    public static void main (String[] args){
        try{
            RegUsers bank = new MetroUserBank();
            bank.register(new MetroUserCard("111", new MetroUser("Petro", "Suvalov", Sex.MAN, "23.09.1989"), "Org"));
            System.out.println(bank);
            TCPServer server = new TCPServer(bank, 9300);
            Service service = new MetroTCPServer(server);
            service.stratService();
            System.out.println("The server has been started!");
            try(java.util.Scanner in = new java.util.Scanner(System.in);){
                System.out.println("\tType 'stop' to finish the server work");
                String answer = "go";
                while (!answer.equalsIgnoreCase("stop")){
                    answer = in.next();
                }
            }
            service.stoptService();
            System.out.println("The server has been stopped");
            System.out.println(bank);
        } catch (ParseException | IOException e){
            e.printStackTrace();
        }
    }
}
