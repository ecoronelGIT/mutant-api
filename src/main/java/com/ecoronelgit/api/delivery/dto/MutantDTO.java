package com.ecoronelgit.api.delivery.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Mutant Model")
public class MutantDTO {

    @ApiModelProperty(notes = "DNA Sequence")
    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
