//package com.indium.spring_boot_assessment.service;
//
//
//import com.indium.spring_boot_assessment.entites.*;
//import com.indium.spring_boot_assessment.repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class MatchService {
//
//    @Autowired
//    private MatchRepository matchRepository;
//    @Autowired
//    private TeamRepository teamRepository;
//    @Autowired
//    private PlayerRepository playerRepository;
//    @Autowired
//    private TeamPlayerRepository teamPlayerRepository;
//    @Autowired
//    private InningsRepository inningsRepository;
//    @Autowired
//    private OverRepository oversRepository;
//    @Autowired
//    private DeliveryRepository deliveriesRepository;
//    @Autowired
//    private MatchTeamRepository matchTeamRepository;
//    @Autowired
//    private DeliveryFielderRepository deliveryFieldersRepository;
//    @Autowired
//    private PowerplayRepository powerplaysRepository;
//    @Autowired
//    private OfficialRepository officialsRepository;
//    @Autowired
//    private EventRepository eventRepository;
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Transactional
//    public void insertDataFromJson(String jsonData) throws IOException {
//        JsonNode root = objectMapper.readTree(jsonData);
//
//        // Extracting teams
//        JsonNode teamsNode = root.path("info").path("teams");
//        for (JsonNode teamNode : teamsNode) {
//            String teamName = teamNode.asText();
//            Team team = new Team();
//            team.setName(teamName);
//            teamRepository.save(team);
//        }
//
//        // Extracting match
//        JsonNode matchNode = root.path("info").path("event");
//        Match match = new Match();
//        match.setName(matchNode.path("name").asText());
//        match.setDate(root.path("info").path("dates").get(0).asText());
//        match.setVenue(root.path("info").path("venue").asText());
//
//        JsonNode teamNode1 = root.path("info").path("teams").get(0).asText();
//        JsonNode teamNode2 = root.path("info").path("teams").get(1).asText();
//        match.setTeam1Id(teamRepository.findByName(teamNode1).getId());
//        match.setTeam2Id(teamRepository.findByName(teamNode2).getId());
//        matchRepository.save(match);
//
//        // Extracting players
//        JsonNode playersNode = root.path("info").path("players");
//        for (Iterator<Map.Entry<String, JsonNode>> teams = playersNode.fields(); teams.hasNext();) {
//            Map.Entry<String, JsonNode> entry = teams.next();
//            String teamName = entry.getKey();
//            JsonNode playerNames = entry.getValue();
//
//            Team team = teamRepository.findByName(teamName);
//            if (team != null) {
//                for (JsonNode playerNameNode : playerNames) {
//                    String playerName = playerNameNode.asText();
//                    Player player = new Player();
//                    player.setName(playerName);
//                    playerRepository.save(player);
//
//                    // Assign player to team
//                    TeamPlayer teamPlayer = new TeamPlayer();
//                    teamPlayer.setTeamId(team.getId());
//                    teamPlayer.setPlayerId(player.getId());
//                    teamPlayer.setMatchId(match.getId());
//                    teamPlayerRepository.save(teamPlayer);
//                }
//            }
//        }
//
//        // Extracting officials
//        JsonNode officialsNode = root.path("info").path("officials");
//        JsonNode referees = officialsNode.path("match_referees");
//        JsonNode tvUmpires = officialsNode.path("tv_umpires");
//        JsonNode umpires = officialsNode.path("umpires");
//
//        for (JsonNode referee : referees) {
//            Official official = new Official();
//            official.setName(referee.asText());
//            official.setOfficialType("Match Referee");
//            officialsRepository.save(official);
//        }
//        for (JsonNode tvUmpire : tvUmpires) {
//            Official official = new Official();
//            official.setName(tvUmpire.asText());
//            official.setOfficialType("TV Umpire");
//            officialsRepository.save(official);
//        }
//        for (JsonNode umpire : umpires) {
//            Official official = new Official();
//            official.setName(umpire.asText());
//            official.setOfficialType("Umpire");
//            officialsRepository.save(official);
//        }
//
//        // Extracting event
//        JsonNode eventNode = root.path("info").path("event");
//        Event event = new Event();
//        event.setMatchId(match.getId());
//        event.setName(eventNode.path("name").asText());
//        event.setMatchNumber(eventNode.path("match_number").asInt());
//        eventRepository.save(event);
//    }
//}
//

//my code

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
//import java.util.HashMap;
//import java.util.Map;
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
//        // Insert Match
//        JsonNode infoNode = root.get("info");
//        JsonNode metaNode = root.get("meta");
//        Match match = new Match();
//        match.setName(infoNode.get("name").asText());
//        match.setDate(LocalDate.parse(infoNode.get("date").asText()));
//        match.setVenue(infoNode.get("venue").asText());
//        match.setTeam1Id(findOrCreateTeam(infoNode.get("team1").asText()).getId());
//        match.setTeam2Id(findOrCreateTeam(infoNode.get("team2").asText()).getId());
//        matchRepository.save(match);
//
//        // Insert Event
////        JsonNode eventNode = infoNode.get("event");
////        Event event = new Event();
////        event.setMatch(match);
////        event.setName(eventNode.get("name").asText());
////        event.setMatchNumber(eventNode.get("match_number").asInt());
////        eventRepository.save(event);
//
//
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
//        for (JsonNode teamNode : infoNode.get("teams")) {
//            String teamName = teamNode.asText();
//            Team team = findOrCreateTeam(teamName);
//            JsonNode playersList = playersNode.get(teamName);
//            if (playersList != null && playersList.isArray()) {
//                for (JsonNode playerNode : playersList) {
//                    TeamPlayer teamPlayer = new TeamPlayer();
//                    teamPlayer.setTeam(team);
//                    teamPlayer.setPlayer(playerMap.get(playerNode.asText()));
//                    teamPlayer.setMatch(match);
//                    teamPlayerRepository.save(teamPlayer);
//                }
//            }
//        }
//
//        // Insert Officials
//        JsonNode officialsNode = infoNode.get("officials");
//        insertOfficials(officialsNode.get("match_referees"), match, "Referee");
//        insertOfficials(officialsNode.get("reserve_umpires"), match, "Reserve Umpire");
//        insertOfficials(officialsNode.get("tv_umpires"), match, "TV Umpire");
//        insertOfficials(officialsNode.get("umpires"), match, "Umpire");
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
//    private void insertOfficials(JsonNode officialsNode, Match match, String type) {
//        if (officialsNode != null) {
//            for (JsonNode officialNode : officialsNode) {
//                Official official = new Official();
//                official.setMatch(match);
//                official.setName(officialNode.asText());
//                official.setOfficialType(type);
//                officialRepository.save(official);
//            }
//        }
//    }
//}
