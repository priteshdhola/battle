package armyBattle.terrain;

import armyBattle.units.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pritesh on 6/24/16.
 */
public class NormalTerrain extends Terrain {
    public String name = "Normal";
    public Map<String, Float> unitModifiers = new HashMap<>();

    public NormalTerrain() {
        unitModifiers.put("Archer", 1f);
        unitModifiers.put("Cavalry", 1f);
        unitModifiers.put("Spearman", 1f);
        unitModifiers.put("Swordsman", 1f);
    }

    public float getArcherModificator() { return unitModifiers.get("Archer"); }
    public float getCavalrymanModificator() {
        return unitModifiers.get("Cavalry");
    }
    public float getSpearmanModificator() {
        return unitModifiers.get("Spearman");
    }
    public float getSwordsmanModificator() {
        return unitModifiers.get("Swordsman");
    }

    @Override
    public String getTerrainName() {
        return name;
    }

    @Override
    public float getModifiersForUnits(Unit unit) {
        if (unit instanceof Archer) {
            return getArcherModificator();
        }
        if (unit instanceof Cavalry) {
            return getCavalrymanModificator();
        }
        if (unit instanceof SpearMan) {
            return getSpearmanModificator();
        }
        if (unit instanceof SwordsMan) {
            return getSwordsmanModificator();
        }
        return 1;
    }
}
