package com.indium.spring_boot_assessment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indium.spring_boot_assessment.entites.*;
import com.indium.spring_boot_assessment.repository.*;
import com.indium.spring_boot_assessment.service.IplService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class IplServiceTest {

    @Mock
    private Match_metadataRepository matchMetadataRepository;

    @Mock
    private MatchDetailsRepository matchDetailsRepository;

    @Mock
    private MatchOfficialsRepository matchOfficialsRepository;

    @Mock
    private MatchTeamsRepository matchTeamsRepository;

    @Mock
    private TeamPlayerRepository teamPlayersRepository;

    @Mock
    private InningsInfoRepository inningsInfoRepository;

    @Mock
    private OverDataRepository overDataRepository;

    @Mock
    private DeliveryDataRepository deliveryDataRepository;

    @Mock
    private PowerPlayInfoRepository powerplayInfoRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private IplService iplService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testInsertMatchData_InvalidJsonStructure() throws IOException {
        String invalidJsonData = "{ \"invalid\": \"data\" }";

        // Mock objectMapper behavior
        JsonNode rootNode = mock(JsonNode.class);
        when(objectMapper.readTree(invalidJsonData)).thenReturn(rootNode);
        when(rootNode.get("info")).thenReturn(null);
        when(rootNode.get("meta")).thenReturn(null);

        // Test invalid JSON structure
        assertThrows(IllegalArgumentException.class, () -> {
            iplService.insertMatchData(invalidJsonData);
        });
    }
}
