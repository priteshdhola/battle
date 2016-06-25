package armyBattle.units;

/**
 * Created by pritesh on 6/24/16.
 */
public class SwordsMan extends CloseCombatUnit{
    public SwordsMan(int strength) {
        super(strength);
    }

    /**
     * Swordman is strong against spearman
     * @param otherUnit
     * @return
     */
    @Override
    public boolean isStrongAgainst(Unit otherUnit) {
        return otherUnit instanceof SpearMan;
    }
}
