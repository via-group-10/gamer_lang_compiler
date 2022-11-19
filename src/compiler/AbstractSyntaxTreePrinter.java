package compiler;

import parser.ast.AbstractSyntaxTree;

public class AbstractSyntaxTreePrinter {
    private AbstractSyntaxTree root;
    public AbstractSyntaxTreePrinter(AbstractSyntaxTree ast){
        this.root = ast;
        this.print(root, 0);
    }

    public void print(AbstractSyntaxTree tree, int level){
        for (AbstractSyntaxTree node: tree.getNodes()) {
            if(node.getNodes() == null) continue;
            System.out.println("  ".repeat(level) + node.getClass().getSimpleName());
            for (AbstractSyntaxTree subnode: node.getNodes()) {
                print(node, level+1);
            }
        }
    }

}
