import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.*;

public class IPLCensorAnalyzer {

    // Define structure for IPL match data
    static class IPLMatch {
        public String matchId;
        public String team1;
        public String team2;
        public String playerOfTheMatch;

        public IPLMatch(String matchId, String team1, String team2, String playerOfTheMatch) {
            this.matchId = matchId;
            this.team1 = team1;
            this.team2 = team2;
            this.playerOfTheMatch = playerOfTheMatch;
        }
    }

    // Apply censorship to team names and player of the match
    private static IPLMatch applyCensorship(IPLMatch match) {
        // Mask team names
        match.team1 = maskTeamName(match.team1);
        match.team2 = maskTeamName(match.team2);
        // Redact Player of the Match
        match.playerOfTheMatch = "REDACTED";
        return match;
    }

    // Mask part of the team name
    private static String maskTeamName(String teamName) {
        if (teamName.contains("Mumbai")) {
            return "Mumbai ***";
        }
        return teamName;
    }

    // Read JSON data
    private static List<IPLMatch> readJsonData(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(new File(filePath), IPLMatch[].class));
    }

    // Read CSV data
    private static List<IPLMatch> readCsvData(String filePath) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper.schemaFor(IPLMatch.class).withHeader().withColumnReordering(true);
        MappingIterator<IPLMatch> it = csvMapper.readerFor(IPLMatch.class).with(schema).readValues(String.valueOf(new File(filePath)));
        return it.readAll();
    }

    // Write JSON data
    private static void writeJsonData(List<IPLMatch> matches, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), matches);
    }

    // Write CSV data
    private static void writeCsvData(List<IPLMatch> matches, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("matchId", "team1", "team2", "playerOfTheMatch"));
        for (IPLMatch match : matches) {
            csvPrinter.printRecord(match.matchId, match.team1, match.team2, match.playerOfTheMatch);
        }
        csvPrinter.flush();
        writer.close();
    }

    public static void main(String[] args) {
        try {
            // Read input files (JSON and CSV)
            List<IPLMatch> jsonMatches = readJsonData("ipl_matches.json");
            List<IPLMatch> csvMatches = readCsvData("ipl_matches.csv");

            // Apply censorship to the data
            List<IPLMatch> censoredJsonMatches = new ArrayList<>();
            for (IPLMatch match : jsonMatches) {
                censoredJsonMatches.add(applyCensorship(match));
            }

            List<IPLMatch> censoredCsvMatches = new ArrayList<>();
            for (IPLMatch match : csvMatches) {
                censoredCsvMatches.add(applyCensorship(match));
            }

            // Write censored data back to new files
            writeJsonData(censoredJsonMatches, "censored_ipl_matches.json");
            writeCsvData(censoredCsvMatches, "censored_ipl_matches.csv");

            System.out.println("Censorship applied successfully and new files generated!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
