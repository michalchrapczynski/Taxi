package com.capgemini.taxi;

public interface Observer {

	public Taxi addTaxi();

	public void removeTaxi(int index);

	public void updateListTaxis();

}
