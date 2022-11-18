package checker;

import ast.Declaration;

import java.util.Vector;

public class IdentificationTable {
    private Vector<IdentificationEntry> table = new Vector<IdentificationEntry>();
    private int level = 0;


    public IdentificationTable()
    {

    }


    public void enter( String id, Declaration attr )
    {
        IdentificationEntry entry = find( id );

        if( entry != null && entry.level == level )
            System.out.println( id + " declared twice" );
        else
            table.add( new IdentificationEntry( level, id, attr ) );
    }


    public Declaration retrieve( String id )
    {
        IdentificationEntry entry = find( id );

        if( entry != null )
            return entry.attr;
        else
            return null;
    }


    public void openScope()
    {
        //add new scope
        ++level;
    }


    public void closeScope()
    {
        //remove scope and all identifiers from that scope
        int pos = table.size() - 1;
        while( pos >= 0 && table.get(pos).level == level ) {
            table.remove( pos );
            pos--;
        }
        level--;
    }


    private IdentificationEntry find(String id )
    {
        for( int i = table.size() - 1; i >= 0; i-- )
            if( table.get(i).id.equals( id ) )
                return table.get(i);

        return null;
    }
}
