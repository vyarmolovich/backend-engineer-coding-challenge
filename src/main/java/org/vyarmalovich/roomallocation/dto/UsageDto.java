package org.vyarmalovich.roomallocation.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.vyarmalovich.roomallocation.util.RevenueSerializer;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsageDto {

    private Integer usagePremium;

    @JsonSerialize(using = RevenueSerializer.class)
    private Double revenuePremium;

    private Integer usageEconomy;

    @JsonSerialize(using = RevenueSerializer.class)
    private Double revenueEconomy;
}
