package fr.gtm.tp.exo11.domain;

public class CirclePlate extends Ustensil implements Plate{
	private Float rayon;

	public float calculArea() {
		
		return (float) (Math.pow(this.rayon, 2)* Math.PI);
	}
	
	

}
