package fr.gtm.tp.exo11.domain;

public class SquarePlate extends Ustensil implements Plate {
	
	private Float score;

	public float calculArea() {
		
		return (float) (Math.pow(this.score, 2));
	}

	@Override
	public void setScore(Object obj) {
		
		this.score = Float.parseFloat(obj.toString());
		
	}

	public Float getScore() {
		return score;
	}

}
