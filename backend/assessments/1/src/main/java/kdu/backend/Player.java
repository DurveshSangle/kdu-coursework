package kdu.backend;

public class Player {
    private String name;
    private String team;
    private String role;
    private int matchesPlayed;
    private int runs;
    private double average;
    private double strikeRate;
    private int wickets;

    public Player(String name, String team, String role, int matchesPlayed, int runs, double average, double strikeRate) {
        this.name = name;
        this.team = team;
        this.role = role;
        this.matchesPlayed = matchesPlayed;
        this.runs = runs;
        this.average = average;
        this.strikeRate = strikeRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(double strikeRate) {
        this.strikeRate = strikeRate;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", team='" + team + '\'' +
                ", role='" + role + '\'' +
                ", matchesPlayed=" + matchesPlayed +
                ", runs=" + runs +
                ", average=" + average +
                ", strikeRate=" + strikeRate +
                ", wickets=" + wickets +
                '}';
    }

    public String toStringWickets(){
        return name+" -- Wickets: "+wickets+", Average: "+average+", Matches: "+matchesPlayed+", Team: "+team;
    }

    public String toStringRuns(){
        return name+" -- Runs: "+runs+", Strike Rate: "+strikeRate+", Matches: "+matchesPlayed+", Team: "+team;
    }
}
