package davanchama.mergescheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * A single meeting that is planned.
 *
 * @author Davanchama
 */
public class Meeting implements Comparable<Meeting> {
    /**
     * the list of participants of this meeting
     */
    public ArrayList<Person> pairing = new ArrayList<Person>();
    /**
     * the sum of the points of the participants. the algorithm will always pick the meeting with the lowest points
     * as next meeting.
     */
    public Double combinedPoints;

    /**
     * constructs a meeting with two participants
     *
     * @param person1
     * @param person2
     */
    public Meeting(Person person1, Person person2) {
        this.pairing = new ArrayList<Person>(List.of(new Person[] {person1, person2}));
        this.combinedPoints = person1.getPoints() + person2.getPoints();
    }

    /**
     * if the given person is contained in this meeting
     *
     * @param person the given person
     * @return true if the given person is contained in this meeting
     */
    public boolean contains(Person person) {
        return this.pairing.contains(person);
    }

    /**
     * if one of the given persons are contained in this meeting
     *
     * @param persons the given persons
     * @return true if one of the given persons are contained in this meeting
     */
    public boolean contains(ArrayList<Person> persons) {
        for (Person p : persons) {
            if (this.contains(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * updates the combined points of this meeting by summing up the current points of the participants.
     */
    public void updatePoints() {
        double sum = 0;
        for (Person p : this.pairing) {
            sum += p.getPoints();
        }
        this.combinedPoints = sum;
    }

    /**
     * two meetings are compared by their combined points.
     *
     * @param other the other meeting
     * @return the compareTo-double-methods return of the two compared points variables
     */
    @Override
    public int compareTo(Meeting other) {
        return combinedPoints.compareTo(other.combinedPoints);
    }

    /**
     * Represents the meeting by listing up the participants in form:
     * "Participant A - Participant B - Participant C - ... - Last Participant"
     *
     * @return a string representing this object
     */
    @Override
    public String toString() {
        String meeting = "";
        for (Person p : this.pairing) {
            meeting += p + " - ";
        }
        //delete the last " - "
        meeting = meeting.substring(0, meeting.length() - 3);
        return meeting;
    }
}
