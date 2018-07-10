package fr.gtm.tp.exo11.domain;

import java.time.LocalDate;

public abstract class Ustensil {
	
	private LocalDate year;
	
	//methode pour calculer la valeur de l'ustensil
	public int calculValue(LocalDate current) {
		return current.getYear() - this.year.getYear() -50;
	}

	public LocalDate getYear() {
		return year;
	}

	public void setYear(LocalDate year) {
		this.year = year;
	}
	
	

}
