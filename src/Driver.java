import java.util.Scanner;

/*This is the main driver of the model
 * This class controls the major process of this model
*/
public class Driver {

	public static void main(String[] args) {
		//Input variables
		Get_variables();
		//initiate the model
		Setup();

	}

	private static void Get_variables() {
		Get_num_people();
		Get_max_vision();
		
		
	}

	@SuppressWarnings("resource")
	private static void Get_max_vision() {
		Scanner in = new Scanner(System.in);
		//Provide Instructions
		//out-of bound number inputs might be processed here
		P.rintln("Please enter the max vision of each people");
		P.rintln("The input can be any integer between 1 and 15 (include)");
		//get input, store it in variables
		Variables.max_vision = in.nextInt();
		P.rintln("Fantatsic! Now every one can see " + Variables.max_vision + " patch(s) behind");	
		return;
	}

	@SuppressWarnings("resource")
	private static void Get_num_people() {
		Scanner in = new Scanner(System.in);
		//Provide Instructions
		//out-of bound number inputs might be processed here
		P.rintln("Please enter the number of people");
		P.rintln("The input can be any integer between 2 and 1000 (include)");
		//get input, store it in variables
		Variables.num_people = in.nextInt();
		P.rintln("Fantatsic! There will be " + Variables.num_people + " people in this world");	
		return;
	}
		
	private static void Setup() {
		//call the procedures to set up the world
		setup_patches();
		setup_turtles();
		
	}

	private static void setup_patches() {
		//give some patches the most grain possible
		
		
	}
	
	private static void setup_turtles() {
		// TODO Auto-generated method stub
		
	}



}
