//This class is used to create world entity
//This class is also handling the status updates of world entities

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class World {
	//World contains patches
	//The total amount of patch subject to the size of the  world
	public Patch[][] patch = 
			new Patch[Parameter.WORLD_SIZE][Parameter.WORLD_SIZE];

	//World also contains turtles
	//The total amount of turtles is determined by user input
	public Turtle[] person = new Turtle[Variables.num_people];
	
	//Constructor of a new world
	public World(){
		//Initiate all the patches
		Create_patches();
		//Initiate all the turtles
		Create_turtles();
	}
	
	public void go(int t) {
		//choose direction hold most grain with the vision
		for (int i=0;i<Variables.num_people;i++){
			Turn_toward_grain(person[i]);
		}
		//harvest before any turtle sets the patch to 0
		harvest();
		//turtle procedure
		for (int i=0;i<Variables.num_people;i++){
			Move_eat_age_die(person[i]);
		}
		//grow grain
		Grow_grain(t);
	}

	//If exceed maximum grain amount, set maximum values
	//Otherwise add the fix value
	private void Grow_grain(int t) {
		if (t%Variables.grain_growth_interval == 0){
			for (int i=0; i<Parameter.WORLD_SIZE; i++)
				for (int j=0; j<Parameter.WORLD_SIZE; j++){
					if ((patch[i][j].grain + Variables.num_grain_growth) 
							< patch[i][j].max_grain){
						patch[i][j].grain += Variables.num_grain_growth;
					}
					else{
						patch[i][j].grain = patch[i][j].max_grain;
					}
				}
		}
		else{}
	}

	public void setup_turtles() {
		// Set initial turtle variables
		for (int i=0;i<Variables.num_people;i++){
			
			//Everyone starts facing direction 0,1,2 or 3
			person[i].facing = Random.i(4);
			//Everyone has different life expectancy
			person[i].life_expectancy = Variables.life_expectancy_min + 
					Random.i((Variables.life_expectancy_max
							-Variables.life_expectancy_min+1));
			//Everyone consumes different amount of grains each tick
			person[i].metabolism = 1 + Random.i(Variables.metabolism_max);
			//Allocate random initial wealth
			person[i].wealth = person[i].metabolism + Random.i(50);
			//Everyone has a random vision
			person[i].vision = 1+ Random.i(Variables.max_vision);
			//Everyone has a random initial age
			person[i].age = Random.i(person[i].life_expectancy);
			//Start at a random location
			person[i].x = Random.i(Parameter.WORLD_SIZE);
			person[i].y = Random.i(Parameter.WORLD_SIZE);
		}
	}

	public void setup_patches() {
		//give some patches the most grain possible --
		//these patches are the "best land"
		Give_some_patches_most_grain();
		
		//spread that grain around the window a little
		//do five times(according to netlogo model)
		for (int i=0;i<5;i++){
			Spread_grain_type_one();
		}
		
		//Spread the grain around some more
		//do 10 times(according to netlogo model) 
		for (int i=0;i<10;i++){
			Spread_grain_type_two();
		}
		//Set max grain for each patch
		//initial-grain level is also maximum
		Set_max_grain();
	}

	private void Set_max_grain() {
		for (int i=0; i<Parameter.WORLD_SIZE; i++)
			for (int j=0; j<Parameter.WORLD_SIZE; j++){
				patch[i][j].max_grain = (int)patch[i][j].grain + 1;
			}
	}

	//Two types of spread algorithm provided in Netlogo
	private void Spread_grain_type_one() {
		for (int i=0; i<Parameter.WORLD_SIZE; i++)
			for (int j=0; j<Parameter.WORLD_SIZE; j++){
					if (patch[i][j].max_grain != 0 ){
						patch[i][j].grain = patch[i][j].max_grain;
						Diffues(i,j,0.25);
					}
				}
		}
	private void Spread_grain_type_two() {
		for (int i=0; i<Parameter.WORLD_SIZE; i++)
			for (int j=0; j<Parameter.WORLD_SIZE; j++){
						Diffues(i,j,0.25);
				}
	}

	//initiating Person one by one
	private void Create_turtles() {
		for (int i=0;i<Variables.num_people;i++){
			person[i] = new Turtle();
		}
	}
	private void Create_patches() {
		for (int i=0; i<Parameter.WORLD_SIZE; i++)
			for (int j=0; j<Parameter.WORLD_SIZE; j++){
			patch[i][j] = new Patch();
		}
	}
	private void Give_some_patches_most_grain() {		
		for (int i=0; i<Parameter.WORLD_SIZE; i++)
			for (int j=0; j<Parameter.WORLD_SIZE; j++){
				patch[i][j].max_grain = 0;
				if(Random.f(100.0) <= Variables.percent_best_land){
					patch[i][j].max_grain = Parameter.MAX_GRAIN;
					patch[i][j].grain = patch[i][j].max_grain;
					}
				}
	}

	private void Diffues(int i, int j, double e) {
		//Method to spread grain to the neighbors
		//When there's less than 8 neighbors, each neighbou still get 1/8
		//The rest goes back to the original patch
		//Handle corners first
		//top left corner
		if (i==0 && j==0){
			double portion = patch[i][j].grain*e/8;
			patch[i][j].grain = patch[i][j].grain - portion*3;
			patch[0][1].grain += portion;
			patch[1][0].grain += portion;
			patch[1][1].grain += portion;
		}
		//bottom left corner
		else if (i==0 && j==50){
			double portion = patch[i][j].grain*e/8;
			patch[i][j].grain = patch[i][j].grain - portion*3;
			patch[0][49].grain += portion;
			patch[1][49].grain += portion;
			patch[1][50].grain += portion;
		}
		//top right corner
		else if (i==50 && j==0){
			double portion = patch[i][j].grain*e/8;
			patch[i][j].grain = patch[i][j].grain - portion*3;
			patch[49][0].grain += portion;
			patch[49][1].grain += portion;
			patch[50][1].grain += portion;
		}
		//bottom right corner
		else if (i==50 && j==50){
			double portion = patch[i][j].grain*e/8;
			patch[i][j].grain = patch[i][j].grain - portion*3;
			patch[49][49].grain += portion;
			patch[49][50].grain += portion;
			patch[50][49].grain += portion;
		}
		//left edge
		else if (i==0){
			double portion = patch[i][j].grain*e/8;
			patch[i][j].grain = patch[i][j].grain - portion*5;
			patch[0][j+1].grain += portion;
			patch[0][j-1].grain += portion;
			patch[1][j].grain += portion;
			patch[1][j+1].grain += portion;
			patch[1][j-1].grain += portion;
		}
		//right edge
		else if (i==50){
			double portion = patch[i][j].grain*e/8;
			patch[i][j].grain = patch[i][j].grain - portion*5;
			patch[50][j+1].grain += portion;
			patch[50][j-1].grain += portion;
			patch[49][j].grain += portion;
			patch[49][j+1].grain += portion;
			patch[49][j-1].grain += portion;
		}
		//top edge
		else if (j==0){
			double portion = patch[i][j].grain*e/8;
			patch[i][j].grain = patch[i][j].grain - portion*5;
			patch[i-1][0].grain += portion;
			patch[i+1][0].grain += portion;
			patch[i-1][1].grain += portion;
			patch[i+1][1].grain += portion;
			patch[i][1].grain += portion;
		}
		//bottom edge
		else if (j==50){
			double portion = patch[i][j].grain*e/8;
			patch[i][j].grain = patch[i][j].grain - portion*5;
			patch[i-1][50].grain += portion;
			patch[i+1][50].grain += portion;
			patch[i-1][49].grain += portion;
			patch[i+1][49].grain += portion;
			patch[i][49].grain += portion;
		}
		//all other inside grids
		else{
			double portion = patch[i][j].grain*e/8;
			patch[i][j].grain = patch[i][j].grain - portion*8;
			patch[i][j+1].grain += portion;
			patch[i][j-1].grain += portion;
			patch[i+1][j].grain += portion;
			patch[i-1][j].grain += portion;
			patch[i+1][j+1].grain += portion;
			patch[i-1][j-1].grain += portion;
			patch[i+1][j-1].grain += portion;
			patch[i-1][j+1].grain += portion;
		}
	}

	private void harvest() {
		//The grain on the patch will be 
		//shared by all person on that location
		for (int i=0;i<Variables.num_people;i++){
			person[i].wealth += (int)(patch[person[i].x][person[i].y].grain
					/count_turtles_here(person[i]));
		}
	}

	//count the number of person on the same spot
	private double count_turtles_here(Turtle p) {
		double person_count = 0;
		for (int i=0;i<Variables.num_people;i++){
			if ((person[i].x == p.x)&&(person[i].y == p.y)){
				person_count += 1;
			}
		};
		return person_count;
	}

	//function copied from netlogo
	private void Move_eat_age_die(Turtle person) {
		//move one step forward
		move_forward(person);
		//consume according to metabolism
		person.wealth -= person.metabolism;
		//grow older
		person.age += 1;
		//check if anyone is about to die
		if(person.wealth<0){
			reborn(person);
		}
		if(person.age>person.life_expectancy){
			reborn(person);
		}
	}

	//person reborn after dead
	private void reborn(Turtle person) {
		//Facing direction 0,1,2 or 3 randomly
		person.facing = Random.i(4);
		//Reset life expectancy
		person.life_expectancy = Variables.life_expectancy_min + 
				Random.i((Variables.life_expectancy_max
						-Variables.life_expectancy_min+1));
		//Reset metabolism
		person.metabolism = 1 + Random.i(Variables.metabolism_max);
		//Initial wealth between the richest and the poorest
		person.wealth = person.metabolism + 
				Random.i(Richest_person_grain());
		//Allocate a random vision
		person.vision = 1+ Random.i(Variables.max_vision);
		//Everyone has a random initial age
		person.age = 0;
		//Start at a random location
		person.x = Random.i(Parameter.WORLD_SIZE);
		person.y = Random.i(Parameter.WORLD_SIZE);
	}

	//get to know how much does the richest person own
	private int Richest_person_grain() {
		int richest_person_grain = 0;
		for (int i=0;i<Variables.num_people;i++){
			if (person[i].wealth > richest_person_grain){
				richest_person_grain = person[i].wealth;
			}
			else{}
		}
		return richest_person_grain = 0;
	}

	//To let person move forward
	private void move_forward(Turtle person) {
		if (person.facing == 0){
			if(person.y>=1){
				person.y -= 1;
			}
			else{}
		}
		else if (person.facing == 1){
			if(person.x+1<=Parameter.WORLD_SIZE-1){
				person.x += 1;
			}
			else{}
		}
		else if (person.facing == 2){
			if(person.y+1<=Parameter.WORLD_SIZE-1){
				person.y += 1;
			}
			else{}
		}
		else if (person.facing == 3){
			if(person.x >= 1){
				person.x -= 1;
			}
			else{}
		}
		else{}
	}

	//To let person turn to the most promising direction
	private void Turn_toward_grain(Turtle person) {
		//Start from facing north
		person.facing = 0;
		//The grain in the north is the best amount
		int best_amount = grain_north(person);
		//If the grain amount in the east is better, turn
		if (grain_east(person)>best_amount){
			person.facing = 1;
			best_amount = grain_east(person);
		}
		//If the grain amount in the south is better, turn
		if (grain_south(person)>best_amount){
			person.facing=2;
			best_amount = grain_south(person);
		}
		//If the grain amount in the west is better, turn
		if (grain_west(person)>best_amount){
			person.facing=3;
			best_amount = grain_west(person);
		}
	}
	private int grain_west(Turtle person) {
		double grain_ahead = 0;
		if(person.x >= person.vision){
			for (int i=0;i<person.vision;i++){
				grain_ahead += patch[person.x-i-1][person.y].grain;
			}
			return ((int)grain_ahead);
		}
		else{
			for (int i=0;i<person.x;i++){
				grain_ahead += patch[person.x-i-1][person.y].grain;
			}
			return ((int)grain_ahead);
		}
		
	}
	private int grain_south(Turtle person) {
		double grain_ahead = 0;
		if((person.y+ person.vision) <= (Parameter.WORLD_SIZE-1)){
			for (int i=0;i<person.vision;i++){
				grain_ahead += patch[person.x][person.y+i+1].grain;
			}
			return ((int)grain_ahead);
		}
		else{
			for (int i=0;i<Parameter.WORLD_SIZE-person.y-1;i++){
				grain_ahead += patch[person.x][person.y+i+1].grain;
			}
			return ((int)grain_ahead);
		}
	}
	private int grain_east(Turtle person) {
		double grain_ahead = 0;
		if ((person.x+person.vision)<=(Parameter.WORLD_SIZE-1)){
			for (int i=0;i<person.vision;i++){
				grain_ahead += patch[person.x+i+1][person.y].grain;
			}
			return ((int)grain_ahead);
		}
		else{
			for (int i=0;i<Parameter.WORLD_SIZE-person.x-1;i++){
				grain_ahead += patch[person.x+i+1][person.y].grain;
			}
			return ((int)grain_ahead);
		}
	}
	private int grain_north(Turtle person) {
		double grain_ahead = 0;
		if (person.y >= person.vision){
			for (int i=0;i<person.vision;i++){
				grain_ahead += patch[person.x][person.y-i-1].grain;
			}
			return((int)grain_ahead);
		}
		else {
			for (int i=0 ;i < person.y; i++){
				grain_ahead += patch[person.x][person.y-i-1].grain;
			}
			return((int)grain_ahead);
		}
	}

	//Output data for lorenz cruve and gini index
	public void Report_lorenz_gini() {
		try{
			File csv = new File("lorenz_gini_output.csv");
			//output heads
			BufferedWriter head = 
					new BufferedWriter(new FileWriter(csv,true));
			head.write("Person_No,Wealth");
			head.newLine();
			head.close();
			//output data
			for (int i=0;i<Variables.num_people;i++){
				BufferedWriter bw = 
						new BufferedWriter(new FileWriter(csv,true));
				bw.write(Integer.toString(i) + 
						"," + Integer.toString(person[i].wealth));
				bw.newLine();
				bw.close();
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}


}

