/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.part;

import java.util.ArrayList;
import java.util.List;

import de.tudresden.inf.lat.wikihtml.common.WikiCons;
import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.RenderedToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;
import de.tudresden.inf.lat.wikihtml.renderer.common.TokenType;
import de.tudresden.inf.lat.wikihtml.renderer.common.WikiLinePartToken;

/**
 * 
 * @author Julian Mendez
 * 
 */
class InternalLinkRenderer implements Renderer {

	private final String htmlTextBegin;
	private final String htmlTextEnd;
	private final String htmlTextMiddleFirst;
	private final String htmlTextMiddleSecond;
	private final String wikiTextBegin;
	private final String wikiTextEnd;
	private final String wikiTextMiddle;

	public InternalLinkRenderer(String wikiTextBegin, String wikiTextMiddle,
			String wikiTextEnd, String htmlTextBegin,
			String htmlTextMiddleFirst, String htmlTextMiddleSecond,
			String htmlTextEnd) {
		if (wikiTextBegin == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (wikiTextMiddle == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (wikiTextEnd == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlTextBegin == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlTextMiddleFirst == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlTextMiddleSecond == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlTextEnd == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		this.wikiTextBegin = wikiTextBegin;
		this.wikiTextMiddle = wikiTextMiddle;
		this.wikiTextEnd = wikiTextEnd;
		this.htmlTextBegin = htmlTextBegin;
		this.htmlTextMiddleFirst = htmlTextMiddleFirst;
		this.htmlTextMiddleSecond = htmlTextMiddleSecond;
		this.htmlTextEnd = htmlTextEnd;
	}

	@Override
	public String getDescription() {
		return this.wikiTextBegin + "link.to" + this.wikiTextMiddle
				+ " other pages" + this.wikiTextEnd;
	}

	public boolean isApplicable(ConversionToken token) {
		return token.getType().equals(TokenType.WIKI_LINE_PART)
				&& (token.getWikiText().toLowerCase()
						.indexOf(this.wikiTextBegin) != -1);
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		if (token == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		List<ConversionToken> ret = new ArrayList<>();
		if (isApplicable(token)) {
			String currentText = token.getWikiText();
			boolean found = true;
			while (found) {
				int beginPos = currentText.toLowerCase().indexOf(
						this.wikiTextBegin);
				if (beginPos == -1) {
					found = false;
				} else {
					{
						String previousText = currentText
								.substring(0, beginPos);
						ret.add(new WikiLinePartToken(previousText));
					}
					currentText = currentText.substring(beginPos);

					int endPos = currentText.toLowerCase().indexOf(
							this.wikiTextEnd, this.wikiTextBegin.length());
					if (endPos == -1) {
						found = false;
					} else {
						int linkEnd = currentText.substring(0, endPos)
								.toLowerCase().indexOf(this.wikiTextMiddle);
						String linkText = null;
						String descriptionText = null;

						if (linkEnd == -1) {
							linkText = currentText.substring(
									this.wikiTextBegin.length(), endPos);

							descriptionText = linkText;
						} else {
							linkText = currentText.substring(
									this.wikiTextBegin.length(), linkEnd);
							descriptionText = currentText.substring(linkEnd
									+ this.wikiTextMiddle.length(), endPos);
						}
						linkText = linkText.replaceAll(WikiCons.LINK_SPACE,
								WikiCons.LINK_UNDERSCORE);
						currentText = currentText.substring(endPos
								+ this.wikiTextEnd.length());

						ret.add(new RenderedToken(this.wikiTextBegin,
								this.htmlTextBegin));
						ret.add(new RenderedToken(linkText, linkText));
						ret.add(new RenderedToken(this.wikiTextMiddle,
								this.htmlTextMiddleFirst));
						if (!this.htmlTextMiddleSecond.isEmpty()) {
							ret.add(new RenderedToken(linkText, linkText));
							ret.add(new RenderedToken("",
									this.htmlTextMiddleSecond));
						}
						ret.add(new WikiLinePartToken(descriptionText));
						ret.add(new RenderedToken(this.wikiTextEnd,
								this.htmlTextEnd));
					}
				}
			}
			ret.add(new WikiLinePartToken(currentText));
		} else {
			ret.add(token);
		}
		return ret;
	}

}
