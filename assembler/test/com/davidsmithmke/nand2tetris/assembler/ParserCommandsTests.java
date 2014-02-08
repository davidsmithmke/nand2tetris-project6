package com.davidsmithmke.nand2tetris.assembler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ParserCommandsTests {
	public static File testFile;
	
	public Parser parser;
	
	@BeforeClass
	public static void setUpClass() {
		try {
			ParserCommandsTests.testFile =
					File.createTempFile("commands", "asm");
			ParserCommandsTests.writeTestData();
		} catch (IOException e) {
			fail();
		}
	}
	
	@AfterClass
	public static void tearDownClass() {
		ParserCommandsTests.testFile.delete();
	}
	
	@Before
	public void setUp() throws IOException {
		this.parser = new Parser(ParserCommandsTests.testFile);
	}
	
	@After
	public void tearDown() {
		this.parser.close();
	}
	
	public static void writeTestData() {
		FileWriter fw = null;
		try {
			fw = new FileWriter(ParserCommandsTests.testFile);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write("(LCOMMAND)");
			writer.newLine();
			writer.write("@acommand");
			writer.newLine();
			writer.write("AD=0");
			writer.newLine();
			writer.write("(BEGIN)");
			writer.newLine();
			writer.write("@variable");
			writer.newLine();
			writer.write("AMD=D|A;JLE");
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	public void commandType_ReturnsCorrectCommandTypes() throws IOException {
		CommandType command = null;
		
		parser.advance();
		command = parser.commandType();
		assertEquals(CommandType.L_COMMAND, command);
		parser.advance();
		command = parser.commandType();
		assertEquals(CommandType.A_COMMAND, command);
		parser.advance();
		command = parser.commandType();
		assertEquals(CommandType.C_COMMAND, command);
		parser.advance();
		command = parser.commandType();
		assertEquals(CommandType.L_COMMAND, command);
		parser.advance();
		command = parser.commandType();
		assertEquals(CommandType.A_COMMAND, command);
		parser.advance();
		command = parser.commandType();
		assertEquals(CommandType.C_COMMAND, command);
	}
	
	@Test
	public void symbol_ReturnsCorrectMnemonic() throws IOException {
		String symbol = null;
		
		parser.advance();
		symbol = parser.symbol();
		assertEquals("LCOMMAND", symbol);
		parser.advance();
		symbol = parser.symbol();
		assertEquals("acommand", symbol);
		parser.advance();
		parser.advance();
		symbol = parser.symbol();
		assertEquals("BEGIN", symbol);
		parser.advance();
		symbol = parser.symbol();
		assertEquals("variable", symbol);
	}
	
	@Test
	public void dest_ReturnsCorrectMnemonic() throws IOException {
		String dest = null;
		
		parser.advance();
		parser.advance();
		parser.advance();
		dest = parser.dest();
		assertEquals("AD", dest);
		parser.advance();
		parser.advance();
		parser.advance();
		dest = parser.dest();
		assertEquals("AMD", dest);
	}
	
	@Test
	public void comp_ReturnsCorrectMnemonic() throws IOException {
		String comp = null;
		
		parser.advance();
		parser.advance();
		parser.advance();
		comp = parser.comp();
		assertEquals("0", comp);
		parser.advance();
		parser.advance();
		parser.advance();
		comp = parser.comp();
		assertEquals("D|A", comp);
	}
	
	@Test
	public void jump_ReturnsCorrectMnemonic() throws IOException {
		String jump = null;
		
		parser.advance();
		parser.advance();
		parser.advance();
		jump = parser.jump();
		assertNull(jump);
		parser.advance();
		parser.advance();
		parser.advance();
		jump = parser.jump();
		assertEquals("JLE", jump);
	}
}
