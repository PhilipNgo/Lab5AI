import java.text.DecimalFormat;

public class TutorialController extends Controller {

    public SpringObject object;

    ComposedSpringObject cso;

    /* These are the agents senses (inputs) */
	DoubleFeature x; /* Positions */
	DoubleFeature y;
	DoubleFeature vx; /* Velocities */
	DoubleFeature vy;
	DoubleFeature angle; /* Angle */

    /* Example:
     * x.getValue() returns the vertical position of the rocket 
     */

	/* These are the agents actuators (outputs)*/
	RocketEngine leftRocket;
	RocketEngine middleRocket;
	RocketEngine rightRocket;
	
	int print_counter = 0;
	DecimalFormat df = new DecimalFormat("0.00##");

    /* Example:
     * leftRocket.setBursting(true) turns on the left rocket 
     */
	
	public void init() {
		cso = (ComposedSpringObject) object;
		x = (DoubleFeature) cso.getObjectById("x");
		y = (DoubleFeature) cso.getObjectById("y");
		vx = (DoubleFeature) cso.getObjectById("vx");
		vy = (DoubleFeature) cso.getObjectById("vy");
		angle = (DoubleFeature) cso.getObjectById("angle");

		leftRocket = (RocketEngine) cso.getObjectById("rocket_engine_left");
		rightRocket = (RocketEngine) cso.getObjectById("rocket_engine_right");
		middleRocket = (RocketEngine) cso.getObjectById("rocket_engine_middle");
	}

    public void tick(int currentTime) {

    	/* TODO: Insert your code here */
    	if(y.getValue() > 1000) {
    		leftRocket.setBursting(true);
    		rightRocket.setBursting(true);
    		middleRocket.setBursting(true);
    	}else {
    		leftRocket.setBursting(false);
    		rightRocket.setBursting(false);
    		middleRocket.setBursting(false);
    	}
    	
    	print_counter++;
		if (print_counter % 10 == 0) {
			System.out.println(" SENSORS: a=" + df.format(angle.getValue()) + 
								" vx=" + df.format(vx.getValue()) + 
								" vy=" + df.format(vy.getValue()) + 
								" Position: y=" + df.format(y.getValue()));
		}
    }

}
