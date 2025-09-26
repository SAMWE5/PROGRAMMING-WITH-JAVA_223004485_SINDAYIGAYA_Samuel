// Case10_TeamsPlayers.java
import java.util.*;

class Player {
    private String name;
    private int score;

    public Player(String name, int score) {
        this.name = name; this.score = score;
    }

    public int getScore() { return score; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name + " (" + score + ")";
    }
}

class Team {
    private String name;
    private List<Player> players = new ArrayList<>();

    public Team(String name) { this.name = name; }

    public void addPlayer(Player p) { players.add(p); }

    public int totalScore() {
        int sum = 0;
        for (Player p : players) sum += p.getScore();
        return sum;
    }

    public Player highestScorer() {
        if (players.isEmpty()) return null;
        Player best = players.get(0);
        for (Player p : players) {
            if (p.getScore() > best.getScore()) best = p;
        }
        return best;
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return name + " | Total: " + totalScore() + " | Players: " + players;
    }
}

public class Case10_TeamsPlayers {
    public static void main(String[] args) {
        Team t1 = new Team("Lions");
        t1.addPlayer(new Player("Alice", 10));
        t1.addPlayer(new Player("Bob", 15));
        t1.addPlayer(new Player("Carol", 8));

        Team t2 = new Team("Tigers");
        t2.addPlayer(new Player("Dave", 12));
        t2.addPlayer(new Player("Eve", 20));
        t2.addPlayer(new Player("Frank", 6));

        Team[] teams = {t1, t2};
        Team bestTeam = null;
        int highestTotal = Integer.MIN_VALUE;

        for (Team t : teams) {
            System.out.println(t);
            Player top = t.highestScorer();
            System.out.println("  Highest scorer: " + (top != null ? top.toString() : "none"));
            if (t.totalScore() > highestTotal) {
                highestTotal = t.totalScore();
                bestTeam = t;
            }
        }

        System.out.println();
        System.out.println("Team with highest total score: " + (bestTeam != null ? bestTeam.getName() + " (" + highestTotal + ")" : "none"));
    }
}
