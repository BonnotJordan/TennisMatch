public class TennisMatch {

    Player player1, player2;

    MatchType matchType;

    boolean tieBreakInLastSet;

    //Constructeur
    public TennisMatch(Player player1, Player player2, MatchType matchType, boolean tieBreakInLastSet) {
        this.player1 = player1;
        this.player2 = player2;
        player1.setNumberOfPoints("0");
        player2.setNumberOfPoints("0");
        this.matchType = matchType;
        this.tieBreakInLastSet = tieBreakInLastSet;
    }

    // Gestion des points
    public void updateWithPointWonBy(Player player) {
        //Check si tieBreak
        if (tieBreakInLastSet && currentSetNumber() == matchType.maxNumberOfSets()) {
            //on compte de 1 en 1 et jeu gagné si au moins 7 avec 2 points d'écart
            player.setNumberOfPoints(String.valueOf(Integer.parseInt(pointsForPlayer(player) + 1)));
            if (Integer.parseInt(pointsForPlayer(player)) >= 7 && Integer.parseInt(pointsForPlayer(player)) - Integer.parseInt(pointsForPlayer(getOtherPlayer(player))) >= 2) {
                updateWithGameWonBy(player);
            }
        }
        //Sinon normal
        else {
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
                    //Si les point de l'autre = 40 alors player passe à A
                    if (pointsForPlayer(getOtherPlayer(player)).equals("40")) {
                        player.setNumberOfPoints("A");
                    }
                    // si point autre = A on passe point autre à 40
                    else if (pointsForPlayer(getOtherPlayer(player)).equals("A")) {
                        getOtherPlayer(player).setNumberOfPoints("40");
                    } // sinon on gagne jeu
                    else {
                        updateWithGameWonBy(player);
                    }
                    break;
                case "A":
                    updateWithGameWonBy(player);
                    break;
            }
        }


    }

    // gestion des jeux
    public void updateWithGameWonBy(Player player) {
        player.winGame();
        getOtherPlayer(player).looseGame();
        // si au moins 6 jeux et 2 jeux d'écart = gagne set
        if (player.getNumberOfGames() >= 6 && Math.abs(player.getNumberOfGames() - getOtherPlayer(player).getNumberOfGames()) >= 2) {
            updateWithSetWonBy(player);
        }
    }


    // gestion set
    public void updateWithSetWonBy(Player player) {
        player.winSet();
        getOtherPlayer(player).looseSet();
        // si match terminé = affichage gagnant
        if (isFinished()) {
            showWinnerAndLooser(player);
        }
    }
    

    // affichage
    private void showWinnerAndLooser(Player player) {
        System.out.println(player.getName() + "win the match");
        System.out.println(getOtherPlayer(player).getName() + "loose the match");
    }

    // point pour le joueur
    public String pointsForPlayer(Player player) {
        return player.getNumberOfPoints();
    }

    // le numéro du set en cours
    public int currentSetNumber() {
        return player1.getNumberOfSets() + player2.getNumberOfSets() + 1;
    }

    //numéro du jeux dans le set en cours
    public int gamesInCurrentSetForPlayer(Player player) {
        return player.getNumberOfGames();
    }

    //numéro du jeux pour 1 joueur pour un set
    public int gamesInSetForPlayer(int setNumber, Player player) {
        return player.getGamesInSet(setNumber);
    }

    //gestion fin du match
    public boolean isFinished() {
        // si J1 ou J2 ont atteinds le nombre de set pour gagner
        if (player1.getNumberOfSets() == matchType.numberOfSetsToWin() || player2.getNumberOfSets() == matchType.numberOfSetsToWin()) {
            return true;
        } else {
            return false;
        }
    }

    //check si dernier set
    public boolean isLastSet() {
        if (currentSetNumber() == matchType.maxNumberOfSets()) {
            return true;
        } else {
            return false;
        }
    }

    //récupère l'autre joueur
    public Player getOtherPlayer(Player player) {
        if (player == player1) {
            return player2;
        } else {
            return player1;
        }
    }

}
