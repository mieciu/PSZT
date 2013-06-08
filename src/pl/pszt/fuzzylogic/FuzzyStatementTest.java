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
        assertEquals(test1.getValue(),0.9);
    }


}
