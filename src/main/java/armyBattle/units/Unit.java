package armyBattle.units;

/**
 * Created by pritesh on 6/24/16.
 */
public interface Unit {
    /** Is it a ranged unit **/
    boolean isRangedUnit();

    /** Is it strong against another unit **/
    boolean isStrongAgainst(Unit otherUnit);

    /** Is it closed combat unit **/
    boolean isCloseCombatUnit();
}
