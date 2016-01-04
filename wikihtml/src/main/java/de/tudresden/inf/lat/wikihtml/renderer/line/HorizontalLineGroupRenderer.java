/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.line;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.tudresden.inf.lat.wikihtml.common.WikiCons;
import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.MultiRenderer;
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class HorizontalLineGroupRenderer implements Renderer {

	private final MultiRenderer renderer;

	public HorizontalLineGroupRenderer() {
		List<Renderer> list = new ArrayList<Renderer>();
		final String m = WikiCons.HYPHEN_MINUS;
		final String s = WikiCons.SPACE;

		list.add(new HorizontalLineRenderer(m + m + m + m));

		list.add(new HorizontalLineRenderer(m + s + m + s + m));

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
