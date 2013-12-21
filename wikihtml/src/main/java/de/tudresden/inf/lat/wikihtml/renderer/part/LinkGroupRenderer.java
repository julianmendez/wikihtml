/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.part;

import java.util.ArrayList;
import java.util.List;

import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.MultiRenderer;
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class LinkGroupRenderer implements Renderer {

	private final MultiRenderer renderer;

	public LinkGroupRenderer() {
		List<Renderer> list = new ArrayList<Renderer>();
		list.add(new InternalLinkRenderer("[[image:", "|", "]]", "<img src=\"",
				"\" alt=\"", "\" />", ""));

		list.add(new InternalLinkRenderer("[[file:", "|", "]]", "<img src=\"",
				"\" alt=\"", "\" />", ""));

		list.add(new InternalLinkRenderer("[[", "|", "]]", "<a href=\"", "\">",
				"", "</a>"));

		list.add(new HyperlinkRenderer("[https://", " ", "]",
				"<a href=\"https://", "\">", "</a>", "[]"));

		list.add(new HyperlinkRenderer("[http://", " ", "]",
				"<a href=\"http://", "\">", "</a>", "[]"));

		list.add(new SimpleHyperlinkRenderer("https://", " ",
				"<a href=\"https://", "\">https://", "</a>"));

		list.add(new SimpleHyperlinkRenderer("http://", " ",
				"<a href=\"http://", "\">http://", "</a>"));

		this.renderer = new MultiRenderer(list);
	}

	@Override
	public String getDescription() {
		return this.renderer.getDescription();
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		if (token == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		return this.renderer.render(token);
	}

}
