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
               Scanner s = new Scanner(sourceFile);
               Parser p = new Parser(s);
               AbstractSyntaxTree ast = p.parseProgram();
               AbstractSyntaxTreeViewer astv = new AbstractSyntaxTreeViewer(ast);

          }
     }
}
