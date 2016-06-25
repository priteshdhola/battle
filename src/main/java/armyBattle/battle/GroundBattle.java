package armyBattle.battle;

import armyBattle.army.Army;
import armyBattle.army.GroundArmy;
import armyBattle.terrain.NormalTerrain;
import armyBattle.terrain.Terrain;
import armyBattle.units.CloseCombatUnit;
import armyBattle.units.RangedCombatUnit;
import armyBattle.units.Unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by pritesh on 6/24/16.
 */
public class GroundBattle extends Battle{

    // To save the winner
    private Army winner;

    // Two ground armies
    private GroundArmy army1;
    private GroundArmy army2;

    // Terrain is defined as normal
    private Terrain terrain = new NormalTerrain();

    /**
     * Set up ground battle
     * @param army1
     * @param army2
     */
    public GroundBattle(GroundArmy army1, GroundArmy army2) {
        this.army1 = army1;
        this.army2 = army2;
    }

    /**
     * Set the terrain for the battle
     * @param terrain
     */
    @Override
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    /**
     * Fight happens here. First a ranged fight followed by a closed combat fight.
     * @return
     */
    @Override
    public Army fight() throws InterruptedException{
        // Ranged units fights first
        System.out.println("\n=================================================\n");
        Thread.sleep(1000);
        System.out.println("            Ranged combat begins");
        Thread.sleep(1000);

        // These are the ranged units of each army
        List<RangedCombatUnit> ranged1 = getAllRangedUnits(army1.getAllUnits());
        List<RangedCombatUnit> ranged2 = getAllRangedUnits(army2.getAllUnits());

        // These are the targets
        List<Unit> units1 = new ArrayList<>(army1.getAllUnits());
        List<Unit> units2 = new ArrayList<>(army2.getAllUnits());

        // Fighting happens here
        int remainingUnitsFromArmy2 = rangedUnitsShoot(ranged1, units2, 1);
        int remainingUnitsFromArmy1 = rangedUnitsShoot(ranged2, units1, 2);
        System.out.println("            Ranged combat ends");

        // Remaining units
        System.out.println("\nRemaining Army 1 : " + remainingUnitsFromArmy1 +
                           " == Remaining Army 2 : " + remainingUnitsFromArmy2);

        // Closed combat begins here
        System.out.println("\n=================================================\n");
        Thread.sleep(1000);
        System.out.println("            Close combat begins here\n");
        List<CloseCombatUnit> closeCombatUnits1 = getAllCloseCombatUnits(units1);
        List<CloseCombatUnit> closeCombatUnits2 = getAllCloseCombatUnits(units2);

        // Fighting happens here. When
        closeCombatUnitsFight(closeCombatUnits1, closeCombatUnits2);

        // If Army 1 is empty then Army 2 wins. Else Army 1.
        if(closeCombatUnits1.isEmpty()) {
            winner = army2;
        } else {
            winner = army1;
        }
        return winner;
    }

    /**
     * Ranged units battle it out here
     * @param rangedUnits
     * @param targets
     * @param army
     */
    private int rangedUnitsShoot(List<RangedCombatUnit> rangedUnits, List<Unit> targets, int army) {
        Random ran = new Random();
        // Go through each ranged unit
        for (RangedCombatUnit rangedUnit : rangedUnits) {
            // Get the terrain modifier
            float terranModifier = terrain.getModifiersForUnits(rangedUnit);

            // Pick a random target
            int target = ran.nextInt(targets.size());

            // Calculate probable vs actual accuracy. If actual is higher then probable then remove the targe
            int probableAccuracy = ran.nextInt(RangedCombatUnit.MAX_ACCURACY);
            int actualAccuracy = (int) (rangedUnit.getAccuracy() * terranModifier);
            if (actualAccuracy > probableAccuracy) {
                targets.remove(target);
            }
        }
        return targets.size();
    }

