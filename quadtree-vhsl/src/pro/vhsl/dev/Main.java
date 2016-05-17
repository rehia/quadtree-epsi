package pro.vhsl.dev;

import java.util.Random;

public class Main {

	public static void main(String[] args) {

	}

	static Random random = new Random(10);
	public static XY createRandomPoint(int max) {
		return new XY(random.nextInt(max) -1, random.nextInt(max) -1);
	}

}
