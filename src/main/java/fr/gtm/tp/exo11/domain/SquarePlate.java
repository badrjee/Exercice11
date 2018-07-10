package fr.gtm.tp.exo11.domain;

public class SquarePlate extends Ustensil implements Plate {
	
	private Float cote;

	public float calculArea() {
		
		return (float) (Math.pow(this.cote, 2));
	}

}
