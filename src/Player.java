import java.util.HashMap;

public class Player {

    private String name;
    private String numberOfPoints = "0";
    private int numberOfGames = 0;
    private int numberOfSets = 0;
    private HashMap<Integer, Integer> allGamesWin = new HashMap<>();

    //Constructeur
    public Player(String name) {
        this.name = name;
    }

    //getters
    public String getName() {
        return name;
    }

    public String getNumberOfPoints() {
        return numberOfPoints;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public int getNumberOfSets() {
        return numberOfSets;
    }

    public int getGamesInSet(Integer setNum){
        return allGamesWin.get(setNum);
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfPoints(String numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    //jeu gagné
    public void winGame() {
        numberOfGames++;
        numberOfPoints = "0";
    }

    //jeu perdu
    public void looseGame() {
        numberOfPoints = "0";
    }

    //set gagné
    public void winSet() {
        numberOfSets++;
        //On stocke le nombre de jeu gagnés pour ce set
        allGamesWin.put(allGamesWin.size() + 1,numberOfGames);
        numberOfGames = 0;
    }

    //set perdu
    public void looseSet() {
        //On stocke le nombre de jeu gagnés pour ce set
        allGamesWin.put(allGamesWin.size() + 1,numberOfGames);
        numberOfGames = 0;
    }

}
