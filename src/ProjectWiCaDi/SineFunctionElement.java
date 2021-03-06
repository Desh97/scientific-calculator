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
public class SineFunctionElement extends FunctionElement{
    @Override
    public String toString() {
        FormulaElement argument_x = this.getArglist().get(0);
        return "sin(" + argument_x.toString() + ")";
    }

    @Override
    public void addarg(FormulaElement argument) {
        if (this.getArglist().size() < 1) {
            super.addarg(argument);
        }
    }

    @Override
    public double evaluate() {
        return Math.sin(getArglist().get(0).evaluate());

    }
}
