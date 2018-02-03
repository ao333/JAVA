/*
 * Created on 01-Mar-2016
 */
package rmi;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.RMISecurityManager;

import common.MessageInfo;

public class RMIClient {

	public static void main(String[] args) {

		RMIServerI iRMIServer = null;

		// Check arguments for Server host and number of messages
		if (args.length < 2){
			System.out.println("Needs 2 arguments: ServerHostName/IPAddress, TotalMessageCount");
			System.exit(-1);
		}

		String urlServer = new String("rmi://" + args[0] + "/RMIServer");
		int numMessages = Integer.parseInt(args[1]);

		// Initialise Security Manager
		if(System.getSecurityManager() == null){
			System.setSecurityManager(new RMISecurityManager());
		}

		// Bind to RMIServer
	 try {
		 iRMIServer = (RMIServerI) Naming.lookup(urlServer);

		 // Attempt to send messages the specified number of times
		 for(int i = 0; i < numMessages; i++) {
			String m = new String( (Integer.toString(numMessages)) + ";" + (Integer.toString(i)) );
			MessageInfo msg = new MessageInfo(m);
			iRMIServer.receiveMessage(msg);
			//System.out.println(i);
		 }
		 System.out.println("All messages sent");

		} catch (MalformedURLException e) {
			System.out.println("Error: Malformed hostname.");
		} catch (RemoteException e) {
			System.out.println("Error: Remote Exception.");
		} catch (NotBoundException e) {
			System.out.println("Error: Not Bound Exception.");
		} catch (Exception e) {
			System.out.println("help, an error!");
		}

	}

}
