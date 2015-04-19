//This is the class for patches
public class Patch {
		//The number of grain a patch can hold
		public int max_grain;
		//The number of grain the patches has
		public int grain;
	
		//Creating each patch
		
		public Patch(){
			//Assigning a random amount of grain from the beginning
			//initial grain shall be no more than the max grain of the patch
			max_grain = Random.i(Parameter.MAX_GRAIN);
			grain = Random.i(max_grain);
		}

		public void diffues(double d) {
			// TODO Auto-generated method stub
			
		}
	


}
