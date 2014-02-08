package com.davidsmithmke.nand2tetris.assembler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class Assembler {
	private File assemblyCode;
	private BufferedWriter machineCode;
	private Code encoder;
	
	public Assembler(File source, File target) throws IOException {
		this.assemblyCode = source;
		
		// Create buffered writer.
		FileWriter fw = new FileWriter(target);
		this.machineCode = new BufferedWriter(fw);
		
		// Initialize assembler components.
		this.encoder = new Code();
	}
	
	// Translate assembly file to machine language.
	public void translate() throws IOException {
		this.parse();
	}
		
	// Parse source file.
	private void parse() throws IOException {
		Parser parser = new Parser(this.assemblyCode);
		while (parser.hasMoreCommands()) {
			parser.advance();
	
			CommandType commandType = parser.commandType();
			String instruction = "1110101010000000";  // No op instruction
			
			if (commandType.equals(CommandType.A_COMMAND)) {
				// Format A-Instruction.
				String address = parser.symbol();
				instruction = this.formatAInstruction(address);
			} else if (commandType.equals(CommandType.C_COMMAND)) {
				// Format C-Instruction
				String comp = parser.comp();
				String dest = parser.dest();
				String jump = parser.jump();
				instruction = this.formatCInstruction(comp, dest, jump);
			}
	
			// Write binary instruction to file.
			this.machineCode.write(instruction);
			this.machineCode.newLine();
		}
		
		// Release resources.
		parser.close();
		this.machineCode.flush();
		this.machineCode.close();
	}

	// Machine-format an A-Instruction.
	private String formatAInstruction(String address) {
		String formattedNumber = this.encoder.formatNumberAsBinary(address);
		return "0" + formattedNumber;
	}
	
	// Machine-format a C-Instruction.
	private String formatCInstruction( String comp, String dest, String jump) {
		StringWriter instruction = new StringWriter();
		instruction.append("111");
		instruction.append(this.encoder.comp(comp));
		instruction.append(this.encoder.dest(dest));
		instruction.append(this.encoder.jump(jump));
		return instruction.toString();
	}
}
