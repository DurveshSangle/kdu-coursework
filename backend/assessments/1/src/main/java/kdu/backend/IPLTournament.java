package kdu.backend;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class IPLTournament {
    private List<Player> allPlayers;
    private Map<String,List<Player>> teamPlayers;

    private Set<String> teams;

    public static Logging log = new Logging();
    public IPLTournament(Path filePath){
        initialisedAllPlayers(parseCSV(filePath));
    }

    public List<Player> getAllPlayers() {
        return allPlayers;
    }
    public void setAllPlayers(List<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }
    public Map<String, List<Player>> getTeamPlayers() {
        return teamPlayers;
    }
    public void setTeamPlayers(Map<String, List<Player>> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }

    public List<String[]> parseCSV(Path filePath){
        List<String[]> csvData = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            boolean isFirstRecord = true;
            for (CSVRecord csvRecord : csvParser) {
                if(isFirstRecord) {
                    isFirstRecord = false;
                    continue;
                }
                String[] row = new String[csvRecord.size()];
                for (int i = 0; i < csvRecord.size(); i++) {
                    row[i] = csvRecord.get(i);
                }
                csvData.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvData;
    }

    public void initialisedAllPlayers(List<String[]> playersList){
        allPlayers = new ArrayList<>();
        teamPlayers = new HashMap<>();
        teams = new HashSet<>();
        for(String[] playerRecord:playersList){
            String name = playerRecord[0];
            String team = playerRecord[1];
            String role = playerRecord[2];
            int matches = Integer.parseInt(playerRecord[3]);
            int runs = Integer.parseInt(playerRecord[4]);
            double average = Double.parseDouble(playerRecord[5]);
            double strikeRate = Double.parseDouble(playerRecord[6]);
            int wickets = Integer.parseInt(playerRecord[7]);

            Player player = new Player(name,team,role,matches,runs,average,strikeRate);
            player.setWickets(wickets);

            allPlayers.add(player);
            List<Player> teamPlayersList = teamPlayers.getOrDefault(team,new ArrayList<>());
            teamPlayersList.add(player);
            teamPlayers.put(team,teamPlayersList);

            teams.add(team);
        }
    }

    public List<Player> bowlersWithAtLeastNWickets(String teamName,int N){
        List<Player> teamPlayersList = teamPlayers.get(teamName);
        return teamPlayersList.stream().filter(player->player.getWickets() >= N).collect(Collectors.toList());
    }

    public Player highestRunScorer(String teamName){
        List<Player> teamPlayersList = teamPlayers.get(teamName);
        return teamPlayersList.stream().max(Comparator.comparing(Player::getRuns)).orElseThrow(NoSuchElementException::new);
    }

    public Player highestWicketTaker(String teamName){
        List<Player> teamPlayersList = teamPlayers.get(teamName);
        return teamPlayersList.stream().max(Comparator.comparing(Player::getWickets)).orElseThrow(NoSuchElementException::new);
    }

    public List<Player> topNRunScorer(int n){
        return allPlayers.stream().sorted((p1,p2)->p2.getRuns()-p1.getRuns()).limit(n).collect(Collectors.toList());
    }

    public List<Player> topNWicketTaker(int n){
        return allPlayers.stream().sorted((p1,p2)->p2.getWickets()-p1.getWickets()).limit(n).collect(Collectors.toList());
    }

    public void displayTeamPlayers(String teamName){
        for(Player player:teamPlayers.getOrDefault(teamName,new ArrayList<>())){
            log.logInfo(player.toString());
        }
    }
}
