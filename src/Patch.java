//This is the class for patches
public class Patch {
		//The number of grain a patch can hold
		public int max_grain;
		//The number of grain the patches has
		public double grain;
		//Location on X-Axis
		public int x;
		//Location on Y-Axis
		public int y;
		//Creating each patch
		
		public Patch(int i, int j){
			x = i-25;
			y = 25-j;
		}
	


}
