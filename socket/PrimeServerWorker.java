package primeService.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.simple.JSONObject;

import primeService.server.AllPrimeQueries;
import primeService.server.AllPrimeQueriesInterface;
import primeService.util.CheckPrime;
import primeService.util.MenuSupport;

/**
 * Description - This class works as a Server Worker class which will acts a
 * data input output stream for the whole application and the things will be
 * communicated through this class.
 * 
 * @author Vinayak
 * @since 1st march 2015
 * @version 1.0
 * @extends Thread
 * @exception IOException
 */
public class PrimeServerWorker extends Thread {
	private Socket socketConnection;
	private OutputStream outputStream;
	private DataOutputStream dataOutputStream;
	private DataInputStream dataInputStream;
	private AllPrimeQueriesInterface allPrimeQueries;
	private InputStream inputStream;
	/**
	 * 
	 * @param socketConnection_IN
	 * @param allPrimeQueries2
	 */
	public PrimeServerWorker(Socket socketConnection_IN, AllPrimeQueriesInterface allPrimeQueries2) {
		this.socketConnection = socketConnection_IN;
		this.outputStream = null;
		this.inputStream=null;
		this.dataOutputStream = null;
		this.dataInputStream = null;
		this.allPrimeQueries = allPrimeQueries2;
		
	}
	
	static boolean isInteger(String s)  // assuming integer is in decimal number system
	{
	 for(int a=0;a<s.length();a++)
	 {
	    if(a==0 && s.charAt(a) == '-') continue;
	    if( !Character.isDigit(s.charAt(a)) ) return false;
	 }
	 return true;
	}
	@Override
	public void run() {
		// get a communication stream associated with the socket
		try {
			outputStream = socketConnection.getOutputStream();
			inputStream = socketConnection.getInputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			dataInputStream = new DataInputStream(inputStream);
			while(true) {
			StringBuffer stringBuffer = new StringBuffer(dataInputStream.readUTF());
			StringTokenizer strToken = new StringTokenizer(stringBuffer.toString(),":");
			String localToken_Name = strToken.nextToken();
			//System.out.println("Prime Server worker localToken_Name "+localToken_Name);
			while (strToken.hasMoreTokens()) {
				String localToken = strToken.nextToken();
			    if(!localToken.equals("0")) {
			    	if(isInteger(localToken)) {
			    		try {
			    		int queryInteger = Integer.parseInt(localToken);
			    		//	dataOutputStream.writeUTF("Please Enter integer value greater than 3 Threshold Error");
			    		//} else {
			    			allPrimeQueries.putData(localToken_Name, queryInteger);
			    		//}
			    		}catch(NumberFormatException numberFormatException) {
			    			System.err.println("Number format exception occurred in Prime Server worker ");
			    			System.exit(1);
			    		}
			    	} else {
			    		allPrimeQueries.putData(localToken_Name, 0);
			    	}
			    }  
			}
			sendToClient(allPrimeQueries.getData(localToken_Name));
			 //System.out.println(AllPrimeQueries.getPrimeQueries().toString()); /* For checking the contents of the list */
			} 
			} catch(NoSuchElementException noSuchElementException) {
				System.err.println("Why are you pressing 3 before 2 ? BYE");
				System.exit(1);
			}
		
			catch (IOException ioException) { 
				try {
					socketConnection.close();
				} catch (IOException ioException2) {
					System.err
					.println("The Server connenction has tried to connect but threw an IOException"
							+ "\nThe reason for it can be :-"
							+ "\n1. Data Input stream is missing the operations that has to be passed"
							+ "\n2. Data Ouput stream have been blocked because of some reason."
							+ "\n3. while getting the socket connection error may have occurred\n");
			
				}
		}
	}
	/**
	 * Send the data to the client depending on the list that has been generated i have used json for sedning the client request
	 * @param list
	 */
	private void sendToClient(List<Integer> list) {
		CopyOnWriteArrayList<Integer> listAccClient = (CopyOnWriteArrayList<Integer>) list;
		int count = 0;
		CheckPrime checkPrime =  new CheckPrime();
		JSONObject jsonObject = null;
		StringBuffer stringResponse = new StringBuffer();
		try {
		//System.out.println("listAccClient.size() "+listAccClient.size());
		while(count < listAccClient.size()){
			jsonObject = new JSONObject();
			jsonObject.put("isPrime", checkPrime.isPrimeNumber(listAccClient.get(count)) );
			jsonObject.put("integerValue", listAccClient.get(count));
			jsonObject.put(" primeQueryResponse", count);
			stringResponse.append(jsonObject.toJSONString()+System.getProperty("line.separator"));
			count ++;
		}
		} catch(NullPointerException nullPointerException) {
			System.err.println("An Null Pointer exception has occurred while fetching the data");
			System.exit(1);
		}
		try {
			dataOutputStream.writeUTF(stringResponse.toString());
			dataOutputStream.flush();
		} catch (IOException e) {
			System.err.println("An IO Exception has occurred while sending data to client");
			System.exit(1);
		}
	}
}
