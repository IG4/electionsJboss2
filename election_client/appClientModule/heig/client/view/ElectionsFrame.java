package heig.client.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import heig.metier.entite.Election;

public class ElectionsFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8287533311669351378L;

	private Election current;
	
	private List<Election> elections;
	
	private ElectionPieChartPanel pieChartCandidate;
	
	private ElectionPieChartPanel pieChartParti;
	
	private ElectionsSelectionPanel selection;
	
	public ElectionsFrame(List<Election> elections) {
		Collections.sort(elections, new Comparator<Election>() {
			@Override
			public int compare(Election o1, Election o2) {
				return o1.getCode().compareTo(o2.getCode());
			}
			
		});
		this.elections = elections;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Visualisation des résultats des éléctions");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
		current = elections.get(0);
		Container contentPane = getContentPane();
		selection = new ElectionsSelectionPanel(this, current, elections);
		contentPane.add(selection, BorderLayout.NORTH);
		JPanel pieChartsPanel = new JPanel();
		pieChartsPanel.setLayout(new BoxLayout(pieChartsPanel, BoxLayout.LINE_AXIS));
		pieChartsPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		pieChartCandidate = new ElectionPieChartPanel(current,  EPieChartType.CANDIDATE);
		pieChartsPanel.add(pieChartCandidate);
		pieChartsPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		pieChartParti = new ElectionPieChartPanel(current, EPieChartType.PARTI);
		pieChartsPanel.add(pieChartParti);
		contentPane.add(pieChartsPanel, BorderLayout.CENTER);
		setLocation((screenSize.width / 4), (screenSize.height / 4));
		pack();
		setVisible(true);
	}
	
	public void selectElection(String code) {
		Election result  = null;
		for (Election el : elections) {
			if (el.getCode().equals(code)) {
				result = el;
				break;
			}
		}
		if (result != null) {
			pieChartCandidate.updateElection(result);
			pieChartParti.updateElection(result);
			selection.updateElection(result);
		}
		else {
			System.out.println("Election with code : " + code + ". Not found");
		}
	}
}
