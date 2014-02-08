package com.davidsmithmke.nand2tetris.assembler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ParserInitializationTests {
	public static File extantFile;
	public static File nonExtantFile;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@BeforeClass
	public static void setUpClass() {
		try {
			ParserInitializationTests.extantFile =
					File.createTempFile("extant", "asm");
			ParserInitializationTests.nonExtantFile =
					File.createTempFile("nonextant", "asm");
			ParserInitializationTests.nonExtantFile.delete();
		} catch (IOException e) {
			fail();
		}
	}
	
	@AfterClass
	public static void tearDownClass() {
		ParserInitializationTests.extantFile.delete();
	}
	
	@Test
	public void nullFile_ThrowsNullPointerException()
			throws IOException {
		thrown.expect(NullPointerException.class);
		Parser parser = new Parser(null);
		parser.close();
	}

	@Test
	public void extantFile_InitializesCorrectly() throws IOException {
		Parser parser = null;
		try {
			parser = new Parser(ParserInitializationTests.extantFile);
		} catch (FileNotFoundException e) {
			fail();
		} finally {
			parser.close();
		}
		assertNotNull(parser);
		parser.close();
	}
	
	@Test
	public void nonExtantFile_ThrowsFileNotFoundException()
			throws IOException {
		thrown.expect(FileNotFoundException.class);
		Parser parser = new Parser(ParserInitializationTests.nonExtantFile);
		parser.close();
	}
}
