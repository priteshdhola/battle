package armyBattle.units;

/**
 * Created by pritesh on 6/24/16.
 */
public class Cavalry extends CloseCombatUnit{
    public Cavalry(int strength) {
        super(strength);
    }

    /**
     * Cavalry is strong against swords men
     * @param otherUnit
     * @return
     */
    @Override
    public boolean isStrongAgainst(Unit otherUnit) {
        return otherUnit instanceof SwordsMan;
    }
}
