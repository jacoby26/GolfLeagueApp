package com.techelevator.model;

import java.math.BigDecimal;

public class LeaderboardRow {

    private String name;
    private Double score;
    private Long rank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(String score) {
        BigDecimal truncator;
        if (score.contains(".")){
            while (score.substring(score.indexOf(".")).length()<4){
                score += "0";
            }
        } else {
            score += ".000";
        }
        truncator = new BigDecimal(score.substring(0,score.indexOf(".")+4));
        this.score = (truncator.doubleValue());
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }
}
