package xyz.thejaysics.sportsdashboard.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Team {
  @Id
  private long id;
  private String teamName;
  private long totalMatches;
  private long totalWins;
  public Team(String teamName, long totalMatches) {
    this.teamName = teamName;
    this.totalMatches = totalMatches;
  }
}
