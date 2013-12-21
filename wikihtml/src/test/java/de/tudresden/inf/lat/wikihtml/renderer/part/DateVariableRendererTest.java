/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.part;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.WikiLinePartToken;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class DateVariableRendererTest extends TestCase {

	public static final Date date20041103195347 = new Date(
			((long) 1) * 0x100 * 0x10000 * 0x10000);

	public DateVariableRendererTest() {
	}

	public void testCurrentDay() {
		tryItem("{{CURRENTDAY}}", "3");
	}

	public void testCurrentDay2() {
		tryItem("{{CURRENTDAY2}}", "03");
	}

	public void testCurrentDayName() {
		tryItem("{{CURRENTDAYNAME}}", "Wednesday");
	}

	public void testCurrentDayOfWeek() {
		tryItem("{{CURRENTDOW}}", "3");
	}

	public void testCurrentHour() {
		tryItem("{{CURRENTHOUR}}", "19");
	}

	public void testCurrentMonth() {
		tryItem("{{CURRENTMONTH}}", "11");
	}

	public void testCurrentMonthAbbrev() {
		tryItem("{{CURRENTMONTHABBREV}}", "Nov");
	}

	public void testCurrentMonthName() {
		tryItem("{{CURRENTMONTHNAME}}", "November");
	}

	public void testCurrentTime() {
		tryItem("{{CURRENTTIME}}", "19:53");
	}

	public void testCurrentTimeStamp() {
		tryItem("{{CURRENTTIMESTAMP}}", "20041103195347");
	}

	public void testCurrentWeek() {
		tryItem("{{CURRENTWEEK}}", "45");
	}

	public void testCurrentYear() {
		tryItem("{{CURRENTYEAR}}", "2004");
	}

	private void tryItem(String variableName, String expectedResult) {
		DateVariableRenderer renderer = new DateVariableRenderer();
		String prefix = "some text here ";
		String suffix = " and some text here";
		ConversionToken token = new WikiLinePartToken(prefix + variableName
				+ suffix);
		List<ConversionToken> expected = new ArrayList<ConversionToken>();
		expected.add(new WikiLinePartToken(prefix + expectedResult + suffix));
		List<ConversionToken> output = renderer.render(token,
				date20041103195347);
		assertEquals(expected, output);
	}

}
