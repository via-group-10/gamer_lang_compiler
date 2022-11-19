package compiler;

import parser.Parser;
import parser.ast.AbstractSyntaxTree;
import parser.ast.AbstractSyntaxTreeViewer;
import scanner.Scanner;
import scanner.SourceFile;

import javax.swing.*;

public class Compiler
{
     private static final String EXAMPLES_DIR = "test";

     public static void main(String[] args)
     {
          JFileChooser fc = new JFileChooser(EXAMPLES_DIR);

          if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
          {
               SourceFile sourceFile = new SourceFile(fc.getSelectedFile().getAbsolutePath());
               Scanner scanner = new Scanner(sourceFile);
               Parser parser = new Parser(scanner);
               AbstractSyntaxTree ast = parser.parseProgram();

               AbstractSyntaxTreeViewer viewer = new AbstractSyntaxTreeViewer(ast);
               AbstractSyntaxTreePrinter printer = new AbstractSyntaxTreePrinter(ast);

          }
     }
}
