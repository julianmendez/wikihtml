/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import de.tudresden.inf.lat.wikihtml.renderer.wikidoc.WikiDocument;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class Main {

	public static final String HELP = "\nArguments: "
			+ "\n  [<input file>] <output file>"
			+ "\n\nwhere:"
			+ "\n  <input file>          input file: a wiki text file or a wiki HTML file"
			+ "\n  <output file>         output file: a wiki HTML file"
			+ "\n\nNote: "
			+ "\n  The input file is optional only when updating a wiki HTML file."
			+ "\n\n";

	public static final void main(String[] args) throws IOException {
		(new Main()).run(args);
	}

	private boolean showHelp = false;

	public void run(String[] args) throws IOException {
		Objects.requireNonNull(args);
		if ((args.length == 1) || (args.length == 2)) {

			// System.out.println((new
			// WikiDocumentRenderer()).getDescription());

			String inputFile = args[0];
			WikiDocument wikiDocument = new WikiDocument(new FileReader(
					inputFile));
			String outputFile = null;
			if (args.length == 2) {
				outputFile = args[1];
			} else if ((args.length == 1) && wikiDocument.isWikiHTML()) {
				outputFile = args[0];
			}
			if (outputFile != null) {
				wikiDocument.renderHTML(new FileWriter(outputFile));
			} else {
				this.showHelp = true;
			}

		} else {
			this.showHelp = true;
		}
		if (this.showHelp) {
			System.out.println(HELP);
		}
	}

}
