package cool.AST;

public class CreateASTTree implements ASTVisitor<String>{
    // Variabila ce marcheaza indentarea (folosita la afisarea
    // arborelui).
    int indent = 0;

    /**
     * Aceasta functie afiseaza string-ul dat ca parametru,
     * tinand cont de indentare.
     * @param item reprezinta item-ul ce se doreste a fi afisat.
     * @return intoarce string-ul generat.
     */
    String getString(String item) {
        return " ".repeat(Math.max(0, indent)) +
                item;
    }

    /**
     * Aceasta functie verifica tipul operanzilor unei
     * expresii aritmetice si apeleaza metoda visit aferenta.
     * @param expr reprezinta operandul pentru care se va apela
     *             metoda visit
     * @return intoarce string-ul in urma apelului metodei vizit
     *         pe variabila expr
     */
    String check_operations_members(Expression expr) {
        String return_value = "";

        if (expr instanceof Id) {
            return_value = visit((Id) expr);
        } else if (expr instanceof Int) {
            return_value = visit((Int) expr);
        } else if (expr instanceof  Neg) {
            return_value = visit((Neg) expr);
        } else if (expr instanceof Plus) {
            return_value = visit((Plus) expr);
        } else if (expr instanceof Minus) {
            return_value = visit((Minus) expr);
        } else if (expr instanceof Mult) {
            return_value = visit((Mult) expr);
        } else if (expr instanceof Div) {
            return_value = visit((Div) expr);
        } else if (expr instanceof Paren) {
            return_value = visit((Paren) expr);
        } else if (expr instanceof FuncCall) {
            return_value = visit((FuncCall) expr);
        }

        return return_value;
    }

    /**
     * Aceasta metoda verifica tipul expresiei ce reprezinta
     * valoarea care va fi atribuita unei variabile, pentru a
     * apela metoda visit aferenta acesteia.
     * @param expr reprezinta parametrul ce va fi transmis functiei visit
     * @return intoarce string-ul generat in urma apelului metodei visit
     */
    String assign_value_for_variable(Expression expr) {
        String ret_val = "";

        if (expr instanceof Int) {
            ret_val = visit((Int) expr);
        } else if (expr instanceof Bool) {
            ret_val = visit((Bool) expr);
        } else if (expr instanceof StringType) {
            ret_val = visit((StringType) expr);
        } else if (expr instanceof Id) {
            ret_val = visit((Id) expr);
        } else if (expr instanceof Assign) {
            ret_val = visit((Assign) expr);
        } else if (expr instanceof Plus) {
            ret_val = visit((Plus) expr);
        } else if (expr instanceof Minus) {
            ret_val = visit((Minus) expr);
        } else if (expr instanceof Mult) {
            ret_val = visit((Mult) expr);
        } else if (expr instanceof Div) {
            ret_val = visit((Div) expr);
        } else if (expr instanceof Not) {
            ret_val = visit((Not) expr);
        } else if (expr instanceof RelOp) {
            ret_val = visit((RelOp) expr);
        } else if (expr instanceof If) {
            ret_val = visit((If) expr);
        } else if (expr instanceof Case) {
            ret_val = visit((Case) expr);
        } else if (expr instanceof Method) {
            ret_val = visit((Method) expr);
        } else if (expr instanceof FuncCall) {
            ret_val = visit((FuncCall) expr);
        }

        return  ret_val;
    }

    /**
     * Aceasta metoda desemneaza expresiile posibile din
     * gramatica ce pot fi folosite drept argumente ale unei functii.
     * @param expr reprezinta expresia ce reprezinta un argument al unei
     *             functii
     * @return intoarce rezultatul generat de apelul metodei visit pe
     *         variabila expr
     */
    String function_arguments(Expression expr) {
        String item = "";

        if (expr instanceof Id) {
            item = visit((Id) expr);
        } else if (expr instanceof Int) {
            item = visit((Int) expr);
        } else if (expr instanceof Bool) {
            item = visit((Bool) expr);
        } else if (expr instanceof StringType) {
            item = visit((StringType) expr);
        } else if (expr instanceof Plus) {
            item = visit((Plus) expr);
        } else if (expr instanceof Minus) {
            item = visit((Minus) expr);
        } else if (expr instanceof Mult) {
            item = visit((Mult) expr);
        } else if (expr instanceof Div) {
            item = visit((Div) expr);
        } else if (expr instanceof Method) {
            item = visit((Method) expr);
        } else if (expr instanceof FuncCall) {
            item = visit((FuncCall) expr);
        }

        return item;
    }

