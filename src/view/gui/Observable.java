package View.gui;

import java.util.ArrayList;

/**
 * Provides an abstract class for model to extend
 * in accordance with the Observer/Observable Design pattern.
 * 
 * @author Christopher Chapline
 * @author Christopher Toepfer
 * @author David Christy
 * @author James Fagan
 */
public abstract class Observable 
{
	ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public void addObserver(Observer newObserver)
	{
		observers.add(newObserver);
	}
	
	public boolean removeObserver(Observer observerToRemove)
	{
		return observers.remove(observerToRemove);
	}
	
	public void notifyObservers()
	{
		for(Observer o: observers)
			o.update();
	}
}
