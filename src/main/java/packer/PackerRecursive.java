package packer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Item;
import model.Package;

public class PackerRecursive {
	
	/**
	 * 
	 * @param pck the object that contain the items and limit size
	 * @return the list of index 
	 */
	public List<String> getIndex(Package pck) {
		
		pck.getItems().sort((item1, item2) -> item1.getWeight().compareTo(item2.getWeight()));
		
		Double[] wt = pck.getItems().stream().map(i -> i.getWeight()).toArray(Double[]::new);
		Integer[] val = pck.getItems().stream().map(i -> i.getCost()).toArray(Integer[]::new);
		Boolean visited[] = new Boolean[pck.getItems().size()];
		Arrays.fill(visited, Boolean.FALSE);
		
		/*
		 * Compute the recursion tree, keeping track of visited items
		 */
		Integer maxValue = maximizeValue(pck.getWeight(), wt, val, pck.getItems().size(), visited);
		
		List<String> solutionItems = new ArrayList<>();
		for ( int i = 0; i < pck.getItems().size(); i++ ) {
			 if ( visited[i] ) {
				 solutionItems.add(pck.getItems().get(i).getIndex().toString());
			 }
		}
		
		/*
		 * Items included in the solution should be listed following index natural order
		 */
		solutionItems.sort( (index1,index2) -> index1.compareTo(index2) );

		return solutionItems;
	}

	/**
	 * @param W the maximum capacity of the package
	 * @param wt array of items weights
	 * @param val array of items values
	 * @param N total number of items
	 * @param visited 
	 * @return maximum value for the package
	 */
	private Integer maximizeValue(Integer W, Double wt[], Integer val[], Integer N, Boolean visited[]) {

		if (N == 0 || W == 0) {
			return 0; // stopping point
		}

		if ( wt[N-1] > W ) {
		
			return maximizeValue(W, wt, val, N-1,visited); // overflow capacity, reject item
			
		}  else {

			// pick max between two cases:
			// (1) nth item included 
			// (2) nth item not included
			
			Boolean v1[] = new Boolean[visited.length];
			Boolean v2[] = new Boolean[visited.length];
       
			System.arraycopy(visited, 0, v1, 0, v1.length);
			System.arraycopy(visited, 0, v2, 0, v2.length);
       
			v1[N-1] = true;

			Integer s1 = val[N-1] + maximizeValue(W-wt[N-1].intValue(), wt, val, N-1,v1);
			Integer s2 = maximizeValue(W, wt, val, N-1,v2);
       
			if( s1 > s2 ){
				System.arraycopy(v1, 0, visited, 0, v1.length);
				return s1;
			} else{
				System.arraycopy(v2, 0, visited, 0, v2.length);
				return s2;
			}
		}
   }      
}
