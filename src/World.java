//This class is used to create world entity
public class World {
	//World contains patches
	//The total amount of patch subject to the size of the  world
	public Patch[][] patch = new Patch[Parameter.WORLD_SIZE][Parameter.WORLD_SIZE];

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


	public void setup_turtles() {
		// Set initial turtle variables
		for (int i=0;i<Variables.num_people;i++){
			
			//Everyone starts facing direction 0,1,2 or 3
			person[i].facing = Random.i(4);
			//Everyone has different life expectancy
			person[i].lift_expectancy = Variables.life_expectancy_min + 
					Random.i((Variables.life_expectancy_max-Variables.life_expectancy_min+1));
			//Everyone consumes different amount of grains each tick
			person[i].metabolism = 1 + Random.i(Variables.metabolism_max);
			//Allocate random initial wealth
			person[i].wealth = person[i].metabolism + Random.i(50);
			//Everyone has a random vision
			person[i].vision = 1+ Random.i(Variables.max_vision);
			//Everyone has a random initial age
			person[i].age = Random.i(person[i].lift_expectancy);
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

	private void Create_turtles() {
		for (int i=0;i<Variables.num_people;i++){
			person[i] = new Turtle();
		}
	}
	private void Create_patches() {
		for (int i=0; i<Parameter.WORLD_SIZE; i++)
			for (int j=0; j<Parameter.WORLD_SIZE; j++){
			patch[i][j] = new Patch(i,j);
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
		//Handle corners
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
}
	


