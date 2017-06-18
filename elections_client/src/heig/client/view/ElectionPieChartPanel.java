package heig.client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import heig.client.view.metier.PieSlice;
import heig.metier.entite.Candidat;
import heig.metier.entite.Election;
import heig.metier.entite.Parti;
import heig.metier.entite.Vote;

public class ElectionPieChartPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6976349614393757764L;
	
	private EPieChartType type;
	
	private Election election;
	
	private List<PieSlice> slices;
	
	public ElectionPieChartPanel(Election election, EPieChartType type) {
		super();
		this.election = election;
		this.slices = getElectionSlices(election, type);
		this.type = type;
	}
	
	private void drawElectionData(Rectangle area, Graphics g) {
		synchronized (slices) {
			int count = 0;
			int offsetY = 30;
			int offsetX = 25;
			double total = election.getVotes().size();
			double percentage = 0.0;
			String msg = null;
			g.setColor(Color.BLACK);
			Font font = g.getFont();
			Font newFont = new Font(font.getFontName(), Font.BOLD, font.getSize() + 5);
			g.setFont(newFont);
			if (type.equals(EPieChartType.CANDIDATE)) {
				g.drawString("Résultats des candidats :", 25, (area.height / 12) + ((area.height / 3) * 2));
			}
			else if (type.equals(EPieChartType.PARTI)) {
				g.drawString("Résultats des partis :", 25, (area.height / 12) + ((area.height / 3) * 2));
			}
			else {
				g.drawString("Type d'affichage non supporté :", 25, (area.height / 12) + ((area.height / 3) * 2));
			}
			
			for (PieSlice ps : slices) {
				percentage = Double.valueOf((double)((ps.getVotes().size() * 100) / total));
				percentage = (int) (percentage * 100);
				percentage /= 100;
				if (type.equals(EPieChartType.CANDIDATE)) {
					msg = ps.getVotes().get(0).getCandidat().getPrenom() + " : " + percentage + "%";
				}
				else if (type.equals(EPieChartType.PARTI)) {
					msg = ps.getVotes().get(0).getCandidat().getParti().getNom() + " : " + percentage + "%";
				}
				else {
					msg = "";
				}
				g.setColor(ps.getColor());
				g.fillRect(offsetX, ((area.height / 12) + ((area.height / 3) * 2)) + offsetY - 10, 10, 10);
				g.setColor(Color.BLACK);
				g.drawString(msg, offsetX + 15, ((area.height / 12) + ((area.height / 3) * 2)) + offsetY);
				offsetY += 20;
				count++;
				if (count % 6 == 0) { // max 6 results per column
					count = 0;
					offsetY = 30;
					offsetX += 135;
				}
			}
		}
	}
	
	private void drawPieChart(Rectangle area, Graphics g) {
		synchronized (slices) {
			Integer total = election.getVotes().size();
			Integer current = 0;
			Integer startAngle = 0;
			Integer arcAngle = 0;
			for (PieSlice ps : slices) {
				startAngle = (current * 360) / total;
				arcAngle = (ps.getVotes().size() * 360) / total;
				g.setColor(ps.getColor());
				g.fillArc(area.width / 12, area.height / 12, (area.width / 3) * 2, (area.height / 3) * 2, startAngle, arcAngle);
				current += ps.getVotes().size();
			}
		}
	}
	
	private void drawViewFrame(Rectangle area, Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(10, 10, area.width - 100, 5);
		g.fillRect(10, 10, 5, area.height - 100);
		g.fillRect(area.width - 95, 10, 5, area.height - 105);
		g.fillRect(10, area.height - 95, area.width - 100, 5);
		Font font = g.getFont();
		Font newFont = new Font(font.getFontName(), Font.BOLD, font.getSize() + 15);
		g.setFont(newFont);
		if (type.equals(EPieChartType.CANDIDATE)) {
			g.drawString("Résultats par candidat(s)", 20, font.getSize() + 25);
		} else if (type.equals(EPieChartType.PARTI)) {
			g.drawString("Résultats par parti(s)", 20, font.getSize() + 25);
		} else {
			g.drawString("Type d'affichage non supporté", 20, font.getSize() + 25);
		}
		g.setFont(font);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Rectangle area = getBounds();
		drawViewFrame(area, g);
		drawPieChart(area, g);
		drawElectionData(area, g);
	}
	
	public List<PieSlice> getElectionSlices(Election election, EPieChartType type) {
		List<PieSlice> result = new ArrayList<>();
		if (type.equals(EPieChartType.CANDIDATE)) {
			Map<Candidat, List<Vote>> votes = new HashMap<Candidat, List<Vote>>();
			List<Vote> tmp = null;
			for (Vote v : election.getVotes()) {
				if (!votes.containsKey(v.getCandidat())) {
					tmp = new ArrayList<>();
				}
				else {
					tmp = votes.get(v.getCandidat());
				}
				tmp.add(v);
				votes.put(v.getCandidat(), tmp);
			}
			Color colors[] = {Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.WHITE, Color.CYAN, Color.DARK_GRAY, Color.YELLOW, Color.MAGENTA, Color.ORANGE};
			int count = 0;
			for (List<Vote> entry : votes.values()) {
				PieSlice ps = new PieSlice(colors[count % 10], entry);
				result.add(ps);
				count++;
			}
		}
		else if (type.equals(EPieChartType.PARTI)) {
			Map<Parti, List<Vote>> votes = new HashMap<Parti, List<Vote>>();
			List<Vote> tmp = null;
			for (Vote v : election.getVotes()) {
				if (!votes.containsKey(v.getCandidat().getParti())) {
					tmp = new ArrayList<>();
				}
				else {
					tmp = votes.get(v.getCandidat().getParti());
				}
				tmp.add(v);
				votes.put(v.getCandidat().getParti(), tmp);
			}
			Color colors[] = {Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.WHITE, Color.CYAN, Color.DARK_GRAY, Color.YELLOW, Color.MAGENTA, Color.ORANGE};
			int count = 0;
			for (List<Vote> entry : votes.values()) {
				PieSlice ps = new PieSlice(colors[count % 10], entry);
				result.add(ps);
				count++;
			}
		}
		return result;
	}
}
