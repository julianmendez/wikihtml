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
public class HorizontalLineGroupRendererTest extends TestCase {

	public HorizontalLineGroupRendererTest() {
	}

	public void testHorizontalLine() {
		Renderer renderer = new HorizontalLineGroupRenderer();
		String text = "----";
		ConversionToken token = new WikiLineToken(text);
		List<ConversionToken> expected = new ArrayList<ConversionToken>();
		expected.add(new RenderedToken("\n----", "\n<hr />\n"));
		List<ConversionToken> output = renderer.render(token);
		assertEquals(expected, output);
	}

	public void testHorizontalLineWithSpaces() {
		Renderer renderer = new HorizontalLineGroupRenderer();
		String text = "- - -";
		ConversionToken token = new WikiLineToken(text);
		List<ConversionToken> expected = new ArrayList<ConversionToken>();
		expected.add(new RenderedToken("\n- - -", "\n<hr />\n"));
		List<ConversionToken> output = renderer.render(token);
		assertEquals(expected, output);
	}

}