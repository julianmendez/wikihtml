/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.part;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.RenderedToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;
import de.tudresden.inf.lat.wikihtml.renderer.common.WikiLinePartToken;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class StyleGroupRendererTest {

	public StyleGroupRendererTest() {
	}

	@Test
	public void testBold() {
		tryText("'''bold'''", "'''", "", "<b>", "bold", "</b>", "");
		tryText("This is '''bold'''.", "'''", "This is ", "<b>", "bold", "</b>", ".");
	}

	@Test
	public void testItalics() {
		tryText("''italics''", "''", "", "<i>", "italics", "</i>", "");
		tryText("This is ''italics''.", "''", "This is ", "<i>", "italics", "</i>", ".");
	}

	@Test
	public void testItalicsAndBold() {
		tryText("'''''italics and bold'''''", "'''''", "", "<i><b>", "italics and bold", "</b></i>", "");
		tryText("This is '''''italics and bold'''''.", "'''''", "This is ", "<i><b>", "italics and bold", "</b></i>",
				".");
	}

	@Test
	public void testMultipleStyles() {
		String wikiText = "This text includes ''italics'' and '''bold'''.";

		Renderer renderer = new StyleGroupRenderer();
		ConversionToken token = new WikiLinePartToken(wikiText);
		List<ConversionToken> expected = new ArrayList<>();
		expected.add(new WikiLinePartToken("This text includes "));
		expected.add(new RenderedToken("''", "<i>"));
		expected.add(new WikiLinePartToken("italics"));
		expected.add(new RenderedToken("''", "</i>"));
		expected.add(new WikiLinePartToken(" and "));
		expected.add(new RenderedToken("'''", "<b>"));
		expected.add(new WikiLinePartToken("bold"));
		expected.add(new RenderedToken("'''", "</b>"));
		expected.add(new WikiLinePartToken("."));
		List<ConversionToken> output = renderer.render(token);
		Assertions.assertEquals(expected, output);
	}

	private void tryText(String wikiText, String wikiMarkup, String beforeText, String styleStart, String text,
			String styleEnd, String afterText) {
		Renderer renderer = new StyleGroupRenderer();
		ConversionToken token = new WikiLinePartToken(wikiText);
		List<ConversionToken> expected = new ArrayList<>();
		expected.add(new WikiLinePartToken(beforeText));
		expected.add(new RenderedToken(wikiMarkup, styleStart));
		expected.add(new WikiLinePartToken(text));
		expected.add(new RenderedToken(wikiMarkup, styleEnd));
		expected.add(new WikiLinePartToken(afterText));
		List<ConversionToken> output = renderer.render(token);
		Assertions.assertEquals(expected, output);
	}

}
