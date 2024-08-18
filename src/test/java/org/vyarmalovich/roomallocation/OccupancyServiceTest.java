package org.vyarmalovich.roomallocation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vyarmalovich.roomallocation.dto.OccupancyDto;
import org.vyarmalovich.roomallocation.dto.UsageDto;
import org.vyarmalovich.roomallocation.service.OccupancyService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OccupancyServiceTest {

    @Autowired
    private OccupancyService occupancyService;


    @Test
    public void testRoomAllocationCase1() {

        UsageDto result = occupancyService.allocateRooms(OccupancyDto.builder()
                .premiumRooms(3)
                .economyRooms(3)
                .potentialGuests(Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0))
                .build());

        assertEquals(3, result.getUsagePremium());
        assertEquals(738.0, result.getRevenuePremium());
        assertEquals(3, result.getUsageEconomy());
        assertEquals(167.99, result.getRevenueEconomy());
    }

    @Test
    public void testRoomAllocationCase2() {

        UsageDto result = occupancyService.allocateRooms(OccupancyDto.builder()
                .premiumRooms(7)
                .economyRooms(5)
                .potentialGuests(Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0))
                .build());

        assertEquals(6, result.getUsagePremium());
        assertEquals(1054.0, result.getRevenuePremium());
        assertEquals(4, result.getUsageEconomy());
        assertEquals(189.99, result.getRevenueEconomy());
    }

    @Test
    public void testRoomAllocationCase3() {

        UsageDto result = occupancyService.allocateRooms(OccupancyDto.builder()
                .premiumRooms(2)
                .economyRooms(7)
                .potentialGuests(Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0))
                .build());

        assertEquals(2, result.getUsagePremium());
        assertEquals(583.0, result.getRevenuePremium());
        assertEquals(4, result.getUsageEconomy());
        assertEquals(189.99, result.getRevenueEconomy());
    }
}
