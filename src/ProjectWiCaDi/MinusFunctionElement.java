/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectWiCaDi;

/**
 *
 * @author Hp
 */
public class MinusFunctionElement extends FunctionElement{
     @Override
    public String toString() {
        return this.getArglist().get(0).toString() + " - " + getArglist().get(1).toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double evaluate() {
        return this.getArglist().get(0).evaluate() - getArglist().get(1).evaluate();

    }
}
