/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.common;

import java.util.ArrayList;
import java.util.List;

import de.tudresden.inf.lat.wikihtml.common.WikiCons;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class MultiRenderer implements Renderer {

	private final List<Renderer> list;

	public MultiRenderer(List<Renderer> list) {
		if (list == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		this.list = list;
	}

	@Override
	public String getDescription() {
		StringBuffer sbuf = new StringBuffer();
		for (Renderer renderer : getList()) {
			String description = renderer.getDescription();
			sbuf.append(description);
			if (!description.endsWith(WikiCons.NEW_LINE)) {
				sbuf.append(WikiCons.NEW_LINE);
			}
		}
		return sbuf.toString();
	}

	public List<Renderer> getList() {
		return this.list;
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		List<ConversionToken> ret = new ArrayList<>();
		ret.add(token);
		for (Renderer converter : this.list) {
			List<ConversionToken> current = new ArrayList<>();
			for (ConversionToken currentToken : ret) {
				current.addAll(converter.render(currentToken));
			}
			ret = current;
		}
		return ret;
	}

}
