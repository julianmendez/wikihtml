/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.global;

import java.util.ArrayList;
import java.util.List;

import de.tudresden.inf.lat.wikihtml.common.HTMLTag;
import de.tudresden.inf.lat.wikihtml.common.WikiCons;
import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;
import de.tudresden.inf.lat.wikihtml.renderer.common.TokenType;
import de.tudresden.inf.lat.wikihtml.renderer.common.WikiLinePartToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.WikiLineToken;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class NowikiRenderer implements Renderer {

	private boolean nowikiMode = false;

	@Override
	public String getDescription() {
		return HTMLTag.NOWIKI_START + "text without wiki markup"
				+ HTMLTag.NOWIKI_END;
	}

	public boolean isApplicable(ConversionToken token) {
		return this.nowikiMode
				|| ((token.getType().equals(TokenType.WIKI_LINE) || token
						.getType().equals(TokenType.WIKI_LINE_PART)) && ((token
						.getWikiText().toLowerCase()
						.indexOf(HTMLTag.NOWIKI_START) != -1)));
	}

	public boolean isNowikiMode() {
		return this.nowikiMode;
	}

	public String normalize(String text) {
		text = text.replaceAll(WikiCons.NUMBER_SIGN, WikiCons.NUMBER_SIGN_U);
		text = text.replaceAll(WikiCons.AMPERSAND_NUMBER_SIGN_U,
				WikiCons.AMPERSAND_NUMBER_SIGN);
		text = text.replaceAll(WikiCons.AMPERSAND + WikiCons.SPACE,
				WikiCons.AMPERSAND_U + WikiCons.SPACE);
		if (text.endsWith(WikiCons.AMPERSAND)) {
			text = text.substring(0,
					text.length() - WikiCons.AMPERSAND.length())
					+ WikiCons.AMPERSAND_U;
		}
		text = text.replaceAll(WikiCons.ASTERISK_E, WikiCons.ASTERISK_U);
		text = text.replaceAll(WikiCons.APOSTROPHE, WikiCons.APOSTROPHE_U);
		text = text.replaceAll(WikiCons.COMMA, WikiCons.COMMA_U);
		text = text.replaceAll(WikiCons.HYPHEN_MINUS, WikiCons.HYPHEN_MINUS_U);
		text = text.replaceAll(WikiCons.SLASH, WikiCons.SLASH_U);
		text = text.replaceAll(WikiCons.COLON, WikiCons.COLON_U);
		text = text.replaceAll(WikiCons.LESS_THAN_SIGN,
				WikiCons.LESS_THAN_SIGN_U);
		text = text.replaceAll(WikiCons.EQUAL_SIGN, WikiCons.EQUAL_SIGN_U);
		text = text.replaceAll(WikiCons.GREATER_THAN_SIGN,
				WikiCons.GREATER_THAN_SIGN_U);
		text = text.replaceAll(WikiCons.BACKSLASH_E, WikiCons.BACKSLASH_U);
		text = text.replaceAll(WikiCons.LEFT_CURLY_BRACKET_E,
				WikiCons.LEFT_CURLY_BRACKET_U);
		text = text
				.replaceAll(WikiCons.VERTICAL_BAR_E, WikiCons.VERTICAL_BAR_U);
		text = text.replaceAll(WikiCons.RIGHT_CURLY_BRACKET_E,
				WikiCons.RIGHT_CURLY_BRACKET_U);
		text = text.replaceAll(WikiCons.TILDE, WikiCons.TILDE_U);
		return text;
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		if (token == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		List<ConversionToken> ret = new ArrayList<ConversionToken>();
		if (isApplicable(token)) {
			String currentText = token.getWikiText();
			String newText = "";
			boolean found = true;

			while (found) {
				found = false;

				if (!this.nowikiMode
						&& (currentText.toLowerCase().indexOf(
								HTMLTag.NOWIKI_START.toLowerCase()) != -1)) {
					found = true;
					this.nowikiMode = true;
					int pos = currentText.toLowerCase().indexOf(
							HTMLTag.NOWIKI_START.toLowerCase());
					newText += currentText.substring(0, pos);
					currentText = currentText.substring(pos
							+ HTMLTag.NOWIKI_START.length());
				}

				if (this.nowikiMode
						&& (currentText.toLowerCase().indexOf(
								HTMLTag.NOWIKI_END.toLowerCase()) != -1)) {
					found = true;
					this.nowikiMode = false;
					int pos = currentText.toLowerCase().indexOf(
							HTMLTag.NOWIKI_END.toLowerCase());
					newText += normalize(currentText.substring(0, pos));
					currentText = currentText.substring(pos
							+ HTMLTag.NOWIKI_END.length());

				}

			}

			if (currentText.trim().length() > 0) {
				if (this.nowikiMode) {
					newText += normalize(currentText);
				} else {
					newText += currentText;
				}
			}

			if (token.getType().equals(TokenType.WIKI_LINE)) {
				ret.add(new WikiLineToken(newText));
			} else if (token.getType().equals(TokenType.WIKI_LINE_PART)) {
				ret.add(new WikiLinePartToken(newText));
			} else {
				throw new IllegalStateException("Token type is wrong: " + token);
			}

		} else {
			ret.add(token);
		}
		return ret;
	}

}
