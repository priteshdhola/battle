package armyBattle.units;

/**
 * Created by pritesh on 6/24/16.
 */
public abstract class CloseCombatUnit implements Unit{
    //Strength coefficient. Used to calculate chances during the battle
    public static final float STRENGTH_COEFF = 1.5f;

    // Max possible strength
    public static final int MAX_STRENGTH = 100;
    // Min possible strength
    public static final int MIN_STRENGTH = 1;

    // Strength of this unit
    private int strength;

    /**
     * Creates a close combat unit
     * @param strength
     */
    public CloseCombatUnit(int strength) {
        this.strength = strength;
    }

    /**
     * Returns strength of this unit
     * @return
     */
    public int getStrength() {
        return strength;
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
    public boolean isRangedUnit() { return false; }

    /**
     * Is this a closed combat unit
     * @return
     */
    @Override
    public boolean isCloseCombatUnit() { return true; }
}
