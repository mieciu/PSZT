package pl.pszt.fuzzylogic;
/**
 * Created with IntelliJ IDEA.
 * User: przemek
 * Date: 08.06.2013
 * Time: 13:24
 * To change this template use File | Settings | File Templates.
 */
public class FuzzyStatement implements LogicalConnective {

    private String Statement;
    private double Value;


    public FuzzyStatement(final String statement,double initialValue) {
        Statement = statement;
        if(initialValue >= 1)
            Value = 1.0;
        else if (initialValue <= 0)
            Value = 0.0;
        else
            Value = initialValue;
    }


    public String getStatement() {
        return Statement;
    }

    public void setStatement(String statement) {
        Statement = statement;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(final double value) {
        if(value >= 1)
            Value = 1.0;
        else if (value <= 0)
            Value = 0.0;
        else
            Value = value;
    }

    public void incrementValue(final double arg) {
        Value = Value + arg;
        if (Value > 1.0)
            Value = 1.0;
    }

    public void decrementValue(final double arg) {
        Value = Value - arg;
        if (Value < 0.0)
            Value = 0.0;
    }

    @Override
    public FuzzyStatement negation() {
        setValue(1 - getValue());
        return null;
    }

    @Override
    public FuzzyStatement conjunction(FuzzyStatement statement) {
        setValue(Math.min(getValue(),statement.getValue()));
        return null;
    }

    @Override
    public FuzzyStatement dissjunction(FuzzyStatement statement) {
        setValue(Math.max(getValue(),statement.getValue()));
        return null;
    }

    /* TO JEST KUPA POPRAWIC */
    public HealthLevels defuzzification()  {
       return HealthLevels.CRITICAL;
    }

    /* public TrackedLevels */
}
