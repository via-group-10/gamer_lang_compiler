package ast;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class AbstractSyntaxTreeViewer extends JFrame {
    private static final Font NODE_FONT = new Font("Verdana", Font.PLAIN, 24);


    public AbstractSyntaxTreeViewer(AbstractSyntaxTree ast) {
        super("Abstract Syntax Tree");

        DefaultMutableTreeNode root = createTree(ast);
        JTree tree = new JTree(root);
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setFont(NODE_FONT);
        tree.setCellRenderer(renderer);

        add(new JScrollPane(tree));

        setSize(1024, 768);
        setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private DefaultMutableTreeNode createTree(AbstractSyntaxTree ast) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode("*** WHAT??? ***");

        if (ast == null)
            node.setUserObject("*** NULL ***");

        else if (ast instanceof Program program) {
            node.setUserObject("Program");
            node.add(createTree(program.getBlock()));
        } else if (ast instanceof Block block) {
            node.setUserObject("Block");
            if (block.hasDeclarations())
                node.add(createTree((block.getDeclarations())));
            node.add(createTree(block.getStatements()));
        } else if (ast instanceof Declarations declarations) {
            node.setUserObject("Declarations");
            for (Declaration d : declarations.getAllDeclarations())
                node.add(createTree(d));
        } else if (ast instanceof VariableDeclaration varDec) {
            node.setUserObject("VariableDeclaration");
            node.add(createTree(varDec.getIdentifier()));
        } else if (ast instanceof FunctionDeclaration funDec) {
            node.setUserObject("FunctionDeclaration");
            node.add(createTree(funDec.getIdentifier()));
            node.add(createTree((funDec.getArguments())));
            node.add(createTree(funDec.getBlock()));
            node.add(createTree((funDec.getMvpExpression())));
        } else if (ast instanceof Statements statements) {
            node.setUserObject("Statements");

            for (Statement s : statements.getStatements())
                node.add(createTree(s));
        } else if (ast instanceof ExpressionStatement es) {
            node.setUserObject("ExpressionStatement");
            node.add(createTree(es.getExpression()));
        } else if (ast instanceof OpStatement os) {
            node.setUserObject("OpStatement");
            node.add(createTree(os.getComparisonExpression()));
            node.add(createTree(os.getPvpStatements()));
            node.add(createTree(os.getPveStatements()));
        } else if (ast instanceof PatchStatement ps) {
            node.setUserObject("WhileStatement");
            node.add(createTree(ps.getExpression()));
            node.add(createTree(ps.getStatements()));
        } else if (ast instanceof ChatStatement cs) {
            node.setUserObject("ChatStatement");
            node.add(createTree(cs.getExpression()));
        } else if (ast instanceof FeedStatement fs) {
            node.setUserObject("FeedStatement");
            node.add(createTree(fs.getIdentifier()));
        } else if (ast instanceof BinaryExpression be) {
            node.setUserObject("BinaryExpression");
            node.add(createTree(be.getOperator()));
            node.add(createTree(be.getExpressionLeft()));
            node.add(createTree(be.getExpressionRight()));
        } else if (ast instanceof FunctionExpression fe) {
            node.setUserObject("FunctionExpression");
            node.add(createTree(fe.getIdentifier()));
            node.add(createTree((fe.getArguments())));
        } else if (ast instanceof IntegerLiteralExpression ile) {
            node.setUserObject("IntegerLiteralExpression");
            node.add(createTree(ile.getIntegerLiteral()));
        } else if (ast instanceof CharacterLiteralExpression cle) {
            node.setUserObject("CharacterLiteralExpression");
            node.add(createTree(cle.getCharacterLiteral()));
        } else if (ast instanceof UnaryExpression ue) {
            node.setUserObject("UnaryExpression");
            node.add(createTree(ue.getExpression()));
            node.add(createTree(ue.getOperator()));
        } else if (ast instanceof VariableExpression ve) {
            node.setUserObject("VariableExpression");
            node.add(createTree(ve.getIdentifier()));
        } else if (ast instanceof ExpressionList el) {
            node.setUserObject("ExpressionList");
            for (Expression e : el.getAllExpressions())
                node.add(createTree(e));
        } else if (ast instanceof Identifier id) {
            node.setUserObject("Identifier " + id.getName() + " " + id.getType());
        } else if (ast instanceof IntegerLiteral il) {
            node.setUserObject("IntegerLiteral " + il.getName());
        } else if (ast instanceof CharacterLiteral cl) {
            node.setUserObject("CharacterLiteral " + cl.getName());
        } else if (ast instanceof Operator op) {
            node.setUserObject("Operator " + op.getName());
        }

        return node;
    }
}
