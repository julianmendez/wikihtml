/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.part;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.WikiLinePartToken;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class DateVariableRendererTest {

	public static final Date date20041103195347 = new Date(((long) 1) * 0x100 * 0x10000 * 0x10000);

	public DateVariableRendererTest() {
	}

	@Test
	public void testCurrentDay() {
		tryItem("{{CURRENTDAY}}", "3");
	}

	@Test
	public void testCurrentDay2() {
		tryItem("{{CURRENTDAY2}}", "03");
	}

	@Test
	public void testCurrentDayName() {
		tryItem("{{CURRENTDAYNAME}}", "Wednesday");
	}

	@Test
	public void testCurrentDayOfWeek() {
		tryItem("{{CURRENTDOW}}", "3");
	}

	@Test
	public void testCurrentHour() {
		tryItem("{{CURRENTHOUR}}", "19");
	}

	@Test
	public void testCurrentMonth() {
		tryItem("{{CURRENTMONTH}}", "11");
	}

	@Test
	public void testCurrentMonthAbbrev() {
		tryItem("{{CURRENTMONTHABBREV}}", "Nov");
	}

	@Test
	public void testCurrentMonthName() {
		tryItem("{{CURRENTMONTHNAME}}", "November");
	}

	@Test
	public void testCurrentTime() {
		tryItem("{{CURRENTTIME}}", "19:53");
	}

	@Test
	public void testCurrentTimeStamp() {
		tryItem("{{CURRENTTIMESTAMP}}", "20041103195347");
	}

	@Test
	public void testCurrentWeek() {
		tryItem("{{CURRENTWEEK}}", "45");
	}

	@Test
	public void testCurrentYear() {
		tryItem("{{CURRENTYEAR}}", "2004");
	}

	private void tryItem(String variableName, String expectedResult) {
		DateVariableRenderer renderer = new DateVariableRenderer();
		String prefix = "some text here ";
		String suffix = " and some text here";
		ConversionToken token = new WikiLinePartToken(prefix + variableName + suffix);
		List<ConversionToken> expected = new ArrayList<>();
		expected.add(new WikiLinePartToken(prefix + expectedResult + suffix));
		List<ConversionToken> output = renderer.render(token, date20041103195347);
		Assert.assertEquals(expected, output);
	}

}
