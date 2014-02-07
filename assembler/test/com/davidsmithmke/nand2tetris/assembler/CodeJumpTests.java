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
public class CodeJumpTests {
	@Parameters(name="{0}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			new String[] { "", "000" }, 
			new String[] { "JGT", "001" },
			new String[] { "JEQ", "010" },
			new String[] { "JGE", "011" },
			new String[] { "JLT", "100" },
			new String[] { "JNE", "101" },
			new String[] { "JLE", "110" },
			new String[] { "JMP", "111" }
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
	public void jumpMnemonics_ReturnCorrectCodes() {
		String actual = this.code.jump(this.mnemonic);
		assertEquals(this.expected, actual);
	}
}