    /**
     * Aceasta metoda desemneaza expresiile posibile ce pot fi
     * folosite in cadrul instructiulor unui program. Ea va fi folosita
     * in cadrul blocurilor de instructiuni, in cadrul corpului unei functii/metode,
     * si in cadrul expresiilor case, let si while.
     * @param expr reprezinta instructiunea pe care se apeleaza metoda visit.
     * @return intoarce rezultatul apelului metodei visit.
     */
    String instruction(Expression expr) {
        String str = "";

        if (expr instanceof Int) {
            str = visit((Int) expr);
        } else if (expr instanceof Bool) {
            str = visit((Bool) expr);
        } else if (expr instanceof StringType) {
            str = visit((StringType) expr);
        } else if (expr instanceof Assign) {
            str = visit((Assign) expr);
        } else if (expr instanceof Id) {
            str = visit((Id) expr);
        } else if (expr instanceof Let) {
            str = visit((Let) expr);
        } else if (expr instanceof If) {
            str = visit((If) expr);
        } else if (expr instanceof Case) {
            str = visit((Case) expr);
        } else if (expr instanceof FuncCall) {
            str = visit((FuncCall) expr);
        } else if (expr instanceof Method) {
            str = visit((Method) expr);
        } else if (expr instanceof While) {
            str = visit((While) expr);
        } else if (expr instanceof Block) {
            str = visit((Block) expr);
        } else if (expr instanceof Plus) {
            str = visit((Plus) expr);
        } else if (expr instanceof Minus) {
            str = visit((Minus) expr);
        } else if (expr instanceof Mult) {
            str = visit((Mult) expr);
        } else if (expr instanceof Div) {
            str = visit((Div) expr);
        } else if (expr instanceof Not) {
            str = visit((Not) expr);
        }

        return str;
    }

    // Mai jos, suprascriu metodele din clasa CoolParserBaseVisitor,
    // generata in urma completarii gramaticii in fisierul CoolParser.g4,
    // conform tehnicii invatate la laborator.

    @Override
    public String visit(Program program) {
        StringBuilder item = new StringBuilder();
        item.append(getString(program.getName()));

        indent = indent + 2;

        for (int i = 0; i < program.classes.size(); i++) {
            item.append(visit(program.classes.get(i)));
        }

        indent = indent - 2;

        return item.toString();
    }

    @Override
    public String visit(Class aClass) {
        String class_name = aClass.class_name.getText();

        StringBuilder item = new StringBuilder();
        item.append("\n");
        item.append(getString("class"));

        indent = indent + 2;

        item.append("\n");
        item.append(getString(class_name));

        if (aClass.parent_class_name != null) {
            String parent_name = aClass.parent_class_name.getText();
            item.append("\n");
            item.append(getString(parent_name));
        }

        if (aClass.features != null) {
            for (int i = 0; i < aClass.features.size(); i++) {
                if (aClass.features.get(i) instanceof FuncDecl) {
                    item.append(visit((FuncDecl) aClass.features.get(i)));
                } else if (aClass.features.get(i) instanceof VarDecl) {
                    item.append(visit((VarDecl) aClass.features.get(i)));
                }
            }
        }

        indent = indent - 2;

        return item.toString();
    }

    @Override
    public String visit(VarDecl varDecl) {
        String name = varDecl.getVar_name().getText();
        String type = varDecl.getType().getText();

        StringBuilder item = new StringBuilder();

        item.append("\n");
        item.append(getString(varDecl.token));

        indent = indent + 2;

        item.append("\n");
        item.append(getString( name));
        item.append("\n");
        item.append(getString(type));

        if (varDecl.value != null) {
            item.append(assign_value_for_variable(varDecl.value));
        }

        indent = indent - 2;

        return item.toString();
    }

