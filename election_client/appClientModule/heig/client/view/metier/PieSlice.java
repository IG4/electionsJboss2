package heig.client.view.metier;

import java.awt.Color;
import java.util.List;

import heig.metier.entite.Candidat;
import heig.metier.entite.Vote;

public class PieSlice {
	
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
	
}
