/*
 * @author Kyle Jonson
 * 
 * CSC&143
 * Worksheet 4: Seattle Public Schools
 * 
 * Takes a .csv file and constructs an object to help evaluate the data
 * has the following public methods-
 * getSchoolName: returns a list of the schools names
 * getDistances(String schoolName): returns a map of the names to the distances between the give school and that school
 * getPhoneBook: returns a map of the names to the phone number of the school
 * getAddressBook: returns a map of the names to the address of the school
 */

import java.util.*;
import java.io.*;
import com.opencsv.*;

public class SeattleSchoolsData {

	public static String filename;
	public static boolean dataIsLoaded;
	private static Map<String,School> schoolData;
	
	public SeattleSchoolsData(String filename) {
		//Consider checking if the string has .csv ending and adding it if it doesn't
		SeattleSchoolsData.filename = filename;
		SeattleSchoolsData.dataIsLoaded = false;
		schoolData = new HashMap<String, School>();
	}
	
	//Loads the data from .csv to useable data in a Map of type School
	private void loadData(){
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(SeattleSchoolsData.filename));
			try {
				Iterator<String[]> itr = reader.iterator();
				reader.readNext();
				while(itr.hasNext()){	//Loop that takes in a line of data then makes a School and adds it to the data Map
					String[] schoolLine = reader.readNext();
					if(schoolLine == null){
						break;
					}
					School sch = new School(schoolLine);
					schoolData.put(sch.school,sch);
				}
				SeattleSchoolsData.dataIsLoaded = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//returns a list of the schools' names
	public List<String> getSchoolName(){
		if(!dataIsLoaded){
			loadData();
		}
		List<String> schoolNames = new ArrayList<>();
		Set<String> a = schoolData.keySet();
		schoolNames.addAll(a);
		return schoolNames;
	}
	
	//returns the distances of other schools from a given school in meters
	public Map<String, Double> getDistances(String schoolName){
		if(!dataIsLoaded){
			loadData();
		}
		//Map that gets returned in the end
		Map<String,Double> distances = new TreeMap<>();
		//forgot what class to use for the calculations of latitude and longitude
		//So this is manually calculating code written with help:
		//http://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude-what-am-i-doi
		School initSchool = schoolData.get(schoolName);
		double initLat = initSchool.latitude;
		double initLon = initSchool.longitude;
		double R = 6371000;
		List<String> schoolNames = getSchoolName();
		schoolNames.remove(schoolName);
		for(String a : schoolNames){
			School compSchool = schoolData.get(a);
			double compLat = compSchool.latitude;
			double compLon = compSchool.longitude;
		    double dLat = Math.toRadians(compLat-initLat);
		    double dLng = Math.toRadians(compLon-initLon);
		    double b = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(initLat)) * Math.cos(Math.toRadians(compLat)) * Math.sin(dLng/2) * Math.sin(dLng/2);
		    double c = 2 * Math.atan2(Math.sqrt(b), Math.sqrt(1-b));
		    double dist = (R * c);
			distances.put(a, dist);
		}
		return distances;
	}

	//returns the names and numbers of every school in the district data
	public Map<String, String> getPhoneBook(){
		if(!dataIsLoaded){
			loadData();
		}
		Map<String,String> phoneBook = new TreeMap<>();
		List<String> schoolNames = getSchoolName();
		for(String a : schoolNames){
			phoneBook.put(a, schoolData.get(a).phoneNumber);
		}
		return phoneBook;
	}
	
	//returns the names an addresses of every school in the district data
	public Map<String, String> getAddressBook(){
		if(!dataIsLoaded){
			loadData();
		}		
		Map<String,String> addressBook = new TreeMap<>();
		List<String> schoolNames = getSchoolName();
		for(String a : schoolNames){
			addressBook.put(a, schoolData.get(a).address);
		}
		return addressBook;	
	}
}
