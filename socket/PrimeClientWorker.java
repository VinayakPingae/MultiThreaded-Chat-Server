package primeService.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import primeService.util.Debug;

public class PrimeClientWorker {
	/**
	 * Request the client queries has been added which is then sent to the client for working the operations
	 * @param clientSocket
	 * @param stringBuffer
	 */
	private Debug debug;
	public PrimeClientWorker() {
		// TODO Auto-generated constructor stub
		debug = Debug.getInstance();
		debug.printToStdout(1, "PrimeClientWorker consturctore has been called.");
	}
	public void requestClientQueries(Socket clientSocket, StringBuffer stringBuffer) {
		debug.printToStdout(2, "PrimeClientWorker requestClientQueries has been called.");
			InputStream inputStream = null;
	        DataInputStream dataInputStream = null;
	        try {
	            inputStream = clientSocket.getInputStream();
	            OutputStream outputStream = clientSocket.getOutputStream();
	            dataInputStream= new DataInputStream(inputStream);
	            DataOutputStream dataOutputStream = new DataOutputStream(outputStream); 
	            String jsonText = stringBuffer.toString();
	            dataOutputStream.writeUTF(jsonText);
	            
	            if(jsonText.contains(":0")) {
	            	System.out.println(dataInputStream.readUTF());
	            }
	        }catch(IOException ex){
	            System.err.println("IO Exception has occurred while initiating the server");
	            System.exit(1);
	        }
	}
}
