package heig.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import heig.entite.Election;
import heig.entite.NamedQueriesConstants;
import heig.exceptions.PersistException;
import heig.monitor.ElectionsMonitorProvider;
import heig.monitor.IElectionsMonitorProvider;
import heig.monitor.IMonitorConsumer;
import heig.observer.IObservable;
import heig.observer.IObserver;
import heig.session.IElectionsRemote;
import heig.view.interactive.ElectionsMonitorPanel;
import heig.view.interactive.ElectionsSelectionPanel;
import heig.view.pie.EPieChartType;
import heig.view.pie.ElectionPieChartPanel;

public class ElectionsFrame extends JFrame implements IObservable, IMonitorConsumer {

	private static final long serialVersionUID = -8287533311669351378L;
	
	private String current;
	
	private IElectionsRemote electionsService;
	
	private IElectionsMonitorProvider monitor;
	
	private ElectionsMonitorPanel monitorPanel;
	
	private List<IObserver> observers;
	
	public ElectionsFrame(IElectionsRemote electionsService) {
		this.current = null;
		this.electionsService = electionsService;
		this.monitor = new ElectionsMonitorProvider(electionsService);
		this.monitor.addIMonitorConsumer(this);
		this.observers = new ArrayList<IObserver>();
		buildInterface(electionsService);
		((Thread) this.monitor).start();
	}
	
	private List<Election> sortElections(List<Election> toSort) {
		List<Election> result = new ArrayList<>(toSort);
		Collections.sort(result, new Comparator<Election>() {
			@Override
			public int compare(Election o1, Election o2) {
				return o1.getCode().compareTo(o2.getCode());
			}
			
		});
		return result;
	}

	private void buildInterface(IElectionsRemote electionsService) {
		List<Election> elections = new ArrayList<Election>();
		try {
			elections = this.electionsService.getPersitableList(NamedQueriesConstants.ELECTION_LIST_QUERY_NAME);
			elections = sortElections(elections);
		} catch (PersistException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Visualisation des résultats des éléctions");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
		
		Election current = elections.get(0);
		this.current = current.getCode();
		
		Container contentPane = getContentPane();
		IObserver observer = new ElectionsSelectionPanel(this, current, elections);
		addIObserver(observer);
		contentPane.add((Component) observer, BorderLayout.NORTH);
		
		JPanel pieChartsPanel = new JPanel();
		pieChartsPanel.setLayout(new BoxLayout(pieChartsPanel, BoxLayout.LINE_AXIS));
		
		pieChartsPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		observer = new ElectionPieChartPanel(current, EPieChartType.CANDIDATE);
		addIObserver(observer);
		pieChartsPanel.add((Component) observer);
		
		pieChartsPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		observer = new ElectionPieChartPanel(current, EPieChartType.PARTI);
		addIObserver(observer);
		pieChartsPanel.add((Component) observer);
		
		contentPane.add(pieChartsPanel, BorderLayout.CENTER);
		monitorPanel = new ElectionsMonitorPanel(this);
		contentPane.add(monitorPanel, BorderLayout.SOUTH);
		setLocation((screenSize.width / 4), (screenSize.height / 4));
		pack();
		setVisible(true);
	}
	
	@Override
	public void addIObserver(IObserver observer) {
		if (!observers.contains(observer)) {
			observers.add(observer);
		}
	}

	@Override
	public void monitor() {
		monitor.monitor(current);
	}

	@Override
	public void observe(String code) {
		Election el = null;
		try {
			monitorPanel.stopMonitoring();
			current = code;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(NamedQueriesConstants.ELECTION_BY_CODE_QUERY_PARAM, code);
			el = (Election) electionsService.getPersistable(NamedQueriesConstants.ELECTION_BY_CODE_QUERY_NAME, params);
			for (IObserver ob : observers) {
				ob.notify(el);
			}
		} catch (PersistException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stopMonitoring() {
		monitor.stopMonitoring();
	}

	@Override
	public void update(Election update) {
		for (IObserver ob : observers) {
			ob.notify(update);
		}
	}
}
