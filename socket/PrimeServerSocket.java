package primeService.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import primeService.server.AllPrimeQueries;
import primeService.server.AllPrimeQueriesInterface;

/**
 * Description - This is the class which has been declared in the package
 * primeService.socket which performs the operation of implementing the socket
 * 
 * @author Vinayak Subhash Pingale
 * @version 1.0
 * @since 28th February 2015
 * @exception IOException
 */
public class PrimeServerSocket {
	private int portNumberforSocket;
	private ServerSocket serverSocket;
	private Socket socketConnection;
	private AllPrimeQueriesInterface allPrimeQueries;

	public PrimeServerSocket(int portNumber_IN,AllPrimeQueriesInterface allPrimeQueries2) {
		this.portNumberforSocket = portNumber_IN;
		this.serverSocket = null;
		this.socketConnection = null;
		this.allPrimeQueries = allPrimeQueries2;
	}

	public int getPortNumberforSocket() {
		return portNumberforSocket;
	}

	public void setPortNumberforSocket(int portNumberforSocket) {
		this.portNumberforSocket = portNumberforSocket;
	}

	/**
	 * Description - Register service on port number which has been provided by the server
	 * driver for running the code. It is an independent method which performs the operation
	 * of creating the socket and connecting it.
	 */
	public void createSocketandConnect() {
		try {
			serverSocket = new ServerSocket(getPortNumberforSocket());
		} catch (IOException e) {
			System.err.println("IO Exception has occurred while initiating the server\n");
			System.exit(1);
		}
		do {	
			try {
				// wait and accept a connection the accept() function will act as a blocking code here.
				socketConnection = serverSocket.accept();
			} catch (IOException ex) {
				System.err.println("IO Exception has occurred while initiating the server\n");
				System.exit(1);
			} 
			new PrimeServerWorker(socketConnection,allPrimeQueries).start();
		} while (true);
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public Socket getSocketConnection() {
		return socketConnection;
	}

	public void setSocketConnection(Socket socketConnection) {
		this.socketConnection = socketConnection;
	}
}
