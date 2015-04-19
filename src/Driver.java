/*This is the main driver of the model
 * This class controls the major process of this model
*/


public class Driver {
	//Creating the world
	public static World world = new World();
	
	//Main function starts here
	public static void main(String[] args) {
		//initial setup
		world.setup_patches();
		world.setup_turtles();
		//Start simulation
		world.go();
		//testing
		//PatchStatus();
		TurtleStatus();
	}

	private static void TurtleStatus() {
		for (int i=0;i<Variables.num_people;i++){
			P.rintln("person " + i + " is facing direction" + world.person[i].facing
					+ " has wealth" + world.person[i].wealth
					+ " has age" + world.person[i].age
					+ " has vision" + world.person[i].vision
					+ " consumes" + world.person[i].metabolism +" graids"
					+ " may live to" + world.person[i].lift_expectancy + " years"
					+ " at (" + world.person[i].x+","+world.person[i].y+")")
					;	
		}
		
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
