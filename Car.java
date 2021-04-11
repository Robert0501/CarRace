
public class Car {
	private String name;
	private double speed;

	public Car(String name, double speed) {
		this.name = name;
		this.speed = speed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void accelerate(double speed) {
		this.speed += speed;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + ", speed=" + speed + "]";
	}

}
