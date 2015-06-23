package primeService.util;


/**
 * Description - Debug class has been defined for providing a logger level to this particular Bug movement application
 * It provides a method called as printToStdOut for printing the messages by defining the debug level 
 * and the message which has been provided as a parameter to it.
 * 
 * @Author Vinayak Subhash Pingale
 * @Version 1.0
 * @Since 9th March 2015
 * 
 */

public class Debug {
	
	private static int DEBUG_VALUE = 0;
	private volatile static Debug debugInstance = null;
	public Debug() {
		//Debug.printToStdout(4,"Constructor has been called for the PrimeService Class");
	}
	public static Debug getInstance() {
		if(debugInstance == null) {
			synchronized (Debug.class) {
				if(debugInstance == null) {
					debugInstance = new Debug();
				}
			}
		}
		return debugInstance;
	}
	public static int getDEBUG_VALUE() {
		return DEBUG_VALUE;
	}
	public static void setDEBUG_VALUE(int debug_value) {
		DEBUG_VALUE = debug_value;
	}
	
	/**
	 * printToStdout - This method has been used to print the debug levels for the Application
	 * @param debguglevel - For getting the debug level from the Command Line arguments
	 * @param message - to print the message that has been passed
	 * 
	 */
	public void printToStdout(int debugLevel, String message) {
		if(debugLevel == 0) {
		}
		else if(DEBUG_VALUE == debugLevel) {
			System.out.println(message);
		}  
	 }
}
