import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TennisMatchTest {
    Player player1;
    Player player2;
    TennisMatch m;

    @Test
    public void gagneJeuSansAvantage() throws Exception {
        player1 = new Player("Stanislas Wawrinka");
        player2 = new Player("Lucas Pouille");
        m = new TennisMatch(player1, player2, MatchType.BEST_OF_THREE, false);

        //Début match
        m.updateWithPointWonBy(player1); //J1 15
        m.updateWithPointWonBy(player1); //J1 30
        m.updateWithPointWonBy(player2); //J2 15
        m.updateWithPointWonBy(player1); //J1 40
        m.updateWithPointWonBy(player2); //J2 30
        m.updateWithPointWonBy(player1); //J1 gagne jeu

        assertEquals(1, player1.getNumberOfGames());
    }

    @Test
    public void gagneJeuAvecAvantage() throws Exception {
        player1 = new Player("Stanislas Wawrinka");
        player2 = new Player("Lucas Pouille");
        m = new TennisMatch(player1, player2, MatchType.BEST_OF_THREE, false);

        //Début match
        m.updateWithPointWonBy(player1); //J1 15
        m.updateWithPointWonBy(player1); //J1 30
        m.updateWithPointWonBy(player2); //J2 15
        m.updateWithPointWonBy(player1); //J1 40
        m.updateWithPointWonBy(player2); //J2 30
        m.updateWithPointWonBy(player2); //J2 40
        m.updateWithPointWonBy(player1); //J1 avantage, J2 40

        //On vérifie que J1 est à A
        assertEquals("A", m.pointsForPlayer(player1));

        m.updateWithPointWonBy(player2); //J2 40, J1 40

        //On vérifie que J1 est à 40
        assertEquals("40", m.pointsForPlayer(player1));

        m.updateWithPointWonBy(player1); //J1 avantage, J2 40

        //On vérifie que J1 est à A
        assertEquals("A", m.pointsForPlayer(player1));

        m.updateWithPointWonBy(player1); //J1 gagne

        assertEquals(1, player1.getNumberOfGames());
    }

    @Test //On gagne un set avec 2 jeux d'écart, donc sans tie break
    public void gagneSetNormal() throws Exception {
        player1 = new Player("Stanislas Wawrinka");
        player2 = new Player("Lucas Pouille");
        m = new TennisMatch(player1, player2, MatchType.BEST_OF_THREE, false);

        for (int i = 0; i < 4; i++) {
            player1.winGame();
            player2.winGame();
        }

        m.updateWithPointWonBy(player1); //J1  15
        m.updateWithPointWonBy(player1); //J1  30
        m.updateWithPointWonBy(player1); //J1  40
        m.updateWithPointWonBy(player1); //J1 gagne un jeu
        System.out.println("P1 point: "+m.pointsForPlayer(player1)+", P1 games: "+ player1.getNumberOfGames());
        m.updateWithPointWonBy(player1); //J1  15
        m.updateWithPointWonBy(player1); //J1  30
        m.updateWithPointWonBy(player1); //J1  40
        m.updateWithPointWonBy(player1); //J1 gagne un jeu
        System.out.println("P1 point: "+m.pointsForPlayer(player1)+", P1 games: "+ player1.getNumberOfGames());
        System.out.println("P1 set: "+player1.getNumberOfSets());
        //J1 gagne le set (auy moins 6 jeux et 2 jeux d'écart)

        assertEquals(1, player1.getNumberOfSets());
    }

    @Test //On gagne un set avec 2 jeux d'écart mais avec égalité à 5-5 avant
    public void gagneSetNormalEquals5() throws Exception {
        player1 = new Player("Stanislas Wawrinka");
        player2 = new Player("Lucas Pouille");
        m = new TennisMatch(player1, player2, MatchType.BEST_OF_THREE, false);

        for (int i = 0; i < 4; i++) {
            player1.winGame();
            player2.winGame();
        }

        //Début match
        m.updateWithPointWonBy(player1); //J1  15
        m.updateWithPointWonBy(player1); //J1  30
        m.updateWithPointWonBy(player1); //J1  40
        m.updateWithPointWonBy(player1); //J1 gagne un jeu
        System.out.println("P1 point: "+m.pointsForPlayer(player1)+", P1 games: "+ player1.getNumberOfGames());
        m.updateWithPointWonBy(player2); //J2  15
        m.updateWithPointWonBy(player2); //J2  30
        m.updateWithPointWonBy(player2); //J2  40
        m.updateWithPointWonBy(player2); //J2 gagne un jeu
        System.out.println("P2 point: "+m.pointsForPlayer(player2)+", P2 games: "+ player2.getNumberOfGames());
        m.updateWithPointWonBy(player1); //J1  15
        m.updateWithPointWonBy(player1); //J1  30
        m.updateWithPointWonBy(player1); //J1  40
        m.updateWithPointWonBy(player1); //J1 gagne un jeu
        System.out.println("P1 point: "+m.pointsForPlayer(player1)+", P1 games: "+ player1.getNumberOfGames());
        m.updateWithPointWonBy(player1); //J1  15
        m.updateWithPointWonBy(player1); //J1  30
        m.updateWithPointWonBy(player1); //J1  40
        m.updateWithPointWonBy(player1); //J1 gagne un jeu
        System.out.println("P1 point: "+m.pointsForPlayer(player1)+", P1 games: "+ player1.getNumberOfGames());

        System.out.println("P1 set: "+ player1.getNumberOfSets());

        assertEquals(1, player1.getNumberOfSets());
    }

    @Test
    public void gagneSetAvecTieBreak() throws Exception {
        player1 = new Player("Stanislas Wawrinka");
        player2 = new Player("Lucas Pouille");
        m = new TennisMatch(player1, player2, MatchType.BEST_OF_THREE, true);

        for (int i = 0; i < 5; i++) {
            player1.winGame();
            player2.winGame();
        }

        //Début match
        m.updateWithPointWonBy(player1); //J1  15
        m.updateWithPointWonBy(player1); //J1  30
        m.updateWithPointWonBy(player1); //J1  40
        m.updateWithPointWonBy(player1); //J1 gagne un jeu mais pas le set
        System.out.println("P1 set: "+player1.getNumberOfSets());
        m.updateWithPointWonBy(player2); //J2  15
        m.updateWithPointWonBy(player2); //J2  30
        m.updateWithPointWonBy(player2); //J2  40
        m.updateWithPointWonBy(player2); //J2 gagne un jeu mais pas le set
        System.out.println("P2 set: "+player2.getNumberOfSets());
        System.out.println("-------------------------");
        System.out.println("P1 games: "+player1.getNumberOfGames());
        System.out.println("P2 games: "+player2.getNumberOfGames());
        System.out.println("-------------------------");
        System.out.println("-------------------------");
        m.updateWithPointWonBy(player1); //J1  15
        m.updateWithPointWonBy(player1); //J1  30
        m.updateWithPointWonBy(player1); //J1  40
        m.updateWithPointWonBy(player1); //J1 gagne un jeu mais pas le set
        System.out.println("P1 set: "+player1.getNumberOfSets());
        System.out.println("P2 set: "+player2.getNumberOfSets());
        System.out.println("P1 games: "+player1.getNumberOfGames());
        System.out.println("P2 games: "+player2.getNumberOfGames());
        System.out.println("-------------------------");
        m.updateWithPointWonBy(player1); //J1  15
        m.updateWithPointWonBy(player1); //J1  30
        m.updateWithPointWonBy(player1); //J1  40
        m.updateWithPointWonBy(player1); //J1 gagne un jeu et le set
        System.out.println("P1 set: "+player1.getNumberOfSets());
        System.out.println("P2 set: "+player2.getNumberOfSets());

        assertEquals(1, player1.getNumberOfSets());

    }

}