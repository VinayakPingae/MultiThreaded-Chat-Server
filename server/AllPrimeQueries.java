package primeService.server;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import primeService.util.Debug;
/**
 * Description - This is the data structure class which has been used to perfom various operations on the data structure.
 * @author Vinayak Subhash Pingale
 * @since 27th February 2015
 * @version 1.1
 * 
 */
public class AllPrimeQueries implements AllPrimeQueriesInterface {
	private static Map<String, List<Integer>> primeQueries = null;
	private Debug debug;
	public AllPrimeQueries() {
		// primeQueries = new ConcurrentHashMap<String, List<Integer>>();
		debug = Debug.getInstance();
		debug.printToStdout(1, "All Prime queries constructor has been called.");
	}
	/**
	 * Description - Put data is used for put the relevant integer list for a particular client
	 * @param clientName - specify the client name on which operation has to be done.
	 * @param queryInteger - specify the query integer which has to be added on the client side
	 */
	public void putData(String clientName, int queryInteger) {
		debug.printToStdout(2, "All Prime queries putData has been called.");
		List<Integer> integerList;
		if (primeQueries == null) {
			primeQueries = new ConcurrentHashMap<String, List<Integer>>();
			integerList = new CopyOnWriteArrayList<Integer>();
			integerList.add(queryInteger);
			primeQueries.put(clientName, integerList);
		} else {
			Iterator<Entry<String, List<Integer>>> iterator = primeQueries
					.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, List<Integer>> pair = (Map.Entry<String, List<Integer>>) iterator
						.next();
				if (pair.getKey().equals(clientName)) {
					integerList = primeQueries.get(clientName);
					integerList.add(queryInteger);
					primeQueries.put(pair.getKey(), integerList);
				} else if (!pair.getKey().equals(clientName)) {
					integerList = new CopyOnWriteArrayList<Integer>();
					integerList.add(queryInteger);
					primeQueries.put(clientName, integerList);
				}
			}
		}
	}
	/**
	 * Description - Print the data depending on the client name.
	 * @param clientName_In
	 * @return List<Integer>
	 */
	public List<Integer> getData(String clientName_In) {
		debug.printToStdout(2, "All Prime queries getData has been called.");
		List<Integer> clientQueriesSubmitted = null;
		if(primeQueries != null) {
			clientQueriesSubmitted = primeQueries.get(clientName_In);
		} else {
			System.out.println("Value is not fetched from client and the request has not been passed");
		}
		return clientQueriesSubmitted;
	}

	public static Map<String, List<Integer>> getPrimeQueries() {
		return primeQueries;
	}

	public static void setPrimeQueries(Map<String, List<Integer>> primeQueries) {
		AllPrimeQueries.primeQueries = primeQueries;
	}

}
