package primeService.util;

import java.util.Scanner;

/**
 * Description - This interface will be used by client and server for describing the values of various server abd clients.
 * @author Vinayak Subhash Pingale
 * @since 28th February 2015
 * @version 1.1
 */
public interface MenuSupport {
	/*
	 * Defining the static final value for the Threshold which should be stick to value 3 cause the prime should be greater than 3.
	 */
	static final int THRESHOLD_VALUE = 3;
	/*
	 * Show menu which will be derived by the client and server for showing the various values
	 * for the menu which showing the Menu driven options of various server and client
	 */
	public int showMenu(Scanner scanValue);
}
