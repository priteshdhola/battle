package armyBattle.army;

import armyBattle.units.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pritesh on 6/24/16.
 */
public class GroundArmy extends Army {
    private List<Unit> allUnits = new ArrayList<>();

    @Override
    public int getTotalUnitCount() {
        return allUnits.size();
    }

    @Override
    public void addUnitToArmy(Unit unit) {
        allUnits.add(unit);
    }

    @Override
    public List<Unit> getAllUnits() {
        return allUnits;
    }
}
