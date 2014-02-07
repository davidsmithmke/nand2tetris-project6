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
public class CodeCompTests {
	@Parameters(name="{0}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			new String[] { "0", "0101010" },
			new String[] { "1", "0111111" },
			new String[] { "-1", "0111010" },
			new String[] { "D", "0001100" },
			new String[] { "A", "0110000" },
			new String[] { "M", "1110000" },
			new String[] { "!D", "0001101" },
			new String[] { "!A", "0110001" },
			new String[] { "!M", "1110001" },
			new String[] { "-D", "0001111" },
			new String[] { "-A", "0110011" },
			new String[] { "-M", "1110011" },
			new String[] { "D+1", "0011111" },
			new String[] { "A+1", "0110111" },
			new String[] { "M+1", "1110111" },
			new String[] { "D-1", "0001110" },
			new String[] { "A-1", "0110010" },
			new String[] { "M-1", "1110010" },
			new String[] { "D+A", "0000010" },
			new String[] { "D+M", "1000010" },
			new String[] { "D-A", "0010011" },
			new String[] { "D-M", "1010011" },
			new String[] { "A-D", "0000111" },
			new String[] { "M-D", "1000111" },
			new String[] { "D&A", "0000000" },
			new String[] { "D&M", "1000000" },
			new String[] { "D|A", "0010101" },
			new String[] { "D|M", "1010101" }
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
	public void compMnemonics_ReturnCorrectCodes() {
		String actual = this.code.comp(this.mnemonic);
		assertEquals(this.expected, actual);
	}
}
