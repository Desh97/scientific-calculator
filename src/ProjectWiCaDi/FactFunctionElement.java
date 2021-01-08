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
public class FactFunctionElement extends FunctionElement {
    @Override
    public String toString() {
        return this.getArglist().get(0).toString() + "!";
    }

    @Override
    public double evaluate() {
        double arg = this.getArglist().get(0).evaluate();
        int fact = 1;
        int no = (int) Math.round(arg);
        
        while(no>0){
            fact = fact*no;
            no--;
        }
        return fact;

    }
}
