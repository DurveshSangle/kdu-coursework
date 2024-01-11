package kdu.backend.hw4.q2;

import kdu.backend.hw4.Logging;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class TicketReservation {

    private static final int CONFIRMEDLIST_LIMIT = 10;
    private static final int WAITINGLIST_LIMIT = 3;

    private List<Passenger> confirmedList = new ArrayList<>();
    private Deque<Passenger> waitingList = new ArrayDeque<>();

    public static final Logging log = new Logging();

    public boolean isConfirmedListFull(){
        return (confirmedList.size()==CONFIRMEDLIST_LIMIT);
    }

    public boolean isWaitingListFull(){
        return (waitingList.size()==WAITINGLIST_LIMIT);
    }

    /**
     * Return whether flight is book or not.
     *
     * @param firstName
     * @param lastName
     * @param age
     * @param gender
     * @param travelClass it can be economy or travelClass
     * @param confirmationNumber it is an alpha-numeric value, e.g., "C9"
     *
     * @return true if booked else false
     * */
    public boolean bookFlight(String firstName, String lastName, int age, String gender, String travelClass,String confirmationNumber) {
        if(isConfirmedListFull() && isWaitingListFull()) {
            log.logInfo("Ticket not available!!");
            return false;
        }
        Passenger passenger = new Passenger(firstName, lastName, age, gender, travelClass, confirmationNumber);
        if(isConfirmedListFull()){
            log.logInfo("Waiting Ticket Booked");
            waitingList.add(passenger);
        }
        else {
            log.logInfo("Confirmed Ticket Booked.");
            confirmedList.add(passenger);
        }
        return true;
    }

    /**
     * Returns whether a booking with confirmationNumber exist or not and cancels booking
     *
     * @param confirmationNumber it is an alpha-numeric value, e.g., "C9"
     *
     * @return true if booking exist and cancelled, else false is no such booking exist
     * */
    public boolean cancel(String confirmationNumber) {
        Iterator<Passenger> iterator = waitingList.iterator();
        if(removePassenger(iterator,confirmationNumber)) {
            log.logInfo("Ticket Cancelled !!");
            return true;
        }
        iterator = confirmedList.iterator();
        if(removePassenger(iterator,confirmationNumber)){
            Passenger waitingPassenger = waitingList.poll();
            confirmedList.add(waitingPassenger);
            log.logInfo("Ticket Cancelled !!");
            return true;
        }
        log.logInfo("No such booking exist !!");
        return false;
    }

    public boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {
        while(iterator.hasNext()){
            Passenger passenger = iterator.next();
            if(passenger.getConfirmationNumber().equalsIgnoreCase(confirmationNumber)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TicketReservation ticketReservation = new TicketReservation();
        
        String firstName = "Durvesh";
        String lastName = "Sangle";
        int age = 21;
        String gender = "male";
        String travelClass = "business";
        /**
         * book 10 flights at first then 11th would be waiting ticket
         * */
        ticketReservation.bookFlight(firstName,"lastName",age,"gender","travelClass","B4");
        ticketReservation.bookFlight("firstName","lastName",age,"gender","travelClass","Z4");
        ticketReservation.bookFlight("firstName","lastName",age,"gender","travelClass","E4");
        ticketReservation.bookFlight("firstName","lastName",age,"gender","travelClass","T4");
        ticketReservation.bookFlight("firstName","lastName",age,"gender","travelClass","K4");
        ticketReservation.bookFlight("firstName","lastName",age,"gender","travelClass","I4");
        ticketReservation.bookFlight("firstName","lastName",age,"gender","travelClass","P4");
        ticketReservation.bookFlight("firstName","lastName",age,"gender","travelClass","C4");
        ticketReservation.bookFlight("firstName","lastName",age,"gender","travelClass","N4");
        ticketReservation.bookFlight("firstName","lastName",age,"gender","travelClass","M4");
        /**
         * 11th booking
         * */
        ticketReservation.bookFlight("firstName","lastName",age,"gender","travelClass","L4");
        /**
         * cancelling ticket with wrong confirmation number
         */
        ticketReservation.cancel("F6");
        /**
         * cancelling ticket with correct confirmation number
         */
        ticketReservation.cancel("B4");
    }
}