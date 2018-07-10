package fr.gtm.tp.exo11.parser;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gtm.tp.exo11.domain.Ustensil;

public class Exo11 implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(Exo11.class);
	
	private void showUsage() {
		LOGGER.info("usage : exo11_path_to_data [virtual]");
		LOGGER.info("\t path_to_data: chemin physique du le disque dur ou virtuel dans la classpath ");
		LOGGER.info("\t virtual : true|false si le chemin est de type classpath");
		LOGGER.info("\t codes d'erreur :");
		LOGGER.info("\t\t 1 - ");
		LOGGER.info("\t\t 1 - Une colonne obligatiore est manquante dans le fichier CSV");
		LOGGER.info("\t\t 2 - ");
	}
	
	
	private String  path;
	private boolean virtual;
	public Exo11(String[] args) {
		if (args.length == 0) {
			this.showUsage();
			throw new IllegalArgumentException("impossible de lnacer l'application sans pramétres (c.f. usage)");
		}
		this.path= args[0];
		if(args.length >1) {
			this.virtual = Boolean.parseBoolean(args[1]);
		}
	}




	public static void main (String[] args) {
		new Exo11(args).run();
	}
	
	
	
	
	public void run() {
		final CsvParser parser = new CsvParser();
		parser.localData(this.path, this.virtual);
		List<Ustensil> toPrint = parser.parse();
		LOGGER.info("Données chargé par le parser");
		LOGGER.info(toPrint.toString());
		
	}

}
