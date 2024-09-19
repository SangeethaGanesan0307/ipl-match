package com.indium.spring_boot_assessment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indium.spring_boot_assessment.entites.*;
import com.indium.spring_boot_assessment.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IplService {

    @Autowired
    private Match_metadataRepository matchMetadataRepository;

    @Autowired
    private MatchDetailsRepository matchDetailsRepository;

    @Autowired
    private MatchOfficialsRepository matchOfficialsRepository;

    @Autowired
    private MatchTeamsRepository matchTeamsRepository;

    @Autowired
    private TeamPlayerRepository teamPlayersRepository;

    @Autowired
    private InningsInfoRepository inningsInfoRepository;

    @Autowired
    private OverDataRepository overDataRepository;

    @Autowired
    private DeliveryDataRepository deliveryDataRepository;

    @Autowired
    private PowerPlayInfoRepository powerplayInfoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void insertMatchData(String jsonData) throws IOException {
        JsonNode rootNode = objectMapper.readTree(jsonData);
        JsonNode infoNode = rootNode.get("info");
        JsonNode metaNode = rootNode.get("meta");

        if (infoNode == null || metaNode == null) {
            throw new IllegalArgumentException("Invalid JSON structure: 'info' or 'meta' node is missing.");
        }

        // Insert Match Metadata
        Match_metadata matchmetadata = new Match_metadata();
        matchmetadata.setVersion(metaNode.path("data_version").path(0).asText("Unknown")); // Default to "Unknown" if not found
        matchmetadata.setCreationDate(LocalDate.parse(metaNode.path("created").path(0).asText("1970-01-01")));
        matchmetadata.setRevisionNumber(metaNode.path("revision").path(0).asInt(0)); // Default to 0 if not found
        matchmetadata = matchMetadataRepository.save(matchmetadata);

        // Insert Match Details
        MatchDetails matchDetails = new MatchDetails();
        matchDetails.setMatchMetadata(matchmetadata);
        matchDetails.setBallsPerOver(infoNode.path("balls_per_over").asInt(6)); // Default to 6 if not found
        matchDetails.setCityName(infoNode.path("city").asText("Unknown"));
        matchDetails.setMatchDate(LocalDate.parse(infoNode.path("dates").path(0).asText("1970-01-01")));
        matchDetails.setMatchNum(infoNode.path("event").path("match_number").asInt(0));
        matchDetails.setEventTitle(infoNode.path("event").path("name").asText("Unknown"));
        matchDetails.setGenderType(infoNode.path("gender").asText("Unknown"));
        matchDetails.setFormatType(infoNode.path("match_type").asText("Unknown"));
        matchDetails.setWinningTeam(infoNode.path("outcome").path("winner").asText("Unknown"));
        matchDetails.setMarginOfVictoryRuns(infoNode.path("outcome").path("by").path("runs").asInt(0));
        matchDetails.setTotalOvers(infoNode.path("overs").asInt(0));
        matchDetails.setBestPlayer(infoNode.path("player_of_match").path(0).asText("Unknown"));
        matchDetails.setTeamCategory("T20"); // Set team category as needed
        matchDetails.setTossWinningTeam(""); // Set toss winning team if available
        matchDetails.setTossDecision(""); // Set toss decision if available
        matchDetails.setStadiumName(""); // Set stadium name if available
        matchDetails = matchDetailsRepository.save(matchDetails);

        // Insert Match Officials
        MatchOfficials matchOfficials = new MatchOfficials();
        matchOfficials.setMatchDetails(matchDetails);
        matchOfficials.setHeadReferee(infoNode.path("officials").path("match_referees").path(0).asText("Unknown"));
        matchOfficials.setBackupUmpire(infoNode.path("officials").path("reserve_umpires").path(0).asText("Unknown"));
        matchOfficials.setTelevisionUmpire(infoNode.path("officials").path("tv_umpires").path(0).asText("Unknown"));
        matchOfficials.setFieldUmpireOne(infoNode.path("officials").path("umpires").path(0).asText("Unknown"));
        matchOfficials.setFieldUmpireTwo(infoNode.path("officials").path("umpires").path(1).asText("Unknown"));
        matchOfficials = matchOfficialsRepository.save(matchOfficials);

        // Insert Match Teams and related data
        JsonNode playersNode = infoNode.path("players");
        for (String teamName : List.of("Kolkata Knight Riders", "Royal Challengers Bangalore")) {
            JsonNode teamPlayersNode = playersNode.path(teamName);
            if (!teamPlayersNode.isMissingNode()) {
                MatchTeams matchTeams = new MatchTeams();
                matchTeams.setMatchId(matchDetails.getMatchId());
                matchTeams.setTeamTitle(teamName);
                matchTeams = matchTeamsRepository.save(matchTeams);

                // Insert Team Players
                for (JsonNode playerNode : teamPlayersNode) {
                    TeamPlayer teamPlayer = new TeamPlayer();
                    teamPlayer.setMatchTeams(matchTeams);
                    teamPlayer.setFullName(playerNode.asText("Unknown"));
                    teamPlayer.setUniqueCode(""); // Set unique code if available
                    teamPlayersRepository.save(teamPlayer);

                    // Insert Innings Info
                    InningsInfo inningsInfo = new InningsInfo();
                    inningsInfo.setMatchDetails(matchDetails);
                    inningsInfo.setMatchTeams(matchTeams); // Example
                    inningsInfo = inningsInfoRepository.save(inningsInfo);

                    // Insert Over Data
                    OverData overData = new OverData();
                    overData.setInningsInfo(inningsInfo);
                    overData.setOverSequence(1); // Set as needed
                    overData = overDataRepository.save(overData);


                    // Insert Delivery Data
                    JsonNode deliveriesNode = infoNode.path("deliveries");
                    if (!deliveriesNode.isMissingNode() && deliveriesNode.isArray()) {
                        for (JsonNode deliveryNode : deliveriesNode) {
                            DeliveryData deliveryData = new DeliveryData();
                            deliveryData.setOverData(overData);

                            // Set default values if fields are missing
                            deliveryData.setBatterName(deliveryNode.path("batter").asText("Unknown"));
                            deliveryData.setBowlerName(deliveryNode.path("bowler").asText("Unknown"));
                            deliveryData.setNonStrikerName(deliveryNode.path("non_striker").asText("Unknown"));

                            JsonNode runsNode = deliveryNode.path("runs");
                            deliveryData.setRunsScoredByBatter(runsNode.path("batter").asInt(0));
                            deliveryData.setTypeOfExtra(runsNode.path("extras").asInt(0)); // Added setting extras
                            deliveryData.setRunsScoredByBatter(runsNode.path("total").asInt(0));

                            // Insert Wickets Data
                            JsonNode wicketsNode = deliveryNode.path("wickets");
                            if (!wicketsNode.isMissingNode() && wicketsNode.isArray()) {
                                for (JsonNode wicketNode : wicketsNode) {
                                    deliveryData.setWicketMode(wicketNode.path("kind").asText("Unknown"));
                                    deliveryData.setDismissedPlayer(wicketNode.path("player_out").asText("Unknown"));

                                    // Handle fielders if needed
                                    JsonNode fieldersNode = wicketNode.path("fielders");
                                    if (!fieldersNode.isMissingNode() && fieldersNode.isArray()) {
                                        for (JsonNode fielderNode : fieldersNode) {
                                            String fielderName = fielderNode.path("name").asText("Unknown");
                                            // You can log or handle fielders as needed
                                            System.out.println("Fielder: " + fielderName);
                                        }
                                    } else {
                                        System.out.println("Fielders node is missing or empty.");
                                    }
                                }
                            } else {
                                System.out.println("Wickets node is missing or empty.");
                            }

                            deliveryDataRepository.save(deliveryData);
                        }
                    } else {
                        System.out.println("Deliveries node is missing or empty.");
                    }
                }
            }
        }
    }
    }