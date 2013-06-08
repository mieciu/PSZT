package pl.pszt.fuzzylogic;

import junit.framework.*;

/**
 * Created with IntelliJ IDEA.
 * User: przemek
 * Date: 08.06.2013
 * Time: 19:06
 * To change this template use File | Settings | File Templates.
 */
public class FuzzyStatementTest extends TestCase{

    public void testPierwszy() {
        FuzzyStatement test1 = new FuzzyStatement("test",0.9);
        assertEquals(test1.getValue(), 0.9);
    }

    public void testConstructor1() {
        FuzzyStatement statement = new FuzzyStatement("test",-3.2);
        assertEquals(statement.getValue(),0.0);
    }

    public void testConstructor2() {
        FuzzyStatement statement = new FuzzyStatement("test",0);
        assertEquals(statement.getValue(),0.0);
    }

    public void testConstructor3() {
        FuzzyStatement statement = new FuzzyStatement("test",1.1);
        assertEquals(statement.getValue(),1.0);
    }

    public void testSetter1 () {
        FuzzyStatement statement = new FuzzyStatement("test",0.6);
        statement.setValue(-3.2);
        assertEquals(statement.getValue(),0.0);
    }

    public void testSetter2 () {
        FuzzyStatement statement = new FuzzyStatement("test",0.6);
        statement.setValue(6);
        assertEquals(statement.getValue(),1.0);
    }

    public void testSetter3 () {
        FuzzyStatement statement = new FuzzyStatement("test",0.6);
        statement.setValue(0.3);
        assertEquals(statement.getValue(),0.3);
    }

    public void testSetter4 () {
        FuzzyStatement statement = new FuzzyStatement("test",0.6);
        statement.setValue(0.99);
        assertEquals(statement.getValue(),0.99);
    }

    public void testNotOperator() {
        FuzzyStatement testStatement = new FuzzyStatement("Test",0.7);
        testStatement.negation();
        assertEquals(testStatement.getValue(),0.3,0.00000001);
    }

    public void testAndOperator() {
        FuzzyStatement testStatement = new FuzzyStatement("Test",0.3);
        FuzzyStatement argument = new FuzzyStatement("Test",0.6);
        testStatement.conjunction(argument);
        assertEquals(testStatement.getValue(), 0.3);
    }
    public void testAndOperatorSymmetry() {
        FuzzyStatement testStatement = new FuzzyStatement("Test",0.3);
        FuzzyStatement argument = new FuzzyStatement("Test",0.6);
        argument.conjunction(testStatement);
        assertEquals(testStatement.getValue(), 0.3);
    }

    public void testOrOperator() {
        FuzzyStatement testStatement = new FuzzyStatement("Test",0.7);

    }




}
