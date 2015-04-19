/*This is the main driver of the model
 * This class controls the major process of this model
*/


public class Driver {
	//Creating the world
	public static World world = new World();
	
	//Main function starts here
	public static void main(String[] args) {
		//initial setup
		Setup();
		//testing
		PatchStatus();
	}
	
	private static void Setup() {
		//initiating patches
		world.setup_patches();
		world.setup_turtles();
	}

		/*
		//spread the grain around some more
		//do ten times(according to netlogo model)
		for (int k=0; k<10; k++){
			for (int i=1; i<51; i++)
				for (int j=1; j<51; j++){
					//patch[i][j].diffues(0.25);
				}
		}
	
		
		
		
	}
*/	
	private static void setup_turtles() {
		// TODO Auto-generated method stub
		
	}
	private static void PatchStatus() {
		for (int i=0; i<Parameter.WORLD_SIZE; i++)
			for (int j=0; j<Parameter.WORLD_SIZE; j++){
				System.out.println("Patch(" + i +","+ j +")has "+
				world.patch[i][j].grain+" grain of "+world.patch[i][j].max_grain
				+ " possible");
				}
	}



}
