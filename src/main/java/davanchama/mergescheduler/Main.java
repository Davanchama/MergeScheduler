package davanchama.mergescheduler;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Implements the main routine.
 *
 * @author Davanchama
 */
public class Main {
    /**
     * the list of all scheduled meetings
     */
    private ArrayList<Meeting> meetings = new ArrayList<Meeting>();
    /**
     * a list containing all persons entered
     */
    private ArrayList<Person> team = new ArrayList<Person>();

    /**
     * the main routine.
     *
     * @param args
     */
    public static void main(String[] args) {
        Main routine = new Main();
        System.out.println("MergeShuffler 1.0, Koeri Inc. (2022)");
        System.out.println("Type 'quit' to exit anytime");
        //read names
        RequestHandler handler = new RequestHandler();
        ArrayList<String> names = handler.requestNames();
        if (names == null) {
            return;
        }
        //read the current week
        System.out.println("Enter the current week.");
        Integer week = handler.requestWeek();
        if (week == null) {
            return;
        }
        //read if the user wants to shuffle the participants before
        System.out.println("Do you first want to shuffle the participants?\n Otherwise the first users entered will" +
            "be in the first meetings scheduled.");
        Boolean ifShuffle = handler.requestYesNo();
        if (ifShuffle == null) {
            return;
        }

        //construct team
        for (String name : names) {
            Person participant = new Person(name);
            routine.team.add(participant);
        }

        //shuffle if requested
        if (ifShuffle) {
            Collections.shuffle(routine.team);
            System.out.println("Shuffled participants");
        }

        //initialize the meetings
        routine.meetings = routine.initMeetings();
        //run the algorithm
        routine.meetings = routine.constructNewMeetings();
        //print the results
        for (Meeting m : routine.meetings) {
            System.out.println("Week " + week + ": " + m);
            week++;
        }

    }

    /**
     * initializes all meetings
     *
     * @return all combinations of meetings.
     */
    public ArrayList<Meeting> initMeetings() {
        ArrayList<Meeting> meetings = new ArrayList<Meeting>();
        for (int i = 0; i < team.size() - 1; i++) {
            for (int j = i + 1; j < team.size(); j++) {
                Meeting newMeeting = new Meeting(team.get(i), team.get(j));
                meetings.add(newMeeting);
            }
        }
        return meetings;
    }

    /**
     * rearranges the meetings list so that nobody gets picked too often.
     *
     * @return the ordered list with meetings
     */
    public ArrayList<Meeting> constructNewMeetings() {
        ArrayList<Meeting> oldMeetings = new ArrayList<Meeting>(this.meetings);
        ArrayList<Meeting> newMeetings = new ArrayList<Meeting>();

        while (newMeetings.size() != this.meetings.size()) {
            Meeting nextMeeting = Collections.min(oldMeetings);
            newMeetings.add(nextMeeting);
            oldMeetings.remove(nextMeeting);
            if (oldMeetings.size() > 0)
                updatePoints(oldMeetings, nextMeeting);
        }
        return newMeetings;
    }

    /**
     * updates the points of all participants. first, all persons that participated in the last meeting
     * increase their points. then, every meeting that is left gets their points updated.
     *
     * @param meetings       the meetings to update
     * @param currentMeeting the last meeting that was chosen
     */
    private void updatePoints(ArrayList<Meeting> meetings, Meeting currentMeeting) {
        for (Person p : currentMeeting.pairing) {
            p.upgradePoints();
        }

        for (Meeting meeting : meetings) {
            meeting.updatePoints();
        }
    }
}
