package model;

//Represents Soccer unit(or item) i.e member of Soccer type.
public abstract class SoccerUnit {
    String name;

    public SoccerUnit(String name) {
        this.name = name;
    }

    // EFFECTS: if list of the sub-units contains the selected unit,
    //                     - return true.
    //                     - Otherwise, return false.
    public boolean isUnitPresent(SoccerUnit sc) {
        return false;
    }
}
