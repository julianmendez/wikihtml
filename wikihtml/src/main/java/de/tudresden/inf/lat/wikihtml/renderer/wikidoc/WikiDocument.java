/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.wikidoc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import de.tudresden.inf.lat.wikihtml.common.HTMLHeader;
import de.tudresden.inf.lat.wikihtml.common.WikiCons;
import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.RenderedToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;
import de.tudresden.inf.lat.wikihtml.renderer.common.TokenType;
import de.tudresden.inf.lat.wikihtml.renderer.common.WikiLineToken;
import de.tudresden.inf.lat.wikihtml.renderer.global.InverseNowikiRenderer;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class WikiDocument {

	private final boolean isWikiHTML;
	private final Renderer renderer = new WikiDocumentRenderer();
	private final List<String> wikiDocument;

	public WikiDocument() {
		this.isWikiHTML = false;
		this.wikiDocument = new ArrayList<>();
	}

	public WikiDocument(Reader reader) throws IOException {
		Objects.requireNonNull(reader);

		List<String> listOfLines = readDocument(reader);
		this.isWikiHTML = isWikiHTMLDocument(listOfLines);
		if (this.isWikiHTML) {
			listOfLines = extractWikiText(listOfLines);
		}
		this.wikiDocument = listOfLines;
	}

	public WikiDocument(String document) {
		Objects.requireNonNull(document);

		StringReader reader = new StringReader(document);
		List<String> listOfLines = null;
		listOfLines = readDocument(reader);
		this.isWikiHTML = isWikiHTMLDocument(listOfLines);
		if (this.isWikiHTML) {
			listOfLines = extractWikiText(listOfLines);
		}
		this.wikiDocument = listOfLines;
	}

	@Override
	public boolean equals(Object o) {
		boolean ret = (this == o);
		if (!ret && (o instanceof WikiDocument)) {
			WikiDocument other = (WikiDocument) o;
			ret = this.wikiDocument.equals(other.wikiDocument);
		}
		return ret;
	}

	private List<String> extractWikiText(List<String> document) {
		List<String> ret = new ArrayList<>();
		boolean extracting = false;
		for (String line : document) {
			if (line.toLowerCase().startsWith(HTMLHeader.BEGIN_WIKI_TEXT.toLowerCase())) {
				extracting = true;

			} else if (line.toLowerCase().startsWith(HTMLHeader.END_WIKI_TEXT.toLowerCase())) {
				extracting = false;

			} else if (extracting) {
				ret.add(line);

			}
		}
		return ret;
	}

	public String getFormattedWikiDocument() {
		return getWikiDocument(renderWikiDocument());
	}

	public String getOriginalWikiDocument() {
		StringBuffer sbuf = new StringBuffer();
		for (String line : this.wikiDocument) {
			sbuf.append(WikiCons.NEW_LINE);
			sbuf.append(line);
		}
		return sbuf.toString();
	}

	private String getRenderedDocument(List<ConversionToken> list) {
		StringBuffer sbuf = new StringBuffer();
		for (ConversionToken token : list) {
			String text = token.getHTMLText();
			sbuf.append(text);
		}
		return sbuf.toString();
	}

	public List<String> getWikiDocument() {
		return Collections.unmodifiableList(this.wikiDocument);
	}

	private String getWikiDocument(List<ConversionToken> list) {
		StringBuffer sbuf = new StringBuffer();
		for (ConversionToken token : list) {
			String text = token.getWikiText();
			sbuf.append(text);
		}
		return sbuf.toString();
	}

	@Override
	public int hashCode() {
		return this.wikiDocument.hashCode();
	}

	public boolean isWikiHTML() {
		return this.isWikiHTML;
	}

	private boolean isWikiHTMLDocument(List<String> document) {
		boolean ret = false;
		Iterator<String> it = document.iterator();
		while (!ret && it.hasNext()) {
			String line = it.next();
			ret = ret || line.toLowerCase().startsWith(HTMLHeader.BEGIN_WIKI_TEXT.toLowerCase());
		}
		return ret;
	}

	private List<ConversionToken> prepareDocument(List<String> document) {
		List<ConversionToken> ret = new ArrayList<>();
		for (String line : document) {
			ret.add(new WikiLineToken(line));
		}
		return ret;
	}

	private List<String> readDocument(Reader input) {
		List<String> ret = new ArrayList<>();
		BufferedReader reader = new BufferedReader(input);
		reader.lines().forEach(line -> ret.add(line));
		return ret;
	}

	private List<ConversionToken> renderDocument(List<ConversionToken> document) {
		List<ConversionToken> ret = new ArrayList<>();
		for (ConversionToken line : document) {
			ret.addAll(this.renderer.render(line));
		}
		return ret;
	}

	private void renderHTML(BufferedWriter writer, boolean withWikiText, boolean withOriginalText) throws IOException {
		writer.write(HTMLHeader.HTML_PREFIX_1);
		writer.newLine();

		if (withWikiText) {
			writer.write(HTMLHeader.BEGIN_WIKI_TEXT);
			writer.write(sanitizeHTMLComment(getFormattedWikiDocument()));
			writer.newLine();
			writer.write(HTMLHeader.END_WIKI_TEXT);
			writer.newLine();
		}

		writer.newLine();
		writer.write(HTMLHeader.HTML_PREFIX_2);
		writer.write(renderToString());
		writer.newLine();

		if (withOriginalText) {
			writer.write(HTMLHeader.BEGIN_ORIGINAL_WIKI_TEXT);
			writer.write(sanitizeHTMLComment(getOriginalWikiDocument()));
			writer.newLine();
			writer.write(HTMLHeader.END_ORIGINAL_WIKI_TEXT);
			writer.newLine();
		}

		writer.write(HTMLHeader.HTML_SUFFIX);
		writer.flush();

	}

	public void renderHTML(Writer output) throws IOException {
		renderHTML(new BufferedWriter(output), true, false);
	}

	public void renderHTMLWithOriginalText(Writer output) throws IOException {
		renderHTML(new BufferedWriter(output), true, true);
	}

	public void renderText(Writer output) throws IOException {
		Objects.requireNonNull(output);

		BufferedWriter writer = new BufferedWriter(output);
		writer.write(renderToString());
		writer.flush();
	}

	public String renderToString() {
		return getRenderedDocument(renderWikiDocument());
	}

	private List<ConversionToken> renderWikiDocument() {
		return revertNowikiTag(renderWikiTokens(renderDocument(prepareDocument(this.wikiDocument))));
	}

	private List<ConversionToken> renderWikiTokens(List<ConversionToken> document) {
		List<ConversionToken> ret = new ArrayList<>();
		for (ConversionToken token : document) {
			if (token.getType().equals(TokenType.HTML_TEXT)) {
				ret.add(token);
			} else {
				ret.add(new RenderedToken(token.getWikiText(), token.getWikiText()));
			}
		}
		return ret;
	}

	private List<ConversionToken> revertNowikiTag(List<ConversionToken> document) {
		List<ConversionToken> ret = new ArrayList<>();
		InverseNowikiRenderer inverseNowikiRenderer = new InverseNowikiRenderer();
		for (ConversionToken token : document) {
			ret.addAll(inverseNowikiRenderer.render(token));
		}
		return ret;
	}

	private String sanitizeHTMLComment(String str) {
		String ret = str;
		ret = ret.replaceAll(WikiCons.HYPHEN_MINUS + WikiCons.HYPHEN_MINUS,
				WikiCons.HYPHEN_MINUS_U + WikiCons.HYPHEN_MINUS_U);
		return ret;
	}

	@Override
	public String toString() {
		StringWriter ret = new StringWriter();
		try {
			BufferedWriter writer = new BufferedWriter(ret);
			for (String line : this.wikiDocument) {
				writer.write(line);
				writer.newLine();
			}
			writer.flush();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		return ret.toString();
	}

}
