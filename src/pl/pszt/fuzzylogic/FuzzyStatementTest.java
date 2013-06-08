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

        FuzzyStatement result = new FuzzyStatement("Test");

        result.conjunction(testStatement,argument);
        assertEquals(result.getValue(), 0.3);

    }
    public void testAndOperatorSymmetry() {
        FuzzyStatement testStatement = new FuzzyStatement("Test",0.3);
        FuzzyStatement argument = new FuzzyStatement("Test",0.6);

        FuzzyStatement result = new FuzzyStatement("Test");

        result.conjunction(argument,testStatement);
        assertEquals(result.getValue(), 0.3);
    }

    public void testOrOperator() {
        FuzzyStatement testStatement = new FuzzyStatement("Test",0.3);
        FuzzyStatement argument = new FuzzyStatement("Test",0.6);

        FuzzyStatement result = new FuzzyStatement("Test");

        result.disjunction(testStatement,argument);
        assertEquals(result.getValue(), 0.6);
    }

    public void testOrOperatorSymmetry() {
        FuzzyStatement testStatement = new FuzzyStatement("Test",0.3);
        FuzzyStatement argument = new FuzzyStatement("Test",0.6);

        FuzzyStatement result = new FuzzyStatement("Test");

        result.disjunction(argument,testStatement);
        assertEquals(result.getValue(), 0.6);
    }

    public void testIncrementation1() {
        FuzzyStatement testStatement = new FuzzyStatement("Test",0.1);
        testStatement.incrementValue(0.23);
        assertEquals(testStatement.getValue(),0.33);
    }

    public void testIncrementation2() {
        FuzzyStatement testStatement = new FuzzyStatement("Test",0.1);
        int i = 0;
        while(i<5) {
            testStatement.incrementValue(0.1);
            ++i;
        }
        assertEquals(testStatement.getValue(),0.6);
    }
    public void testIncrementation3() {
        FuzzyStatement testStatement = new FuzzyStatement("Test",0.1);
        int i = 0;
        while(i<20) {
            testStatement.incrementValue(0.3);
            ++i;
        }
        assertEquals(testStatement.getValue(),1.0);
    }

    /*    Chyba nie zakladamy przypadku inkrenentacji o ujemne ?!
    public void testIncrementation4() {
        FuzzyStatement testStatement = new FuzzyStatement("Test",0.1);
        int i = 0;
        while(i<20) {
            testStatement.incrementValue(-0.1);
            ++i;
        }
        assertEquals(testStatement.getValue(),0.0);
    }   */

}
