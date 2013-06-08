package pl.pszt.fuzzylogic;

/**
 * Created with IntelliJ IDEA.
 * User: przemek
 * Date: 08.06.2013
 * Time: 14:06
 * To change this template use File | Settings | File Templates.
 */
public enum TrackedLevels {
    TRACKED,            /* > 0.75 */
    RATHERTRACKED,      /* <= 0.75>*/
    RATHERNOTTRACKED,   /* <= 0.5*/
    NOTTRACKED          /* == 0.0*/
}
