package heig.observer;

public interface IObservable {

	public void addIObserver(IObserver observer);
	
	public void observe(String code);
}
