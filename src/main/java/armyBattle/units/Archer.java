package armyBattle.units;

/**
 * Created by pritesh on 6/24/16.
 */
public class Archer extends RangedCombatUnit{
    public Archer(int accuracy) {
        super(accuracy);
    }

    /**
     * Archer is strong against everyone in a ranged fight
     * @param otherUnit
     * @return
     */
    @Override
    public boolean isStrongAgainst(Unit otherUnit) {
        return false;
    }

    @Override
    public boolean isCloseCombatUnit() {
        return false;
    }
}
