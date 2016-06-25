package armyBattle.terrain;

import armyBattle.units.Unit;

import java.util.Map;

/**
 * Created by pritesh on 6/24/16.
 */
public abstract class Terrain{
    protected String name;
    protected Map<String, Float> unitModifiers;

    public abstract float getModifiersForUnits(Unit unit);
    public abstract String getTerrainName();
}
