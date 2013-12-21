/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.line;

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
public class ListGroupRenderer implements Renderer {

	private final MultiRenderer renderer;

	public ListGroupRenderer() {
		List<Renderer> list = new ArrayList<Renderer>();
		list.add(new ListRenderer(WikiCons.ASTERISK, HTMLTag.UL_START,
				HTMLTag.UL_END, HTMLTag.LI_START, HTMLTag.LI_END));

		list.add(new ListRenderer(WikiCons.COLON, HTMLTag.DL_START,
				HTMLTag.DL_END, HTMLTag.DD_START, HTMLTag.DD_END));

		list.add(new ListRenderer(WikiCons.NUMBER_SIGN, HTMLTag.OL_START,
				HTMLTag.OL_END, HTMLTag.LI_START, HTMLTag.LI_END));

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
