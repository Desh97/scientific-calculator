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
public class PercentageFunctionElement extends FunctionElement {
    @Override
    public String toString() {
        return this.getArglist().get(0).toString() + "%";
    }

    @Override
    public double evaluate() {
        return this.getArglist().get(0).evaluate()/100;

    }
}
