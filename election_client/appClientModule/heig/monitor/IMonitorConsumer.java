package heig.monitor;

import heig.entite.Election;

public interface IMonitorConsumer {
	
	void monitor();
	
	void stopMonitoring();
	
	void update(Election update);
}
