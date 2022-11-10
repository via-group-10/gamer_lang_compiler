package checker;

import ast.*;
import checker.*;

import java.util.Vector;

public class Checker implements AbstractSyntaxTreeVisitor {
    private IdentifierTable idTable = new IdentifierTable();

    public void check( Program p )
    {
        p.accept( this, null );
    }

    @Override
    public Object visit(Program astNode, Object arg) {
        idTable.openScope();
        astNode.getBlock().accept(this, arg);
        idTable.closeScope();
        return null;

    }

    @Override
    public Object visit(Block astNode, Object arg) {
        astNode.accept(this,null);
        astNode.getStatements().accept(this,null );

        return null;    }

    @Override
    public Object visit(BinaryExpression dec, Object arg) {

        Type t1 = (Type) dec.getExpressionLeft().accept( this, null );
        Type t2 = (Type) dec.getExpressionRight().accept( this, null );

        String operator = (String)  dec.getOperator().accept(this, null);

        if( operator.equals( ":=" ) && t1.rvalueOnly )
            System.out.println( "Left-hand side of := must be a variable" );

        return new Type( true );
    }

    @Override
    //toto neznam co je
    public Object visit(CharacterLiteral dec, Object arg) {

        return null;
    }

    @Override
    //neznam ci uplne dobre
    public Object visit(CharacterLiteralExpression dec, Object arg) {
        dec.getCharacterLiteral().accept(this, null);
        return null;
    }

    @Override
    public Object visit(ChatStatement dec, Object arg) {
        dec.getExpression().accept(this, null);
        return null;
    }

    @Override
    public Object visit(ExpressionStatement dec, Object arg) {
        dec.getExpression().accept(this, null);

        return null;
    }

    @Override
    //tiez neviem ci dobre
    public Object visit(FeedStatement dec, Object arg) {
        dec.getIdentifier().accept(this, null);
        return null;
    }

    @Override
    public Object visit(FunctionDeclaration dec, Object arg) {
        String id = (String) dec.getIdentifier().accept(this, null);

        idTable.enter( id, dec );
        idTable.openScope();

        dec.getArguments().accept( this, null );
        dec.getBlock().accept( this, null );
        dec.getMvpExpression().accept( this, null );

        idTable.closeScope();

        return null;
    }

    @Override
    public Object visit(FunctionExpression dec, Object arg) {
        String id = (String) dec.getIdentifier().accept( this, null );
        Vector<Type> t = (Vector<Type>)( dec.getArguments().accept( this, null ) );

        Declaration d = idTable.retrieve( id );
        if( d == null )
            System.out.println( id + " is not declared" );
        else if( !( d instanceof FunctionDeclaration ) )
            System.out.println( id + " is not a function" );
        else {
            FunctionDeclaration f = (FunctionDeclaration) d;

            //tu chyba function declaration? pridal som podlha OG Janka
            dec.decl = f;

            if( f.getArguments().getAllDeclarations().size() != t.size() )
                System.out.println( "Incorrect number of arguments in call to " + id );
        }

        return new Type( true );
    }

    @Override
    // nema byt nic
    public Object visit(IntegerLiteral dec, Object arg) {
        return null;
    }

    @Override
    public Object visit(IntegerLiteralExpression dec, Object arg) {
        dec.getIntegerLiteral().accept(this, true);
        return new Type(true);
    }

    @Override
    public Object visit(Operator dec, Object arg) {
       return  dec.getName();
    }

    @Override
    public Object visit(OpStatement dec, Object arg) {
        dec.getComparisonExpression().accept(this, null);
        dec.getPvpStatements().accept(this,null);
        dec.getPveStatements().accept(this,null);
        return null;

    }

    @Override
    public Object visit(PatchStatement dec, Object arg) {
        dec.getExpression().accept( this, null );
        dec.getStatements().accept( this, null );

        return null;
    }

    @Override
    public Object visit(VariableDeclaration dec, Object arg) {
        String id = (String) dec.getIdentifier().accept(this, null);

        idTable.enter( id, dec );
        return null;
    }

    @Override
    public Object visit(VariableExpression dec, Object arg) {

        return null;
    }
}
