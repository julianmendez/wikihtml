/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.part;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
class HyperlinkRenderer implements Renderer {

	private final String defaultDescription;
	private final String htmlTextBegin;
	private final String htmlTextEnd;
	private final String htmlTextMiddle;
	private final String wikiTextBegin;
	private final String wikiTextEnd;
	private final String wikiTextMiddle;

	public HyperlinkRenderer(String wikiTextBegin, String wikiTextMiddle, String wikiTextEnd, String htmlTextBegin,
			String htmlTextMiddle, String htmlTextEnd, String defaultDescription) {
		Objects.requireNonNull(wikiTextBegin);
		Objects.requireNonNull(wikiTextMiddle);
		Objects.requireNonNull(wikiTextEnd);
		Objects.requireNonNull(htmlTextBegin);
		Objects.requireNonNull(htmlTextMiddle);
		Objects.requireNonNull(htmlTextEnd);
		Objects.requireNonNull(defaultDescription);

		this.wikiTextBegin = wikiTextBegin;
		this.wikiTextMiddle = wikiTextMiddle;
		this.wikiTextEnd = wikiTextEnd;
		this.htmlTextBegin = htmlTextBegin;
		this.htmlTextMiddle = htmlTextMiddle;
		this.htmlTextEnd = htmlTextEnd;
		this.defaultDescription = defaultDescription;
	}

	@Override
	public String getDescription() {
		return this.wikiTextBegin + "link.to" + this.wikiTextMiddle + " other pages" + this.wikiTextEnd;
	}

	public boolean isApplicable(ConversionToken token) {
		Objects.requireNonNull(token);

		return token.getType().equals(TokenType.WIKI_LINE_PART)
				&& (token.getWikiText().toLowerCase().indexOf(this.wikiTextBegin) != -1);
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		Objects.requireNonNull(token);

		List<ConversionToken> ret = new ArrayList<>();
		if (isApplicable(token)) {
			String currentText = token.getWikiText();
			boolean found = true;
			while (found) {
				int beginPos = currentText.toLowerCase().indexOf(this.wikiTextBegin);
				if (beginPos == -1) {
					found = false;
				} else {
					{
						String previousText = currentText.substring(0, beginPos);
						ret.add(new WikiLinePartToken(previousText));
					}
					currentText = currentText.substring(beginPos);

					int endPos = currentText.toLowerCase().indexOf(this.wikiTextEnd, this.wikiTextBegin.length());
					if (endPos == -1) {
						found = false;
					} else {
						int linkEnd = currentText.substring(0, endPos).toLowerCase().indexOf(this.wikiTextMiddle);
						String linkText = null;
						String descriptionText = null;

						if (linkEnd == -1) {
							linkText = currentText.substring(this.wikiTextBegin.length(), endPos);
							descriptionText = this.defaultDescription;
						} else {
							linkText = currentText.substring(this.wikiTextBegin.length(), linkEnd);
							descriptionText = currentText.substring(linkEnd + this.wikiTextMiddle.length(), endPos);
						}
						currentText = currentText.substring(endPos + this.wikiTextEnd.length());

						ret.add(new RenderedToken(this.wikiTextBegin, this.htmlTextBegin));
						ret.add(new RenderedToken(linkText, linkText));
						ret.add(new RenderedToken(this.wikiTextMiddle, this.htmlTextMiddle));
						ret.add(new WikiLinePartToken(descriptionText));
						ret.add(new RenderedToken(this.wikiTextEnd, this.htmlTextEnd));
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
