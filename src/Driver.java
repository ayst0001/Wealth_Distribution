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
		//Creating patches according to the size of the world
		//Default size is 51*51, the same as in Netlogo
		Patch[][] patch = new Patch[Parameter.WORLD_SIZE][Parameter.WORLD_SIZE];
		for (int i=1; i<51; i++)
			for (int j=1; j<51; j++){
				patch[i][j] = new Patch();
			}
		
		//give some patches the most grain possible --
		//these patches are the "best land"
		for (int i=1; i<51; i++)
			for (int j=1; j<51; j++){
			if(Random.f(100.0) <= Variables.percent_best_land){
				patch[i][j].max_grain = Parameter.MAX_GRAIN;
				patch[i][j].grain = patch[i][j].max_grain;
			}
		}
	/*	
		//spread that grain around the window a little
		//do five times(according to netlogo model)
		for (int k=0; k<5; k++){
			for (int i=1; i<51; i++)
				for (int j=1; j<51; j++){
					if (patch[i][j].max_grain != 0 ){
						patch[i][j].grain = patch[i][j].max_grain;
						//patch[i][j].diffues(0.25);
					}
				}
		}
		
		//spread the grain around some more
		//do ten times(according to netlogo model)
		for (int k=0; k<10; k++){
			for (int i=1; i<51; i++)
				for (int j=1; j<51; j++){
					//patch[i][j].diffues(0.25);
				}
		}
*/		
		//testing
		for (int i=1; i<51; i++)
			for (int j=1; j<51; j++){
				System.out.println("Patch(" + i +","+ j +")has "+
						patch[i][j].grain+" grain of "+patch[i][j].max_grain);
			}
		
		
		
	}
	
	private static void setup_turtles() {
		// TODO Auto-generated method stub
		
	}



}
