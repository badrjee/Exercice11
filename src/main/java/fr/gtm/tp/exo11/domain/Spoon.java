package fr.gtm.tp.exo11.domain;

public class Spoon  extends Ustensil{

	private Float lenght;
	
	//getter setter

	public Float getLenght() {
		return lenght;
	}

	public void setLenght(Float lenght) {
		this.lenght = lenght;
	}

	@Override
	public String toString() {
	
		return super.toString().replaceAll("Ustensiles", "Cuill�re");
	}

	@Override
	public void setScore(Object obj) {
		this.setLenght(Float.parseFloat(obj.toString()));
		
	}
	
	
}