    @Override
    public String visit(FuncDecl funcDecl) {
        StringBuilder item = new StringBuilder();
        item.append("\n");
        item.append(getString(funcDecl.token));

        indent = indent + 2;

        item.append("\n");
        item.append(getString(funcDecl.func_name.getText()));

        if (funcDecl.declarations != null) {
            for (int i = 0; i < funcDecl.declarations.size(); i++) {
                item.append("\n");
                item.append(getString(funcDecl.declarations.get(i).token));

                indent = indent + 2;

                item.append("\n");
                item.append(getString(funcDecl.declarations.get(i).id.getText()));
                item.append("\n");
                item.append(getString(funcDecl.declarations.get(i).type.getText()));

                indent = indent - 2;
            }
        }

        item.append("\n");
        item.append(getString(funcDecl.type.getText()));

        if (funcDecl.lines != null) {
            for (int i = 0; i < funcDecl.lines.size(); i++) {
                item.append(instruction(funcDecl.lines.get(i)));
            }
        }

        indent = indent - 2;

        return item.toString();
    }

    @Override
    public String visit(Declaration declaration) {

        return "\n" +
                getString(declaration.token);
    }

    @Override
    public String visit(Id id) {

        return "\n" +
                getString(id.token);
    }

    @Override
    public String visit(Int intt) {

        return "\n" +
                getString(intt.token);
    }

    @Override
    public String visit(Bool bool) {

        return "\n" +
                getString(bool.token);
    }

    @Override
    public String visit(StringType stringType) {

        return "\n" +
                getString(stringType.token);
    }

    @Override
    public String visit(If anIf) {
        StringBuilder item = new StringBuilder();
        item.append("\n");
        item.append(getString(anIf.token));

        indent = indent + 2;

        if (anIf.cond instanceof RelOp) {
            item.append(visit((RelOp) anIf.cond));
        } else if (anIf.cond instanceof IsVoid) {
            item.append(visit((IsVoid) anIf.cond));
        } else if (anIf.cond instanceof Paren) {
            item.append(visit((Paren) anIf.cond));
        }

        if (anIf.thenBranch instanceof Id) {
            item.append(visit((Id) anIf.thenBranch));
        } else if (anIf.thenBranch instanceof If) {
            item.append(visit((If) anIf.thenBranch));
        } else if (anIf.thenBranch instanceof Assign) {
            item.append(visit((Assign) anIf.thenBranch));
        } else if (anIf.thenBranch instanceof Int) {
            item.append(visit((Int) anIf.thenBranch));
        } else if (anIf.thenBranch instanceof Bool) {
            item.append(visit((Bool) anIf.thenBranch));
        } else if (anIf.thenBranch instanceof Plus) {
            item.append(visit((Plus) anIf.thenBranch));
        } else if (anIf.thenBranch instanceof Minus) {
            item.append(visit((Minus) anIf.thenBranch));
        } else if (anIf.thenBranch instanceof Mult) {
            item.append(visit((Mult) anIf.thenBranch));
        } else if (anIf.thenBranch instanceof Div) {
            item.append(visit((Div) anIf.thenBranch));
        } else if (anIf.thenBranch instanceof FuncCall) {
            item.append(visit((FuncCall) anIf.thenBranch));
        } else if (anIf.thenBranch instanceof Method) {
            item.append(visit((Method) anIf.thenBranch));
        }

        if (anIf.elseBranch instanceof Id) {
            item.append(visit((Id) anIf.elseBranch));
        } else if (anIf.elseBranch instanceof If) {
            item.append(visit((If) anIf.elseBranch));
        } else if (anIf.elseBranch instanceof Assign) {
            item.append(visit((Assign) anIf.elseBranch));
        } else if (anIf.elseBranch instanceof Int) {
            item.append(visit((Int) anIf.elseBranch));
        } else if (anIf.elseBranch instanceof Bool) {
            item.append(visit((Bool) anIf.elseBranch));
        } else if (anIf.elseBranch instanceof Plus) {
            item.append(visit((Plus) anIf.elseBranch));
        } else if (anIf.elseBranch instanceof Minus) {
            item.append(visit((Minus) anIf.elseBranch));
        } else if (anIf.elseBranch instanceof Mult) {
            item.append(visit((Mult) anIf.elseBranch));
        } else if (anIf.elseBranch instanceof Div) {
            item.append(visit((Div) anIf.elseBranch));
        } else if (anIf.elseBranch instanceof FuncCall) {
            item.append(visit((FuncCall) anIf.elseBranch));
        } else if (anIf.elseBranch instanceof Method) {
            item.append(visit((Method) anIf.elseBranch));
        }

        indent = indent - 2;

        return item.toString();
    }

