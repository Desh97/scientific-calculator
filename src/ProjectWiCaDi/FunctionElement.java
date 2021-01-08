/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectWiCaDi;

import java.util.*;

/**
 *
 * @author Hp
 */
public class FunctionElement extends FormulaElement {
    private Vector<FormulaElement> argumentList;

    public Vector<FormulaElement> getArglist() {
        return argumentList;
    }

    public FunctionElement() {
        this.argumentList = new Vector<FormulaElement>();
    }

    public void addarg(FormulaElement argument) {
        this.argumentList.add(argument);
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public void setVariableValue(String varName, double value) {
        this.argumentList.stream().forEach((argumentList1) -> {
            argumentList1.setVariableValue(varName, value);
        });
    }

    @Override
    public boolean isFullyGrounded() {
        for (FormulaElement argumentList1 : argumentList) {
            if (!argumentList1.isFullyGrounded()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void upperBoundSetMethod(double u){
    }
    
    @Override
    public void lowerBoundSetMethod(double l){
    }
    
    @Override
    public void variableSetMethod(char u){
    }
}
