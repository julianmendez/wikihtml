/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.wikidoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.MultiRenderer;
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;
import de.tudresden.inf.lat.wikihtml.renderer.global.NowikiRenderer;
import de.tudresden.inf.lat.wikihtml.renderer.line.HeadingGroupRenderer;
import de.tudresden.inf.lat.wikihtml.renderer.line.HorizontalLineGroupRenderer;
import de.tudresden.inf.lat.wikihtml.renderer.line.LineBreakRenderer;
import de.tudresden.inf.lat.wikihtml.renderer.line.ListGroupRenderer;
import de.tudresden.inf.lat.wikihtml.renderer.line.TableGroupRenderer;
import de.tudresden.inf.lat.wikihtml.renderer.line.WikiLineDivider;
import de.tudresden.inf.lat.wikihtml.renderer.part.DateVariableRenderer;
import de.tudresden.inf.lat.wikihtml.renderer.part.LinkGroupRenderer;
import de.tudresden.inf.lat.wikihtml.renderer.part.StyleGroupRenderer;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class WikiDocumentRenderer implements Renderer {

	private final Renderer renderer = new MultiRenderer(getRendererList());

	public WikiDocumentRenderer() {
	}

	@Override
	public String getDescription() {
		return this.renderer.getDescription();
	}

	public List<Renderer> getRendererList() {
		List<Renderer> ret = new ArrayList<Renderer>();

		ret.add(new NowikiRenderer());
		ret.add(new TableGroupRenderer());
		ret.add(new ListGroupRenderer());
		ret.add(new HeadingGroupRenderer());
		ret.add(new HorizontalLineGroupRenderer());
		ret.add(new LineBreakRenderer());
		ret.add(new WikiLineDivider());
		ret.add(new TemplateRenderer());
		ret.add(new DateVariableRenderer());
		ret.add(new StyleGroupRenderer());
		ret.add(new LinkGroupRenderer());

		return Collections.unmodifiableList(ret);
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		if (token == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		return this.renderer.render(token);
	}

}
