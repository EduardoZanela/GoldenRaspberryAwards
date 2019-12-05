package com.eduardozanela.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CSVAwardsDTO {

    private Integer year;
    private String title;
    private List<String> studios;
    private List<String> producers;
    private String winner;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getStudios() {
        return studios;
    }

    public void setStudios(String studios) {
    	List<String> asList = Arrays.asList(studios.replaceAll("and", ",").split(","));
    	List<String> trimmedStrings = 
    			asList.stream().map(String::trim).collect(Collectors.toList());
        this.studios = trimmedStrings;
    }

    public List<String> getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
    	List<String> asList = Arrays.asList(producers.replaceAll("and", ",").split(","));
    	List<String> trimmedStrings = 
    			asList.stream().map(String::trim).collect(Collectors.toList());
        this.producers = trimmedStrings;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
