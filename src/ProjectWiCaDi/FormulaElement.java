
package ProjectWiCaDi;

import java.util.*;


/**
 *
 * @author Hp
 */
public class FormulaElement {
//Assignment 3
    @Override
    public String toString() {
        return "";
    }
    
    private double upperb;
    private double lowerb;
    
    public FormulaElement parseFormula(String text, double upperb, double lowerb, char intgvar) {

        StringTokenizer tokenizer = new StringTokenizer(text, "+-*/^√()!πe%∫ \t", true);

        Vector<Object> vec = new Vector<>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (Character.isDigit(token.charAt(0)) && Character.isLetter(token.charAt(token.length() - 1))) {
                int i = 1;
                while (Character.isDigit(token.charAt(i)) || token.charAt(i) == '.') {
                    i++;
                }
                vec.add(token.substring(0, i));
                vec.add(token.substring(i));
            } else {
                if (!token.equals(" ") && !token.equals("/t")) {

                    vec.add(token);
                }
            }
        }
        System.out.println(vec);

        for (int i = 0; i < vec.size(); i++) {
            if (vec.get(i) instanceof String) {
                String s = (String) vec.get(i);
                if (s.equals("(")) {
                    vec.remove(i);
                    s = (String) vec.get(i);
                    int count = 1;
                    String out = "";

                    while (!s.equals(")") || count != 1) {
                        switch (s) {
                            case "(":
                                count++;
                                break;
                            case ")":
                                count--;
                                break;
                        }
                        out = out + s;
                        vec.remove(i);
                        s = (String) vec.get(i);
                    }

                    if (i > 0 && vec.get(i - 1) instanceof String) {
                        String p = (String) vec.get(i - 1);
                        if (p.toLowerCase().equals("cos") || p.toLowerCase().equals("sin") || p.toLowerCase().equals("tan")|| p.equals("ln")) {
                            vec.add(i, "(");
                            vec.add(i + 1, parseFormula(out,upperb,lowerb,intgvar));
                        } else {
                            vec.remove(i);
                            vec.add(i, parseFormula(out,upperb,lowerb,intgvar));
                        }
                    } else {
                        vec.remove(i);
                        vec.add(i, parseFormula(out,upperb,lowerb,intgvar));
                    }
                }
            }
        }


        for (int i = 0; i < vec.size(); i++) {
            if (vec.get(i) instanceof String) {
                String s = (String) vec.get(i);
                if (Character.isDigit(s.charAt(0))) {
                    vec.remove(i);
                    vec.add(i, new ConstantElement(Double.valueOf(s)));
                } else if (Character.isLetter(s.charAt(0)) && !s.toLowerCase().equals("cos") && !s.toLowerCase().equals("sin") && !s.toLowerCase().equals("tan") && !s.toLowerCase().equals("ln") && !s.toLowerCase().equals("mod") && !s.toLowerCase().equals("d")) {
                    vec.remove(i);
                    vec.add(i, new VariableElement(s));
                }
            }
        }
        
        for (int i = 0; i < vec.size(); i++) {
            if (vec.get(i).toString().equals("∫")) {
                IntegrationFunctionElement intg = new IntegrationFunctionElement();
                intg.addarg((FormulaElement) vec.get(i + 1));
                intg.upperBoundSetMethod(upperb);
                intg.lowerBoundSetMethod(lowerb);
                intg.variableSetMethod(intgvar);
                vec.remove(i);
                vec.remove(i);
                vec.remove(i);
                
                vec.add(i, intg);
                i--;
            }
        }
        for (int i = 0; i < vec.size(); i++){
            if (vec.get(i).toString().equals("d")){
                vec.remove(i);
                vec.remove(i);
            }
        }
        
        for (int i = 1; i < vec.size(); i++) {
            if (vec.get(i).toString().equals("!")) {
                FactFunctionElement fac = new FactFunctionElement();
                fac.addarg((FormulaElement) vec.get(i - 1));

                vec.remove(i - 1);
                vec.remove(i - 1);

                vec.add(i - 1, fac);
            }
        }
        
