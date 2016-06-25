package armyBattle.battle;

import armyBattle.army.Army;
import armyBattle.terrain.Terrain;

/**
 * Created by pritesh on 6/24/16.
 */
public abstract class Battle {
    protected Army winner;
    protected Army army1;
    protected Army army2;
    protected Terrain terrain;

    /**
     * Set the terrain for the battle
     * @param terrain
     */
    public abstract void setTerrain(Terrain terrain);

    /**
     * Fight here
     * @return
     */
    public abstract Army fight() throws InterruptedException;
}
