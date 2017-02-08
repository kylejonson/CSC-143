/*
 * @author Kyle Jonson
 * CSC&143
 * Worksheet 4: Seattle Public Schools
 * 
 *  This is a constructor for a school type to help contain all the data
 *  in a better organized fashion
 */
public class School implements Comparable<School> {
	
	//All of the data in the same order as the file
	public double latitude;
	public double longitude;
	public String name;
	public String grade;
	public String type;
	public String address;
	public String phoneNumber;
	public String city;
	public int zip;
	public String website;
	public String school;
	
	//Constructor to type School which transfers the line of data from a csv
	public School(String[] data){

		this.latitude = Double.parseDouble(data[1]);
		this.longitude = Double.parseDouble(data[2]);
		this.name = data[3];
		this.grade = data[4];
		this.type = data[5];
		this.address = data[6];
		this.city = data[7];
		this.zip = Integer.parseInt(data[8]);
		this.phoneNumber = data[9];
		this.website = data[10];
		this.school = data[11];
	}
	//Secondary constructor just in case to make a School by just filling in the data
	public School(double latitude, double longitude, String name, String grade, String type, String address, String phoneNumber, String city, int zip, String website, String school){
		this.latitude =latitude;
		this.longitude =longitude;
		this.name = name;
		this.grade = grade;
		this.type = type;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.zip = zip;
		this.website = website;
		this.school = school;
	}
	
	//Generic toString
	public String toString(){
		String schoolData = name +" " + type + " " + grade +": ";
		schoolData += (" Address: " + address + " " + city + " " + zip);
		schoolData += (" Phone: " + phoneNumber);
		schoolData += (" Latitude: " + latitude);
		schoolData += (" Longitude: " + longitude);
		schoolData += (" Website: ");
		return schoolData;
		
	}
	
	//Compares using the school name assuming the same name isn't used in multiple places
	public int compareTo(School other){
		return this.name.compareTo(other.name);
	}
}
