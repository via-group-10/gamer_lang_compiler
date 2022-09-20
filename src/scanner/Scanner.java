package scanner;

public class Scanner
{
     private SourceFile source;
     private char currentChar;
     private StringBuffer currentSpelling = new StringBuffer();

     public Scanner(SourceFile source)
     {
          this.source = source;
          currentChar = source.getNextChar();
     }

     public Token scan()
     {
          while( currentChar == '$' || currentChar == '\n' ||
               currentChar == '\r' || currentChar == '\t' ||
               currentChar == ' ' ) {
               scanSeparator();
          }
          currentSpelling = new StringBuffer( "" );
          TokenKind kind = scanToken();

          return new Token( kind, new String( currentSpelling ) );
     }

     private void takeIt()
     {
          currentSpelling.append(currentChar);
          currentChar = source.getNextChar();
     }

     private boolean isLetter(char c)
     {
          return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
     }

     private boolean isDigit(char c)
     {
          return c >= '0' && c <= '9';
     }

     private boolean isSingleQuote(char c)
     {
          return c == '\'';
     }

     private void scanSeparator()
     {
          switch(currentChar)
          {
               case '?':
                    takeIt();
                    while (currentChar != source.EOL && currentChar != source.EOF)
                         takeIt();

                    if (currentChar == source.EOL)
                         takeIt();
                    break;

               case ' ':
               case '\n':
               case '\r':
               case '\t':
                    takeIt();
                    break;
          }
     }

     private TokenKind scanToken()
     {
          if (isLetter(currentChar))
          {
               takeIt();
               while (isLetter(currentChar) || isDigit(currentChar))
                    takeIt();

               return TokenKind.IDENTIFIER;
          }
          else if (isDigit(currentChar))
          {
               takeIt();
               while (isDigit(currentChar))
                    takeIt();
               return TokenKind.INTEGERLITERAL;
          }
          // Zaujimave, bo charakter musi mat single quote, dalsi character,
          // a ak dalsi charakter nie je single quote, tak nastala chyba
          else if (isSingleQuote(currentChar))
          {
               takeIt();
               takeIt();
               if (!isSingleQuote(currentChar))
                    return TokenKind.ERROR;
               takeIt();
               return TokenKind.CHARACTERLITERAL;
          }
          switch (currentChar)
          {
               case '+':
               case '-':
               case '*':
               case '^':
               case '/':
               case '%':
               case '>':
                    takeIt();
                    return TokenKind.OPERATOR;

               case '=':
                    takeIt();
                    if (currentChar == '=')
                         takeIt();
                    return TokenKind.OPERATOR;

               case '<':
                    takeIt();
                    if (currentChar == '>')
                         takeIt();
                    return TokenKind.OPERATOR;

               case ',':
                    takeIt();
                    return TokenKind.COMMA;
               case '!':
                    takeIt();
                    if (currentChar == '!')
                    {
                         takeIt();
                         if (currentChar == '!')
                         {
                              takeIt();
                              return TokenKind.TRIPLEEXCLAMATIONMARK;
                         }
                    }
                    return TokenKind.ERROR;
               case '(':
                    takeIt();
                    return TokenKind.LEFTPARAN;
               case ')':
                    takeIt();
                    return TokenKind.RIGHTPARAN;
               case SourceFile.EOF:
                    return TokenKind.EOF;
               default:
                    takeIt();
                    return TokenKind.ERROR;
          }
     }
}
