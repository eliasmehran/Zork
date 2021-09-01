package ch.bbw.em;

/**
 * @author : Elias Mehran
 * @version : 28.05.2020
 **/

public class Item {

    private String name, description;
    private int weight;
    private boolean powerup;

    public Item(String name, String description, int weight, boolean powerup) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.powerup = powerup;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isPowerup() {
        return powerup;
    }

}