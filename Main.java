import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int carNumber = 0;
		double distance = 0;

		int time = 0;
		String name = "";

		File file = new File("src//inputs.txt");
		Scanner scan;
		try {
			scan = new Scanner(file);
			while (scan.hasNextLine()) {
				distance = scan.nextInt();
				carNumber = scan.nextInt();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Car[] cars = new Car[carNumber];
		double carPosition[] = new double[carNumber];

		for (int i = 0; i < cars.length; i++) {
			cars[i] = new Car("Masina: " + String.valueOf(i + 1));
		}

		for (int i = 0; i < cars.length; i++) {
			cars[i].start();
		}

		int i = 0;
		while (carPosition[i] < distance) {

			if (i == carPosition.length - 1) {
				i = 0;
			}
			if (carPosition[i] < distance) {
				if (cars[i].getSpeed() < 45) {
					cars[i].accelerate();
				}
				carPosition[i] += cars[i].getSpeed();
				cars[i].setTime(cars[i].getTime() + 1);
			}
			if (carPosition[i] >= distance) {
				name = cars[i].getCarName();
				time = cars[i].getTime();
				break;
			}
			i++;
		}

		System.out.println(name + " a castigat cursa in " + time + " secunde");
	}
}
