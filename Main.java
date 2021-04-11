import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int distance = 0;
		int totalTime = 0;
		int time = 0;
		int pozitie = 0;
		int carNumber = 0;

		Car cars[] = new Car[carNumber];

		File file = new File("src//input.txt");
		Scanner scan;
		try {
			scan = new Scanner(file);
			while (scan.hasNextLine()) {
				distance = scan.nextInt();
				carNumber = scan.nextInt();
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < carNumber; i++) {

			cars[i] = new Car("" + (i + 1), 0);
		}

		for (int i = 0; i < carNumber; i++) {
			while (pozitie < distance) {
				Random rand = new Random();
				double acc = rand.nextInt() % 4.1;
				while (acc <= 0) {
					acc = rand.nextInt() % 4.1;
				}
				cars[i].accelerate(acc);
				pozitie += cars[i].getSpeed();
				time++;
			}
			System.out.println("Masina " + cars[i].getName() + " a ajuns la finish in " + time + " secunde");
			pozitie = 0;
			totalTime += time;
			time = 0;
		}

		System.out.println(totalTime);
	}

}
