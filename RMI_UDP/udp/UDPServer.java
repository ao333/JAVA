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

	// Use a timeout (e.g. 30 secs) to ensure the program doesn't block forever
	private void run() {
		int pacSize;
		byte[] pacData;
		pacData = new byte[32];
		DatagramPacket pac = new DatagramPacket(pacData, pacData.length);
		close = false;

		// Receive the messages and process them by calling processMessage(...)
		while(!close) {
			try {
				recvSoc.receive(pac);
				String data = new String(pac.getData(), 0, pac.getLength());
				processMessage(data);
			} catch (SocketTimeoutException tm_out_exc) {
				System.out.println("timed out!");
				boolean any_miss = false;
				for (int index=0; index<totalMessages; index++)
					if (miss_msg[index] == true){
						any_miss = true;
						System.out.print((index+1)+"missing ");
					}
				if(!any_miss)
					System.out.println("no message is missing");
				System.exit(0);
			} catch (Exception e){
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public void processMessage(String data) {
		// Use the data to construct a new MessageInfo object
		MessageInfo msg = null;
		try{
			msg = new MessageInfo(data);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		// Log receipt of the message
		if(totalMessages == -1) {
			close = false;
			totalMessages = msg.totalMessages;
			receivedMessages = new int[totalMessages];
			miss_msg = new boolean[totalMessages];
			for(int index = 0; index<totalMessages; index++)
				miss_msg[index] = true;
		}

		// If this is the last expected message, then identify any missing messages
		System.out.print("Reveived "+msg);
		miss_msg[msg.messageNum-1] = false;
		receivedMessages[msg_index++] = msg.messageNum;
		if (msg.messageNum == totalMessages){
			boolean any_miss = false;
			for (int index=0; index<totalMessages; index++)
				if (miss_msg[index] == true){
					any_miss = true;
					System.out.print((index+1)+"missing ");
				}
			if(!any_miss)
				System.out.println("No message is missing");
			close = true;
			System.exit(0);
		}
	}

	public UDPServer(int rp) {
		try {
			// Initialise UDP socket for receiving data
			recvSoc = new DatagramSocket(rp);
			// Use a timeout (e.g. 30 secs) to ensure the program doesn't block forever
			recvSoc.setSoTimeout(30000);
			// On receipt of first message, initialise the receive buffer
			recvSoc.setReceiveBufferSize(51200);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
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
		// Construct Server object and start it by calling run().
		UDPServer udpServer = new UDPServer(recvPort);
		udpServer.run();
	}
}
