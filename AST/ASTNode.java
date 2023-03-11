package cool.AST;

import org.antlr.v4.runtime.Token;
import java.util.ArrayList;

// Pentru crearea nodurilor arborelui, m-am inspirat din
// laboratorul 4 de CPLang.

public abstract class ASTNode {
    // Aici retin numele nodului.
    String token;

    ASTNode(String token) {
        this.token = token;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}

class Program extends ASTNode {
    ArrayList<Class> classes;

    Program(String token, ArrayList<Class> classes) {
        super(token);
        this.classes = classes;
    }

    public String getName() {
        return this.token;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Class extends ASTNode {
    Token class_name;
    Token parent_class_name;
    ArrayList<Feature> features;

    Class(String token, Token class_name, Token parent_class_name,
          ArrayList<Feature> features) {
        super(token);
        this.features = features;
        this.class_name = class_name;
        this.parent_class_name = parent_class_name;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Aici retin o clasa care generalizeaza un feature, ce
// reprezinta compozitia unei clase. Ea va fi extinsa de clasele
// VarDecl si FuncDecl.

abstract class Feature extends ASTNode {
    Feature(String token) {
        super(token);
    }
}

// Aici creez un nou nod pentru declararea de functie
// (aferenta regulii unei clase: o clasa este formata din
// declarari de functii si declarari de variabile).

class FuncDecl extends Feature {
    Token func_name;
    Token type;
    ArrayList<Declaration> declarations;
    ArrayList<Expression> lines;

    FuncDecl(String token, Token func_name, Token type,
             ArrayList<Declaration> declarations, ArrayList<Expression> lines) {
        super(token);
        this.type = type;
        this.func_name = func_name;
        this.lines = lines;
        this.declarations = declarations;
    }

    public Token getType() {
        return this.type;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Aici creez un nou nod pentru declararea de variabila
// (aferenta regulii unei clase: o clasa este formata din
// declarari de functii si declarari de variabile).

class VarDecl extends Feature {
    Token var_name;
    Token type;
    Expression value;

    VarDecl(String token, Token var_name, Token type, Expression value) {
        super(token);
        this.var_name = var_name;
        this.type = type;
        this.value = value;
    }

    public Token getType() {
        return this.type;
    }

    public Token getVar_name() {
        return this.var_name;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Aici creez un nou nod pentru declarari de variabile, aferente
// parametrilor functiilor.

class Declaration extends ASTNode {
    Token id;
    Token type;

    Declaration(String token, Token id, Token type) {
        super(token);
        this.id = id;
        this.type = type;
    }

    public Token getType() {
        return this.type;
    }

    public Token getId() {
        return this.id;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Aici creez un nod ce reprezinta declararea (si initializarea) unei
// variabile, pe care il voi folosi in cadrul celorlaltor noduri
// din gramatica expr.

class VarDeclAndInit extends ASTNode {
    Token var_name;
    Token type;
    Expression value;

    VarDeclAndInit(String token, Token var_name,
                   Token type, Expression value) {
        super(token);
        this.type = type;
        this.var_name =var_name;
        this.value = value;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

abstract class Expression extends ASTNode {
    Expression(String token) {
        super(token);
    }
}

// Nume de variabila.

class Id extends Expression {
    Id(String token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Valoare de tip Int.

class Int extends Expression {
    Int(String token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Valoare de tip Bool.

class Bool extends Expression {

    Bool(String token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Valoare de tip String (cu prelucrarile precizate in cerinta).

class StringType extends Expression {

    StringType(String token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Operatorul Not.

class Not extends Expression {
    Expression not_op;

    Not(String token, Expression not_op) {
        super(token);
        this.not_op = not_op;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Operatorul Neg

class Neg extends Expression {
    Expression unary_op;

    Neg(String token, Expression unary_op) {
        super(token);
        this.unary_op = unary_op;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Operatorul de inmultire

class Mult extends Expression {
    Expression left_op;
    Expression right_op;

    Mult(String token, Expression left_op, Expression right_op) {
        super(token);
        this.left_op = left_op;
        this.right_op = right_op;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Operatorul de impartire.

class Div extends Expression {
    Expression left_op;
    Expression right_op;

    Div(String token, Expression left_op, Expression right_op) {
        super(token);
        this.left_op = left_op;
        this.right_op = right_op;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Operatorul de adunare.

class Plus extends Expression {
    Expression left_op;
    Expression right_op;

    Plus(String token, Expression left_op, Expression right_op) {
        super(token);
        this.left_op = left_op;
        this.right_op = right_op;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Operatorul de scadere.

class Minus extends Expression {
    Expression left_op;
    Expression right_op;

    Minus(String token, Expression left_op, Expression right_op) {
        super(token);
        this.left_op = left_op;
        this.right_op = right_op;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Expresie incadrata intre paranteze.

class Paren extends Expression {
    Expression expression;

    Paren(String token, Expression expression) {
        super(token);
        this.expression = expression;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Operator relational.

class RelOp extends Expression {
    Expression left_op;
    Expression right_op;

    RelOp(String token, Expression left_op, Expression right_op) {
        super(token);
        this.left_op = left_op;
        this.right_op = right_op;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Operatorul de atribuire

class Assign extends Expression {
    Id var_name;
    Expression value;

    Assign(String token, Id var_name, Expression value) {
        super(token);
        this.var_name = var_name;
        this.value = value;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Constructia If

class If extends Expression {

    Expression cond;
    Expression thenBranch;
    Expression elseBranch;

    If(Expression cond, Expression thenBranch, Expression elseBranch, String token) {
        super(token);
        this.cond = cond;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Constructia While

class While extends Expression {
    Expression cond;
    Expression line;

    While(String token, Expression cond, Expression line) {
        super(token);
        this.line = line;
        this.cond = cond;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Constructia Let

class Let extends Expression {

    Expression line;
    ArrayList<VarDeclAndInit> declarations;

    Let(String token, Expression line, ArrayList<VarDeclAndInit> declarations) {
        super(token);
        this.line = line;
        this.declarations = declarations;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Functia isvoid

class IsVoid extends Expression {
    Expression expr;

    IsVoid(String token, Expression expression) {
        super(token);
        this.expr = expression;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Instantierea unei clase (folosind cuvantul-cheie NEW).

class Instantiation extends Expression {
    Token type;

    Instantiation(String token, Token type) {
        super(token);
        this.type = type;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Constructia Case

class Case extends Expression {
    Expression var_name;
    ArrayList<Token> vars;
    ArrayList<Token> types;
    ArrayList<Expression> branches;

    Case(String token, Expression var_name, ArrayList<Token> vars,
         ArrayList<Token> types, ArrayList<Expression> expressions) {
        super(token);
        this.var_name = var_name;
        this.vars = vars;
        this.types = types;
        this.branches = expressions;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Bloc de instructiuni

class Block extends Expression {
    ArrayList<Expression> lines;

    Block(String token, ArrayList<Expression> lines) {
        super(token);
        this.lines = lines;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Explicit dispatch

class Method extends Expression {
    Expression func_name;
    Token type;
    Token method_name;
    ArrayList<Expression> args;

    Method(String token, Expression func_name, Token type,
           Token method_name, ArrayList<Expression> args) {
        super(token);
        this.func_name = func_name;
        this.method_name = method_name;
        this.type = type;
        this.args = args;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Implicit dispatch

class FuncCall extends Expression {
    Token func_name;
    ArrayList<Expression> args;

    FuncCall(String token, Token func_name, ArrayList<Expression> args) {
        super(token);
        this.func_name = func_name;
        this.args = args;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
