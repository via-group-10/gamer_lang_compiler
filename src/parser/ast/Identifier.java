package parser.ast;

public class Identifier extends Terminal
{
     private String type;

     public Identifier(String type, String name)
     {
          super(name);
          this.type = type;
     }

     public String getType()
     {
          return type;
     }
}
