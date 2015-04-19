/*
 * This class is used to store values that obtained
 * from user inputs from Netlogo GUI
 * These values are expected to be changed more frequently
 * than the ones in Parameter class
 * */
public class Variables {
	//The total population of the world
	public static int num_people = 500;
	//The maximum range a turtle can see
	//range from 1,2,3...max_vision
	public static int max_vision = 15;
	//The max amount of grain one can consume in one tick
	public static int metabolism_max = 13;
	//The minimum number of life expectancy
	public static int life_expectancy_min = 50;
	//The maximum number of life expectancy
	public static int life_expectancy_max = 52;
	//The percentage of best land at the creation of the world
	public static float percent_best_land = 15;
	//The interval of grain growth
	public static int grain_growth_interval = 6;
	//The amount of grain growth each time
	public static int num_grain_growth = 6;
	//The number of ticks to be tested
	public static int tick= 500;
}
