package armyBattle;

import armyBattle.army.GroundArmy;
import armyBattle.battle.GroundBattle;
import armyBattle.terrain.NormalTerrain;
import armyBattle.terrain.Terrain;
import armyBattle.units.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by pritesh on 6/24/16.
 */
public class GroundWar {
    public static final int MAX_ARMY_SIZE = 100;

    public static void main(String[] args) throws IOException,InterruptedException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("This is a battle between two armies.");
        Thread.sleep(1000);
        System.out.println("First select a size for both armies.");
        Thread.sleep(1000);
        System.out.println("It's only fair if they both are of same size.");
        System.out.println("\n=================================================\n");
        int armySize = validateInput("Army Size", 1, MAX_ARMY_SIZE, br);

        System.out.println("\nGenerating Two Armies with equal number of units.");
        Thread.sleep(1000);
        System.out.println("They are cavalry,swordsman,spearman and Archers. ");
        Thread.sleep(1000);
        System.out.println("They will have random strength/accuracy");
        Thread.sleep(1000);
        GroundArmy army1 = createArmy(armySize);
        GroundArmy army2 = createArmy(armySize);

        // Set the battle
        GroundBattle battle = new GroundBattle(army1, army2);
        Terrain terrain = new NormalTerrain();
        battle.setTerrain(terrain);

        System.out.println("\nTerrain for this battle is : " + terrain.getTerrainName());
        System.out.println("\n=================================================");
        System.out.println("\nLET THE BATTLE BEGIN.PRESS ANY KEY.");
        br.readLine();

        // Battle happens here
        if(battle.fight() == army1) {
            System.out.println("=================================================");
            System.out.println("                   ARMY 1 WINS                   ");
        } else {
            System.out.println("=================================================");
            System.out.println("                   ARMY 2 WINS                   ");
        }
        System.out.println("=================================================");
        br.close();
    }

    /**
     * Create the army with the given size.
     * For now just use the default army formation
     * @param size
     * @return
     * @throws IOException
     */
    private static GroundArmy createArmy(int size) throws IOException {
        GroundArmy army = new GroundArmy();
        defaultArmy(army, size);
        return army;
    }

    /**
     * Default army consist of 1/4 of each units
     * @param army
     * @param size
     */
    private static void defaultArmy(GroundArmy army, int size) {
        addUnits("Archer", army, size/4);
        addUnits("Cavalry", army, size/4);
        addUnits("Swordsman", army, size/4);
        addUnits("Spearman", army, size/4);
    }

    /**
     * Validate input of the command prompt that we are showing to user
     * @param inputName
     * @param minValue
     * @param maxValue
     * @param br
     * @return
     * @throws IOException
     */
    private static int validateInput(String inputName, int minValue, int maxValue, BufferedReader br) throws IOException {
        boolean validInput;
        int input = minValue;
        do {
            System.out.print("Enter " + inputName + "from range : ("+minValue +"-"+maxValue+") ==> ");
            String inputString = br.readLine();
            validInput = checkIfValid(inputString, minValue, maxValue);
            if (!validInput) {
                System.out.println("Invalid input provided : " + inputName + ".Try Again");
            } else {
                input = Integer.parseInt(inputString);
            }
        } while (!validInput);

        return input;
    }

    /**
     * Check if the input is an integer and also falls between given min and max
     * @param intString
     * @param minValue
     * @param maxValue
     * @return
     */
    private static boolean checkIfValid(String intString, int minValue, int maxValue) {
        int parsedNumber;
        try {
            parsedNumber = Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            return false;
        }
        if(parsedNumber >= minValue && parsedNumber <= maxValue)
            return true;
        else
            return false;
    }

    /**
     * Add a given unit to the army. It selects a random number generated betwene the min and max accuracy/strength
     * and assigns that to the unit.
     * @param unitType
     * @param army
     * @param numberOfUnits
     */
    private static void addUnits(String unitType, GroundArmy army, int numberOfUnits) {
        switch(unitType) {
            case "Archer" :
                for (int i = 0; i < numberOfUnits; i++) {
                    army.addUnitToArmy(new Archer(getRandomNumFromRange(Archer.MIN_ACCURACY, Archer.MAX_ACCURACY)));
                }
                break;
            case "Swordsman" :
                for (int i = 0; i < numberOfUnits; i++) {
                    army.addUnitToArmy(new SwordsMan(getRandomNumFromRange(CloseCombatUnit.MIN_STRENGTH, CloseCombatUnit.MAX_STRENGTH)));
                }
                break;
            case "Cavalry":
                for (int i = 0; i < numberOfUnits; i++) {
                    army.addUnitToArmy(new Cavalry(getRandomNumFromRange(CloseCombatUnit.MIN_STRENGTH, CloseCombatUnit.MAX_STRENGTH)));
                }
                break;
            case "Spearman":
                for (int i = 0; i < numberOfUnits; i++) {
                    army.addUnitToArmy(new SpearMan(getRandomNumFromRange(CloseCombatUnit.MIN_STRENGTH, CloseCombatUnit.MAX_STRENGTH)));
                }
        }
    }

    /**
     * Generates a random number out of the given range
     * @param minValue
     * @param maxValue
     * @return
     */
    public static int getRandomNumFromRange(int minValue, int maxValue) {
        Random ran = new Random();
        return ran.nextInt((maxValue-minValue)+1) + minValue;
    }
}
