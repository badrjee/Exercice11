package fr.gtm.tp.exo11.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gtm.tp.exo11.domain.CirclePlate;
import fr.gtm.tp.exo11.domain.Spoon;
import fr.gtm.tp.exo11.domain.SquarePlate;
import fr.gtm.tp.exo11.domain.Ustensil;

public class CsvParser implements Parser {

	private static final Logger LOGGER = LoggerFactory.getLogger(CsvParser.class);
	private InputStream inputData;
	private Map<HEADER, Integer> headerMap;

	public List<Ustensil> parse() {
		List<Ustensil> result = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.inputData))) {
			String line;
			LOGGER.debug("Début lecture du flux : ");
			boolean firstline = true;
			while ((line = reader.readLine()) != null) {
				if (firstline) {
					this.buildHeaders(line);
					firstline = false;
				} else {
					this.buildUstensil(line);
				}
				LOGGER.debug(line);
			}
		} catch (Exception e) {
			LOGGER.error("erreur pendant la lecture du fichier:", e);
			System.exit(-1);
		}
		return result;
	}

	private Ustensil buildUstensil(String line) {
		Ustensil ustensil = null;
		final String[] data = line.split(",");
		// construire une instence avec la colonne Type
		final String type = data[this.headerMap.get(HEADER.TYPE)];
		switch (type) {
		case "Assiette ronde":
			ustensil = new CirclePlate();
			break;
		case "cuillière":
			ustensil = new Spoon();
			break;
		case "Assiette carré":
			ustensil = new SquarePlate();
			break;
		default:
			// Cas d'erreur aucune correspondence avec un ustensil connu de l'application
			LOGGER.error("Cannot transform[{}] into ustensil, not a valid type");
			break;
		}
		// recuperer et memoriser l'année de fabrication
		final int year = Integer.parseInt(data[this.headerMap.get(HEADER.YEAR)]);
		ustensil.setYear(LocalDate.of(year, 1, 1));
		// recuperer et memoriser les dimensions des objets
		ustensil.setScore(data[this.headerMap.get(HEADER.SCORE)]);
		return ustensil;

	}

	private void buildHeaders(String line) {
		this.headerMap = new HashMap<>();
		final List<String> headers = Arrays.asList(line.split(","));
		final List<String> found = new ArrayList<>();
		for (final HEADER header : HEADER.values()) {
			final int index = headers.indexOf(header.column);
			if (index >= 0) {
				// colonne retrouver dans le csv
				this.headerMap.put(header, index);
				found.add(header.column);
			} else {
				// colonne obligatoire manquante !
				LOGGER.error("Colone {} manquante dans le fichier CSV, Impossible de Continuer les traitements",
						header.column);
				System.exit(1);
			}
		}
		if (headers.size() != found.size()) {
			final List<String>unused = new ArrayList<>(headers);
			unused.removeAll(found);
			for (final String unknownHeader : headers) {
				LOGGER.warn("la colonne [{}] n'est pas reconnue dans l'application", unknownHeader);

			}

		}
	}

	public void localData(String path, boolean virtual) {
		try {
			if (virtual) {
				this.loadDataVirtual(path);

			} else {
				this.loadDataHDD(path);
			}
		} catch (IOException e) {
			String msg = "Parser cannot load data";
			LOGGER.error(msg, e);
			throw new IllegalArgumentException(msg, e);
		}
	}

	private void loadDataHDD(String path) throws IOException {
		final File csvFile = new File(path);
		if (csvFile.exists() && csvFile.canRead()) {
			this.inputData = new FileInputStream(csvFile);
		} else {
			throw new IOException(
					"Impossible d'executer le parseur sur un fichier non existant  ou sans les droits de lecture");
		}

	}

	public void loadDataVirtual(String path) throws IOException {
		final InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
		if (is != null) {
			this.inputData = is;

		} else {
			throw new IOException("Impossible de trouver le fichier dans le classpath");
		}

	}

}
