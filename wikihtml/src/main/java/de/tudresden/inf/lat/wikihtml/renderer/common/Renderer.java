/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.common;

import java.util.List;

/**
 * 
 * @author Julian Mendez
 * 
 */
public interface Renderer {

	List<ConversionToken> render(ConversionToken token);

	String getDescription();

}
