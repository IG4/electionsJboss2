package heig.view.pie;

import java.awt.Color;
import java.util.List;

import heig.entite.Candidat;
import heig.entite.Vote;

public class PieSlice implements Comparable<PieSlice> {
	
	private Color color;
	
	private List<Vote> votes;

	public PieSlice(Color color, List<Vote> votes) {
		super();
		this.color = color;
		this.votes = votes;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public List<Vote> getVotes() {
		return votes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((votes == null) ? 0 : votes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PieSlice other = (PieSlice) obj;
		if (votes == null) {
			if (other.votes != null)
				return false;
		} 
		else if (other.votes == null) {
			return false;
		}
		Candidat c = votes.get(0).getCandidat();
		Candidat c2 = other.votes.get(0).getCandidat();
		if (!c.equals(c2)) {
			return false;
		}
		
		return true;
	}

	@Override
	public int compareTo(PieSlice o) {
		if (o.votes.size() == votes.size()) {
			return o.votes.get(0).getCandidat().getNom().compareTo(votes.get(0).getCandidat().getNom());
		}
		return Integer.valueOf(o.votes.size()).compareTo(Integer.valueOf(votes.size()));
	}	
}
