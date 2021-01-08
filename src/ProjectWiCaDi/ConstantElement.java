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
public class ConstantElement extends FormulaElement {
    private double value;

    public double getValue() {
        return value;
    }

    public ConstantElement(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
    @Override
    public double evaluate() {
        return this.getValue();

    }
}
