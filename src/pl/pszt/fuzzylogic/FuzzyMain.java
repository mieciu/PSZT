package pl.pszt.fuzzylogic;

/**
 * Created with IntelliJ IDEA.
 * User: przemek
 * Date: 08.06.2013
 * Time: 13:34
 * To change this template use File | Settings | File Templates.
 */
public class FuzzyMain {
    public static void main(String [ ] args)
    {
        FuzzyStatement statement = new FuzzyStatement("I am tracked",0.6);
        statement.defuzzification()
    }
}
