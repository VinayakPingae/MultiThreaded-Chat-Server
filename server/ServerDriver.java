package primeService.server;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import primeService.socket.PrimeServerSocket;
import primeService.util.DriverSupport;
import primeService.util.MenuSupport;

/**
 * Description - This class has been added to the Application to spawn two
 * threads which will act as a driving force for the Menu and the Socket
 * connection This is the server related code which helps in understanding how
 * server provides a menu driven code and an implementation of server socket to
 * which a parameter has been passed to act upon the port number which has been
 * passed.
 * 
 * @author Vinayak Subhash Pingale
 * @since 29th Feb 2015
 * @version 1.0
 * @interface Runnable
 * @exception NumberFormatException
 */
public class ServerDriver implements Runnable, DriverSupport {

	private int portNumber;
	private static AllPrimeQueriesInterface allPrimeQueries;
	
	/*
	 * Description - This constructor has been added to the class ServerDriver
	 * which accepts the arguments that has been passed to the constructor which
	 * decides what arguments have been passed to this function
	 * 
	 * @interface Runnable
	 * @exception NumberFormatException
	 */
	public ServerDriver(String[] args) {
		try {
			this.portNumber = Integer.parseInt(args[1]);
			allPrimeQueries = new AllPrimeQueries();
		} catch (NumberFormatException numberFormatException) {
			System.err
					.println("Number Formate Exception occurred for either PortNumber/DebugV alue."
							+ "\nPlease check and enter them properly according to the requirment");
			System.exit(1);
		}
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	/*
	 * Description - This serverFunction has been declared in this class which can be dependent upon the menu selected and will create a server socket.
	 * This will keep on doing the while loop until and unless the value entered on the screen is not 3 which is equivalent to the value 3 in the socket.
	 */
	public void spawnMenuandSocket() {
		int selectedMenuOption = 0;
		String[] args = {"Server",""+getPortNumber()};
		Runnable r = new ServerDriver(args);
		new Thread(r).start();
		MenuSupport menuSupport = new ServerMenuSupport();
		Scanner scanValue = new Scanner(System.in);
		do {
			selectedMenuOption = menuSupport.showMenu(scanValue);
			if(selectedMenuOption == 1) {
				System.out.println("Enter Client Name to Query");
				String clientName = scanValue.next();
				CopyOnWriteArrayList<Integer> returnedQueries = (CopyOnWriteArrayList<Integer>) allPrimeQueries.getData(clientName);
				if(returnedQueries != null)	{
					for(int i = 0; i < returnedQueries.size(); i++) {
						if(returnedQueries.get(i)!=0)
						System.out.print(returnedQueries.get(i)+"---");
					}
					System.out.println();
				} 
				else {
					System.out.println("There is no entry for Client "+clientName);
				}
			} else if(selectedMenuOption == 2) {
				Map<String, List<Integer>> allQueries = AllPrimeQueries.getPrimeQueries();
				if(allQueries != null) {
					System.out.println(allQueries.toString());
				} else {
					System.out.println("There is no request made yet");
				}
			} else if(selectedMenuOption == 3) {
				System.out.println("Exiting Server");
				System.exit(1);
			}
		} while ((selectedMenuOption != 3));
		scanValue.close();
	}

	@Override
	public void run() {
		PrimeServerSocket primeServerSocket = new PrimeServerSocket(getPortNumber(),allPrimeQueries);
		primeServerSocket.createSocketandConnect();
	}

	@Override
	public String toString() {
		return "ServerDriver [portNumber=" + portNumber + "]";
	}

}
