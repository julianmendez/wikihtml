/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.common;

/**
 * 
 * @author Julian Mendez
 * 
 */
public interface HTMLTag {

	String ANL = "\n";
	String BOLD_END = "</b>";
	String BOLD_START = "<b>";
	String BR = "<br />" + ANL;
	String CELL_END = "</td>" + ANL;
	String CELL_START = "<td>";
	String DD_END = "</dd>" + ANL;
	String DD_START = "<dd>";
	String DL_END = "</dl>" + ANL;
	String DL_START = ANL + "<dl>" + ANL;
	String H1_END = "</h1>" + ANL;
	String H1_START = ANL + "<h1>";
	String H2_END = "</h2>" + ANL;
	String H2_START = ANL + "<h2>";
	String H3_END = "</h3>" + ANL;
	String H3_START = ANL + "<h3>";
	String H4_END = "</h4>" + ANL;
	String H4_START = ANL + "<h4>";
	String H5_END = "</h5>" + ANL;
	String H5_START = ANL + "<h5>";
	String H6_END = "</h6>" + ANL;
	String H6_START = ANL + "<h6>";
	String HR = ANL + "<hr />" + ANL;
	String ITALICS_BOLD_END = "</b></i>";
	String ITALICS_BOLD_START = "<i><b>";
	String ITALICS_END = "</i>";
	String ITALICS_START = "<i>";
	String LI_END = "</li>" + ANL;
	String LI_START = "<li>";
	String NOWIKI_END = "</nowiki>";
	String NOWIKI_START = "<nowiki>";
	String OL_END = "</ol>" + ANL;
	String OL_START = ANL + "<ol>" + ANL;
	String ROW_END = "</tr>" + ANL;
	String ROW_START = "<tr>" + ANL;
	String SPACE = " ";
	String TABLE_END = "</table>" + ANL;
	String TABLE_START_POST = " >" + ANL;
	String TABLE_START_PRE = ANL + "<table summary=\"\" ";
	String UL_END = "</ul>" + ANL;
	String UL_START = ANL + "<ul>" + ANL;

}
