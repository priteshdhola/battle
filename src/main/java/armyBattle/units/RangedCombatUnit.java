package armyBattle.units;

/**
 * Created by pritesh on 6/24/16.
 */
public abstract class RangedCombatUnit implements Unit {
    // Max possible accuracy
    public static final int MAX_ACCURACY = 100;
    // Min possible accuracy
    public static final int MIN_ACCURACY = 1;
    // Accuracy of the unit
    private int accuracy;

    /**
     * Creates a ranged combat unit
     * @param accuracy
     */
    public RangedCombatUnit(int accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * Gets accuracy of a rangedUnit
     * @return
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * Is this unit strong against other unit
     * @param otherUnit
     * @return
     */
    @Override
    public abstract boolean isStrongAgainst(Unit otherUnit);

    /**
     * Is this a ranged unit
     * @return
     */
    @Override
    public boolean isRangedUnit() { return true; }

    /**
     * Is this a closed combat unit
     * @return
     */
    @Override
    public boolean isCloseCombatUnit() {
        return false;
    }
}
