/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.line;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.RenderedToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;
import de.tudresden.inf.lat.wikihtml.renderer.common.WikiLineToken;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class LineBreakRendererTest extends TestCase {

	public LineBreakRendererTest() {
	}

	public void testEmptyLine() {
		Renderer renderer = new LineBreakRenderer();
		ConversionToken token = new WikiLineToken("");
		List<ConversionToken> expected = new ArrayList<>();
		String renderedText = "<br />\n";
		expected.add(new RenderedToken("\n", renderedText));
		List<ConversionToken> output = renderer.render(token);
		assertEquals(expected, output);
	}

	public void testLineWithSpacesAndTabs() {
		Renderer renderer = new LineBreakRenderer();
		ConversionToken token = new WikiLineToken("   \t   \t ");
		List<ConversionToken> expected = new ArrayList<>();
		String renderedText = "<br />\n";
		expected.add(new RenderedToken("\n", renderedText));
		List<ConversionToken> output = renderer.render(token);
		assertEquals(expected, output);
	}

}
