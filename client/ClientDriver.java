package primeService.client;

import java.util.Scanner;

import org.json.simple.JSONObject;

import primeService.socket.PrimeClientSocket;
import primeService.util.Debug;
import primeService.util.DriverSupport;
import primeService.util.MenuSupport;
/**
 * Description - Client Driver class is used to spawn the client Menu and socket for operations. This is the class which plays a vital role in this application
 * this class will display all the elements. 
 * @author Vinayak Subhash Pingale
 * @since 2nd March 2015
 * @version 1.0
 * @implements DriverSupport
 * @exception 
 */
public class ClientDriver implements DriverSupport {
	
	private int portNumber;
	private int debugValue;
	private Debug debug;
	private String localHostName;
	private String clientName;
	private int queryInteger;
	JSONObject jsonObject;
	/**
	 * The Client driver is the one which invokes the socket class and sets the appropriate client functions. 
	 * @param args
	 */
	public ClientDriver(String[] args) {
		try {
			//Client localhost 8907 3
			debug = Debug.getInstance();
			this.setLocalHostName(args[1]);
			this.setPortNumber(Integer.parseInt(args[2]));
			this.setDebugValue(Integer.parseInt(args[3]));
			if (this.getDebugValue() < 0 || this.getDebugValue() > 4) {
				System.err.println("Please provide the debug value for proper debugging which should be in the range [0-4]\n");
				System.exit(1);
			} else {
				/* Here the code for initiating the debug value should be passed considering what value has been passed to the Driver classes.*/
				Debug.setDEBUG_VALUE(getDebugValue());
			}
			debug.printToStdout(1, "Client Drive Constructor has been called");
		} catch(NumberFormatException numberFormatException) {
			System.err.println("Number Formate Exception occurred for either PortNumber/DebugValue."
					+ "\nPlease check and enter them properly according to the requirment\n");
			System.exit(1);
		}
	}
	/**
	 * Description - spawn socket is overridden from MenuSupport which is present in the util package
	 */
	@Override
	public void spawnMenuandSocket() {
		debug.printToStdout(2, "Client Drive spawnMenuandSocket method has been called");
		int selectedMenuOption = 0;
		MenuSupport menuSupport = new ClientMenuSupport();
		Scanner scanValue = new Scanner(System.in);
		StringBuffer stringToSendAsRequest = new StringBuffer();
		//System.out.println("jsonArray.toJSONString() ---- > "+jsonArray.toJSONString());
		do {
			selectedMenuOption = menuSupport.showMenu(scanValue);
			/* This is the code snippet where the actual handling of the menu options are done.*/
			if(selectedMenuOption == 1) {
				System.out.println("Please Enter Client Name for Setting : ");
				this.setClientName(scanValue.next());
				stringToSendAsRequest = new StringBuffer();
			} else if(selectedMenuOption == 2) {
				System.out.println("Please Enter integer number query for " + this.getClientName());
				do {
					
					this.setQueryInteger(scanValue.nextInt());
					if(stringToSendAsRequest.toString().contains(this.getClientName())) {
							stringToSendAsRequest.append(this.getQueryInteger()+":");
						//}
					} else {
						//if(this.getQueryInteger()!=MenuSupport.THRESHOLD_VALUE) {
							stringToSendAsRequest.append(this.getClientName()+":"+this.getQueryInteger()+":");
						//} else {
							//System.out.println("Please Enter integer value greater than 3 Threshold Error");
						//}
						
					}
				} while(this.getQueryInteger() !=0); // This is to end the number of requests that has been made at the client side. The jsonobject is ended by 0 "Zero"
			} else if(selectedMenuOption == 3) {
				new PrimeClientSocket(getPortNumber(),getLocalHostName(),stringToSendAsRequest);
			} else if(selectedMenuOption == 4) {
				System.out.println("Exiting Client");
				System.exit(1);
			}
		} while ((selectedMenuOption != 4));
		
		scanValue.close();
		
			
	}
	public String getClientName() {
		debug.printToStdout(2, "Client Drive getClientName method has been called");
		return clientName;
	}

	public void setClientName(String clientName) {
		debug.printToStdout(2, "Client Drive setClientName method has been called");
		this.clientName = clientName;
	}

	public int getQueryInteger() {
		debug.printToStdout(2, "Client Drive getQueryInteger method has been called");
		return queryInteger;
	}

	public void setQueryInteger(int queryInteger) {
		debug.printToStdout(2, "Client Drive setQueryInteger method has been called");
		this.queryInteger = queryInteger;
	}
	
	public int getPortNumber() {
		debug.printToStdout(2, "Client Drive getPortNumber method has been called");
		return portNumber;
	}
	
	public void setPortNumber(int portNumber) {
		debug.printToStdout(2, "Client Drive setPortNumber method has been called");
		this.portNumber = portNumber;
	}
	
	public int getDebugValue() {
		debug.printToStdout(2, "Client Drive getDebugValue method has been called");
		return debugValue;
	}
	
	public void setDebugValue(int debugValue) {
		debug.printToStdout(2, "Client Drive spawnMenuandSocket method has been called");
		this.debugValue = debugValue;
	}

	@Override
	public String toString() {
		return "ClientDriver [portNumber=" + portNumber + ", debugValue="
				+ debugValue + ", clientName=" + clientName + ", queryInteger="
				+ queryInteger + ", jsonObject=" + jsonObject + "]";
	}

	public String getLocalHostName() {
		debug.printToStdout(2, "Client Drive getLocalHostName method has been called");
		return localHostName;
	}

	public void setLocalHostName(String localHostName) {
		debug.printToStdout(2, "Client Drive setLocalHostName method has been called");
		this.localHostName = localHostName;
	}


}
