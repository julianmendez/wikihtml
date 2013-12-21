/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.part;

import java.util.ArrayList;
import java.util.List;

import de.tudresden.inf.lat.wikihtml.common.HTMLTag;
import de.tudresden.inf.lat.wikihtml.common.WikiCons;
import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.MultiRenderer;
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class StyleGroupRenderer implements Renderer {

	private final MultiRenderer renderer;

	public StyleGroupRenderer() {
		List<Renderer> list = new ArrayList<Renderer>();
		final String a = WikiCons.APOSTROPHE;
		list.add(new StyleRenderer(a + a + a + a + a,
				HTMLTag.ITALICS_BOLD_START, HTMLTag.ITALICS_BOLD_END));

		list.add(new StyleRenderer(a + a + a, HTMLTag.BOLD_START,
				HTMLTag.BOLD_END));

		list.add(new StyleRenderer(a + a, HTMLTag.ITALICS_START,
				HTMLTag.ITALICS_END));

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