    @Override
    public String visit(Neg neg) {
        StringBuilder item = new StringBuilder();
        item.append("\n");
        item.append(getString(neg.token));

        indent = indent + 2;

        if (neg.unary_op instanceof Id) {
            item.append(visit((Id) neg.unary_op));
        } else if (neg.unary_op instanceof Int) {
            item.append(visit((Int) neg.unary_op));
        } else if (neg.unary_op instanceof  Neg) {
            item.append(visit((Neg) neg.unary_op));
        } else if (neg.unary_op instanceof Plus) {
            item.append(visit((Plus) neg.unary_op));
        } else if (neg.unary_op instanceof Minus) {
            item.append(visit((Minus) neg.unary_op));
        } else if (neg.unary_op instanceof Mult) {
            item.append(visit((Mult) neg.unary_op));
        } else if (neg.unary_op instanceof Div) {
            item.append(visit((Div) neg.unary_op));
        } else if (neg.unary_op instanceof Paren) {
            item.append(visit((Paren) neg.unary_op));
        }

        indent = indent - 2;

        return item.toString();
    }

    @Override public String visit(Plus plus) {
        StringBuilder item = new StringBuilder();
        item.append("\n");
        item.append(getString(plus.token));

        indent = indent + 2;

        item.append(check_operations_members(plus.left_op));
        item.append(check_operations_members(plus.right_op));

        indent = indent - 2;

        return item.toString();
    }

    @Override
    public String visit(Div div) {
        StringBuilder str = new StringBuilder();
        str.append("\n");
        str.append(getString(div.token));

        indent = indent + 2;

        str.append(check_operations_members(div.left_op));
        str.append(check_operations_members(div.right_op));

        indent = indent - 2;

        return str.toString();
    }

    @Override
    public String visit(Mult mult) {
        StringBuilder item = new StringBuilder();
        item.append("\n");
        item.append(getString(mult.token));

        indent = indent + 2;

        item.append(check_operations_members(mult.left_op));
        item.append(check_operations_members(mult.right_op));

        indent = indent - 2;

        return item.toString();
    }

    @Override
    public String visit(Minus minus) {
        StringBuilder item = new StringBuilder();
        item.append("\n");
        item.append(getString(minus.token));

        indent = indent + 2;

        item.append(check_operations_members(minus.left_op));
        item.append(check_operations_members(minus.right_op));

        indent = indent - 2;

        return item.toString();
    }

    @Override
    public String visit(Paren paren) {
        StringBuilder item = new StringBuilder();

        if (paren.expression instanceof Id) {
            item.append(visit((Id) paren.expression));
        } else if (paren.expression instanceof Int) {
            item.append(visit((Int) paren.expression));
        } else if (paren.expression instanceof  Neg) {
            item.append(visit((Neg) paren.expression));
        } else if (paren.expression instanceof Plus) {
            item.append(visit((Plus) paren.expression));
        } else if (paren.expression instanceof Minus) {
            item.append(visit((Minus) paren.expression));
        } else if (paren.expression instanceof Mult) {
            item.append(visit((Mult) paren.expression));
        } else if (paren.expression instanceof Div) {
            item.append(visit((Div) paren.expression));
        } else if (paren.expression instanceof RelOp) {
            item.append(visit((RelOp) paren.expression));
        } else if (paren.expression instanceof Not) {
            item.append(visit((Not) paren.expression));
        } else if (paren.expression instanceof IsVoid) {
            item.append(visit((IsVoid) paren.expression));
        }

        return item.toString();
    }