        for (int i = 0; i < vec.size() - 1; i++) {
            if (vec.get(i).toString().equals("√")) {
                SqrRootFunctionElement sqr = new SqrRootFunctionElement();

                sqr.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i);
                vec.remove(i);

                vec.add(i, sqr);
            }
        }
        
        for (int i = 0; i < vec.size() - 3; i++) {
            if (vec.get(i).toString().toLowerCase().equals("cos")) {
                CosineFunctionElement cos = new CosineFunctionElement();

                cos.addarg((FormulaElement) vec.get(i + 2));

                vec.remove(i); 
                vec.remove(i);
                vec.remove(i); 
                vec.remove(i); 

                vec.add(i, cos);
            }
            if (vec.get(i).toString().toLowerCase().equals("sin")) {
                SineFunctionElement sin = new SineFunctionElement();

                sin.addarg((FormulaElement) vec.get(i + 2));

                vec.remove(i); 
                vec.remove(i); 
                vec.remove(i); 
                vec.remove(i); 

                vec.add(i, sin);
            }
            if (vec.get(i).toString().toLowerCase().equals("tan")) {
                TanFunctionElement tan = new TanFunctionElement();

                tan.addarg((FormulaElement) vec.get(i + 2));

                vec.remove(i); 
                vec.remove(i); 
                vec.remove(i); 
                vec.remove(i); 

                vec.add(i, tan);
            }
        }
        
        for (int i = 0; i < vec.size() - 3; i++) {
            if (vec.get(i).toString().toLowerCase().equals("ln")) {
                LogFunctionElement ln = new LogFunctionElement();

                ln.addarg((FormulaElement) vec.get(i + 2));

                vec.remove(i); 
                vec.remove(i); 
                vec.remove(i); 
                vec.remove(i); 

                vec.add(i, ln);
            }
        }
        
        if (vec.get(0).toString().equals("-")){
                NegativeFunctionElement neg = new NegativeFunctionElement();
                neg.addarg((FormulaElement) vec.get(1));

                vec.remove(0);
                vec.remove(0);

                vec.add(0, neg);
            }
        for (int i = 1; i < vec.size() - 1; i++) {
            if (vec.get(i).toString().equals("-") && vec.get(i-1)=="(") {
                NegativeFunctionElement neg = new NegativeFunctionElement();
                neg.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i);
                vec.remove(i);

                vec.add(i, neg);
                i--;
            }
        }
        
        
        
        for (int i = 1; i < vec.size() - 1; i++) {
            if (vec.get(i).toString().equals("mod")) {
                ModFunctionElement mod = new ModFunctionElement();
                mod.addarg((FormulaElement) vec.get(i - 1));
                mod.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i - 1);
                vec.remove(i - 1);
                vec.remove(i - 1);

                vec.add(i - 1, mod);
                i--;
            }
        }
        
        for (int i = 1; i < vec.size(); i++) {
            if (vec.get(i).toString().equals("%")) {
                PercentageFunctionElement per = new PercentageFunctionElement();

                per.addarg((FormulaElement) vec.get(i - 1));

                vec.remove(i - 1);
                vec.remove(i - 1);

                vec.add(i - 1, per);
            }
        }
        
        for (int i = 0; i < vec.size() - 1; i++) {
            if ((vec.get(i) instanceof VariableElement || vec.get(i) instanceof ConstantElement || vec.get(i) instanceof FunctionElement)
                    && (vec.get(i + 1) instanceof VariableElement || vec.get(i + 1) instanceof ConstantElement || vec.get(i + 1) instanceof FunctionElement)) {

                MultipleFunctionElement mul = new MultipleFunctionElement();
                mul.addarg((FormulaElement) vec.get(i));
                mul.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i); 
                vec.remove(i); 

                vec.add(i, mul);
                i--;
            }
        }



        for (int i = 1; i < vec.size() - 1; i++) {
            if (vec.get(i).toString().equals("^")) {
                PowerFunctionElement power = new PowerFunctionElement();

                power.addarg((FormulaElement) vec.get(i - 1));
                power.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i - 1);
                vec.remove(i - 1);
                vec.remove(i - 1);

                vec.add(i - 1, power);
                
            }
        }
        

        for (int i = 0; i < vec.size() - 1; i++) {
            if ((vec.get(i) instanceof VariableElement || vec.get(i) instanceof ConstantElement)
                    && (vec.get(i + 1) instanceof VariableElement || vec.get(i + 1) instanceof ConstantElement)) {

                MultipleFunctionElement mul = new MultipleFunctionElement();
                mul.addarg((FormulaElement) vec.get(i));
                mul.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i); 
                vec.remove(i); 

                vec.add(i, mul);
                i--;
            } else if (vec.get(i).toString().equals("/")) {
                DivideFunctionElement div = new DivideFunctionElement();
                div.addarg((FormulaElement) vec.get(i - 1));
                div.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i - 1);
                vec.remove(i - 1);
                vec.remove(i - 1);

                vec.add(i - 1, div);
                i--;
            }else if (vec.get(i).toString().equals("*")) {
                MultipleFunctionElement mul = new MultipleFunctionElement();
                mul.addarg((FormulaElement) vec.get(i - 1));
                mul.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i - 1);
                vec.remove(i - 1);
                vec.remove(i - 1);

                vec.add(i - 1, mul);
                i--;
            }
        }

        for (int i = 1; i < vec.size() - 1; i++) {
            if (vec.get(i).toString().equals("+")) {
                PlusFunctionElement plu = new PlusFunctionElement();
                plu.addarg((FormulaElement) vec.get(i - 1));
                plu.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i - 1);
                vec.remove(i - 1);
                vec.remove(i - 1);

                vec.add(i - 1, plu);
                i--;
            } else if (vec.get(i).toString().equals("-")) {
                MinusFunctionElement min = new MinusFunctionElement();
                min.addarg((FormulaElement) vec.get(i - 1));
                min.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i - 1);
                vec.remove(i - 1);
                vec.remove(i - 1);

                vec.add(i - 1, min);
                i--;
            }
        }
        
        

        return (FormulaElement) vec.get(0);
    }

    public void setVariableValue(String varName, double value) {
    }
    
    public boolean isFullyGrounded() {
        return true;
    }

    public double evaluate() {
        return 0;
    }
    
    public void upperBoundSetMethod(double u){
    }
    
    public void lowerBoundSetMethod(double l){
    }
    
    public void variableSetMethod(char u){
    }

    public static void main(String[] args) {

        FormulaElement test_2 = new FormulaElement() {};
        FormulaElement fe = test_2.parseFormula("∫(2y)dy",0,5,'y');
        System.out.println("It is done");
        fe.setVariableValue("e", 2.718);
        fe.setVariableValue("π", 3.141);
        System.out.println(fe);
        //fe.setVariableValue("x", 5);
        //fe.setVariableValue("y", 2);
        System.out.println(fe.isFullyGrounded());
        System.out.println(fe.evaluate());
        
    }

}
