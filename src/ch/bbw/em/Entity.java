package ch.bbw.em;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author : Elias Mehran
 * @version : 28.05.2020
 **/

public class Entity {

    private String name;
    private Room location;
    private int lvl, exp, capacity, currentCapacity;
    private double health, dmg;
    private final ArrayList<Item> bag;

    public Entity(String name, int lvl, Room location) {
        this.name = name;
        this.lvl = lvl;
        this.location = location;
        this.exp = 0;
        this.capacity = lvl * 10;
        this.currentCapacity = 0;
        this.health = lvl * 3.5;
        this.dmg = lvl * 1.5;
        this.bag = new ArrayList<>();
    }

    public Entity(String name, int lvl) {
        this.name = name;
        this.lvl = lvl;
        this.location = null;
        this.exp = 0;
        this.capacity = lvl * 10;
        this.currentCapacity = 0;
        this.health = lvl * 3.5;
        this.dmg = lvl * 1.5;
        this.bag = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentCapacity() {
        currentCapacity = 0;
        for (Item item : getBag()) {
            currentCapacity = currentCapacity + item.getWeight();
        }
        return currentCapacity;
    }

    public double getDmg() {
        return dmg;
    }

    public void setDmg(double dmg) {
        this.dmg = dmg;
    }

    public Room getLocation() {
        return location;
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    public ArrayList<Item> getBag() {
        return bag;
    }

    public void lvlUp() {
        setExp(getExp() - getLvl() * 3);
        setLvl(getLvl() + 1);
        setCapacity(getLvl() * 10);
        setHealth(getLvl() * 3.5);
        setDmg(getLvl() * 1.5);
        System.out.println("\u001B[32mLVL UP!\u001B[0m");
        System.out.println("new stats:");
        printStats();
    }

    public String getItems() {
        if (getBag().size() > 0) {
            StringBuilder items = new StringBuilder();
            for (Item item : getBag()) {
                items = new StringBuilder(items + item.getName() + ", ");
            }
            return items.substring(0, items.length() - 2);
        }
        return "None";
    }

    public void pickUpItem(Item item) {
        getBag().add(item);
        System.out.println("You've picked up a " + item.getName());
    }

    public void disposeItem(Item item) {
        if (getBag().contains(item)) {
            getBag().remove(item);
        }
    }

    public void printStats() {
        System.out.println("Name: " + getName());
        System.out.println("Level: " + getLvl());
        System.out.println("Health: " + getHealth() + "/" + getLvl() * 3.5);
        System.out.println("Base damage: " + getDmg());
        System.out.println("EXP: " + getExp() + "/" + getLvl() * 3);
        System.out.println("Items: " + getItems());
        System.out.println("Capacity: " + getCurrentCapacity() + "/" + getCapacity());
    }

    public void printItems() {
        StringBuilder bag = new StringBuilder();
        for (Item item : getBag()) {
            bag.append(item.getName()).append("\n\t").append(item.getDescription()).append("\n");
        }
        System.out.print(bag.toString());
    }

    public void attack(Entity opponent) {
        double base = getDmg();
        for (Item item : getBag()) {
            if (item.isPowerup()) {
                base += (getLvl() * 1.5);
            }
        }
        setDmg(base);
        Random random = new Random();
        int luck = random.nextInt(20);
        if (luck == 0) {
            System.out.println("\u001B[41m\u001B[30mA critical hit!\u001B[0m");
            opponent.setHealth(opponent.getHealth() - (getDmg() * 2));
            System.out.println("dmg dealt: \u001B[31m" + getDmg() * 2 + "dmg\u001B[0m");
        } else if (luck == 1) {
            System.out.println("\u001B[33m missed!\u001B[0m");
        } else {
            opponent.setHealth(opponent.getHealth() - getDmg());
            System.out.println("dmg dealt: \u001B[31m" + getDmg() + "dmg\u001B[0m");
        }
        setDmg(getLvl() * 1.5);
    }

}
