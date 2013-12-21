/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.wikidoc;

import java.util.List;

import junit.framework.TestCase;
import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;
import de.tudresden.inf.lat.wikihtml.renderer.common.TokenType;
import de.tudresden.inf.lat.wikihtml.renderer.common.WikiLinePartToken;
import de.tudresden.inf.lat.wikihtml.renderer.wikidoc.WikiDocumentRenderer;

/**
 * 
 * @author Julian Mendez
 */
public class WikiDocumentRendererTest extends TestCase {

	private final Renderer renderer = new WikiDocumentRenderer();

	public WikiDocumentRendererTest() {
	}

	public String convertToText(String text) {
		StringBuffer sbuf = new StringBuffer();
		List<ConversionToken> list = this.renderer
				.render(new WikiLinePartToken(text));
		for (ConversionToken token : list) {
			if (token.getType().equals(TokenType.HTML_TEXT)) {
				sbuf.append(token.getHTMLText());
			} else {
				sbuf.append(token.getWikiText());
			}
		}
		return sbuf.toString();
	}

	public void testBold() {
		assertEquals("<b>bold</b>", convertToText("'''bold'''"));
		assertEquals("at the end <b>bold</b>",
				convertToText("at the end '''bold'''"));
		assertEquals("<b>bold</b> at the beginning",
				convertToText("'''bold''' at the beginning"));
		assertEquals("we have <b>bold</b> in the middle",
				convertToText("we have '''bold''' in the middle"));
		assertEquals("we have <b>bold</b> in <b>the</b> middle",
				convertToText("we have '''bold''' in '''the''' middle"));
	}

	public void testItalics() {
		assertEquals("<i>italics</i>", convertToText("''italics''"));
		assertEquals("at the end <b>italics</b>",
				convertToText("at the end '''italics'''"));
		assertEquals("<b>italics</b> at the beginning",
				convertToText("'''italics''' at the beginning"));
		assertEquals("we have <b>italics</b> in the middle",
				convertToText("we have '''italics''' in the middle"));
		assertEquals("we have <i>italics</i> in <i>the</i> middle",
				convertToText("we have ''italics'' in ''the'' middle"));
	}

	public void testItalicsBold() {
		assertEquals("<i><b>italics and bold</b></i>",
				convertToText("'''''italics and bold'''''"));
		assertEquals("at the end <i><b>italics and bold</b></i>",
				convertToText("at the end '''''italics and bold'''''"));
		assertEquals("<i><b>italics and bold</b></i> at the beginning",
				convertToText("'''''italics and bold''''' at the beginning"));
		assertEquals(
				"we have <i><b>italics and bold</b></i> in the middle",
				convertToText("we have '''''italics and bold''''' in the middle"));
		assertEquals(
				"we have <i><b>italics and bold</b></i> in <i><b>the</b></i> middle",
				convertToText("we have '''''italics and bold''''' in '''''the''''' middle"));
	}

}
