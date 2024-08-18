package org.vyarmalovich.roomallocation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OccupancyDto {

    @NotNull
    @Min(0)
    private Integer premiumRooms;

    @NotNull
    @Min(0)
    private Integer economyRooms;

    @NotEmpty
    private List<@Min(0) Double> potentialGuests;
}
