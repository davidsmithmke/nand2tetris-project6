package com.davidsmithmke.nand2tetris.assembler;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ParserCommandsTests.class, ParserHasMoreCommandsTests.class,
		ParserInitializationTests.class })
public class ParserTests {

}
