package com.ecoronelgit.api.delivery.rest;

import com.ecoronelgit.api.core.action.GetStats;
import com.ecoronelgit.api.core.action.IsMutant;
import com.ecoronelgit.api.core.action.SaveMutantDNA;
import com.ecoronelgit.api.core.domain.exception.DNASequenceExistException;
import com.ecoronelgit.api.delivery.dto.MutantDTO;
import com.ecoronelgit.api.delivery.dto.MutantStatsDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Mutant API")
public class MutantController {

    public static final boolean IS_HUMAN = false;
    public static final boolean IS_MUTANT = true;
    private final IsMutant isMutant;
    private final SaveMutantDNA saveMutantDNA;
    private final GetStats getStats;

    public MutantController(IsMutant isMutant, SaveMutantDNA saveMutantDNA, GetStats getStats) {
        this.isMutant = isMutant;
        this.saveMutantDNA = saveMutantDNA;
        this.getStats = getStats;
    }

    @ApiOperation(value = "Return 200 for mutant and 403 for human, but save only correct data.")
    @PostMapping("/mutant")
    public ResponseEntity mutant(@RequestBody MutantDTO mutantDTO) {
        try {
            if (!isMutant.execute(mutantDTO.getDna())) {
                saveMutantDNA.execute(mutantDTO.getDna(), IS_HUMAN);
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
            saveMutantDNA.execute(mutantDTO.getDna(), IS_MUTANT);
            return new ResponseEntity(HttpStatus.OK);
        } catch (DNASequenceExistException existException) {
            return ResponseEntity.status(HttpStatus.OK).body(existException.getMessage());
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

    @GetMapping("/stats")
    public MutantStatsDTO stats() {
        return new MutantStatsDTO(getStats.execute());
    }
}
