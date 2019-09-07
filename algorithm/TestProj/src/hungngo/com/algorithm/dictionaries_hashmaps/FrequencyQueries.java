package hungngo.com.algorithm.dictionaries_hashmaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// https://www.hackerrank.com/challenges/frequency-queries/problem?h_l=interview&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=dictionaries-hashmaps

// SOlution: dùng hashmap lưu trữ number: frequency
public class FrequencyQueries {

	public FrequencyQueries() {
		// TODO Auto-generated constructor stub
	}

	public List<Integer> freqQuery(List<List<Integer>> queries) {
		List<Integer> res = new ArrayList<>();
		
		Map<Integer, Integer> data = new HashMap();
		int frequency = 0;
		
		Iterator ite = queries.iterator();
		while (ite.hasNext()) {
			List<Integer> query = (List<Integer>)ite.next();
			int operator = query.get(0);
			int value = query.get(1);
			
			if (operator == 1) {
				data.put(value, data.getOrDefault(value, 0) + 1);
			} else if (operator == 2) {
				int currentFreq = data.getOrDefault(value, 0);
				if (currentFreq > 1) 
					data.put(value, --currentFreq);
				else 
					data.remove(value);
			} else {  // == 3 according to requirement
				
				int tmpRes = 0;
				
				for (Map.Entry<Integer, Integer> entry: data.entrySet()) {
					if (entry.getValue() == value) {
						tmpRes = 1;
						break;
					}
				}
				
				res.add(tmpRes);
			}
		}
		
		return res;
    }
	
	public static void main(String[] args) {
		// (1,1), (2,2), (3,2), (1,1), (1,1), (2,1), (3,2)
		// (1,3), (2,3), (3,2), (1,4), (1,5), (1,5), (1,4), (3,2), (2,4), (3,2)
	
		
		// TODO Auto-generated method stub
		FrequencyQueries frequencyQueries = new FrequencyQueries();
		
		List<List<Integer>> queries = new ArrayList();
		/*queries.add(Arrays.asList(1, 1));
		queries.add(Arrays.asList(2, 2));
		queries.add(Arrays.asList(3, 3));
		queries.add(Arrays.asList(1, 1));
		queries.add(Arrays.asList(1, 1));
		queries.add(Arrays.asList(2, 1));
		queries.add(Arrays.asList(3, 2));*/
		
		queries.add(Arrays.asList(1, 3));
		queries.add(Arrays.asList(2, 3));
		queries.add(Arrays.asList(3, 2));
		queries.add(Arrays.asList(1, 4));
		queries.add(Arrays.asList(1, 5));
		queries.add(Arrays.asList(1, 5));
		queries.add(Arrays.asList(1, 4));
		queries.add(Arrays.asList(3, 2));
		queries.add(Arrays.asList(2, 4));
		queries.add(Arrays.asList(3, 2));
		
		List<Integer> res = frequencyQueries.freqQuery(queries);
		for (Integer i: res) {
			System.out.print(i + " ");
		}

	}

}
