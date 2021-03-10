/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.wikidoc;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;
import de.tudresden.inf.lat.wikihtml.renderer.common.TokenType;
import de.tudresden.inf.lat.wikihtml.renderer.common.WikiLinePartToken;

/**
 * 
 * @author Julian Mendez
 */
public class WikiDocumentRendererTest {

	private final Renderer renderer = new WikiDocumentRenderer();

	public WikiDocumentRendererTest() {
	}

	public String convertToText(String text) {
		StringBuffer sbuf = new StringBuffer();
		List<ConversionToken> list = this.renderer.render(new WikiLinePartToken(text));
		for (ConversionToken token : list) {
			if (token.getType().equals(TokenType.HTML_TEXT)) {
				sbuf.append(token.getHTMLText());
			} else {
				sbuf.append(token.getWikiText());
			}
		}
		return sbuf.toString();
	}

	@Test
	public void testBold() {
		Assertions.assertEquals("<b>bold</b>", convertToText("'''bold'''"));
		Assertions.assertEquals("at the end <b>bold</b>", convertToText("at the end '''bold'''"));
		Assertions.assertEquals("<b>bold</b> at the beginning", convertToText("'''bold''' at the beginning"));
		Assertions.assertEquals("we have <b>bold</b> in the middle", convertToText("we have '''bold''' in the middle"));
		Assertions.assertEquals("we have <b>bold</b> in <b>the</b> middle",
				convertToText("we have '''bold''' in '''the''' middle"));
	}

	@Test
	public void testItalics() {
		Assertions.assertEquals("<i>italics</i>", convertToText("''italics''"));
		Assertions.assertEquals("at the end <b>italics</b>", convertToText("at the end '''italics'''"));
		Assertions.assertEquals("<b>italics</b> at the beginning", convertToText("'''italics''' at the beginning"));
		Assertions.assertEquals("we have <b>italics</b> in the middle",
				convertToText("we have '''italics''' in the middle"));
		Assertions.assertEquals("we have <i>italics</i> in <i>the</i> middle",
				convertToText("we have ''italics'' in ''the'' middle"));
	}

	@Test
	public void testItalicsBold() {
		Assertions.assertEquals("<i><b>italics and bold</b></i>", convertToText("'''''italics and bold'''''"));
		Assertions.assertEquals("at the end <i><b>italics and bold</b></i>",
				convertToText("at the end '''''italics and bold'''''"));
		Assertions.assertEquals("<i><b>italics and bold</b></i> at the beginning",
				convertToText("'''''italics and bold''''' at the beginning"));
		Assertions.assertEquals("we have <i><b>italics and bold</b></i> in the middle",
				convertToText("we have '''''italics and bold''''' in the middle"));
		Assertions.assertEquals("we have <i><b>italics and bold</b></i> in <i><b>the</b></i> middle",
				convertToText("we have '''''italics and bold''''' in '''''the''''' middle"));
	}

}
