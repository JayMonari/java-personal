package xyz.thejaysics.sportsdashboard.data;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import xyz.thejaysics.sportsdashboard.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {
  private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

  @Override
  public Match process(final MatchInput matchInput) throws Exception {
    String[] InningsTeams = getInningTeams(matchInput);
    Match match = Match.builder()
      .id(Long.parseLong(matchInput.getId()))
      .city(matchInput.getCity())
      .date(LocalDate.parse(matchInput.getDate()))
      .playerOfMatch(matchInput.getPlayer_of_match())
      .venue(matchInput.getVenue())
      .team1(InningsTeams[0])
      .team2(InningsTeams[1])
      .tossWinner(matchInput.getToss_winner())
      .tossDecision(matchInput.getToss_decision())
      .umpire1(matchInput.getUmpire1())
      .umpire2(matchInput.getUmpire2())
      .matchWinner(matchInput.getWinner())
      .result(matchInput.getResult())
      .resultMargin(matchInput.getResult_margin())
      .build();
    return match;
  }

  private String[] getInningTeams(MatchInput matchInput) {
    String firstInningsTeam;
    String secondInningsTeam;
    if ("bat".equals(matchInput.getToss_decision())) {
      firstInningsTeam = matchInput.getToss_winner();
      secondInningsTeam = firstInningsTeam.equals(matchInput.getTeam1())
          ? matchInput.getTeam2()
          : matchInput.getTeam1();
    } else {
      secondInningsTeam = matchInput.getToss_winner();
      firstInningsTeam = secondInningsTeam.equals(matchInput.getTeam1())
          ? matchInput.getTeam2()
          : matchInput.getTeam1();
    }
    return new String[] { firstInningsTeam, secondInningsTeam };
  }
}
