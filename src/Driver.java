/*This is the main driver of the model
 * This class controls the major process of this model
*/
public class Driver {

	public static void main(String[] args) {
		//initiate the model
		Setup();

	}
	
	private static void Setup() {
		//call the procedures to set up the world
		setup_patches();
		setup_turtles();
	}

	private static void setup_patches() {
		Patch[][] patch = new Patch[Parameter.WORLD_SIZE][Parameter.WORLD_SIZE];
		//give some patches the most grain possible --
		//these patches are the "best land"
		for (int i=1; i<51; i++)
			for (int j=1; j<51; j++){
			if(Random.f(100.0)<=Variables.percent_best_land){
				patch[i][j].grain = Parameter.MAX_GRAIN;
			}
		}
		
		
	}
	
	private static void setup_turtles() {
		// TODO Auto-generated method stub
		
	}



}
