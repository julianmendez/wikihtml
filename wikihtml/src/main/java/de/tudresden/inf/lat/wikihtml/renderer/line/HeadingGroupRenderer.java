/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.line;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
public class HeadingGroupRenderer implements Renderer {

	private final MultiRenderer renderer;

	public HeadingGroupRenderer() {
		List<Renderer> list = new ArrayList<Renderer>();
		final String e = WikiCons.EQUAL_SIGN;
		list.add(new HeadingRenderer(e + e + e + e + e + e, HTMLTag.H6_START, HTMLTag.H6_END));

		list.add(new HeadingRenderer(e + e + e + e + e, HTMLTag.H5_START, HTMLTag.H5_END));

		list.add(new HeadingRenderer(e + e + e + e, HTMLTag.H4_START, HTMLTag.H4_END));

		list.add(new HeadingRenderer(e + e + e, HTMLTag.H3_START, HTMLTag.H3_END));

		list.add(new HeadingRenderer(e + e, HTMLTag.H2_START, HTMLTag.H2_END));

		list.add(new HeadingRenderer(e, HTMLTag.H1_START, HTMLTag.H1_END));

		this.renderer = new MultiRenderer(list);
	}

	@Override
	public String getDescription() {
		return this.renderer.getDescription();
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		Objects.requireNonNull(token);

		return this.renderer.render(token);
	}

}
