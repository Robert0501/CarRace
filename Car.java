import java.util.Random;

public class Car extends Thread {
	private String carName;
	private double speed;
	private double acc;
	private int time;

	volatile int carNumber = 1;

	Random rand = new Random();

	public Car(String name) {
		this.carName = name;
		this.speed = 0;
		this.time = 0;
		acc = generateRandomAcceleration();
	}

	
	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void accelerate() {
		this.speed += this.acc;
	}

	public double getAcc() {
		return this.acc;
	}

	public void setAcc(double acc) {
		this.acc = acc;
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double generateRandomAcceleration() {
		int nr = rand.nextInt() % 5;
		while (nr <= 0) {
			nr = rand.nextInt() % 5;
		}
		double accelerate = rand.nextDouble() + nr;
		while (accelerate <= 0) {
			accelerate = rand.nextDouble() % nr;
		}

		return accelerate;
	}

	@Override
	public String toString() {
		return "Car [name=" + carName + ", speed=" + speed + "]";
	}

	public void run() {
		accelerate();
	}

}