    @Override
    public String visit(RelOp relOp) {
        StringBuilder item = new StringBuilder();
        item.append("\n");
        item.append(getString(relOp.token));
        indent = indent + 2;

        if (relOp.left_op instanceof Int) {
            item.append(visit((Int) relOp.left_op));
        } else if (relOp.left_op instanceof Plus) {
            item.append(visit((Plus) relOp.left_op));
        } else if (relOp.left_op instanceof Minus) {
            item.append(visit((Minus) relOp.left_op));
        } else if (relOp.left_op instanceof Mult) {
            item.append(visit((Mult) relOp.left_op));
        } else if (relOp.left_op instanceof Div) {
            item.append(visit((Div) relOp.left_op));
        } else if (relOp.left_op instanceof Not) {
            item.append(visit((Not) relOp.left_op));
        } else if (relOp.left_op instanceof Id) {
            item.append(visit((Id) relOp.left_op));
        } else if (relOp.left_op instanceof Bool) {
            item.append(visit((Bool) relOp.left_op));
        } else if (relOp.left_op instanceof RelOp) {
            item.append(visit((RelOp) relOp.left_op));
        }

        if (relOp.right_op instanceof Int) {
            item.append(visit((Int) relOp.right_op));
        } else if (relOp.right_op instanceof Plus) {
            item.append(visit((Plus) relOp.right_op));
        } else if (relOp.right_op instanceof Minus) {
            item.append(visit((Minus) relOp.right_op));
        } else if (relOp.right_op instanceof Mult) {
            item.append(visit((Mult) relOp.right_op));
        } else if (relOp.right_op instanceof Div) {
            item.append(visit((Div) relOp.right_op));
        } else if (relOp.right_op instanceof Not) {
            item.append(visit((Not) relOp.right_op));
        } else if (relOp.right_op instanceof Id) {
            item.append(visit((Id) relOp.right_op));
        } else if (relOp.right_op instanceof Bool) {
            item.append(visit((Bool) relOp.right_op));
        } else if (relOp.right_op instanceof RelOp) {
            item.append(visit((RelOp) relOp.right_op));
        }

        indent = indent - 2;

        return item.toString();
    }

    @Override
    public String visit(Not not) {
        StringBuilder item = new StringBuilder();
        item.append("\n");
        item.append(getString(not.token));
        indent = indent + 2;

        if (not.not_op instanceof RelOp) {
            item.append(visit((RelOp) not.not_op));
        } else if (not.not_op instanceof IsVoid) {
            item.append(visit((IsVoid) not.not_op));
        } else if (not.not_op instanceof Paren) {
            item.append(visit((Paren) not.not_op));
        } else if (not.not_op instanceof Id) {
            item.append(visit((Id) not.not_op));
        }

        indent = indent - 2;

        return item.toString();
    }

    @Override
    public String visit(Assign assign) {
        StringBuilder item = new StringBuilder();
        item.append("\n");
        item.append(getString(assign.token));

        indent = indent + 2;

        item.append(visit(assign.var_name));

        item.append(assign_value_for_variable(assign.value));

        indent = indent - 2;

        return item.toString();
    }

    @Override
    public String visit(While aWhile) {
        StringBuilder item = new StringBuilder();
        item.append("\n");
        item.append(getString(aWhile.token));

        indent = indent + 2;
        if (aWhile.cond instanceof RelOp) {
            item.append(visit((RelOp) aWhile.cond));
        } else if (aWhile.cond instanceof Id) {
            item.append(visit((Id) aWhile.cond));
        } else if (aWhile.cond instanceof Not) {
            item.append(visit((Not) aWhile.cond));
        } else if (aWhile.cond instanceof Paren) {
            item.append(visit((Paren) aWhile.cond));
        }

        item.append(instruction(aWhile.line));

        indent = indent - 2;

        return item.toString();
    }

    @Override
    public String visit(Let let) {
        StringBuilder item = new StringBuilder();
        item.append("\n");
        item.append(getString(let.token));

        indent = indent + 2;

        for (int i = 0; i < let.declarations.size(); i++) {
            item.append(visit(let.declarations.get(i)));
        }

        item.append(instruction(let.line));

        indent = indent - 2;

        return item.toString();
    }

