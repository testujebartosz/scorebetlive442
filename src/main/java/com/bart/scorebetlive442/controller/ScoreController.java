package com.bart.scorebetlive442.controller;

import com.bart.scorebetlive442.model.Score;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    @GetMapping(value = "/italy/seriea")
    public List<String> getScores() {
        List<Score> scores = Arrays.asList(
                new Score("Juventus FC", 2, 0, "AC Milan"),
                new Score("AS Roma", 3, 3, "SSC Napoli"),
                new Score("Atalanta BC", 4, 2, "Como 1907")
        );

        LocalDate today = LocalDate.now();

        return scores.stream()
                .map(score -> score.getTeamA() + " " + score.getScoreA() +
                        ":" + score.getScoreB() + " " + score.getTeamB() +
                        " - " + today)
                .collect(Collectors.toList());
    }
}
