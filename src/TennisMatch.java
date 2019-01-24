public class TennisMatch {
    Player player1, player2;
    MatchType matchType;
    boolean tieBreakInLastSet;
    String numberOfPointWinByPlayer1 = "0", numberOfPointWinByPlayer2 = "0";
    int numberOfSetsWinByPlayer1 = 0, numberOfSetsWinByPlayer2 = 0;
    int numberOfGamesWinByPlayer1 = 0, numberOfGamesWinByPlayer2 = 0;

    public TennisMatch(Player player1, Player player2, MatchType matchType, boolean tieBreakInLastSet) {
        this.player1 = player1;
        this.player2 = player2;
        this.matchType = matchType;
        this.tieBreakInLastSet = tieBreakInLastSet;
    }

    public void updateWithPointWonBy(Player player) {
        if(player == player1){
            switch (numberOfPointWinByPlayer1){
                case "0":
                    numberOfPointWinByPlayer1 = "15";
                    break;
                case "15":
                    numberOfPointWinByPlayer1 = "30";
                    break;
                case "30":
                    numberOfPointWinByPlayer1 = "40";
                    break;
                case "40":
                    numberOfPointWinByPlayer1 = "A";
                    break;
                case "A":
                    numberOfGamesWinByPlayer1 += 1;
                    break;
            }
        } else if (player == player2){
            switch (numberOfPointWinByPlayer2){
                case "0":
                    numberOfPointWinByPlayer2 = "15";
                    break;
                case "15":
                    numberOfPointWinByPlayer2 = "30";
                    break;
                case "30":
                    numberOfPointWinByPlayer2 = "40";
                    break;
                case "40":
                    numberOfPointWinByPlayer2 = "A";
                    break;
                case "A":
                    numberOfGamesWinByPlayer2 += 1;
                    break;
            }
        }
        if (numberOfGamesWinByPlayer1 == 6){

        } else if (numberOfGamesWinByPlayer2) {
            
        }


    }

    public String pointsForPlayer(Player player) {
        if (player == player1) {
            return numberOfPointWinByPlayer1;
        } else {
            return numberOfPointWinByPlayer2;
        }

    }

    public int currentSetNumber() {
        return 0;
    }

    public int gamesInCurrentSetForPlayer(Player player) {
        if (player == player1) {
            return numberOfGamesWinByPlayer1;
        } else {
            return numberOfGamesWinByPlayer2;
        }
    }

    public int gamesInSetForPlayer(int setNumber, Player player) {
        return 0;
    }

    public boolean isFinished() {
        return false;
    }


}
