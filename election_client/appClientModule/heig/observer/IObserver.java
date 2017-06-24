package heig.observer;

import heig.entite.Election;

public interface IObserver {

	public void notify(Election election);
}
