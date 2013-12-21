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
public interface HTMLHeader {

	String BEGIN_ORIGINAL_WIKI_TEXT = "<!--begin_original_wiki_text";
	String BEGIN_WIKI_TEXT = "<!--begin_wiki_text";
	String END_ORIGINAL_WIKI_TEXT = "end_original_wiki_text-->";
	String END_WIKI_TEXT = "end_wiki_text-->";
	String HTML_PREFIX_1 = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
	String HTML_PREFIX_2 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">"
			+ "\n"
			+ "\n<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\">"
			+ "\n<head>"
			+ "\n  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"
			+ "\n  <title></title>"
			+ "\n</head>"
			+ "\n<body>"
			+ "\n  <div>"
			+ "\n\n";
	String HTML_SUFFIX = "\n\n  </div>" + "\n</body>" + "\n</html>\n\n";

}
