/*
 * @author Kyle Jonson
 */

public class Queens {
	//I made it a constructor but I can't remember why I thought it was in the worksheet guide but I guess not
	//I think I saw another program that did this and I subconsciously copied it
	public Queens(int size){
		if(size < 4){
			System.out.println("No Solutions");
		}else{
			Board b = new Board(size);
			System.out.println("Solutions:");
			int a = explore(b,1,0);
			System.out.println("Total Solutions: " + a);
		}
	}
	private static int explore(Board b, int row, int total){
		//Base Case
		if(row == b.size()+1){
			b.print();
			System.out.println();
			return total + 1;
		//Recursive Case
		}else{
			for(int col = 1; col < b.size()+1; col++){
				if(b.safe(row, col)){
					b.place(row, col);
					total = explore(b,row+1,total);
					b.remove(row,col);
				}	
			}
			return total;
		}
	}
	public static void main(String[] args){
		Queens queen = new Queens(8);
	}
}
