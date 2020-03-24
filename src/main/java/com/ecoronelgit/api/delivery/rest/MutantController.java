package com.ecoronelgit.api.delivery.rest;

import com.ecoronelgit.api.core.action.IsMutant;
import com.ecoronelgit.api.delivery.dto.MutantDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantController {

    private final IsMutant isMutant;

    public MutantController(IsMutant isMutant) {
        this.isMutant = isMutant;
    }

    @PostMapping("/mutant")
    public ResponseEntity mutant(@RequestBody MutantDTO mutantDTO) {
        try {
            if (!isMutant.execute(mutantDTO.getDna())) {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
