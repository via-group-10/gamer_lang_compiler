package scanner;

import javax.swing.*;

public class ScannerTest
{
     private static final String EXAMPLES_DIR = "C:\\Users\\anton\\OneDrive - ViaUC\\Semester 6 and 7\\CMC1\\gamer_lang_compiler\\test";

     public static void main(String[] args)
     {
          JFileChooser fc = new JFileChooser(EXAMPLES_DIR);

          if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
          {
               SourceFile sourceFile = new SourceFile(fc.getSelectedFile().getAbsolutePath());
               Scanner s = new Scanner(sourceFile);

               TokenKind k;
               do {
                  Token t = s.scan();
                  k = t.kind;
                    System.out.println(k + " " + t.spelling);
               } while (k != TokenKind.EOF);
          }
     }
}
