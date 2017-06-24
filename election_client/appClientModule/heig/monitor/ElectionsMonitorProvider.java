package heig.monitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import heig.entite.Election;
import heig.entite.NamedQueriesConstants;
import heig.exceptions.PersistException;
import heig.session.IElectionsRemote;

public class ElectionsMonitorProvider extends Thread implements IElectionsMonitorProvider {

	private Boolean run;
	
	private IElectionsRemote electionsService;
	
	private List<IMonitorConsumer> consumers;
	
	private String monitored;
	
	public ElectionsMonitorProvider(IElectionsRemote electionsService) {
		super();
		this.run = Boolean.TRUE;
		this.electionsService = electionsService;
		this.consumers = new ArrayList<IMonitorConsumer>();
		this.monitored = null;
	}
	
	@Override
	public synchronized void monitor(String code) {
		monitored = code;
	}
	
	@Override
	public synchronized void stopMonitoring() {
		monitored = null;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Elections Updates Manager started");
			while(run) {
				sleep(3000l);
				if (monitored != null) {
					synchronized (monitored) {
						Map<String, Object> params = new HashMap<String, Object>();
						params.put(NamedQueriesConstants.ELECTION_BY_CODE_QUERY_PARAM, monitored);
						Election el = (Election) electionsService.getPersistable(NamedQueriesConstants.ELECTION_BY_CODE_QUERY_NAME, params);
						for (IMonitorConsumer c : consumers) {
							c.update(el);
						}
					}
				}
			}
			System.out.println("Elections Updates Manager stopped");
		} catch (InterruptedException | PersistException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addIMonitorConsumer(IMonitorConsumer consumer) {
		if (!consumers.contains(consumers)) {
			consumers.add(consumer);
		}
	}
}
