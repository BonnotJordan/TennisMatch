import java.util.ArrayList;
import java.util.HashMap;

import static sun.misc.Version.println;

public class TennisMatch {
    Player player1, player2;

    MatchType matchType;

    boolean tieBreakInLastSet;


    public TennisMatch(Player player1, Player player2, MatchType matchType, boolean tieBreakInLastSet) {
        this.player1 = player1;
        this.player2 = player2;
        this.matchType = matchType;
        this.tieBreakInLastSet = tieBreakInLastSet;
    }

    public void updateWithPointWonBy(Player player) {
        switch (pointsForPlayer(player)) {
            case "0":
                player.setNumberOfPoints("15");
                break;
            case "15":
                player.setNumberOfPoints("30");
                break;
            case "30":
                player.setNumberOfPoints("40");
                break;
            case "40":
                if (pointsForPlayer(getOtherPlayer(player)).equals("40")) {
                    player.setNumberOfPoints("40");
                } else if (pointsForPlayer(getOtherPlayer(player)).equals("A")) {
                    getOtherPlayer(player).setNumberOfPoints("40");
                } else {
                    updateWithGameWonBy(player);
                }
                break;
            case "A":
                updateWithGameWonBy(player);
                break;
        }

    }

    public void updateWithGameWonBy(Player player) {
        player.winGame();
        getOtherPlayer(player).looseGame();
        if (player.getNumberOfGames() >= 6 && player.getNumberOfGames() - getOtherPlayer(player).getNumberOfGames() >= 2) {
            updateWithSetWonBy(player);
        }
    }

    public void updateWithSetWonBy(Player player) {
        player.winSet();
        getOtherPlayer(player).looseSet();
        if (isFinished()) {
            showWinnerAndLooser(player);
        }
    }
    

    private void showWinnerAndLooser(Player player) {
        System.out.println(player.getName() + "win the match");
        System.out.println(getOtherPlayer(player).getName() + "loose the match");
    }


    public String pointsForPlayer(Player player) {
        return player.getNumberOfPoints();
    }

    public int currentSetNumber() {
        return player1.getNumberOfSets() + player2.getNumberOfSets() + 1;
    }

    public int gamesInCurrentSetForPlayer(Player player) {
        return player.getNumberOfGames();
    }

    public int gamesInSetForPlayer(int setNumber, Player player) {
        return player.getGamesInSet(setNumber);
    }

    public boolean isFinished() {
        return true;
    }

    public boolean isLastSet() {
        if (currentSetNumber() == matchType.maxNumberOfSets()) {
            return true;
        } else {
            return false;
        }
    }

    public Player getOtherPlayer(Player player) {
        if (player == player1) {
            return player2;
        } else {
            return player1;
        }
    }


}
