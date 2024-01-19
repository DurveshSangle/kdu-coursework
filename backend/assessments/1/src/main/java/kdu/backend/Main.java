package kdu.backend;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    final static Scanner scanner = new Scanner(System.in);
    final static Logging log = new Logging();
    public static IPLTournament iplTournament;
    static boolean canShowMenu = true;

    public static void main(String[] args) {
        Path filePath = Path.of("src/main/resources/IPL_2021-data.csv");
        iplTournament= new IPLTournament(filePath);
        while(canShowMenu){
            menu();
        }
    }

    public static void menu(){
        printMenu();
        int optionChoose = scanner.nextInt();
        mapFunctionToOption(optionChoose);
    }

    public static void printMenu(){
        log.logInfo("\n");
        log.logInfo("******************************** MENU *********************************************");
        log.logInfo("1. Given the team's name, find all the bowlers who have taken at least 40 wickets.");
        log.logInfo("2. Given a team, show the details of the highest wicket-taker and highest run-scorer");
        log.logInfo("3. Fetch the top 3 run-scorer and top 3 wicket-takers of the season.");
        log.logInfo("4. Exit");
        log.logInfo("Enter you option: ");
    }

    public static void mapFunctionToOption(int choosedOption){
        switch (choosedOption){
            case 1: displayBowlersWithNWickets();
                    break;
            case 2: displayHighestRunAndWicketTaker();
                    break;
            case 3: displayTopNWicketTakerRunScorer();
                    break;
            case 4: canShowMenu = false;
                    break;
            default: log.logInfo("Choose valid option");
        }
    }

    public static void displayBowlersWithNWickets(){
        log.logInfo("Enter the teams name: ");
        String teamName = scanner.next();
        List<Player> result = iplTournament.bowlersWithAtLeastNWickets(teamName,40);
        for(Player player:result){
            log.logInfo(player.toStringWickets());
        }
    }

    public static void displayHighestRunAndWicketTaker(){
        log.logInfo("Enter the teams name: ");
        String teamName = scanner.next();
        Player batsman = iplTournament.highestRunScorer(teamName);
        if(batsman==null) log.logInfo("No result found");
        else log.logInfo(batsman.toStringRuns());
        Player bowler = iplTournament.highestWicketTaker(teamName);
        if(bowler==null) log.logInfo("No result found");
        else log.logInfo(bowler.toStringWickets());
    }

    public static void displayTopNWicketTakerRunScorer(){
        List<Player> batsman = iplTournament.topNRunScorer(3);
        log.logInfo("Top 3 Run Scorer:");
        if(batsman.isEmpty()) log.logInfo("Leaderboard is empty !!!");
        else{
            for(Player player:batsman){
                log.logInfo(player.toStringRuns());
            }
        }
        List<Player> bowler = iplTournament.topNWicketTaker(3);
        log.logInfo("Top 3 Wicket Taker:");
        if(bowler.isEmpty()) log.logInfo("Leaderboard is empty !!!");
        else{
            for(Player player:bowler){
                log.logInfo(player.toStringWickets());
            }
        }
    }

}