    @Override
    public String visit(IsVoid isVoid) {
        StringBuilder str = new StringBuilder();

        str.append("\n");
        str.append(getString(isVoid.token));

        indent = indent + 2;

        if (isVoid.expr instanceof Instantiation) {
            str.append(visit((Instantiation) isVoid.expr));
        } else if (isVoid.expr instanceof Id) {
            str.append(visit((Id) isVoid.expr));
        }

        indent = indent - 2;

        return str.toString();
    }

    @Override
    public String visit(Instantiation instantiation) {
        StringBuilder str = new StringBuilder();

        str.append("\n");

        str.append(getString(instantiation.token));
        str.append("\n");

        indent = indent + 2;

        str.append(getString(instantiation.type.getText()));

        indent = indent - 2;

        return str.toString();
    }

    @Override
    public String visit(Case aCase) {
        StringBuilder str = new StringBuilder();

        str.append("\n");
        str.append(getString(aCase.token));

        indent = indent + 2;

        if (aCase.var_name instanceof Id) {
            str.append(visit((Id) aCase.var_name));
        }

        for (int i = 0; i < aCase.vars.size(); i++) {
            str.append("\n");
            str.append(getString("case branch"));

            indent = indent + 2;

            str.append("\n");
            str.append(getString(aCase.vars.get(i).getText()));
            str.append("\n");
            str.append(getString(aCase.types.get(i).getText()));

            str.append(instruction(aCase.branches.get(i)));

            indent = indent - 2;
        }
        indent = indent - 2;

        return str.toString();
    }

    @Override
    public String visit(Block block) {
        StringBuilder str = new StringBuilder();

        str.append("\n");
        str.append(getString(block.token));

        indent = indent + 2;

        for (int i = 0; i < block.lines.size(); i++) {
            str.append(instruction(block.lines.get(i)));
        }

        indent = indent - 2;

        return str.toString();
    }

    @Override
    public String visit(VarDeclAndInit varDeclAndInit) {
        StringBuilder item = new StringBuilder();

        item.append("\n");
        item.append(getString(varDeclAndInit.token));

        indent = indent + 2;

        item.append("\n");
        item.append(getString(varDeclAndInit.var_name.getText()));

        item.append("\n");
        item.append(getString(varDeclAndInit.type.getText()));

        if (varDeclAndInit.value != null ) {
            item.append(assign_value_for_variable(varDeclAndInit.value));
        }

        indent = indent - 2;

        return item.toString();
    }

    @Override
    public String visit(Method method) {
        StringBuilder builder = new StringBuilder();

        builder.append("\n");
        builder.append(getString("."));

        indent = indent + 2;

        if (method.func_name instanceof Instantiation) {
            builder.append(visit((Instantiation) method.func_name));
        } else if (method.func_name instanceof Id) {
            builder.append(visit((Id) method.func_name));
        } else if (method.func_name instanceof FuncCall) {
            builder.append(visit((FuncCall) method.func_name));
        } else if (method.func_name instanceof Method) {
            builder.append(visit((Method) method.func_name));
        }

        if (method.type != null) {
            builder.append("\n");
            builder.append(getString(method.type.getText()));
        }

        builder.append("\n");
        builder.append(getString(method.method_name.getText()));

        if (method.args != null) {
            for (int i = 0; i < method.args.size(); i++) {
                builder.append(function_arguments(method.args.get(i)));
            }
        }


        indent = indent - 2;

        return builder.toString();
    }

    @Override
    public String visit(FuncCall funcCall) {
        StringBuilder builder = new StringBuilder();

        builder.append("\n");
        builder.append(getString("implicit dispatch"));

        indent = indent + 2;

        builder.append("\n");
        builder.append(getString(funcCall.func_name.getText()));

        if (funcCall.args != null) {
            for (int i = 0; i < funcCall.args.size(); i++) {
                builder.append(function_arguments(funcCall.args.get(i)));
            }
        }

        indent = indent - 2;

        return builder.toString();
    }
}
