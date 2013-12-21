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
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;
import de.tudresden.inf.lat.wikihtml.renderer.common.WikiLineToken;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class NowikiRendererTest extends TestCase {

	public void testTextWithIgnoredFormat() {
		Renderer renderer = new NowikiRenderer();
		ConversionToken input = new WikiLineToken(
				"<nowiki>'''text with ignored format'''</nowiki>");
		List<ConversionToken> expected = new ArrayList<ConversionToken>();
		expected.add(new WikiLineToken(
				"&#x0027;&#x0027;&#x0027;text with ignored format&#x0027;&#x0027;&#x0027;"));
		List<ConversionToken> output = renderer.render(input);
		assertEquals(expected, output);
	}

	public void testTextWithManyLines() {
		Renderer renderer = new NowikiRenderer();
		ConversionToken input1 = new WikiLineToken("<nowiki>text with spaces");
		ConversionToken input2 = new WikiLineToken("and '''new''' lines");
		ConversionToken input3 = new WikiLineToken(" as well</nowiki>");
		List<ConversionToken> expected = new ArrayList<ConversionToken>();
		expected.add(new WikiLineToken("text with spaces"));
		expected.add(new WikiLineToken(
				"and &#x0027;&#x0027;&#x0027;new&#x0027;&#x0027;&#x0027; lines"));
		expected.add(new WikiLineToken(" as well"));
		List<ConversionToken> output = new ArrayList<ConversionToken>();
		output.addAll(renderer.render(input1));
		output.addAll(renderer.render(input2));
		output.addAll(renderer.render(input3));
		assertEquals(expected, output);
	}

	public void testTextWithNewLines() {
		Renderer renderer = new NowikiRenderer();
		ConversionToken input = new WikiLineToken(
				"<nowiki>text with spaces\nand new lines\n as well</nowiki>");
		List<ConversionToken> expected = new ArrayList<ConversionToken>();
		expected.add(new WikiLineToken(
				"text with spaces\nand new lines\n as well"));
		List<ConversionToken> output = renderer.render(input);
		assertEquals(expected, output);
	}

	public void testTextWithSpaces() {
		Renderer renderer = new NowikiRenderer();
		ConversionToken input = new WikiLineToken(
				"<nowiki>text with spaces</nowiki>");
		List<ConversionToken> expected = new ArrayList<ConversionToken>();
		expected.add(new WikiLineToken("text with spaces"));
		List<ConversionToken> output = renderer.render(input);
		assertEquals(expected, output);
	}

}
