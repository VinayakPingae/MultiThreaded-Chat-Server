package primeService.driver;

import primeService.client.ClientDriver;
import primeService.server.ServerDriver;
import primeService.util.DriverSupport;

/**
 * Description - This class has been created which will act as a driving force for the Server Driver and Client Driver which in turn will enforce which 
 * method to be determined to call a particular method.
 * @author Vinayak Subhash Pingale
 * @since 27th February 2015
 * @version 1.1
 */
public class PrimeDriver {
	public static void main(String[] args) {
		/*Validation of the arguments that have been passed to the main function has been accumulated over here and respective validation for that 
		 * arguments has been done*/
		if (args.length == 0 && args.length > 4)
		{
			System.err.println("Invalid Use  : You have to Fix this error by running the arguments in a more proper way"
					+ "\n1st Argument : Decides which application to run should be Server\\Client"
					+ "\n2nd Argument : Decides on which port it has to run <Integer> Value"
					+ "\n3rd Argument : The host name has to be provided in this argument"
					+ "\n4th Argument : The debug value for which the client has to run - should accompany [Client] only");
			System.exit(1);
		}
		if("Server".equals(args[0])) {
			DriverSupport serverDriver = new ServerDriver(args);
			serverDriver.spawnMenuandSocket();
		} else if("Client".equals(args[0])) {
			DriverSupport clientDriver = new ClientDriver(args);
			clientDriver.spawnMenuandSocket();
		} else {
			System.err.println("Invalid Driver provided : it should either be Client/Server"
					+ "\n Please provide the arguments from runnning again [CLIENT\\SERVER]");
			System.exit(1);
		}
	}

}
