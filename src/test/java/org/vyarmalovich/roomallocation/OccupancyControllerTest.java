package org.vyarmalovich.roomallocation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.vyarmalovich.roomallocation.controller.OccupancyController;
import org.vyarmalovich.roomallocation.dto.OccupancyDto;
import org.vyarmalovich.roomallocation.dto.UsageDto;
import org.vyarmalovich.roomallocation.service.OccupancyService;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OccupancyController.class)
public class OccupancyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OccupancyService occupancyService;

    @Test
    void testSuccessfulAllocation() throws Exception {

        UsageDto response = UsageDto.builder()
                .usagePremium(6)
                .revenuePremium(1054.0)
                .usageEconomy(4)
                .revenueEconomy(189.99)
                .build();

        when(occupancyService.allocateRooms(any(OccupancyDto.class)))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/occupancy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"premiumRooms\": 7, \"economyRooms\": 5, \"potentialGuests\": [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.revenueEconomy").value("189.99"))
                .andExpect(jsonPath("$.revenuePremium").value("1054"));
    }

    @Test
    void testAllocationWithNoRooms() throws Exception {

        UsageDto response = UsageDto.builder()
                .usagePremium(0)
                .revenuePremium(0.0)
                .usageEconomy(0)
                .revenueEconomy(0.0)
                .build();

        when(occupancyService.allocateRooms(any(OccupancyDto.class)))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/occupancy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"premiumRooms\": 0, \"economyRooms\": 0, \"potentialGuests\": [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.revenueEconomy").value("0"))
                .andExpect(jsonPath("$.revenuePremium").value("0"));
    }

    @Test
    void testAllocationWithNoGuests() throws Exception {

        UsageDto response = UsageDto.builder()
                .usagePremium(0)
                .revenuePremium(0.0)
                .usageEconomy(0)
                .revenueEconomy(0.0)
                .build();

        when(occupancyService.allocateRooms(any(OccupancyDto.class)))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/occupancy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"premiumRooms\": 0, \"economyRooms\": 0, \"potentialGuests\": []}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.potentialGuests").value("must not be empty"));
    }
}