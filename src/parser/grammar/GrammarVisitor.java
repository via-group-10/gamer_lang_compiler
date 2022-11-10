package parser.grammar;

import parser.Parser;
import ast.AbstractSyntaxTree;

public interface GrammarVisitor<T extends AbstractSyntaxTree>
{
     T visit(Parser parser);
}
