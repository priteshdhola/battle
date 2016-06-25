package armyBattle.units;

/**
 * Created by pritesh on 6/24/16.
 */
public class SpearMan extends CloseCombatUnit{
    public SpearMan(int strength) {
        super(strength);
    }

    /**
     * Spearman is strong against cavalry
     * @param otherUnit
     * @return
     */
    @Override
    public boolean isStrongAgainst(Unit otherUnit) {
        return otherUnit instanceof Cavalry;
    }

}
