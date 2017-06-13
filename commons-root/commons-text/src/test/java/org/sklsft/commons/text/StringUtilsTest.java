package org.sklsft.commons.text;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void test() {
		String arg = "âãäåāăàÁÂÃÄÅĀĂĄèééêëēĕėęěĒĔĖĘĚìíîïìĩīĭÌÍÎÏÌĨĪĬóôõöōŏőÒÓÔÕÖŌŎŐùúûüũūŭůÙÚÛÜŨŪŬŮ";
		String expected = "aaaaaaaaaaaaaaaeeeeeeeeeeeeeeeiiiiiiiiiiiiiiiiooooooooooooooouuuuuuuuuuuuuuuu";
		Assert.assertEquals(StringUtils.unaccent(arg), expected);
	}
}