package primeService.server;

import java.util.NoSuchElementException;
import java.util.Scanner;

import primeService.util.MenuSupport;

/**
 * Description - This class has been included in the project directory structure so that all the menu regarding the specific clients will be 
 * implemented and the appropriate user selection has been done for the client and server appplication.
 * @author Vinayak
 * @since 29th feb 2015
 * @version 1.0
 * @interface MenuSupport
 * @exception NullPointerException NoSuchElementException
 */
class ServerMenuSupport implements MenuSupport {

	private int selectedMenuOption = 0;
	/*
	 * This function has been overridden from the MenuSupport interface which is used to display the menu driven options to the server. 
	 */
	@Override
	public int showMenu(Scanner scanValue) {
		System.out.println("[1] Client Name [print the name and query integer]");
		System.out.println("[2] All Client Queries  [print all names and queries so far]");
		System.out.println("[3] Quit [quit the server]");
		this.acceptValueMenuSelection(scanValue);
		return this.getSelectedMenuOption();
	}
	public void acceptValueMenuSelection(Scanner scanValue) {
		try {
			this.setSelectedMenuOption(scanValue.nextInt());
			if(this.getSelectedMenuOption() > 3 || this.getSelectedMenuOption() < 1 ) {
				System.err.println("The value which has been entered for Menu Selection is not a valid value.\n");
				this.setSelectedMenuOption(0);
				System.exit(1);
			}
		} catch (NoSuchElementException noSuchElementException) {
			System.err.println("noSuchElementException has ocurred please check the value for the scanner.  Only Integer Value Accepted\n");
			System.exit(1);
		} catch (NumberFormatException numberFormatException) {
			System.err.println("Null Pointer Exception has ocurred please check the value for integer value entered.  Only Integer Value Accepted\n");
			System.exit(1);
		} catch (NullPointerException nullPointerException) {
			System.err.println("Null Pointer Exception has ocurred please check the value for scanner and integer value entered.\n");
			System.exit(1);
		}
	}
	public int getSelectedMenuOption() {
		return selectedMenuOption;
	}
	public void setSelectedMenuOption(int selectedMenuOption) {
		this.selectedMenuOption = selectedMenuOption;
	}
}
