package com.iluvpwny.graderbot.utils;

public class Score {

    private final int score;
    private final int fullscore;
    private final double percent;
    private final int full;
    private final int questions;

    public Score(int score, int fullscore, int full,int questions) {
        this.score = score;
        this.fullscore = fullscore;
        this.full = full;
        this.percent = Math.round(score*1.0/fullscore*10000.0)/100.0;
        this.questions = questions;
    }

    public int getScore() {
        return score;
    }

    public int getFullscore() {
        return fullscore;
    }

    public double getPercent() {
        return percent;
    }

    public int getFull() {
        return full;
    }

    public int getQuestions() {
        return questions;
    }
}
