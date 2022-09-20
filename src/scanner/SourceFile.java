package scanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SourceFile
{
     public static final char EOL = '\n';
     public static final char EOF = 0;

     private FileInputStream source;

     public SourceFile(String filename)
     {
          try
          {
               source = new FileInputStream(filename);

          }
          catch (FileNotFoundException ex)
          {
               System.out.println("File could not be found: " + filename);
               System.exit(1);
          }
     }

     public char getNextChar()
     {
          try
          {
               int c = source.read();
               if (c < 0)
                    return EOF;
               else
                    return (char) c;
          }
          catch  (IOException ex)
          {
               return EOF;
          }
     }
}
