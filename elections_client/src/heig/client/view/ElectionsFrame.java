package heig.client.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
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
	
	public ElectionsFrame(List<Election> elections) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Visualisation des résultats des éléctions");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
		current = elections.get(0);
		Container contentPane = getContentPane();
		contentPane.add(new ElectionsSelectionPanel(current, elections), BorderLayout.NORTH);
		JPanel pieChartsPanel = new JPanel();
		pieChartsPanel.setLayout(new BoxLayout(pieChartsPanel, BoxLayout.LINE_AXIS));
		pieChartsPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		pieChartsPanel.add(new ElectionPieChartPanel(current,  EPieChartType.CANDIDATE));
		pieChartsPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		pieChartsPanel.add(new ElectionPieChartPanel(current, EPieChartType.PARTI));
		contentPane.add(pieChartsPanel, BorderLayout.CENTER);
		setLocation((screenSize.width / 4), (screenSize.height / 4));
		pack();
		setVisible(true);
	}
}
