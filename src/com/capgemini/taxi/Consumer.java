package com.capgemini.taxi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Consumer {
	private List<Taxi> taxis = new ArrayList<>();
	private Random rand = new Random();

	public Consumer() {
		// empty
	}

	/**
	 * for unit testing
	 */
	public Consumer(Random rand) {
		this.rand = rand;
	}

	public void run(int x, int y) {
		createTaxis(x, y);
		int timeCatchTaxi = rand.nextInt(300) + 5;

		for (int i = 0; i < timeCatchTaxi; i++) {
			updateTaxis();
			removeTaxis();
			addTaxis(x, y);

			if (i == timeCatchTaxi - 1) {
				Taxi nearestTaxi = findNearestTaxi(x, y);
				int distanceX = Math.abs(nearestTaxi.x - x);
				int distanceY = Math.abs(nearestTaxi.y - y);

				if (distanceX < 1000 && distanceY < 1000) {
					System.out.println("My position is : x=" + x + " y=" + y);
					System.out.println("I waited for taxi : " + timeCatchTaxi + "s");
					System.out.println("Nearest taxi position is : " + nearestTaxi);
				} else {
					System.out.println("I don't see taxi in the area 1km");
				}
			}
		}
	}

	private void createTaxis(int x, int y) {
		int startNumbersTaxi = rand.nextInt(60) + 5;
		for (int i = 0; i < startNumbersTaxi; i++) {
			taxis.add(addTaxi(x, y));
		}
	}

	private Taxi addTaxi(int x, int y) {
		int xRand = rand.nextInt(10000 + x) - 5000;
		int yRand = rand.nextInt(10000 + y) - 5000;
		return new Taxi(xRand, yRand);
	}

	private void removeTaxi(int index) {
		taxis.remove(index);
	}

	private void updateTaxis() {
		for (Taxi taxi : taxis) {
			int shift = rand.nextInt(14);
			int choice = rand.nextInt(4);
			int changeValue = 0;

			switch (choice) {
			case 0:
				changeValue = taxi.x - shift;
				taxi.x = changeValue;
				break;
			case 1:
				changeValue = taxi.x + shift;
				taxi.x = changeValue;
				break;
			case 2:
				changeValue = taxi.y - shift;
				taxi.y = changeValue;
				break;
			case 3:
				changeValue = taxi.y + shift;
				taxi.y = changeValue;
				break;
			}
		}
	}

	private void removeTaxis() {
		if (taxis.size() > 7) {
			int numbersOfTaxiToRemove = rand.nextInt(2) + 1;
			for (int i = 0; i < numbersOfTaxiToRemove; i++) {
				int indexToRemove = rand.nextInt(taxis.size());
				removeTaxi(indexToRemove);
			}
		}
	}

	private void addTaxis(int x, int y) {
		if (taxis.size() < 62) {
			int numbersAddTaxis = rand.nextInt(2) + 1;
			for (int i = 0; i < numbersAddTaxis; i++) {
				taxis.add(addTaxi(x, y));
			}
		}
	}

	private Taxi findNearestTaxi(int x, int y) {
		Taxi nearestTaxi = taxis.get(0);

		for (int i = 1; i < taxis.size(); i++) {
			int absoutValueDifferentCheckTaxi = Math.abs((taxis.get(i).x - x)) + Math.abs((taxis.get(i).y - y));
			int absoutValueDifferentActualTaxi = Math.abs((nearestTaxi.x - x)) + Math.abs((nearestTaxi.y - y));

			if (absoutValueDifferentCheckTaxi < absoutValueDifferentActualTaxi) {
				nearestTaxi = taxis.get(i);
			}
		}

		return nearestTaxi;
	}

}
