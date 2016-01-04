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
public class TableGroupRenderer implements Renderer {

	private final MultiRenderer renderer;

	public TableGroupRenderer() {
		List<Renderer> list = new ArrayList<Renderer>();
		list.add(new TableRenderer(WikiCons.TABLE_ALT_COMMA_START,
				WikiCons.TABLE_ALT_END, WikiCons.COMMA,
				HTMLTag.TABLE_START_PRE, HTMLTag.TABLE_START_POST,
				HTMLTag.TABLE_END, HTMLTag.ROW_START, HTMLTag.ROW_END,
				HTMLTag.CELL_START, HTMLTag.CELL_END));

		list.add(new TableRenderer(WikiCons.TABLE_ALT_SEMICOLON_START,
				WikiCons.TABLE_ALT_END, WikiCons.SEMICOLON,
				HTMLTag.TABLE_START_PRE, HTMLTag.TABLE_START_POST,
				HTMLTag.TABLE_END, HTMLTag.ROW_START, HTMLTag.ROW_END,
				HTMLTag.CELL_START, HTMLTag.CELL_END));

		list.add(new TableRenderer(WikiCons.TABLE_ALT_TAB_START,
				WikiCons.TABLE_ALT_END, WikiCons.TAB, HTMLTag.TABLE_START_PRE,
				HTMLTag.TABLE_START_POST, HTMLTag.TABLE_END, HTMLTag.ROW_START,
				HTMLTag.ROW_END, HTMLTag.CELL_START, HTMLTag.CELL_END));

		list.add(new TableRenderer(WikiCons.TABLE_START, WikiCons.TABLE_END,
				WikiCons.NEW_ROW, WikiCons.NEW_CELL,
				WikiCons.NEW_CELL_SAME_LINE, HTMLTag.TABLE_START_PRE,
				HTMLTag.TABLE_START_POST, HTMLTag.TABLE_END, HTMLTag.ROW_START,
				HTMLTag.ROW_END, HTMLTag.CELL_START, HTMLTag.CELL_END));

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
