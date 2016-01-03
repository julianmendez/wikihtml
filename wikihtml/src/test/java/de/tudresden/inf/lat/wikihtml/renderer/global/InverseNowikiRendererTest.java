/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.global;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.RenderedToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class InverseNowikiRendererTest extends TestCase {

	public void testAllSymbolsTogether() {
		Renderer renderer = new InverseNowikiRenderer();
		String escapedText = "&#x0023;&#x0026;&#x0027;&#x002A;&#x002C;&#x002D;&#x002F;&#x003A;&#x003D;&#x005C;&#x007B;&#x007C;&#x007D;&#x007E;";
		ConversionToken token = new RenderedToken(escapedText, escapedText);
		List<ConversionToken> expected = new ArrayList<>();
		String renderedText = "<nowiki>#&'*,-/:=\\{|}~</nowiki>";
		expected.add(new RenderedToken(renderedText, escapedText));
		List<ConversionToken> output = renderer.render(token);
		assertEquals(expected, output);
	}

	public void testSymbolBySymbol() {
		tryOneSymbol("&#x0023;", "#");
		tryOneSymbol("&#x0026;", "&");
		tryOneSymbol("&#x0027;", "'");
		tryOneSymbol("&#x002A;", "*");
		tryOneSymbol("&#x002C;", ",");
		tryOneSymbol("&#x002D;", "-");
		tryOneSymbol("&#x002F;", "/");
		tryOneSymbol("&#x003A;", ":");
		tryOneSymbol("&#x003D;", "=");
		tryOneSymbol("&#x005C;", "\\");
		tryOneSymbol("&#x007B;", "{");
		tryOneSymbol("&#x007C;", "|");
		tryOneSymbol("&#x007D;", "}");
		tryOneSymbol("&#x007E;", "~");
	}

	private void tryOneSymbol(String htmlSymbol, String nowikiSymbol) {
		Renderer renderer = new InverseNowikiRenderer();
		ConversionToken token = new RenderedToken(htmlSymbol, htmlSymbol);
		List<ConversionToken> expected = new ArrayList<>();
		String renderedText = "<nowiki>" + nowikiSymbol + "</nowiki>";
		expected.add(new RenderedToken(renderedText, htmlSymbol));
		List<ConversionToken> output = renderer.render(token);
		assertEquals(expected, output);
	}

}
