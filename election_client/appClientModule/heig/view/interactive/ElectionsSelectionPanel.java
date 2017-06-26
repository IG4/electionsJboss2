package heig.view.interactive;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import heig.entite.Election;
import heig.observer.IObservable;
import heig.observer.IObserver;

public class ElectionsSelectionPanel extends JPanel implements IObserver {

	private static final long serialVersionUID = -5637923506082741590L;
	
	private JComboBox<String> jcbElections;
	
	private JLabel lblCode, lblNom, lblCandidats, lblElecteurs, lblVotes, lblDebut, lblFin;
	
	private IObservable parent;
	
	public ElectionsSelectionPanel(IObservable parent, Election current, List<Election> elections) {
		this.parent = parent;
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		displaySelectionComboBox(current, elections);
		displayElectionData(current);
	}
	
	private void displaySelectionComboBox(Election current, List<Election> elections) {
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
		// place combo
		add(Box.createRigidArea(new Dimension(10, 10)));
		jcbElections = new JComboBox<String>(elements.toArray(new String[elements.size()]));
		jcbElections.setMaximumSize(jcbElections.getPreferredSize());
		jcbElections.setSelectedIndex(index);
		jcbElections.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ElectionsSelectionPanel.this.observe((String)ElectionsSelectionPanel.this.jcbElections.getSelectedItem());
			}
		});
		add(jcbElections);
	}
	
	private void displayElectionData(Election current) {
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(new JLabel("Code : "));
		add(Box.createRigidArea(new Dimension(2, 0)));
		lblCode = new JLabel(current.getCode());
		add(lblCode);
		
		add(Box.createRigidArea(new Dimension(40, 10)));
		add(new JLabel("Nom : "));
		add(Box.createRigidArea(new Dimension(2, 0)));
		lblNom = new JLabel(current.getNom());
		add(lblNom);
		
		add(Box.createRigidArea(new Dimension(40, 10)));
		add(new JLabel("Candidats : "));
		add(Box.createRigidArea(new Dimension(2, 0)));
		lblCandidats = new JLabel(String.valueOf(current.getCandidats().size()));
		add(lblCandidats);
		
		add(Box.createRigidArea(new Dimension(40, 10)));
		add(new JLabel("Electeurs : "));
		add(Box.createRigidArea(new Dimension(2, 0)));
		lblElecteurs = new JLabel(String.valueOf(current.getElecteurs().size()));
		add(lblElecteurs);
		
		add(Box.createRigidArea(new Dimension(40, 10)));
		add(new JLabel("Votes : "));
		add(Box.createRigidArea(new Dimension(2, 0)));
		lblVotes = new JLabel(String.valueOf(current.getVotes().size()));
		add(lblVotes);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		add(Box.createRigidArea(new Dimension(40, 10)));
		add(new JLabel("Date de début : "));
		add(Box.createRigidArea(new Dimension(2, 0)));
		lblDebut = new JLabel(sdf.format(current.getDebut()));
		add(lblDebut);
		
		add(Box.createRigidArea(new Dimension(40, 10)));
		add(new JLabel("Date de fin : "));
		add(Box.createRigidArea(new Dimension(2, 0)));
		lblFin = new JLabel(sdf.format(current.getFin()));
		add(lblFin);
		add(Box.createHorizontalGlue());
		add(Box.createRigidArea(new Dimension(2, 0)));
	}

	@Override
	public void notify(Election election) {
		lblCode.setText(election.getCode());
		lblNom.setText(election.getNom());
		lblCandidats.setText(String.valueOf(election.getCandidats().size()));
		lblElecteurs.setText(String.valueOf(election.getElecteurs().size()));
		lblVotes.setText(String.valueOf(election.getVotes().size()));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		lblDebut.setText(sdf.format(election.getDebut()));
		lblFin.setText(sdf.format(election.getFin()));
		repaint();
	}
	
	public void observe(String code) {
		parent.observe(code);
	}
}
