package cool.AST;

import cool.parser.CoolParser;
import cool.parser.CoolParserBaseVisitor;
import java.util.ArrayList;

import org.antlr.v4.runtime.Token;

/**
 * In aceasta clasa construiesc arborele de parsare, care,
 * pe baza fisierului CoolParser.g4, va construi nodurile aferente.
 */
public class CreateParseTree extends CoolParserBaseVisitor<ASTNode> {
    @Override
    public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
        String program_name = "program";
        ArrayList<Class> classes = new ArrayList<>();

        for (int i = 0; i < ctx.classes.size(); i++) {
            classes.add((Class) visit(ctx.classes.get(i)));
        }

        return new Program(program_name, classes);
    }

    @Override
    public ASTNode visitClass_definition(CoolParser.Class_definitionContext ctx) {
        Token class_name = ctx.name;
        Token parent_name = ctx.parent_class_name;
        ArrayList<Feature> features = new ArrayList<>();

        for (int i = 0; i < ctx.features.size(); i++) {
            features.add((Feature) visit(ctx.features.get(i)));
        }

        return new Class("class", class_name, parent_name, features);
    }

    @Override
    public ASTNode visitFuncDecl(CoolParser.FuncDeclContext ctx) {
        Token func_name = ctx.func_name;
        Token func_type = ctx.type;
        ArrayList<Declaration> declarations = new ArrayList<>();
        ArrayList<Expression> expressions = new ArrayList<>();

        for (int i = 0; i < ctx.declarations.size(); i++) {
            declarations.add((Declaration) visit(ctx.declarations.get(i)));
        }

        for (int i = 0; i < ctx.lines.size(); i++) {
            expressions.add((Expression) visit(ctx.lines.get(i)));
        }

        return new FuncDecl("method", func_name, func_type, declarations, expressions);
    }

    @Override
    public ASTNode visitDeclaration(CoolParser.DeclarationContext ctx) {
        Token id = ctx.id;
        Token type = ctx.type;

        return new Declaration("formal", id, type);
    }

    @Override
    public ASTNode visitVarDeclAndInit(CoolParser.VarDeclAndInitContext ctx) {
        Token var_name = ctx.var_name;
        Token type = ctx.type;
        Expression value = null;

        if (ctx.value != null) {
            value = (Expression) visit(ctx.value);
        }

        return new VarDeclAndInit("local", var_name, type, value);
    }

    @Override
    public ASTNode visitVarDecl(CoolParser.VarDeclContext ctx) {
        Token var_name = ctx.var_name;
        Token type = ctx.type;
        Expression value = null;

        if (ctx.value != null) {
            value = (Expression) visit(ctx.value);
        }
        return new VarDecl("attribute", var_name, type, value);
    }

    @Override
    public ASTNode visitDiv(CoolParser.DivContext ctx) {
        Expression first_operand = (Expression) visit(ctx.left_op);
        Expression second_operand = (Expression) visit(ctx.right_op);

        return new Div("/", first_operand, second_operand);
    }

    @Override
    public ASTNode visitMinus(CoolParser.MinusContext ctx) {
        Expression first_operand = (Expression) visit(ctx.left_op);
        Expression second_operand = (Expression) visit(ctx.right_op);

        return new Div("-", first_operand, second_operand);
    }

    @Override
    public ASTNode visitMult(CoolParser.MultContext ctx) {
        Expression first_operand = (Expression) visit(ctx.left_op);
        Expression second_operand = (Expression) visit(ctx.right_op);

        return new Div("*", first_operand, second_operand);
    }

    @Override
    public ASTNode visitPlus(CoolParser.PlusContext ctx) {
        Expression first_operand = (Expression) visit(ctx.left_op);
        Expression second_operand = (Expression) visit(ctx.right_op);

        return new Div("+", first_operand, second_operand);
    }

    @Override
    public ASTNode visitNegExpr(CoolParser.NegExprContext ctx) {
        Expression unary_op = (Expression) visit(ctx.operand);

        return new Neg("~", unary_op);
    }

    @Override
    public ASTNode visitParen(CoolParser.ParenContext ctx) {
        Expression expression = (Expression) visit(ctx.value);

        return new Paren("paren", expression);
    }

    @Override
    public ASTNode visitRelOp(CoolParser.RelOpContext ctx) {
        Expression left = (Expression) visit(ctx.left_op);
        Expression right = (Expression) visit(ctx.right_op);

        return new RelOp(ctx.op.getText(), left, right);
    }

    @Override
    public ASTNode visitNotExpr(CoolParser.NotExprContext ctx) {
        Expression unary_op = (Expression) visit(ctx.op);

        return new Not("not", unary_op);
    }

    @Override
    public ASTNode visitInt(CoolParser.IntContext ctx) {
        return new Int(ctx.getText());
    }

    @Override
    public ASTNode visitBool(CoolParser.BoolContext ctx) {
        return new Bool(ctx.getText());
    }

    @Override
    public ASTNode visitString(CoolParser.StringContext ctx) {
        return new StringType(ctx.getText());
    }

    @Override
    public ASTNode visitId(CoolParser.IdContext ctx) {
        return new Id(ctx.getText());
    }

    @Override
    public ASTNode visitVarAssign(CoolParser.VarAssignContext ctx) {
        Id var_name = new Id(ctx.var_name.getText());
        Expression value = (Expression) visit(ctx.value);

        return new Assign("<-", var_name, value);
    }

    @Override
    public ASTNode visitIf(CoolParser.IfContext ctx) {
        Expression cond = (Expression) visit(ctx.cond);
        Expression then_b = (Expression) visit(ctx.thenBranch);
        Expression else_b = (Expression) visit(ctx.elseBranch);

        return new If(cond, then_b, else_b, "if");
    }

    @Override
    public ASTNode visitWhile(CoolParser.WhileContext ctx) {
        Expression cond = (Expression) visit(ctx.cond);
        Expression line = (Expression) visit(ctx.line);

        return new While("while", cond, line);
    }

    @Override
    public ASTNode visitLet(CoolParser.LetContext ctx) {
        ArrayList<VarDeclAndInit> declarations = new ArrayList<>();
        Expression line = null;

        for (int i = 0; i < ctx.declarations.size(); i++) {
            declarations.add((VarDeclAndInit) visit(ctx.declarations.get(i)));
        }

        if (ctx.line != null) {
            line = (Expression) visit(ctx.line);
        }

        return new Let("let", line, declarations);
    }

    @Override
    public ASTNode visitIsvoid(CoolParser.IsvoidContext ctx) {
        Expression expression = (Expression) visit(ctx.op);

        return new IsVoid("isvoid", expression);
    }

    @Override
    public ASTNode visitInstantiation(CoolParser.InstantiationContext ctx) {
        Token type = ctx.type;

        return new Instantiation("new", type);
    }

    @Override
    public ASTNode visitCase(CoolParser.CaseContext ctx) {
        Expression var_name = (Expression) visit(ctx.var_name);
        ArrayList<Token> vars = new ArrayList<>(ctx.vars);
        ArrayList<Token> types = new ArrayList<>(ctx.types);
        ArrayList<Expression> branches = new ArrayList<>();

        for (int i = 0; i < ctx.branches.size(); i++) {
            branches.add((Expression) visit(ctx.branches.get(i)));
        }

        return new Case("case", var_name, vars, types, branches);
    }

    @Override
    public ASTNode visitBlockSection(CoolParser.BlockSectionContext ctx) {
        ArrayList<Expression> lines = new ArrayList<>();

        for (int i = 0; i < ctx.line.size(); i++) {
            lines.add((Expression) visit(ctx.line.get(i)));
        }

        return new Block("block", lines);
    }

    @Override
    public ASTNode visitMethod(CoolParser.MethodContext ctx) {
        Expression func_name = (Expression) visit(ctx.func_name);
        Token type = null;
        Token method_name = ctx.met_name;
        ArrayList<Expression> args = null;

        if (ctx.type != null) {
            type = ctx.type;
        }

        if (ctx.args != null) {
            args = new ArrayList<>();

            for (int i = 0; i < ctx.args.size(); i++) {
                args.add((Expression) visit(ctx.args.get(i)));
            }
        }

        return new Method("explicit dispatch", func_name, type, method_name, args);
    }

    @Override
    public ASTNode visitFuncCall(CoolParser.FuncCallContext ctx) {
        Token func_name = ctx.func_name;
        ArrayList<Expression> args = null;

        if (ctx.args != null) {
            args = new ArrayList<>();

            for (int i = 0; i < ctx.args.size(); i++) {
                args.add((Expression) visit(ctx.args.get(i)));
            }
        }

        return new FuncCall("implicit dispatch", func_name, args);
    }
}
