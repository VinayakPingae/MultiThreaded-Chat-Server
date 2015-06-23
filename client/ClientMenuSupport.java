package primeService.client;

import java.util.NoSuchElementException;
import java.util.Scanner;

import primeService.util.Debug;
import primeService.util.MenuSupport;

/**
 * Description - This class has been defined to show the menu to the client which implements menu support of the client.
 * @author Vinayak
 * @since 3rd March 2015
 * @version 1.0
 * @interface MenuSupport
 * @exception NoSuchElementException, NumberFormatException, NullPointerException
 */
public class ClientMenuSupport implements MenuSupport {
	
	private int selectedMenuOption;
	private Debug debug;
	public ClientMenuSupport() {
		selectedMenuOption = 0;
		debug = Debug.getInstance();
		debug.printToStdout(1, "ClientMenuSupport consturctor has been called");
	}
	@Override
	public int showMenu(Scanner scanValue) {
		debug.printToStdout(2, "ClientMenuSupport showMenu has been called");
		System.out.println("[1] Set client name");
		System.out.println("[2] Enter number to query for prime");
		System.out.println("[3] What is the server response ?");
		System.out.println("[4] Quit");
		this.acceptValueMenuSelection(scanValue);
		return this.getSelectedMenuOption();
	}
	/**
	 * This menu selection function is used to get the scanner value from the input and perform the operations specified the prefix menu operations in that.
	 * @param scanValue
	 */
	private void acceptValueMenuSelection(Scanner scanValue) {
		debug.printToStdout(2, "ClientMenuSupport acceptValueMenuSelection has been called");
		try {
			this.setSelectedMenuOption(scanValue.nextInt());
			if(this.getSelectedMenuOption() > 4 || this.getSelectedMenuOption() < 1 ) {
				System.err.println("The value which has been entered for Menu Selection is not a valid value.\n");
				this.setSelectedMenuOption(0);
			}	
		} catch (NoSuchElementException noSuchElementException) {
			System.err.println("noSuchElementException has ocurred please check the value for the scanner.  Only Integer Value Accepted\n");
			System.exit(1);
		} catch (NumberFormatException numberFormatException) {
			System.err.println("Number Format Exception has ocurred please check the value for integer value entered.  Only Integer Value Accepted\n");
			System.exit(1);
		} catch (NullPointerException nullPointerException) {
			System.err.println("Null Pointer Exception has ocurred please check the value for scanner and integer value entered.\n");
			System.exit(1);
		}
		
	}
	public int getSelectedMenuOption() {
		debug.printToStdout(2, "ClientMenuSupport getSelectedMenuOption has been called");
		return selectedMenuOption;
	}
	public void setSelectedMenuOption(int selectedMenuOption) {
		debug.printToStdout(2, "ClientMenuSupport setSelectedMenuOption has been called");
		this.selectedMenuOption = selectedMenuOption;
	}
	@Override
	public String toString() {
		return "ClientMenuSupport [selectedMenuOption=" + selectedMenuOption
				+ "]";
	}	
}
