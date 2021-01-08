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
public class SqrRootFunctionElement extends FunctionElement{
    @Override
    public String toString() {
        return this.getArglist().get(0).toString() + "^0.5";
    }

    @Override
    public double evaluate() {
        return Math.pow(this.getArglist().get(0).evaluate(), 0.5);

    }
}
