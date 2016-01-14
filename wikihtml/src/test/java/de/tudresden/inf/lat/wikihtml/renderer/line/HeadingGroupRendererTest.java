/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.line;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.RenderedToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;
import de.tudresden.inf.lat.wikihtml.renderer.common.WikiLinePartToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.WikiLineToken;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class HeadingGroupRendererTest {

	@Test
	public void testHeading1() {
		tryHeading("=", 1);
	}

	@Test
	public void testHeading2() {
		tryHeading("==", 2);
	}

	@Test
	public void testHeading3() {
		tryHeading("===", 3);
	}

	@Test
	public void testHeading4() {
		tryHeading("====", 4);
	}

	@Test
	public void testHeading5() {
		tryHeading("=====", 5);
	}

	@Test
	public void testHeading6() {
		tryHeading("======", 6);
	}

	private void tryHeading(String wikiMarkup, int index) {
		String text = "Title";
		Renderer renderer = new HeadingGroupRenderer();
		ConversionToken token = new WikiLineToken(wikiMarkup + text + wikiMarkup);
		List<ConversionToken> expected = new ArrayList<>();
		expected.add(new RenderedToken("\n" + wikiMarkup, "\n<h" + index + ">"));
		expected.add(new WikiLinePartToken(text));
		expected.add(new RenderedToken(wikiMarkup, "</h" + index + ">\n"));
		List<ConversionToken> output = renderer.render(token);
		Assert.assertEquals(expected, output);
	}

}
