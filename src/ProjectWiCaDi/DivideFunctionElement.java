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
public class DivideFunctionElement extends FunctionElement{
     @Override
    public String toString() {
        return this.getArglist().get(0).toString() + " / " + getArglist().get(1).toString();
    }

//    @Override
//    public void setVariableValue(String varName, double value) {
//        if (this.getArglist().size() > 0) {
//            this.getArglist().get(0).setVariableValue(varName, value);
//        }
//    }

    @Override
    public double evaluate() {
        System.out.println(this.getArglist().get(1).evaluate());
        return this.getArglist().get(0).evaluate() / getArglist().get(1).evaluate();

    }
}
