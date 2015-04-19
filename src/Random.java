//This class is to simplify the process of generating random number

public class Random {
		//generate a random float
		public static float f(double range_max) {
			float random = (float)(Math.random() * range_max) ;
			return random;
	}
		//generate an integer from 0 to range_max-1
		//range_max amount of different results in total!
		public static int i(int range_max) {
			int random = (int)(Math.random() * range_max) ;
			return random;
		}

}
