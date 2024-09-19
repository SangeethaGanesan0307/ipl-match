//package com.indium.spring_boot_assessment.service;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.indium.spring_boot_assessment.entites.*;
//import com.indium.spring_boot_assessment.repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.format.DateTimeParseException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//@Service
//public class MatchService {
//
//    @Autowired
//    private MatchRepository matchRepository;
//
//    @Autowired
//    private EventRepository eventRepository;
//
//    @Autowired
//    private TeamRepository teamRepository;
//
//    @Autowired
//    private TeamPlayerRepository teamPlayerRepository;
//
//    @Autowired
//    private PlayerRepository playerRepository;
//
//    @Autowired
//    private OfficialRepository officialRepository;
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Transactional
//    public void insertMatchData(String jsonData) throws IOException {
//        JsonNode root = objectMapper.readTree(jsonData);
//
//        // Check if root is null
//        if (root == null) {
//            System.out.println("Root node is null.");
//            return;
//        }
//
//        // Retrieve 'info' node and check for null
//        JsonNode infoNode = root.get("info");
//        if (infoNode == null) {
//            System.out.println("Info node is missing in the JSON.");
//            return;
//        }
//
//        // Insert Match
//        Match match = new Match();
//
//        // Set match name
//        if (infoNode.has("name")) {
//            match.setName(infoNode.get("name").asText());
//        } else {
//            match.setName("Unknown Match");
//        }
//
//        // Set match date
//        if (infoNode.has("date")) {
//            try {
//                match.setDate(LocalDate.parse(infoNode.get("date").asText()));
//            } catch (DateTimeParseException e) {
//                System.out.println("Date parsing failed. Setting default date.");
//                match.setDate(LocalDate.now());
//            }
//        } else {
//            match.setDate(LocalDate.now());
//        }
//
//        // Set match venue
//        match.setVenue(infoNode.has("venue") ? infoNode.get("venue").asText() : "Unknown Venue");
//
//        // Set team IDs
//        //match.setTeam1Id(getTeamIdOrDefault(infoNode, "team1"));
//        //match.setTeam2Id(getTeamIdOrDefault(infoNode, "team2"));
//
//        // Save match
//        matchRepository.save(match);
//
//        // Insert Event (if exists)
//        JsonNode eventNode = infoNode.get("event");
//        if (eventNode != null) {
//            Event event = new Event();
//            event.setMatch(match);
//            event.setName(eventNode.has("name") ? eventNode.get("name").asText() : "Unknown Event");
//            event.setMatchNumber(eventNode.has("match_number") ? eventNode.get("match_number").asInt() : 0);
//            eventRepository.save(event);
//        }
//
//        // Insert Players
//        JsonNode playersNode = infoNode.get("players");
//        Map<String, Player> playerMap = new HashMap<>();
//        if (playersNode != null) {
//            playersNode.fieldNames().forEachRemaining(teamName -> {
//                JsonNode playersList = playersNode.get(teamName);
//                if (playersList != null && playersList.isArray()) {
//                    for (JsonNode playerNode : playersList) {
//                        Player player = getOrCreatePlayer(playerNode.asText());
//                        playerMap.put(playerNode.asText(), player);
//                    }
//                }
//            });
//        }
//
//        // Insert TeamPlayer
//        JsonNode teamsNode = infoNode.get("teams");
//        if (teamsNode != null && teamsNode.isArray()) {
//            for (JsonNode teamNode : teamsNode) {
//                String teamName = teamNode.asText();
//                Team team = findOrCreateTeam(teamName);
//                JsonNode playersList = playersNode != null ? playersNode.get(teamName) : null;
//                if (playersList != null && playersList.isArray()) {
//                    for (JsonNode playerNode : playersList) {
//                        TeamPlayer teamPlayer = new TeamPlayer();
//                        teamPlayer.setTeam(team);
//                        teamPlayer.setPlayer(playerMap.get(playerNode.asText()));
//                        teamPlayer.setMatch(match);
//                        teamPlayerRepository.save(teamPlayer);
//                    }
//                }
//            }
//        }
//
//        // Insert Officials
//        JsonNode officialsNode = infoNode.get("officials");
//        if (officialsNode != null) {
//            insertOfficials(officialsNode.get("match_referees"), match, "Referee");
//            insertOfficials(officialsNode.get("reserve_umpires"), match, "Reserve Umpire");
//            insertOfficials(officialsNode.get("tv_umpires"), match, "TV Umpire");
//            insertOfficials(officialsNode.get("umpires"), match, "Umpire");
//        }
//    }
//
//    private Long getTeamIdOrDefault(JsonNode infoNode, String teamField) {
//        JsonNode teamNode = infoNode.get(teamField);
//        if (teamNode != null && teamNode.asText() != null) {
//            Team team = findOrCreateTeam(teamNode.asText());
//            return team.getId();
//        }
//        return null; // or a default team ID if needed
//    }
//
//    private Team findOrCreateTeam(String teamName) {
//        return teamRepository.findByName(teamName)
//                .orElseGet(() -> {
//                    Team newTeam = new Team();
//                    newTeam.setName(teamName);
//                    return teamRepository.save(newTeam);
//                });
//    }
//
//    private Player getOrCreatePlayer(String playerName) {
//        return playerRepository.findByName(playerName)
//                .orElseGet(() -> {
//                    Player newPlayer = new Player();
//                    newPlayer.setName(playerName);
//                    return playerRepository.save(newPlayer);
//                });
//    }
//
//    private void insertOfficials(JsonNode officialsNode, Match match, String officialType) {
//        if (officialsNode != null && officialsNode.isArray()) {
//            for (JsonNode officialNode : officialsNode) {
//                Official official = new Official();
//                //official.setMatch(match);
//                official.setOfficialType(officialType);
//                official.setName(officialNode.asText());
//                officialRepository.save(official);
//            }
//        }
//    }
//
//    public void saveAllMatches(List<Match> matches) {
//        matchRepository.saveAll(matches);
//    }
//
//
//
//    public void saveMatch(String match) {
//     }
////
////    public List<Match> getMatchesByPlayerName(String playerName) {
////        return matchRepository.findMatchesByPlayerName(playerName);
////    }
//}
//
//
