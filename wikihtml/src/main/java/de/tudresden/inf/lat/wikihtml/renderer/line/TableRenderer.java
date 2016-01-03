/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.line;

import java.util.ArrayList;
import java.util.List;

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
class TableRenderer implements Renderer {

	private int depth = 0;
	private boolean firstCell = false;
	private boolean firstRow = false;
	private final String htmlCellBegin;
	private final String htmlCellEnd;
	private final String htmlRowBegin;
	private final String htmlRowEnd;
	private final String htmlTableBeginTagPost;
	private final String htmlTableBeginTagPre;
	private final String htmlTableEnd;
	private final boolean simpleTable;
	private final String wikiNewCell;
	private final String wikiNewCellSameLine;
	private final String wikiNewRow;
	private final String wikiTableBegin;
	private final String wikiTableEnd;

	public TableRenderer(String wikiTableBegin, String wikiTableEnd,
			String wikiNewCellSameLine, String htmlTableBeginTagPre,
			String htmlTableBeginTagPost, String htmlTableEnd,
			String htmlRowBegin, String htmlRowEnd, String htmlCellBegin,
			String htmlCellEnd) {
		if (wikiTableBegin == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (wikiTableEnd == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (wikiNewCellSameLine == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlTableBeginTagPre == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlTableBeginTagPost == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlTableEnd == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlRowBegin == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlRowEnd == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlCellBegin == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlCellEnd == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		this.wikiTableBegin = wikiTableBegin;
		this.wikiTableEnd = wikiTableEnd;
		this.wikiNewRow = "";
		this.wikiNewCell = "";
		this.wikiNewCellSameLine = wikiNewCellSameLine;
		this.htmlTableBeginTagPre = htmlTableBeginTagPre;
		this.htmlTableBeginTagPost = htmlTableBeginTagPost;
		this.htmlTableEnd = htmlTableEnd;
		this.htmlRowBegin = htmlRowBegin;
		this.htmlRowEnd = htmlRowEnd;
		this.htmlCellBegin = htmlCellBegin;
		this.htmlCellEnd = htmlCellEnd;
		this.simpleTable = true;
	}

	public TableRenderer(String wikiTableBegin, String wikiTableEnd,
			String wikiNewRow, String wikiNewCell, String wikiNewCellSameLine,
			String htmlTableBeginTagPre, String htmlTableBeginTagPost,
			String htmlTableEnd, String htmlRowBegin, String htmlRowEnd,
			String htmlCellBegin, String htmlCellEnd) {
		if (wikiTableBegin == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (wikiTableEnd == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (wikiNewRow == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (wikiNewCell == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (wikiNewCellSameLine == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlTableBeginTagPre == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlTableBeginTagPost == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlTableEnd == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlRowBegin == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlRowEnd == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlCellBegin == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlCellEnd == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		this.wikiTableBegin = wikiTableBegin;
		this.wikiTableEnd = wikiTableEnd;
		this.wikiNewRow = wikiNewRow;
		this.wikiNewCell = wikiNewCell;
		this.wikiNewCellSameLine = wikiNewCellSameLine;
		this.htmlTableBeginTagPre = htmlTableBeginTagPre;
		this.htmlTableBeginTagPost = htmlTableBeginTagPost;
		this.htmlTableEnd = htmlTableEnd;
		this.htmlRowBegin = htmlRowBegin;
		this.htmlRowEnd = htmlRowEnd;
		this.htmlCellBegin = htmlCellBegin;
		this.htmlCellEnd = htmlCellEnd;
		this.simpleTable = false;
	}

	private List<ConversionToken> addNewCells(String line) {
		List<ConversionToken> ret = new ArrayList<>();
		if (!this.firstCell) {
			ret.add(new RenderedToken("", this.htmlCellEnd));
		}
		ret.add(new RenderedToken((this.simpleTable ? "" : WikiCons.NEW_LINE)
				+ this.wikiNewCell, this.htmlCellBegin));
		String str = line;
		while (!str.isEmpty()) {
			int pos = str.indexOf(this.wikiNewCellSameLine);
			if (pos != -1) {
				ret.add(new WikiLinePartToken(str.substring(0, pos)));
				ret.add(new RenderedToken("", this.htmlCellEnd));
				ret.add(new RenderedToken(this.wikiNewCellSameLine,
						this.htmlCellBegin));
				str = str.substring(pos + this.wikiNewCellSameLine.length());

			} else {
				ret.add(new WikiLinePartToken(str));
				str = "";
			}
		}
		return ret;
	}

	public int getDepth() {
		return this.depth;
	}

	@Override
	public String getDescription() {
		return this.wikiTableBegin + " table " + this.wikiNewCell + " and "
				+ this.wikiNewCellSameLine + " value " + this.wikiTableEnd;
	}

	public boolean isApplicable(ConversionToken token) {
		if (token == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		String text = token.getWikiText().toLowerCase();
		boolean isApp1 = text.startsWith(this.wikiTableBegin);
		boolean isApp2 = (this.depth > 0)
				&& (text.startsWith(this.wikiTableEnd)
						|| text.startsWith(this.wikiNewRow) || text
							.startsWith(this.wikiNewCell));
		return token.getType().equals(TokenType.WIKI_LINE)
				&& (isApp1 || isApp2);
	}

	public boolean isSimpleTable() {
		return this.simpleTable;
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		if (token == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		List<ConversionToken> ret = new ArrayList<>();

		if (isApplicable(token)) {
			String text = token.getWikiText().toLowerCase();

			if (text.startsWith(this.wikiTableBegin)) {
				this.depth++;
				String str = token.getWikiText().substring(
						this.wikiTableBegin.length());
				ret.add(new RenderedToken(WikiCons.NEW_LINE
						+ this.wikiTableBegin, this.htmlTableBeginTagPre));
				ret.add(new RenderedToken(str, str));
				ret.add(new RenderedToken(this.simpleTable ? WikiCons.NEW_LINE
						: "", this.htmlTableBeginTagPost));
				ret.add(new RenderedToken("", this.htmlRowBegin));
				this.firstRow = true;
				this.firstCell = true;

			} else if ((this.depth > 0) && text.startsWith(this.wikiTableEnd)) {
				if (!this.firstCell) {
					ret.add(new RenderedToken("", this.htmlCellEnd));
				}
				ret.add(new RenderedToken(WikiCons.NEW_LINE, this.htmlRowEnd));
				ret.add(new RenderedToken(this.wikiTableEnd, this.htmlTableEnd));
				this.depth--;
				String str = token.getWikiText().substring(
						this.wikiTableEnd.length());
				ret.add(new WikiLinePartToken(str));
				this.firstRow = false;
				this.firstCell = false;

			} else if ((this.depth > 0) && text.startsWith(this.wikiNewRow)) {
				if (!this.firstCell) {
					ret.add(new RenderedToken("", this.htmlCellEnd));
				}
				if (!this.firstRow) {
					ret.add(new RenderedToken("", this.htmlRowEnd));
					ret.add(new RenderedToken(WikiCons.NEW_LINE
							+ this.wikiNewRow, this.htmlRowBegin));
				}
				this.firstRow = false;
				this.firstCell = true;

				if (this.simpleTable) {
					String str = token.getWikiText().substring(
							this.wikiNewRow.length());
					ret.addAll(addNewCells(str));
					this.firstCell = false;
				}

			} else if ((this.depth > 0) && text.startsWith(this.wikiNewCell)) {
				String str = token.getWikiText().substring(
						this.wikiNewCell.length());
				ret.addAll(addNewCells(str));
				this.firstRow = false;
				this.firstCell = false;

			} else {
				ret.add(token);

			}

		} else {
			ret.add(token);
		}

		return ret;
	}

}
