package net;

import rules.RegUsers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable {
    private final RegUsers users;
    private final ServerSocket serverSocket;
    private boolean isStopped = false;
    private Thread runningThread = null;

    public TCPServer (RegUsers users, int serverPort) throws IOException{
        this.users = users;
        this.serverSocket = new ServerSocket(serverPort);
    }

    @Override
    public void run(){
        synchronized (this){
            this.runningThread = Thread.currentThread();
        }
        while (!isStopped()){
            try{
                Socket clientSocket = this.serverSocket.accept();
                System.out.println("New client connection: " + clientSocket.getInetAddress());
                new Thread(new TCPWorker(clientSocket, users)).start();
            } catch (IOException e){
                if (isStopped()){
                    System.out.println("Server Stopped.");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
        }
        System.out.println("Server stopped.");
    }

    private synchronized boolean isStopped(){
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try{
            this.serverSocket.close();
        } catch (IOException e){
            throw new RuntimeException("Error closing server", e);
        }
    }

    public static void main (String[] args){

    }
}
