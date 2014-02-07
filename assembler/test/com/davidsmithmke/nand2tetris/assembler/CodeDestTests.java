package com.davidsmithmke.nand2tetris.assembler;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CodeDestTests {
	@Parameters(name="{0}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			new String[] { "", "000" }, 
			new String[] { "M", "001" },
			new String[] { "D", "010" },
			new String[] { "MD", "011" },
			new String[] { "A", "100" },
			new String[] { "AM", "101" },
			new String[] { "AD", "110" },
			new String[] { "AMD", "111" }
		});
	}
	
	@Parameter(value = 0)
	public static String mnemonic;
	@Parameter(value = 1)
	public static String expected;
	
	private Code code; 
	
	@Before
	public void setUp() {
		this.code = new Code();
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void destMnemonics_ReturnCorrectCodes() {
		String actual = this.code.dest(this.mnemonic);
		assertEquals(this.expected, actual);
	}
}
