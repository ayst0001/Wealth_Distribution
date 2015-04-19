//This class is to simplify the process of generating random number
public class Random {
	
		public static float f(double range_max) {
			float random = (float)(Math.random() * range_max) ;
			return random;
	}
		public static int i(int range_max) {
			int random = (int)(Math.random() * range_max) ;
			return random;
		}

}
