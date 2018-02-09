/*
 * Created on 01-Mar-2016
 */
package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

import common.*;

public class RMIServer extends UnicastRemoteObject implements RMIServerI {

	private int totalMessages = -1;
	private int[] receivedMessages;
	private int msg_index = 0;
	private boolean[] miss_msg;
	public RMIServer() throws RemoteException {
		super();
	}

	public void receiveMessage(MessageInfo msg) throws RemoteException {
		if(totalMessages == -1) {
			totalMessages = msg.totalMessages;
			receivedMessages = new int[totalMessages];
			miss_msg = new boolean[totalMessages];
			for(int index = 0; index<totalMessages; index++)
				miss_msg[index] = true;
		}

		System.out.println("REceived Message: "+msg);
		miss_msg[msg.messageNum-1] = false;
		receivedMessages[msg_index++] = msg.messageNum;
		if (msg.messageNum == totalMessages){
			for (int index = 0; index<totalMessages; index++)
				if (miss_msg[index] == true)
					System.out.println("Msg Number "+index+1+" is missing.");
					totalMessages = -1;
					msg_index = 0;
		}

	}



	public static void main(String[]       args) {

		if(System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());
		try{
			String serverURL = "rmi://localhost/RMIServer";
			RMIServer rmis = null;
			rmis = new RMIServer();
			// RMIServer stub = (RMIServer) UnicastRemoteObject.exportObject(rmis, 0);
			rebindServer(serverURL, rmis);
			// Registry registry = LocateRegistry.getRegistry();
			// registry.rebind("RMIServer", rmis);
			System.out.println("RMIServer Bound");

		}
		catch ( Exception e){
			e.printStackTrace();
			System.exit(1);
		}

	}

	protected static void rebindServer(String serverURL, RMIServer server) {
		try {
			LocateRegistry.createRegistry(1099);
			Naming.rebind(serverURL, server);
		}
		catch (Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
}
