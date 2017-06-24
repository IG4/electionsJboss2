package heig.view.interactive;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import heig.entite.Election;
import heig.monitor.IMonitorConsumer;

public class ElectionsMonitorPanel extends JPanel implements IMonitorConsumer {

	private static final long serialVersionUID = -559640232582863600L;
	
	private final String BTN_GO = "Monitorer";
	
	private final String BTN_STOP = "Stop";
	
	private JButton btn;
	
	private IMonitorConsumer parent;
	
	public ElectionsMonitorPanel(IMonitorConsumer parent) {
		super();
		this.parent = parent;
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		btn = new JButton(BTN_GO);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btn.getText().equals(BTN_GO)) {
					ElectionsMonitorPanel.this.monitor();
				}
				else {
					ElectionsMonitorPanel.this.stopMonitoring();
				}
			}
		});
		add(btn);
	}

	@Override
	public void monitor() {
		parent.monitor();
		btn.setText(BTN_STOP);
	}

	@Override
	public void stopMonitoring() {
		parent.stopMonitoring();
		btn.setText(BTN_GO);
	}

	@Override
	public void update(Election update) {
		// does nothing
	}
}
