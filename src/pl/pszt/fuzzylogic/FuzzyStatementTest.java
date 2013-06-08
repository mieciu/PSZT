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

    public void testNotOperator() {
        FuzzyStatement testStatement = new FuzzyStatement("Test",0.7);
        testStatement.negation();
        assertEquals(testStatement.getValue(),0.3,0.00000001);
    }

    public void testAndOperator() {
        FuzzyStatement testStatement = new FuzzyStatement("Test",0.3);
        FuzzyStatement argument = new FuzzyStatement("Test",0.6);
        testStatement.conjunction(argument);
        assertEquals(testStatement.getValue(),0.3);
    }
    public void testAndOperatorSymmetry() {
        FuzzyStatement testStatement = new FuzzyStatement("Test",0.3);
        FuzzyStatement argument = new FuzzyStatement("Test",0.6);
        argument.conjunction(testStatement);
        assertEquals(testStatement.getValue(),0.3);
    }

    public void testOrOperator() {
        FuzzyStatement testStatement = new FuzzyStatement("Test",0.7);

    }




}
