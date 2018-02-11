package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import common.*;

public class RMIServer extends UnicastRemoteObject implements RMIServerI {

	private int totalMessages = -1;
	private int[] receivedMessages;
	private int msg_index = 0;
	private boolean[] miss_msg;

	public RMIServer() throws RemoteException {}

	public void receiveMessage(MessageInfo msg) throws RemoteException {
		// On receipt of first message, initialise the receive buffer
		if(totalMessages == -1) {
			totalMessages = msg.totalMessages;
			receivedMessages = new int[totalMessages];
			miss_msg = new boolean[totalMessages];
			for(int index=0; index<totalMessages; index++)
				miss_msg[index] = true;
		}

		// Log receipt of the message
		System.out.println("Received "+msg);
		miss_msg[msg.messageNum-1] = false;
		receivedMessages[msg_index++] = msg.messageNum;
		// If this is the last expected message, then identify any missing messages
		if (msg.messageNum == totalMessages){
			boolean any_miss = false;
			for (int index=0; index<totalMessages; index++)
				if (miss_msg[index] == true){
					any_miss = true;
					System.out.println("missing "+index+1);
				}
			if(!any_miss)
				System.out.println("No message is missing");
			totalMessages = -1;
			msg_index = 0;
		}
	}

	public static void main(String[] args) {
		// Initialise Security Manager
		if(System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());

		try{
			// Instantiate the server class
			String serverURL = "rmi://localhost/RMIServer";
			RMIServer rmis = new RMIServer();
			// Bind to RMI registry
			rebindServer(serverURL, rmis);
			System.out.println("RMIServer Bound");
		} catch ( Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}

	protected static void rebindServer(String serverURL, RMIServer server) {
		try {
			// Start / find the registry
			LocateRegistry.createRegistry(1099);
			// Rebind the server to the registry
			Naming.rebind(serverURL, server);
		} catch (Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
}
