package com.capgemini.taxi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Consumer implements Observer {
	List<Taxi> taxis = new ArrayList<>();
	Taxi nearestTaxi = null;
	int valueX = 0;
	int valueY = 0;
	Random r = new Random();

	public Consumer() {
	}

	public void run(int x, int y) {
		valueX = x;
		valueY = y;
		createListTaxis();
		int timeCatchTaxi = r.nextInt(300) + 5;
		for (int i = 0; i < timeCatchTaxi; i++) {
			updateListTaxis();
			/*
			 * for (Taxi taxi : taxis) { System.out.println(taxi);
			 * System.out.println(); }
			 */
			removeTaxisFromList();
			addTaxisToList();
			checkNearestTaxi();

			int checkDistanceX = Math.abs(nearestTaxi.getX() - valueX);
			int checkDistanceY = Math.abs(nearestTaxi.getY() - valueY);

			if (i == timeCatchTaxi - 1) {
				if (checkDistanceX < 1000 && checkDistanceY < 1000) {
					System.out.println("My position is : x=" + x + " y=" + y);
					System.out.println("I waited for taxi : " + timeCatchTaxi + "s");
					System.out.println("Nearest taxi position is : " + nearestTaxi);
				} else {
					System.out.println("I don't see taxi in the area 1km");
				}
			}
		}

	}

	private void createListTaxis() {
		int startNumbersTaxi = r.nextInt(60) + 5;

		for (int i = 0; i < startNumbersTaxi; i++) {
			taxis.add(addTaxi());
		}
	}

	@Override
	public Taxi addTaxi() {
		int x = r.nextInt(10000 + valueX) - 5000;
		int y = r.nextInt(10000 + valueY) - 5000;

		Taxi newTaxi = new Taxi(x, y);

		return newTaxi;
	}

	@Override
	public void removeTaxi(int index) {
		taxis.remove(index);
	}

	@Override
	public void updateListTaxis() {
		for (Taxi taxi : taxis) {
			int shift = r.nextInt(14);
			int choice = r.nextInt(4);
			int changeValue = 0;

			switch (choice) {
			case 0:
				changeValue = taxi.getX() - shift;
				taxi.setX(changeValue);
				break;
			case 1:
				changeValue = taxi.getX() + shift;
				taxi.setX(changeValue);
				break;
			case 2:
				changeValue = taxi.getY() - shift;
				taxi.setY(changeValue);
				break;
			case 3:
				changeValue = taxi.getY() + shift;
				taxi.setY(changeValue);
				break;
			default:
				break;
			}
		}
	}

	private void removeTaxisFromList() {
		if (taxis.size() > 7) {
			int numbersRemoveTaxis = r.nextInt(2) + 1;
			for (int i = 0; i < numbersRemoveTaxis; i++) {
				int indexRemove = r.nextInt(taxis.size());
				removeTaxi(indexRemove);
			}
		}
	}

	private void addTaxisToList() {
		if (taxis.size() < 62) {
			int numbersAddTaxis = r.nextInt(2) + 1;
			for (int i = 0; i < numbersAddTaxis; i++) {
				taxis.add(addTaxi());
			}
		}
	}

	private void checkNearestTaxi() {

		nearestTaxi = taxis.get(0);
		for (int i = 1; i < taxis.size(); i++) {
			int absoutValueDifferentCheckTaxi = Math.abs((taxis.get(i).getX() - valueX)) + Math.abs((taxis.get(i).getY() - valueY));
			int absoutValueDifferentActualTaxi = Math.abs((nearestTaxi.getX() - valueX)) + Math.abs((nearestTaxi.getY() - valueY));

			if (absoutValueDifferentCheckTaxi < absoutValueDifferentActualTaxi) {
				nearestTaxi = taxis.get(i);

			}
		}
	}

}
