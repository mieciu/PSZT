package pl.pszt.fuzzylogic;

/**
 * Created with IntelliJ IDEA.
 * User: przemek
 * Date: 08.06.2013
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public interface LogicalConnective {
    FuzzyStatement negation();
    FuzzyStatement conjunction(FuzzyStatement statement);
    FuzzyStatement disjunction(FuzzyStatement statement);
}
