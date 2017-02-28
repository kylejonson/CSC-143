/*
 * @author Kyle Jonson
 * Using Building Java Programs Textbook
 * 
 * This program implements a quicksort for Strings to use for data
 * of the Seattle School Districts
 * 
 */
import java.util.*;

public class Quicksort  {
	// THE PROGRAM CURRENTLY SORTS STRINGS HOWEVER YOU ARE ABLE TO
	// CHANGE THE TYPES OF THESE CONSTANTS TO THE TYPE BEING SORTED
	// AS WELL AS THE PARAMETER TYPE OF quickSort() sorry for caps
	private static String[] array;
	private static String pivot;
	private static String temp;
	
	private static int length;

	public static void main(String[] args){
		//I would like to apologize in advance for all of the ugly casting in the main
		//It's all there due to my choice to make the sort take and return an Object array
		//I'm going to consider just changing this to Strings but I still think it's a fair choice
		Quicksort b = new Quicksort();
		
		SeattleSchoolsData data = new SeattleSchoolsData("data.csv");
		Map<String,String> map = data.getAddressBook();
		
		String[] addressBook = (String[])map.values().toArray(new String[0]);
		addressBook = (String[])b.quickSort(addressBook);			//Seattle School Data Test
		for(String str : addressBook){
			System.out.println(str);
		}
		
		String[] ar = new String[100];
		for(int a = 0; a < 100; a++){
			ar[a] = Double.toString(100 * Math.random());
			System.out.print(ar[a]+" ");
		}
		System.out.println();
		
		ar = (String[]) b.quickSort(ar);							//Random doubles test
		for(String i : ar){
			System.out.print(i+" ");
		}
	}
    //Sorts an array of some object using the quicksort algorithm
	public static Object[] quickSort(String[] array) {
		if (array == null || array.length == 0){					//Check if the array even has data to sort
			return null;
		}
		Quicksort.array = array;
		length = array.length;
		return sort(0, length - 1);
	}
	//
	private static Object[] sort(int low, int high) {
		int i = low;
		int j = high;
		pivot = array[low + (high-low)/2];							//The pivot is the value of comparison in the sort
		while (i <= j) {
			while (array[i].compareTo(pivot) < 0) {					//Compares objects larger than the pivot
				i++;
			}
			while (array[j].compareTo(pivot) > 0) {					//Compares objects smaller than the pivot
				j--;
			}
			if (i <= j) {											//Finds a case to flip values
				flip(i, j);
				i++;
				j--;
			}
		}
		if (low < j){												//Continues the sort on the lower half of the array recursively
			sort(low, j);
		}
		if (i < high){												//Continues the sort on the upper half of the array recursively
			sort(i, high);
		}
		return array;
	}
	private void flip(int i, int j) {								//Simple flip method between two values
		temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
