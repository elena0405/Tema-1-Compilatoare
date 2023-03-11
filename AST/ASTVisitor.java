package cool.AST;

public interface ASTVisitor<T> {
    T visit(Id id);
    T visit(Int intt);
    T visit(Bool bool);
    T visit(If anIf);
    T visit(Program program);
    T visit(Class aClass);
    T visit(VarDecl varDecl);
    T visit(FuncDecl funcDecl);
    T visit(Declaration declaration);
    T visit(Plus plus);
    T visit(Mult mult);
    T visit(Div div);
    T visit(Minus minus);
    T visit(Neg neg);
    T visit(Paren paren);
    T visit(RelOp relOp);
    T visit(Not not);
    T visit(Assign assign);
    T visit(While aWhile);
    T visit(Let let);
    T visit(IsVoid isVoid);
    T visit(Instantiation instantiation);
    T visit(StringType stringType);
    T visit(Case aCase);
    T visit(Block block);
    T visit(VarDeclAndInit varDeclAndInit);
    T visit(Method method);
    T visit(FuncCall funcCall);
}
