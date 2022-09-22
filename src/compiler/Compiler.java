package compiler;

import parser.Parser;
import scanner.Scanner;
import scanner.SourceFile;

import javax.swing.*;

public class Compiler
{
     private static final String EXAMPLES_DIR = "C:\\Users\\anton\\OneDrive - ViaUC\\Semester 6 and 7\\CMC1\\gamer_lang_compiler\\test";

     public static void main(String[] args)
     {
          JFileChooser fc = new JFileChooser(EXAMPLES_DIR);

          if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
          {
               SourceFile sourceFile = new SourceFile(fc.getSelectedFile().getAbsolutePath());
               Scanner s = new Scanner(sourceFile);
               Parser p = new Parser(s);
               p.parseProgram();
          }
     }
}
