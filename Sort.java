import java.util.Random;

/**
 * This class offers a variety of different sorting algorithms.
 *
 * @author Chris-Bennet Fleger {@literal <}chris.fleger@gmx.de{@literal >}
 * @version 09.03.21
 */

	/**
	 * This method sorts a String in ascending order and counts the words of equal length.
	 * 
	 * @param text String
	 * @return String containing the words in ascending order with length
	 */	
	public class Sort{

		public static String sort(String text){
		
		String result = "";
		
		String[] words = text.split(" ");
		
		for(int i=0;i<words.length;i++){
			for(int j=i+1;j<words.length;j++){
				if(words[i].length() > words[j].length()){
					String tmp = words[i];
					words[i] = words[j];
					words[j] = tmp;
				}
			}
			
		}
		
		for(int i=0;i<words.length;i++){
			result += String.format("%d| ",words[i].length());
			result += String.format("%s ", words[i]);
			while(i<words.length-1 && words[i].length() == words[i+1].length()){
				result += String.format("%s ", words[i+1]);
				i++;
			}
			result += "\n";
		}
		
		return result;
		
	}

	/**
	 * This method swaps two elements within an integer array.
	 * 
	 * @param arr int[]
	 * @param x int
	 * @param y int
	 */
	public static void swap(int[] arr, int x, int y){
		int tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}

	/**
	 * This method sorts an integer array in ascending order using bogosort. 
	 * Average Complexity is O(n * n!)
	 * Warning: Do not use with arrays with size greater than 10
	 * 
	 * @param arr int
	 * @return int[] containing the sorted elements
	 */
	public static int[] d_bogosort(int[] arr){
		Random r = new Random();
		while(true){
			boolean is_sorted = true;
			int n = arr.length;
			for(int i=0;i<n;i++){
				int currand = r.nextInt(n);
				swap(arr,i,currand);
			}
			//print_arr(arr);
			for(int i=0;i<n;i++){
				if(i<n-1 && arr[i+1]<arr[i]){
					is_sorted = false;
				}
			}
			if(is_sorted){
				break;
			}
		}
		return arr;
	}

	/**
	 * This method sorts an integer array in ascending order using bubblesort. 
	 * Best Case O(n)
	 * Average Case O(n^2) 
	 * Worst Case O(n^2)
	 * 
	 * @param arr int[]
	 * @return int[] containing the sorted elements
	 */
	public static int[] d_bubblesort(int[] arr){

		for(int i = 0;i<arr.length;i++){
			for(int j=0;j<arr.length-i;j++){
				if(j < arr.length-1 && arr[j]>arr[j+1]){
					int tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
			}
		}

		return arr;
	}

	/**
	 * This method sorts an integer array in ascending order using selectionsort. 
	 * Best Case O(n^2)
	 * Average Case O(n^2) 
	 * Worst Case O(n^2)
	 * 
	 * @param arr int[]
	 * @return int[] containing the sorted elements
	 */
	public static int[] d_selectionsort(int[] arr){
		for(int i=0;i<arr.length;i++){
			int min = i;
			for(int j = i+1;j<arr.length;j++){
				if(arr[min] > arr[j]){
					min = j;
				}
			}
			if(i != min){
				int tmp = arr[min];
				arr[min] = arr[i];
				arr[i] = tmp;
			}
		}

		return arr;
	}

	/**
	 * This method sorts an integer array in ascending order using insertionsort. 
	 * Best Case O(n)
	 * Average Case O(n^2) 
	 * Worst Case O(n^2)
	 * 
	 * @param arr int[]
	 * @return int[] containing the sorted elements
	 */
	public static int[] d_insertionsort(int[] arr){
		for(int i=0;i<arr.length-1;i++){
			int tmp = arr[i+1];
			int j = i+1;
			while(j>0 && arr[j-1]>tmp){
				arr[j] = arr[j-1];
				j = j-1;
			}
			arr[j] = tmp;
		}

		return arr;
	}

	/**
	 * This method sorts an integer array in ascending order using quicksort. 
	 * Best Case O(n*log(n))
	 * Average Case O(n*log(n))
	 * Worst Case O(n^2)
	 * 
	 * @param arr int[]
	 * @param leftIndex int
	 * @param rightIndex int
	 * @return int[] containing the sorted elements
	 */
	public static void d_quicksort(int[] arr, int leftIndex, int rightIndex){
		if(rightIndex>leftIndex){
			int pivotValue = arr[rightIndex];
			int i = leftIndex;
			int j = rightIndex-1;
			while(true){
				while(i<rightIndex && arr[i] <= pivotValue) i++;
				while(j>i && arr[j] >= pivotValue) j--;
				if(i>=j) break;
				swap(arr, i, j);
			}
			swap(arr, i, rightIndex);
			d_quicksort(arr, leftIndex, i-1);
			d_quicksort(arr, i+1, rightIndex);
		}
	}

	/**
	 * This method sorts an integer array in ascending order using countingsort. 
	 * Best Case O(n)
	 * Average Case O(n)
	 * Worst Case O(n+k)
	 * 
	 * @param arr int[]
	 * @param k int
	 * @return int[] containing the sorted elements
	 */
	public static int[] d_countingsort(int[] arr, int k){
		int size = arr.length;
		int[] output = new int[size]; //Output Array O(n)
		int[] count = new int[k];	//Hilfsarray in  O(n)

		for(int i=0;i<size;i++){
			count[arr[i]] += 1;	//Zählschritt in O(n)   
		}

		int i = size-1;
		int j = count.length -1;

		while(j>=0){	//äußere Schleife: k
			while(count[j]>0){	//innere Schleife: n/k: O(n)
				output[i] = j;							
				count[j] -= 1;
				i--;
			}
			j--;
		}
		return output;
	}

	public static void countingsort_radix(int[] arr, int exp1){
		int n = arr.length;
		int[] output = new int[n];
		int[] count = new int[10]; //Hilfsarray bildet Ziffern 0-9 ab

		for(int i=0;i<n;i++){
			int index = (arr[i]/exp1);
			count[index % 10]++;
		}
		for(int i=1;i<10;i++){
			count[i] += count[i-1]; //Ermittlung der Position
		}

		//Build the output array
		int i = n-1;
		while(i>=0){
			int index = (arr[i]/exp1);
			output[count[index % 10] -1] = arr[i];
			count[index % 10]--;
			i--;
		}
		i=0;
		for(;i<arr.length;i++){
			arr[i] = output[i];
		}

	}

	public static int[] radixsort(int[] arr){
		int max1 = 0;
		for(int i=0;i<arr.length;i++){
			if(arr[i]>max1) max1 = arr[i];
		}
		int exp = 1;
		while(max1/exp > 0){
			countingsort_radix(arr, exp);
			exp *= 10;
		}
		return arr;
	}

	public static void print_arr(int[] arr){
		String result = "";
		result += "[";
		for(int i=0;i<arr.length;i++){
			
			if(i == arr.length-1){
				result += String.format("%d]", arr[i]);
			}else{
				result += String.format("%d, ", arr[i]);
			}
			
		}
		System.out.println(result);
	}

}