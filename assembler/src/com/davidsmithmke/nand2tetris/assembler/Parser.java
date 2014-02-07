package com.davidsmithmke.nand2tetris.assembler;

import java.io.File;

public class Parser {
	// Opens the input file/stream and gets ready to parse it.
	public Parser(File source) {
		throw new RuntimeException();
	}
	
	// Are there more commands in the input?
	public boolean hasMoreCommands() {
		throw new RuntimeException();
	}
	
	// Reads the next command from 	the input and makes it the current command.
	// Should be called only if hasMoreCommands() is true. Initially there is no
	// current command.
	public void advance() {
		throw new RuntimeException();
	}
	
	// Returns the type of the current command:
	// - A_COMMAND for @Xxx where Xxx is either a symbol or a decimal number
	// - C_COMMAND for dest=comp;jump
	// - L_COMMAND (actually psuedo-command) for (Xxx) where Xxx is a symbol.
	public CommandType commandType() {
		throw new RuntimeException();
	}
	
	// Returns the symbol or decimal Xxx of the current command @Xxx or (Xxx).
	// Should be called only when commandType() is A_COMMAND or L_COMMAND.
	public String symbol() {
		throw new RuntimeException();
	}
	
	// Returns the dest mnemonic of the currrent C-command (8 possibilities).
	// Should be called only when commandType() is C_COMMAND.
	public String dest() {
		throw new RuntimeException();
	}
	
	// Returns the comp mnemonic of the currrent C-command (28 possibilities).
	// Should be called only when commandType() is C_COMMAND.
	public String comp() {
		throw new RuntimeException();
	}
	
	// Returns the jump mnemonic of the currrent C-command (28 possibilities).
		// Should be called only when commandType() is C_COMMAND.
		public String jump() {
			throw new RuntimeException();
		}
}
