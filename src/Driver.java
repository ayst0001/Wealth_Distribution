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
		for (int i=0;i<Variables.tick;i++){
			world.go(i);
		}
		//Output results
		world.Report_lorenz_gini();
		P.rintln("Result generated!");
	}
}
