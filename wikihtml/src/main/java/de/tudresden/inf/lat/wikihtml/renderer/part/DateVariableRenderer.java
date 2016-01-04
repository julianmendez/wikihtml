/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.part;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.TreeMap;

import de.tudresden.inf.lat.wikihtml.common.WikiCons;
import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;
import de.tudresden.inf.lat.wikihtml.renderer.common.TokenType;
import de.tudresden.inf.lat.wikihtml.renderer.common.WikiLinePartToken;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class DateVariableRenderer implements Renderer {

	public enum VarName {
		DAY, DAY2, DAYNAME, DOW, HOUR, MONTH, MONTHABBREV, MONTHNAME, TIME, TIMESTAMP, WEEK, YEAR
	}

	private static final String CURRENT = "CURRENT";
	private static final String DF_DAY = "d";
	private static final String DF_DAY2 = "dd";
	private static final String DF_DAYNAME = "EEEE";
	private static final String DF_HOUR = "HH";
	private static final String DF_MONTH = "MM";
	private static final String DF_MONTHABBREV = "MMM";
	private static final String DF_MONTHNAME = "MMMM";
	private static final String DF_TIME = "HH:mm";
	private static final String DF_TIMESTAMP = "yyyyMMddHHmmss";
	private static final String DF_WEEK = "w";
	private static final String DF_YEAR = "yyyy";
	private static final String GMT = "GMT";
	private static final String LOCAL = "LOCAL";
	private static final String Z_CURRENTDOW_ID = WikiCons.VAR_BEGIN + CURRENT
			+ VarName.DOW.toString() + WikiCons.VAR_END;
	private static final String Z_LOCALDOW_ID = WikiCons.VAR_BEGIN + LOCAL
			+ VarName.DOW.toString() + WikiCons.VAR_END;

	private final Map<String, SimpleDateFormat> translationMap;

	public DateVariableRenderer() {
		Map<String, SimpleDateFormat> map = new TreeMap<>();
		put(map, VarName.DAY, DF_DAY);
		put(map, VarName.DAY2, DF_DAY2);
		put(map, VarName.DAYNAME, DF_DAYNAME);
		put(map, VarName.HOUR, DF_HOUR);
		put(map, VarName.MONTH, DF_MONTH);
		put(map, VarName.MONTHABBREV, DF_MONTHABBREV);
		put(map, VarName.MONTHNAME, DF_MONTHNAME);
		put(map, VarName.TIME, DF_TIME);
		put(map, VarName.TIMESTAMP, DF_TIMESTAMP);
		put(map, VarName.WEEK, DF_WEEK);
		put(map, VarName.YEAR, DF_YEAR);
		this.translationMap = Collections.unmodifiableMap(map);
	}

	private int getCurrentDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setTimeZone(TimeZone.getTimeZone(GMT));
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	@Override
	public String getDescription() {
		return WikiCons.VAR_BEGIN + CURRENT + VarName.TIMESTAMP
				+ WikiCons.VAR_END + " (date variables)";
	}

	private int getLocalDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	public boolean isApplicable(ConversionToken token) {
		Objects.requireNonNull(token);

		return token.getType().equals(TokenType.WIKI_LINE_PART)
				&& (token.getWikiText().toLowerCase()
						.indexOf(WikiCons.VAR_BEGIN) != -1);
	}

	private void put(Map<String, SimpleDateFormat> map, VarName varName,
			String dateFormatStr) {

		map.put(WikiCons.VAR_BEGIN + LOCAL + varName.toString()
				+ WikiCons.VAR_END, new SimpleDateFormat(dateFormatStr));

		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		dateFormat.setTimeZone(TimeZone.getTimeZone(GMT));
		map.put(WikiCons.VAR_BEGIN + CURRENT + varName.toString()
				+ WikiCons.VAR_END, dateFormat);
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		Objects.requireNonNull(token);

		return render(token, new Date());
	}

	protected List<ConversionToken> render(ConversionToken token, Date date) {
		Objects.requireNonNull(token);
		Objects.requireNonNull(date);

		List<ConversionToken> ret = new ArrayList<>();
		if (isApplicable(token)) {
			String currentText = token.getWikiText();
			for (String key : this.translationMap.keySet()) {
				currentText = replaceAll(currentText, key, this.translationMap
						.get(key).format(date));
			}
			currentText = replaceAll(currentText, Z_CURRENTDOW_ID, ""
					+ getCurrentDayOfWeek(date));
			currentText = replaceAll(currentText, Z_LOCALDOW_ID, ""
					+ getLocalDayOfWeek(date));

			ret.add(new WikiLinePartToken(currentText));

		} else {
			ret.add(token);
		}
		return ret;
	}

	private String replaceAll(String text, String origSubStr, String newSubStr) {
		StringBuffer ret = new StringBuffer();
		int pointer = 0;
		int pos = text.indexOf(origSubStr, pointer);
		while (pos != -1) {
			ret.append(text.substring(pointer, pos));
			ret.append(newSubStr);
			pointer = pos + origSubStr.length();
			pos = text.indexOf(origSubStr, pointer);
		}
		ret.append(text.substring(pointer));
		return ret.toString();
	}

}
