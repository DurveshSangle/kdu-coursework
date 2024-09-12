package kdu.backend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Fixtures {
    private Set<String> teams;
    private Map<String, Set<String>> matchesScheduled = new HashMap<>();

    private List<Match> matches = new ArrayList<>();

    private LocalDate date = LocalDate.of(2023,01,10);
    public Fixtures(Set<String> teams){
        this.teams= teams;
    }
    public void createFixtures(){
        int matchNumber = 1;
        for(String teamA:teams){
            for(String teamB:teams){
                if(isMatchAlreadyDone(teamA,teamB)) continue;
                scheduleFixture(teamA,teamB,matchNumber);
            }
        }
    }

    public boolean isMatchAlreadyDone(String teamA, String teamB){
        if(!matchesScheduled.containsKey(teamA)) return false;
        return matchesScheduled.get(teamA).contains(teamB);
    }

    public void scheduleFixture(String teamA,String teamB,int matchNumber){
        Set<String> teamAMatches = matchesScheduled.getOrDefault(teamA,new HashSet<>());
        teamAMatches.add(teamB);
        matchesScheduled.put(teamA,teamAMatches);
        String time = "6:30";
        if(matchNumber%2==1) time = "9:30";
        Match match = new Match(date,time,matchNumber++,teamA,teamB,teamA+"_home");
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
