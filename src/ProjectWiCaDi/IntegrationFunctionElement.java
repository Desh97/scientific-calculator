/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectWiCaDi;

/**
 *
 * @author sithi
 */
public class IntegrationFunctionElement extends FunctionElement{
    
    String formula;
    double a;                                           
    double b;
    char variable;
    int n = 100 ;
    
    @Override
    public String toString() {
        return "∫(" + this.getArglist().get(0).toString() + ")";
    }

    
    
    @Override
    public double evaluate() {
        this.formula = String.valueOf(this.getArglist().get(0));
        double result = IntSimpson(a,b,n);
        return result;

    }
    
    double f (double x) {    
        FormulaElement IntgFormula = new FormulaElement() {};
        FormulaElement IntgFormula2= IntgFormula.parseFormula(formula,a,b,variable);
        IntgFormula2.setVariableValue(Character.toString(variable), x);
        return IntgFormula2.evaluate();
        
    }
    
    double IntSimpson(double a, double b, int n){    
       int i,z;                                                       
       double h,s;                                                    

       n=n+n;
       s = f(a)*f(b);
       h = (b-a)/n;                                        
       z = 4;

       for(i = 1; i<n; i++){
          s = s + z * f(a+i*h);
          z = 6 - z;
       }
       return (s * h)/3;
    }
    
    @Override
    public void upperBoundSetMethod(double u){
        this.a = u;
    }
    
    @Override
    public void lowerBoundSetMethod(double l){
        this.b = l;
    }
    
    @Override
    public void variableSetMethod(char u){
        this.variable = u;
    }
}
