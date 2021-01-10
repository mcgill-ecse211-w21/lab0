import simlejos.ExecutionController;
import simlejos.hardware.motor.Motor;
import simlejos.hardware.port.SensorPort;
import simlejos.hardware.sensor.EV3TouchSensor;
import simlejos.robotics.RegulatedMotor;
import simlejos.robotics.SampleProvider;

/**
 * Main class of the program.
 */
public class Lab0 {
  
  // Constants
  /** The number of threads used in the program. */ // short javadocs can be written on 1 line
  public static final int NUMBER_OF_THREADS = 1;
  /** The speed with which the robot moves forward, in deg/s. */
  public static final int FORWARD_SPEED = 300;

  // Hardware resources
  /** The left motor. */
  public static final RegulatedMotor leftMotor = Motor.A;
  /** The right motor. */
  public static final RegulatedMotor rightMotor = Motor.D;
  /** The touch sensor sample provider. */
  public static final SampleProvider touchSensor = new EV3TouchSensor(SensorPort.S2).getTouchMode();
  /** Array of one element where the sample is stored. */
  public static float[] touchSensorSample = new float[touchSensor.sampleSize()];

  /**
   * The main entry point.
   * 
   * @param args not used
   */
  public static void main(String[] args) {
    System.out.println("Starting Lab 0 demo"); // prints to Webots console
    init();
    forward();
    
    // Keep moving forward while the touch sensor is not pressed
    while (!isPressed(touchSensorSample)) {
      /* Update touchSensorSample by fetching a sample from the touch sensor provider and storing
       * in touchSensorSample at index 0.
       * 
       * Equivalent pseudocode: touchSensorSample = touchSensor.fetchSample() */
      touchSensor.fetchSample(touchSensorSample, 0);
    }
    
    stopMotors();    
    System.exit(0);
  }
  
  /**
   * Returns true if the touch sensor sample indicates the sensor was pressed.
   * 
   * @param touchSensorSample touch sensor sample, a one-element float array
   * @return true if the touch sensor sample indicates the sensor was pressed
   */
  public static boolean isPressed(float[] touchSensorSample) {
    // TODO Complete this method.
    return false;
  }
  
  /**
   * Helper method used to initialize program.
   */
  private static void init() {
    // Need to define how many threads are synchronized to simulation steps
    ExecutionController.setNumberOfParties(NUMBER_OF_THREADS);
    // Wait a short time before moving to make sure everything has settled
    ExecutionController.performPhysicsSteps(10);
    // Periodically perform physics steps in the background
    ExecutionController.performPhysicsStepsInBackground();
  }
  
  /**
   * Moves the robot forward with FORWARD_SPEED. In future labs, a method like this should be
   * placed in its own class along with other related methods.
   */
  public static void forward() {
    leftMotor.setSpeed(FORWARD_SPEED);
    rightMotor.setSpeed(FORWARD_SPEED);
    leftMotor.forward();
    rightMotor.forward();
  }
  
  /**
   * Stops the motors.
   */
  public static void stopMotors() {
    leftMotor.stop();
    rightMotor.stop();
  }
  
}
