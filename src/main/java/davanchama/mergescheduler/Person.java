package davanchama.mergescheduler;

import java.util.ArrayList;

/**
 * A class representing a single person.
 *
 * @author Davanchama
 */
public class Person {
    /**
     * the points of this person. this variable relates to how often this person already was in a meeting.
     * internally, the points are just integers. the getter of this variable can implement a function for this value.
     */
    public Integer points;
    /**
     * the name of the person.
     */
    private String name;
    /**
     * the last persons that this person was paired with
     */
    private ArrayList<Person> personsPairedWith;

    /**
     * creates a person with a name
     *
     * @param name the given name
     */
    public Person(String name) {
        this.name = name;
        this.points = 0;
    }

    /**
     * increases the points by one.
     */
    public void upgradePoints() {
        this.points++;
    }

    /**
     * gets the points of the person. currently, log is used for the algorithm,
     * but log is optional
     *
     * @return the points of the person
     */
    public double getPoints() {
        return Math.log1p(this.points);
    }

    /**
     * represents a person by its name.
     *
     * @return the name of the person
     */
    @Override
    public String toString() {
        return name;
    }
}
