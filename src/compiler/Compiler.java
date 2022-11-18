package compiler;

import ast.Program;
import checker.Checker;
import parser.Parser;
import ast.AbstractSyntaxTree;
import ast.AbstractSyntaxTreeViewer;
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
               Program program = p.parseProgram();
               AbstractSyntaxTreeViewer astv = new AbstractSyntaxTreeViewer(program);

               Checker checker = new Checker();
               checker.check( program );
               System.out.println(program);
          }
     }
}
