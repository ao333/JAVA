/*
 * Created on 01-Mar-2016
 */
package rmi;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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

		if(System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());

		try{
			Registry registry = LocateRegistry.getRegistry(args[0]);
			RMIServerI server = (RMIServerI) registry.lookup("RMIServer");
			for (int msgNum = 1; msgNum <= numMessages; msgNum++) {
				System.out.println(msgNum);
				MessageInfo msg = new MessageInfo(numMessages, msgNum);
				server.receiveMessage(msg);
			}
			System.exit(0);
		}
		catch (Exception e){
			e.printStackTrace();
			System.exit(1);
		}

	}
}
