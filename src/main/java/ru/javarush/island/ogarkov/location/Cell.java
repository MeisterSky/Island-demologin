package ru.javarush.island.ogarkov.location;

import ru.javarush.island.ogarkov.entity.Organism;
import ru.javarush.island.ogarkov.entity.landform.Landform;
import ru.javarush.island.ogarkov.settings.Items;

import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static ru.javarush.island.ogarkov.settings.Items.*;

// Участок локации, ячейка
public class Cell implements Comparable<Cell> {
    private final Lock lock = new ReentrantLock(true);
    public Territory territory;
    private Set<Organism> population;
    private Items residentItem;
    private Landform landform;

    public Cell(Territory territory) {
        this.territory = territory;
    }

    public Landform getLandform() {
        return landform;
    }

    public void setLandform(Landform landform) {
        this.landform = landform;
    }

    public Set<Organism> getPopulation() {
        return population;
    }

    public void setPopulation(Set<Organism> population) {
        this.population = population;
    }

    public Territory getTerritory() {
        return territory;
    }

    public Items getResidentItem() {
        return residentItem;
    }

    public void setResidentItem(Items item) {
        residentItem = item;
    }

    public Lock getLock() {
        return lock;
    }

    @Override
    public int compareTo(Cell secondCell) {
        int result = 0;
        Items firstItem = this.getResidentItem();
        Items secondItem = secondCell.getResidentItem();
        //TODO ---  Many if with equals results. I see just 2 results -1 and 1
        if (firstItem.is(CARNIVORE) && secondItem.isNot(CARNIVORE)) {
            result = 1;
        } else if (firstItem.isNot(CARNIVORE) && secondItem.is(CARNIVORE)) {
            result = -1;
        } else if (firstItem.is(HERBIVORE) && secondItem.isNot(HERBIVORE)) {
            result = 1;
        } else if (firstItem.isNot(HERBIVORE) && secondItem.is(HERBIVORE)) {
            result = -1;
        } else if (firstItem.is(PLANT) && secondItem.isNot(PLANT)) {
            result = 1;
        } else if (firstItem.isNot(PLANT) && secondItem.is(PLANT)) {
            result = -1;
        }

        if (result == 0) {
            Set<Organism> firstPopulation = this.getPopulation();
            Set<Organism> secondPopulation = secondCell.getPopulation();

            double firstTerritoryWeight = firstItem.getMaxWeight() * firstPopulation.size();
            double secondTerritoryWeight = secondItem.getMaxWeight() * secondPopulation.size();
            result = Double.compare(firstTerritoryWeight, secondTerritoryWeight);
        }
        return result;
    }


}
