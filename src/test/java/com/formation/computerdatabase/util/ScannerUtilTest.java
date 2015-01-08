package com.formation.computerdatabase.util;

import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;

public class ScannerUtilTest {

	@Test
	public void getNextLongIsNull() {
		Assert.assertThat(ScannerUtil.getNextLong(), IsNull.nullValue());
	}
	
}
