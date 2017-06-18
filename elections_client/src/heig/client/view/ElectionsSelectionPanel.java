package heig.client.view;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import heig.metier.entite.Election;

public class ElectionsSelectionPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5637923506082741590L;

	private JComboBox<String> jcbElections;
	
	public ElectionsSelectionPanel(Election current, List<Election> elections) {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		int index = 0;
		// place combo label
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(new JLabel(elections.size() + " élection(s) disponible(s) : "));
		// get combo entries and selection
		List<String> elements = new ArrayList<String>(elections.size());
		for (int i = 0; i < elections.size(); i++) {
			Election el = elections.get(i);
			elements.add(el.getCode());
			if (current != null && current.equals(el)) {
				index = i;
			}
		}
		Collections.sort(elements);
		// place combo
		add(Box.createRigidArea(new Dimension(10, 10)));
		jcbElections = new JComboBox<String>(elements.toArray(new String[elements.size()]));
		jcbElections.setMaximumSize(jcbElections.getPreferredSize());
		jcbElections.setSelectedIndex(index);
		add(jcbElections);
		// place election data
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(new JLabel("Code : "));
		add(Box.createRigidArea(new Dimension(2, 0)));
		add(new JLabel(current.getCode()));
		
		add(Box.createRigidArea(new Dimension(40, 10)));
		add(new JLabel("Nom : "));
		add(Box.createRigidArea(new Dimension(2, 0)));
		add(new JLabel(current.getNom()));
		
		add(Box.createRigidArea(new Dimension(40, 10)));
		add(new JLabel("Candidats : "));
		add(Box.createRigidArea(new Dimension(2, 0)));
		add(new JLabel(String.valueOf(current.getCandidats().size())));
		
		add(Box.createRigidArea(new Dimension(40, 10)));
		add(new JLabel("Electeurs : "));
		add(Box.createRigidArea(new Dimension(2, 0)));
		add(new JLabel(String.valueOf(current.getElecteurs().size())));
		
		add(Box.createRigidArea(new Dimension(40, 10)));
		add(new JLabel("Votes : "));
		add(Box.createRigidArea(new Dimension(2, 0)));
		add(new JLabel(String.valueOf(current.getVotes().size())));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		add(Box.createRigidArea(new Dimension(40, 10)));
		add(new JLabel("Date de début : "));
		add(Box.createRigidArea(new Dimension(2, 0)));
		add(new JLabel(sdf.format(current.getDebut())));
		
		add(Box.createRigidArea(new Dimension(40, 10)));
		add(new JLabel("Date de fin : "));
		add(Box.createRigidArea(new Dimension(2, 0)));
		add(new JLabel(sdf.format(current.getFin())));
		add(Box.createHorizontalGlue());
		add(Box.createRigidArea(new Dimension(2, 0)));
	}
}
