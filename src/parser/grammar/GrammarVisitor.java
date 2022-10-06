package parser.grammar;

import parser.Parser;
import parser.ast.AbstractSyntaxTree;

public interface GrammarVisitor<T extends AbstractSyntaxTree>
{
     T visit(Parser parser);
}
