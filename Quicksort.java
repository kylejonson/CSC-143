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
	/* THE PROGRAM CURRENTLY SORTS STRINGS HOWEVER YOU ARE ABLE TO  */
	/* CHANGE THE TYPES OF THESE CONSTANTS TO THE TYPE BEING SORTED */
	/* AS WELL AS THE PARAMETER TYPE OF quickSort()                 */
	private String[] array;
	private String pivot;
	private String temp;
	
	private int length;

	public static void main(String[] args){
		
		Quicksort b = new Quicksort();
		
		SeattleSchoolsData data = new SeattleSchoolsData("data.csv");
		Map<String,String> map = data.getAddressBook();
		
		String[] addressBook = (String[])map.values().toArray(new String[0]);	//
		addressBook = (String[])b.quickSort(addressBook);
		for(String str : addressBook){
			System.out.println(str);
		}
		
		String[] ar = new String[100];
		for(int a = 0; a < 100; a++){
			ar[a] = Double.toString(100 * Math.random());
			System.out.print(ar[a]+" ");
		}
		System.out.println();
		
		ar = (String[]) b.quickSort(ar);
		for(String i : ar){
			System.out.print(i+" ");
		}
	}
    //Sorts an array of some object using the quicksort algorithm
	public Object[] quickSort(String[] array) {
		if (array == null || array.length == 0){							//Check if the array even has data to sort
			return null;
		}
		this.array = array;
		length = array.length;
		return sort(0, length - 1);
	}
	//
	private Object[] sort(int low, int high) {
		int i = low;
		int j = high;
		pivot = array[low + (high-low)/2];
		while (i <= j) {
			while (array[i].compareTo(pivot) < 0) {
				i++;
			}
			while (array[j].compareTo(pivot) > 0) {
				j--;
			}
			if (i <= j) {
				flip(i, j);
				i++;
				j--;
			}
		}
		if (low < j){
			sort(low, j);
		}
		if (i < high){
			sort(i, high);
		}
		return array;
	}
	private void flip(int i, int j) {
		temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}