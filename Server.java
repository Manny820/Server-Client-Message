import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
//import java.util.List;

public class Server 
{
    public static void main(String[] args) throws IOException, ClassNotFoundException 
    {
    	Message msg = new Message();
    	
        // Create a ServerSock on localhost:7777
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("ServerSocket awaiting connections...");

        // .accept blocks until an inbound connection on this port is attempted
        Socket socket = ss.accept();
        System.out.println("Connection from " + socket + "!");

        // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();

        // create a ObjectInputStream so we can read data from it.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        if(msg.getType().equalsIgnoreCase("login"))
        {
        	msg.setStatus("success");
        }
        else if(msg.getType().equalsIgnoreCase("logout"))
        {
        	msg.setStatus("success");
        	System.out.println("Client Logout: " + msg.getStatus());
            System.out.println("Closing sockets.");
            ss.close();
            socket.close();
        }
        else if(msg.getType().equalsIgnoreCase("text"))
        {
        	msg.getText().toUpperCase();
        }
        else
        	return;
    }
    
    private static void printMessage(Message msg)
    {
        System.out.println("Status: " + msg.getStatus());
        System.out.println("Type: " + msg.getType());
        System.out.println("Text: " + msg.getText().toUpperCase());
    }

}