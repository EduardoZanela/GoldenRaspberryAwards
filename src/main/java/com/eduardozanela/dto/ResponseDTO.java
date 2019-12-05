package com.eduardozanela.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseDTO {

    List<AwardsWinnersDTO> min = new ArrayList<>();
    List<AwardsWinnersDTO> max = new ArrayList<>();

    public List<AwardsWinnersDTO> getMin() {
        return min;
    }

    public List<AwardsWinnersDTO> getMax() {
        return max;
    }
}
