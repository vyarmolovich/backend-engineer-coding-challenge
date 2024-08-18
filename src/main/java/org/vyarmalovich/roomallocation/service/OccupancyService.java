package org.vyarmalovich.roomallocation.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vyarmalovich.roomallocation.dto.OccupancyDto;
import org.vyarmalovich.roomallocation.dto.UsageDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OccupancyService {

    @Value("${luxury.threshold.eur}")
    private Integer luxuryThreshold;

    public UsageDto allocateRooms(OccupancyDto occupancy) {

        int usagePremium = 0;
        double revenuePremium = 0;
        int usageEconomy = 0;
        double revenueEconomy = 0;
        List<Double> economyGuests = new ArrayList<>();

        List<Double> sortedGuests = occupancy.getPotentialGuests()
                .stream()
                .sorted(Collections.reverseOrder())
                .toList();

        for (double price : sortedGuests) {
            if (price >= luxuryThreshold && usagePremium < occupancy.getPremiumRooms()) {
                usagePremium++;
                revenuePremium += price;
            } else if (price < luxuryThreshold) {
                economyGuests.add(price);
            }
        }

        for (double price : economyGuests) {
            if (usageEconomy < occupancy.getEconomyRooms()) {
                usageEconomy++;
                revenueEconomy += price;
            } else if (usagePremium < occupancy.getPremiumRooms()) {
                usagePremium++;
                revenuePremium += price;
            }
        }

        return UsageDto.builder()
                .usagePremium(usagePremium)
                .revenuePremium(revenuePremium)
                .usageEconomy(usageEconomy)
                .revenueEconomy(revenueEconomy)
                .build();
    }
}
