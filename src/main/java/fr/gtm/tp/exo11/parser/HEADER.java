package fr.gtm.tp.exo11.parser;

public enum HEADER {
	TYPE("Type"),YEAR("Ann�e"), SCORE("Cote");

	public final String column;
	
	private HEADER(String column) {
		this.column = column;
	}
}
