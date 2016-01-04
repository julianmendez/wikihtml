/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.wikidoc;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
public class TemplateRenderer implements Renderer {

	public static final String ERROR_MESSAGE_BEGIN = " <!-- error reading resource ";
	public static final String ERROR_MESSAGE_END = " --> ";

	public TemplateRenderer() {
	}

	@Override
	public String getDescription() {
		return WikiCons.TEMPLATE_BEGIN + "template" + WikiCons.TEMPLATE_END;
	}

	public boolean isApplicable(ConversionToken token) {
		Objects.requireNonNull(token);

		return token.getType().equals(TokenType.WIKI_LINE_PART)
				&& (token.getWikiText().toLowerCase()
						.indexOf(WikiCons.TEMPLATE_BEGIN) != -1);
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		Objects.requireNonNull(token);

		List<ConversionToken> ret = new ArrayList<>();
		if (isApplicable(token)) {
			boolean applied = false;
			String currentText = token.getWikiText();
			int start = currentText.toLowerCase().indexOf(
					WikiCons.TEMPLATE_BEGIN);
			if (start != -1) {
				int end = currentText.toLowerCase().indexOf(
						WikiCons.TEMPLATE_END,
						start + WikiCons.TEMPLATE_BEGIN.length());
				if (end != -1) {
					applied = true;

					ret.add(new WikiLinePartToken(currentText.substring(0,
							start)));

					String resourceName = currentText.substring(start
							+ WikiCons.TEMPLATE_BEGIN.length(), end);
					String resource = WikiCons.TEMPLATE_BEGIN + resourceName
							+ WikiCons.TEMPLATE_END;
					URL url = null;
					Reader reader = null;
					WikiDocument document;
					try {

						try {
							url = new URL(resourceName);
							URLConnection conn = url.openConnection();
							reader = new InputStreamReader(
									conn.getInputStream());

						} catch (MalformedURLException e) {
							reader = new FileReader(resourceName);

						}
						document = new WikiDocument(reader);
						ret.add(new RenderedToken(resource, document
								.renderToString()));

					} catch (IOException e) {
						ret.add(new RenderedToken(resource, ERROR_MESSAGE_BEGIN
								+ sanitizeHTMLComment(resource)
								+ ERROR_MESSAGE_END));
					}

					ret.add(new WikiLinePartToken(currentText.substring(end
							+ WikiCons.TEMPLATE_END.length())));
				}
			}

			if (!applied) {
				ret.add(token);
			}
		} else {
			ret.add(token);
		}
		return ret;
	}

	private String sanitizeHTMLComment(String str) {
		String ret = str;
		ret = ret.replaceAll(WikiCons.HYPHEN_MINUS + WikiCons.HYPHEN_MINUS,
				WikiCons.HYPHEN_MINUS_U + WikiCons.HYPHEN_MINUS_U);
		return ret;
	}

}
