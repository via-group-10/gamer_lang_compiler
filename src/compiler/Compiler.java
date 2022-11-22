package compiler;

import ast.Program;
import checker.Checker;
import parser.Parser;
import ast.AbstractSyntaxTreeViewer;
import scanner.Scanner;
import scanner.SourceFile;
import wasm.Generator;

import static wasm.Encoding.printHex;
import static wasm.Encoding.writeToFile;

public class Compiler
{
    private static final String EXAMPLES_DIR = "test";

    public static void main(String[] args)
    {
        // Scanner
        final String inputPath = "test/program.txt";
        SourceFile sourceFile = new SourceFile(inputPath);
        Scanner s = new Scanner(sourceFile);

        // Parser
        Parser p = new Parser(s);
        Program program = p.parseProgram();

        //Abstract Syntax Tree Viewer
        AbstractSyntaxTreeViewer astv = new AbstractSyntaxTreeViewer(program);

        // Checking
        Checker checker = new Checker();
        checker.check( program );

        // Encoding
        Generator generator = new Generator();
        final String outputPath = "/Users/janlishak/Desktop/CMC/chasm/web/module.wasm";
        int[] machineInstructions = generator.generate(program);
        System.out.println("WASI HEX FORMAT: ");
        printHex(machineInstructions);
        writeToFile(machineInstructions, "/Users/janlishak/Desktop/CMC/chasm/web/module.wasm");
    }
}
