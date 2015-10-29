package com.capgemini.taxi;

public class Taxi {

	int x;
	int y;

	public Taxi(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Taxi x=" + x + ", y=" + y;
	}

}
