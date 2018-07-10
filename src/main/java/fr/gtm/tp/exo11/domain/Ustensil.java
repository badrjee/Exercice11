package fr.gtm.tp.exo11.domain;

import java.time.LocalDate;

public abstract class Ustensil {
	
	private LocalDate year;
	
	public abstract void setScore(Object obj);
	
	//methode pour calculer la valeur de l'ustensil
	public int calculValue(LocalDate current) {
		final int age= current.getYear() - this.year.getYear();
		return age >50 ? age-50 : 0;
	}

	//getter et setter
	public LocalDate getYear() {
		return year;
	}

	public void setYear(LocalDate year) {
		this.year = year;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Fabrication ->");
		sb.append(this.year);
		return sb.toString();
		}
	
	

}
