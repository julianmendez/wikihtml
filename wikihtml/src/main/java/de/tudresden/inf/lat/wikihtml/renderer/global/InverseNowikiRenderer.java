/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import de.tudresden.inf.lat.util.map.OptMap;
import de.tudresden.inf.lat.util.map.OptMapImpl;
import de.tudresden.inf.lat.wikihtml.common.HTMLTag;
import de.tudresden.inf.lat.wikihtml.common.WikiCons;
import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.RenderedToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;
import de.tudresden.inf.lat.wikihtml.renderer.common.TokenType;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class InverseNowikiRenderer implements Renderer {
	private final OptMap<String, String> map = new OptMapImpl<>(new HashMap<>());

	public InverseNowikiRenderer() {
		put(WikiCons.NUMBER_SIGN_U, WikiCons.NUMBER_SIGN);
		put(WikiCons.AMPERSAND_U, WikiCons.AMPERSAND);
		put(WikiCons.ASTERISK_U, WikiCons.ASTERISK);
		put(WikiCons.APOSTROPHE_U, WikiCons.APOSTROPHE);
		put(WikiCons.COMMA_U, WikiCons.COMMA);
		put(WikiCons.HYPHEN_MINUS_U, WikiCons.HYPHEN_MINUS);
		put(WikiCons.SLASH_U, WikiCons.SLASH);
		put(WikiCons.COLON_U, WikiCons.COLON);
		put(WikiCons.EQUAL_SIGN_U, WikiCons.EQUAL_SIGN);
		put(WikiCons.BACKSLASH_U, WikiCons.BACKSLASH_E);
		put(WikiCons.LEFT_CURLY_BRACKET_U, WikiCons.LEFT_CURLY_BRACKET_E);
		put(WikiCons.VERTICAL_BAR_U, WikiCons.VERTICAL_BAR);
		put(WikiCons.RIGHT_CURLY_BRACKET_U, WikiCons.RIGHT_CURLY_BRACKET);
		put(WikiCons.TILDE_U, WikiCons.TILDE);
	}

	@Override
	public String getDescription() {
		return HTMLTag.NOWIKI_START + "replacement of HTML character entities by nowiki tags" + HTMLTag.NOWIKI_END;
	}

	public boolean isApplicable(ConversionToken token) {
		boolean ret = false;
		if (token.getType().equals(TokenType.HTML_TEXT)) {
			String text = token.getWikiText();
			for (Iterator<String> it = this.map.keySet().iterator(); !ret && it.hasNext();) {
				String key = it.next();
				ret |= text.indexOf(key) != -1;
			}
		}
		return ret;
	}

	public String normalize(String text) {
		String ret = text;

		for (String key : this.map.keySet()) {
			ret = ret.replaceAll(key, this.map.get(key).get());
		}

		boolean simplifying = true;
		while (simplifying) {
			String newText = ret.replaceAll(HTMLTag.NOWIKI_END + HTMLTag.NOWIKI_START, "");
			simplifying = (newText.length() < ret.length());
			if (simplifying) {
				ret = newText;
			}
		}
		return ret;
	}

	private void put(String key, String value) {
		this.map.put(key, HTMLTag.NOWIKI_START + value + HTMLTag.NOWIKI_END);
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		Objects.requireNonNull(token);

		List<ConversionToken> ret = new ArrayList<>();
		if (isApplicable(token)) {
			String normalizedText = normalize(token.getWikiText());
			ret.add(new RenderedToken(normalizedText, token.getHTMLText()));
		} else {
			ret.add(token);
		}
		return ret;
	}

}
