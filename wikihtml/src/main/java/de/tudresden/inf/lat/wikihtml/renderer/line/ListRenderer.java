/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.line;

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
class ListRenderer implements Renderer {

	private int depth = 0;
	private final String htmlTextItemBegin;
	private final String htmlTextItemEnd;
	private final String htmlTextListBegin;
	private final String htmlTextListEnd;
	private final String wikiTextItem;

	public ListRenderer(String wikiTextItem, String htmlTextListBegin,
			String htmlTextListEnd, String htmlTextItemBegin,
			String htmlTextItemEnd) {
		if (wikiTextItem == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlTextListBegin == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlTextListEnd == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlTextItemBegin == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlTextItemEnd == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		this.wikiTextItem = wikiTextItem;
		this.htmlTextListBegin = htmlTextListBegin;
		this.htmlTextListEnd = htmlTextListEnd;
		this.htmlTextItemBegin = htmlTextItemBegin;
		this.htmlTextItemEnd = htmlTextItemEnd;
	}

	public int getDepth() {
		return this.depth;
	}

	@Override
	public String getDescription() {
		return this.wikiTextItem + " item";
	}

	public boolean isApplicable(ConversionToken token) {
		if (token == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		return token.getType().equals(TokenType.WIKI_LINE)
				&& token.getWikiText().toLowerCase()
						.startsWith(this.wikiTextItem);
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		if (token == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		List<ConversionToken> ret = new ArrayList<>();

		if (isApplicable(token)) {
			String text = token.getWikiText();
			int start = 0;
			while ((start < text.length())
					&& text.substring(start).startsWith(this.wikiTextItem)) {
				start++;
			}
			if (this.depth < start) {
				for (int i = this.depth; i < (start - 1); i++) {
					ret.add(new RenderedToken("", this.htmlTextListBegin));
					ret.add(new RenderedToken("", this.htmlTextItemBegin));
				}
				ret.add(new RenderedToken("", this.htmlTextListBegin));
				ret.add(new RenderedToken(WikiCons.NEW_LINE
						+ text.substring(0, start), this.htmlTextItemBegin));

			} else if (this.depth > start) {
				for (int i = start; i < this.depth; i++) {
					ret.add(new RenderedToken("", this.htmlTextItemEnd));
					ret.add(new RenderedToken("", this.htmlTextListEnd));
				}
				ret.add(new RenderedToken("", this.htmlTextItemEnd));
				ret.add(new RenderedToken(WikiCons.NEW_LINE
						+ text.substring(0, start), this.htmlTextItemBegin));

			} else {
				ret.add(new RenderedToken("", this.htmlTextItemEnd));
				ret.add(new RenderedToken(WikiCons.NEW_LINE
						+ text.substring(0, start), this.htmlTextItemBegin));

			}
			ret.add(new WikiLinePartToken(text.substring(start)));
			this.depth = start;

		} else {
			int count = 0;

			if (this.depth > count) {
				for (int i = count; i < this.depth; i++) {
					ret.add(new RenderedToken("", this.htmlTextItemEnd));
					ret.add(new RenderedToken("", this.htmlTextListEnd));
				}
				this.depth = count;
			}

			ret.add(token);
		}

		return ret;
	}

}