    /**
     * Returns the ranged unit out of an army
     * @param units
     * @return
     */
    private List<RangedCombatUnit> getAllRangedUnits(List<Unit> units) {
        List<RangedCombatUnit> rangedUnits = new ArrayList<>();
        for (Unit unit : units) {
            if (unit.isRangedUnit()) {
                rangedUnits.add((RangedCombatUnit) unit);
            }
        }
        return rangedUnits;
    }

    /**
     * Close combat happens here
     * @param closeCombatUnit1
     * @param closeCombatUnit2
     */
    private void closeCombatUnitsFight(List<CloseCombatUnit> closeCombatUnit1, List<CloseCombatUnit> closeCombatUnit2) throws InterruptedException{
        do {
            // Randomize both units before putting back in battle
            Collections.shuffle(closeCombatUnit1);
            Collections.shuffle(closeCombatUnit2);

            // Figure out which one is the smaller unit
            int smaller = closeCombatUnit1.size();
            if (closeCombatUnit2.size() < smaller) {
                smaller = closeCombatUnit2.size();
            }

            // Store the units that you will be removing from the battle at the end of each round
            List<CloseCombatUnit> unitsToRemoveFromArmy1 = new ArrayList<>();
            List<CloseCombatUnit> unitsToRemoveFromArmy2 = new ArrayList<>();

            // Loop through smaller unit
            for (int i = 0; i < smaller; i++) {
                // Get one unit from each army
                CloseCombatUnit unit1 = closeCombatUnit1.get(i);
                CloseCombatUnit unit2 = closeCombatUnit2.get(i);

                // Be default co-efficient of strength is 1.5f
                // If both units are same then its set to 1
                float strCoef = CloseCombatUnit.STRENGTH_COEFF;
                if (unit1.isStrongAgainst(unit2)) {
                    // do nothing
                } else if (unit2.isStrongAgainst(unit1)) {
                    // do nothing
                } else {
                    // both are from same unit so no advantage is given to a unit
                    strCoef = 1;
                }

                // Now get the terrain modifiers.
                float terrainModificator1 = terrain.getModifiersForUnits(unit1);
                float terrainModificator2 = terrain.getModifiersForUnits(unit2);

                // calculate the chances of a winner from unit's strength, strength coeff and terrain modifier
                int unit1Chances = (int) (unit1.getStrength() * strCoef * terrainModificator1);
                int unit2Chances = (int) (unit2.getStrength() * strCoef * terrainModificator2);

                // if unit 1 has better chance, remove unit 2
                // if unit 2 has better chance, remove unit 1
                // if both has equal chance, then do a coin flip
                if (unit1Chances > unit2Chances) {
                    unitsToRemoveFromArmy2.add(unit2);
                } else if (unit1Chances < unit2Chances) {
                    unitsToRemoveFromArmy1.add(unit1);
                } else {
                    Random randomNumber = new Random();
                    if (50 > randomNumber.nextInt(100)) {
                        unitsToRemoveFromArmy2.add(unit2);
                    } else {
                        unitsToRemoveFromArmy1.add(unit1);
                    }
                }
            }
            System.out.println("Remaining Army 1 : " + (closeCombatUnit1.size() - unitsToRemoveFromArmy1.size()) +
                               " == Remaining Army 2 : " + (closeCombatUnit2.size() - unitsToRemoveFromArmy2.size()));
            System.out.println("-------------------------------------------------");
            Thread.sleep(500);
            // Remove the above mentioned units from the combat
            closeCombatUnit1.removeAll(unitsToRemoveFromArmy1);
            closeCombatUnit2.removeAll(unitsToRemoveFromArmy2);
        } while (!closeCombatUnit1.isEmpty() && !closeCombatUnit2.isEmpty());
        System.out.println("\n     Close combat ends.Battle ends as well. \n");
    }

    /**
     * Get closed combat unit out of an army
     * @param units
     * @return
     */
    private List<CloseCombatUnit> getAllCloseCombatUnits(List<Unit> units) {
        List<CloseCombatUnit> meleeUnits = new ArrayList<>();
        for (Unit unit : units) {
            if (!unit.isRangedUnit()) {
                meleeUnits.add((CloseCombatUnit) unit);
            }
        }
        return meleeUnits;
    }
}
