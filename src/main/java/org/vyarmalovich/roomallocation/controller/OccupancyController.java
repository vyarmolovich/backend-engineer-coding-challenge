package org.vyarmalovich.roomallocation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.vyarmalovich.roomallocation.dto.OccupancyDto;
import org.vyarmalovich.roomallocation.service.OccupancyService;

import java.util.stream.Collectors;


@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/occupancy")
public class OccupancyController {

    private final OccupancyService occupancyService;

    @PostMapping
    public ResponseEntity<?> calculateOccupancy(@Valid @RequestBody OccupancyDto payload, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(bindingResult.getFieldErrors().stream()
                            .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
        }

        return ResponseEntity.ok(occupancyService.allocateRooms(payload));
    }
}
