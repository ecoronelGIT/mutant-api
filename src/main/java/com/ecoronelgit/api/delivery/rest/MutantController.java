package com.ecoronelgit.api.delivery.rest;

import com.ecoronelgit.api.core.action.IsMutant;
import com.ecoronelgit.api.delivery.dto.MutantDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Mutant API")
public class MutantController {

    private final IsMutant isMutant;

    public MutantController(IsMutant isMutant) {
        this.isMutant = isMutant;
    }

    @ApiOperation(value = "Is Mutant")
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
