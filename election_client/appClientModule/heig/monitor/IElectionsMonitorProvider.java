package heig.monitor;

public interface IElectionsMonitorProvider {

	void addIMonitorConsumer(IMonitorConsumer consumer);
	
	void monitor(String code);
	
	void stopMonitoring();
}
