package TestingBigO;
import java.util.Arrays;

/*
 * Kelley Kelley
 */

public class Testing {

	static int arraySize = 10000;
	
	public static void main(String[] args) {
		
		sortThem(ArrayUtil.randomIntArray(arraySize, 1000), "randomly generated");
		sortThem(ArrayUtil.ascendingArray(arraySize, 1000), "ascending order");
		sortThem(ArrayUtil.descendingArray(arraySize, 1000), "descending order");
		
	}
	
	public static void sortThem(int[] array, String data) {
		
		int[] a = array.clone();
		int[] b = array.clone();
		int[] c = array.clone();
		
		long selectionSortTime1 = selectionSort(a);
		long mergeSortTime1 = mergeSort(b);
		long systemSortTime1 = systemSort(c);

		System.out.println("For " + data + " data: \nthe selection sort took " + selectionSortTime1 + " milliseconds \nthe merge sort took " + mergeSortTime1 + " milliseconds \nthe system sort took " + systemSortTime1 + " milliseconds\n");
		
		
	}
	
	public static long selectionSort(int[] array) {
		
		SelectionSorter selectionSorter = new SelectionSorter(array);
		
		StopWatch timer = new StopWatch();
		
		timer.start();
		selectionSorter.sort();
		timer.stop();
		
		return timer.getElapsedTime();
		
	}
	
	public static long mergeSort(int[] array) {
		
		MergeSorter mergeSorter = new MergeSorter(array);
		
		StopWatch timer = new StopWatch();
		
		timer.start();
		mergeSorter.sort();
		timer.stop();
		
		return timer.getElapsedTime();
		
	}
	
	public static long systemSort(int[] array) {
		
		StopWatch timer = new StopWatch();
		
		timer.start();
		Arrays.sort(array);
		timer.stop();
		
		return timer.getElapsedTime();
		
		
	}

}
