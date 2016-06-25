package armyBattle.army;

import armyBattle.units.Unit;

import java.util.List;

/**
 * Created by pritesh on 6/24/16.
 */
public abstract class Army {
    /** Get total unit count **/
    public abstract int getTotalUnitCount();

    /** Add an unit to army **/
    public abstract void addUnitToArmy(Unit unit);

    /** Get all units **/
    public abstract List<Unit> getAllUnits();
}
