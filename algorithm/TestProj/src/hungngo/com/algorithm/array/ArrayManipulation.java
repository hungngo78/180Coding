package hungngo.com.algorithm.array;

// https://www.hackerrank.com/challenges/crush/problem?h_l=interview&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=arrays
//   each operation add a value to each of the array element between two given indices
// Input:
//    n = 10    --> array n phần tử ( 0 )
//    a b k		
//    1 5 3		-> Add the  3 between the indices 1 and 5   
//    4 8 7		-> Add the  7 between the indices 4 and 8   
//	  6 9 1		-> Add the  1 between the indices 6 and 9   
// Output:  giá tri max của array n phần tử

// Solution: tìm các đoạn overlap lẫn nhau, phần tử nằm trong đoạn overlap sẽ có giá trị cộng hưởng
   // thay vì cộng giá trị  k vào từng phần tử giữa a và b, chỉ cần cộng vào phần tử đầu tiên (a)
   //  khi bước ra khỏi vùng [a - b] thì triệt tiêu giá trị k vừa cộng vào
   //  do đó, arr[b+1] = -k 


public class ArrayManipulation {

	public ArrayManipulation() {
		// TODO Auto-generated constructor stub
	}
	
	public long manipulate(int n, int[][] queries) {
		int max = 0;
		
		int arr[] = new int[n];
		for (int i=0; i<n; i++)
			arr[i] = 0;
		
		for (int i=0; i<queries.length; i++) {
			// get a, b, k element
			int a = queries[i][0];
			int b = queries[i][1];
			int k = queries[i][2];
			
			arr[a-1] += k;
			if (b < n)
				arr[b] -= k;
		}
		
		int sum = 0;
		for (int i=0; i<n; i++) {
			sum += arr[i];
			if (sum > max) 
				max = sum;
		}
		
		return max;
    }


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//int[][] queries = 
		//    { { 1, 5, 3},		//-> Add the value 3 between the indices 1 and 5  and :
		//      { 4, 8, 7},		//-> Add the value 7 between the indices 4 and 8  and :
		//      { 6, 9, 1} 		//-> Add the value 1 between the indices 6 and 9  and :
		//    };
		
		int[][] queries = {{ 1, 2, 100 },
				{ 2, 5, 100},
				{ 3, 4, 100 } };
		
		ArrayManipulation arrayManipulation = new ArrayManipulation();
		System.out.print("Max = " + arrayManipulation.manipulate(5, queries));
	}

}
