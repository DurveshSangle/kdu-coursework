package kdu.backend;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Match {
    private LocalDate dateTime;
    private String time;
    private int matchNo;
    private String teamHome;
    private String teamAway;
    private String ground;

    public Match(LocalDate date, String time,int matchNo, String teamHome, String teamAway, String ground) {
        this.dateTime = date;
        this.time = time;
        this.matchNo = matchNo;
        this.teamHome = teamHome;
        this.teamAway = teamAway;
        this.ground = ground;
    }
}
