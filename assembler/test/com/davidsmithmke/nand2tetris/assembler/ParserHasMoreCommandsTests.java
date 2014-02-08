package com.davidsmithmke.nand2tetris.assembler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ParserHasMoreCommandsTests {
	public static File emptyFile;
	public static File nonEmptyFile;
	
	@BeforeClass
	public static void setUpClass() {
		try {
			ParserHasMoreCommandsTests.emptyFile =
					File.createTempFile("empty", "asm");
			ParserHasMoreCommandsTests.nonEmptyFile =
					File.createTempFile("nonempty", "asm");
			ParserHasMoreCommandsTests.writeTestData();
		} catch (IOException e) {
			fail();
		}
	}
	
	@AfterClass
	public static void tearDownClass() {
		ParserHasMoreCommandsTests.emptyFile.delete();
		ParserHasMoreCommandsTests.nonEmptyFile.delete();
	}
	
	public static void writeTestData() {
		FileWriter fw = null;
		try {
			fw = new FileWriter(ParserHasMoreCommandsTests.nonEmptyFile);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write("ADD A,D");
			writer.close();
		} catch (IOException e) {
			fail();
		}
	}

	@Test
	public void emptyFile_DoesNotHaveMoreCommands()
			throws IOException {
		Parser parser = new Parser(ParserHasMoreCommandsTests.emptyFile);
		assertFalse(parser.hasMoreCommands());
	}

	@Test
	public void nonEmptyFile_HasMoreCommands()
			throws IOException {
		Parser parser = new Parser(ParserHasMoreCommandsTests.nonEmptyFile);
		assertTrue(parser.hasMoreCommands());
	}
}
