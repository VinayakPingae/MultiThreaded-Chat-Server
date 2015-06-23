package primeService.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import primeService.util.Debug;

/**
 * Description - This class is used for prime client socket
 * @author Vinayak Subhash Pingale
 * @since 28th February 2015
 * @version 1.1
 */
public class PrimeClientSocket {

	private String hostName;
	private int portNumber;
	private StringBuffer jsonObject;
	private PrimeClientWorker primeClientWorker;
	private Socket clientSocket = null;
	private Debug debug;
    /**
     * 
     * @param portNumber
     * @param localHostName
     * @param stringToSendAsRequest
     */
	public PrimeClientSocket(int portNumber,String localHostName, StringBuffer stringToSendAsRequest) {
		{
			this.setPortNumber(portNumber);
			this.setHostName(localHostName);
			this.jsonObject = stringToSendAsRequest;
			//System.out.println("clientItems.toString --- >" +stringToSendAsRequest.toString());
			primeClientWorker = new PrimeClientWorker();
			debug = Debug.getInstance();
			debug.printToStdout(1, "PrimeClientSocket construcots has been called");
			createSocketandConnect();
		}
	}
	private void createSocketandConnect() {
		debug.printToStdout(2, "PrimeClientSocket construcots has been called");
		try {
        	clientSocket = new Socket(getHostName(),getPortNumber());
        	primeClientWorker.requestClientQueries(clientSocket,getJsonObject());
        } catch(UnknownHostException ex){
            System.err.println("Unknown host exception has occurred please provide the host name properly");
            System.exit(1);
        }catch(IOException ex){
            System.err.println("IO Exception has occurred while initiating the server");
            System.exit(1);
        }
       		
	}
	public String getHostName() {
		return hostName;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	@Override
	public String toString() {
		return "PrimeClientSocket [hostName=" + hostName + ", portNumber="
				+ portNumber + ", jsonObject=" + jsonObject
				+ ", primeClientWorker=" + primeClientWorker
				+ ", clientSocket=" + clientSocket + "]";
	}
	public StringBuffer getJsonObject() {
		return jsonObject;
	}
	public void setJsonObject(StringBuffer jsonObject) {
		this.jsonObject = jsonObject;
	}
	public PrimeClientWorker getPrimeClientWorker() {
		return primeClientWorker;
	}
	public void setPrimeClientWorker(PrimeClientWorker primeClientWorker) {
		this.primeClientWorker = primeClientWorker;
	}
	public Socket getClientSocket() {
		return clientSocket;
	}
	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

}
