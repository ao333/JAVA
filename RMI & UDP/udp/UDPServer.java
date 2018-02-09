/*
 * Created on 01-Mar-2016
 */
package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

import common.MessageInfo;

public class UDPServer {

	private DatagramSocket recvSoc;
	private int totalMessages = -1;
	private int[] receivedMessages;
	boolean[] miss_msg;
	private boolean close;
	private int msg_index = 0;
	private void run() {
		int				pacSize;
		byte[]			pacData;
		pacData = new byte[32];
		DatagramPacket 	pac;
		pac = new DatagramPacket(pacData, pacData.length);
		close = false;
		while(!close) {
			try {
				recvSoc.receive(pac);
				String data = new String(pac.getData(), 0, pac.getLength());
				processMessage(data);
			}
			catch (Exception e){
				e.printStackTrace();
				System.exit(1);
			}
		}

	}

	public void processMessage(String data) {
		MessageInfo msg = null;

		try{
			msg = new MessageInfo(data);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}

		if(totalMessages == -1) {
			close = false;
			totalMessages = msg.totalMessages;
			receivedMessages = new int[totalMessages];
			miss_msg = new boolean[totalMessages];
			for(int index = 0; index<totalMessages; index++)
				miss_msg[index] = true;
		}
		System.out.println("Rceived Message: "+msg);
		miss_msg[msg.messageNum-1] = false;
		receivedMessages[msg_index++] = msg.messageNum;
		if (msg.messageNum == totalMessages){
			for (int index = 0; index<totalMessages; index++)
				if (miss_msg[index] == true)
					System.out.println("Msg Number "+index+1+" is missing.");
			close = true;
		}

	}


	public UDPServer(int rp) {
		try {
			recvSoc = new DatagramSocket(rp);
			recvSoc.setSoTimeout(30000);
			recvSoc.setReceiveBufferSize(512000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}

		// TO-DO: Initialise UDP socket for receiving data

		// Done Initialisation
		System.out.println("UDPServer ready");
	}

	public static void main(String args[]) {
		int	recvPort;

		// Get the parameters from command line
		if (args.length < 1) {
			System.err.println("Arguments required: recv port");
			System.exit(-1);
		}
		recvPort = Integer.parseInt(args[0]);
		UDPServer udpServer = new UDPServer(recvPort);
		udpServer.run();
		// TO-DO: Construct Server object and start it by calling run().
	}

}
