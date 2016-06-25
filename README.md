## Battle of Two armies
  Checkout the repo and run following from the command line with in the battle directory
  ```
  mvn clean install exec:java -Dexec.mainClass="armyBattle.GroundWar
  ```

### Assumptions
  - Army size is fixed for both sides
  - Both armies will have similar number of units for each unit type
  - Ranged unit(Archers) go first to battles followed by close combat unit
  - Archers have "accuracy" as their main trait and others have "strength" as their trait
  - First consideration will be given to this traits. if both unit has same traits then it becomes a coin toss

