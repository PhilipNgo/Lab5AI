public class StateAndReward {

	
	/* State discretization function for the angle controller */
	public static String getStateAngle(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */

		String state = "OneStateToRuleThemAll";
		// 0.174533 = 10 grader
		if(angle < 0.174533 && angle > -0.174533) {
			state = "NORTH";
		//	0.785398 = 45 grader
		}else if((angle < 0.785398 && angle > 0.174533)) {
			state = "NORTH_EAST";
		}else if(angle > 0.785398 && angle < Math.PI/2) {
			state = "EAST";
		}else if((angle > -0.785398 && angle < -0.174533)) {
			state = "NORTH_WEST";
		}else if(angle < -0.785398 && angle > -Math.PI/2) {
			state = "WEST";
		}else {
			state = "SOUTH";
		}
		return state;
	}

	/* Reward function for the angle controller */
	public static double getRewardAngle(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */
		
		double reward = 0;
		if(angle < 0.174533 && angle > -0.174533) {
			reward = 1;
		//	0.785398 = 45 grader
		}else if((angle < 0.785398 && angle > 0.174533)) {
			reward = 0.7;
		}else if(angle > 0.785398 && angle < Math.PI/2) {
			reward = 0.3;
		}else if((angle > -0.785398 && angle < -0.174533)) {
			reward = 0.7;
		}else if(angle < -0.785398 && angle > -Math.PI/2) {
			reward = 0.3;
		}else {
			reward = -1;
		}

		return reward;
	}

	/* State discretization function for the full hover controller */
	public static String getStateHover(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */

		String state = "OneStateToRuleThemAll2";
		if( vx > 0 && vy < 0 ) {
			state = "NORTH_EAST";
		}else if( vx > 0 && vy > 0 ) {
			state = "SOUTH_EAST";
		}else if ( vx < 0 && vy > 0 ) {
			state = "SOUTH_WEST";
		}else {
			state = "NORTH_WEST";
		}
		
		return state;
	}

	/* Reward function for the full hover controller */
	public static double getRewardHover(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */
		
		double reward = 0;
		if( vx < 0.5 && vx > -0.5 && vy < 0 && vy > -1.2 ) {
			reward += 1;
		}else {
			reward -= 0;
		}

		return reward;
	}

	// ///////////////////////////////////////////////////////////
	// discretize() performs a uniform discretization of the
	// value parameter.
	// It returns an integer between 0 and nrValues-1.
	// The min and max parameters are used to specify the interval
	// for the discretization.
	// If the value is lower than min, 0 is returned
	// If the value is higher than min, nrValues-1 is returned
	// otherwise a value between 1 and nrValues-2 is returned.
	//
	// Use discretize2() if you want a discretization method that does
	// not handle values lower than min and higher than max.
	// ///////////////////////////////////////////////////////////
	public static int discretize(double value, int nrValues, double min,
			double max) {
		if (nrValues < 2) {
			return 0;
		}

		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * (nrValues - 2)) + 1;
	}

	// ///////////////////////////////////////////////////////////
	// discretize2() performs a uniform discretization of the
	// value parameter.
	// It returns an integer between 0 and nrValues-1.
	// The min and max parameters are used to specify the interval
	// for the discretization.
	// If the value is lower than min, 0 is returned
	// If the value is higher than min, nrValues-1 is returned
	// otherwise a value between 0 and nrValues-1 is returned.
	// ///////////////////////////////////////////////////////////
	public static int discretize2(double value, int nrValues, double min,
			double max) {
		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * nrValues);
	}

}
