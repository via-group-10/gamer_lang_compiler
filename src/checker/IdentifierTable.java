package checker;

import ast.Declaration;

import java.util.Vector;

public class IdentifierTable {
    private Vector<IdentifierId> table = new Vector<IdentifierId>();
    private int level = 0;


    public IdentifierTable()
    {

    }


    public void enter( String id, Declaration attr )
    {
        IdentifierId entry = find( id );

        if( entry != null && entry.level == level )
            System.out.println( id + " declared twice" );
        else
            table.add( new IdentifierId( level, id, attr ) );
    }


    public Declaration retrieve( String id )
    {
        IdentifierId entry = find( id );

        if( entry != null )
            return entry.attr;
        else
            return null;
    }


    public void openScope()
    {
        ++level;
    }


    public void closeScope()
    {
        int pos = table.size() - 1;
        while( pos >= 0 && table.get(pos).level == level ) {
            table.remove( pos );
            pos--;
        }

        level--;
    }


    private IdentifierId find( String id )
    {
        for( int i = table.size() - 1; i >= 0; i-- )
            if( table.get(i).id.equals( id ) )
                return table.get(i);

        return null;
    }
}
