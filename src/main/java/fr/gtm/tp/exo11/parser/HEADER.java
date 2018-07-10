package fr.gtm.tp.exo11.parser;

public enum HEADER {
	TYPE("Type"),YEAR("Année"), SCORE("Cote");

	public final String column;
	
	private HEADER(String column) {
		this.column = column;
	}
}
