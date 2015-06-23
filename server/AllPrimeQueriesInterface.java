package primeService.server;

import java.util.List;
/**
 * Description - All primequeries has been added to inform about the data structure operations
 * @author Vinayak
 * @since 27th February 2015
 * @version 1.1
 * 
 */

public interface AllPrimeQueriesInterface {
	/**
	 * Description - Put data is used for put the relevant integer list for a particular client
	 * @param clientName - specify the client name on which operation has to be done.
	 * @param queryInteger - specify the query integer which has to be added on the client side
	 */
	
	public void putData(String clientName, int queryInteger);
	/**
	 * Description - Print the data depending on the client name.
	 * @param clientName_In
	 * @return List<Integer>
	 */
	public List<Integer> getData(String clientName_In);
}
