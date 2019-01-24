import java.util.HashMap;

public class Player {
    private String name;
    private String numberOfPoints = "0";
    private int numberOfGames = 0;
    private int numberOfSets = 0;
    private HashMap<Integer, Integer> allGamesWin = new HashMap<>();

    public Player(String name) {
        this.name = name;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfPoints(String numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public void winGame() {
        numberOfGames++;
        numberOfPoints = "0";
    }

    public void looseGame() {
        numberOfPoints = "0";
    }

    public void winSet() {
        numberOfSets++;
        allGamesWin.put(allGamesWin.size() + 1,numberOfGames);
        numberOfGames = 0;
    }

    public void looseSet() {
        allGamesWin.put(allGamesWin.size() + 1,numberOfGames);
        numberOfGames = 0;
    }


    public int getGamesInSet(Integer setNum){
        return allGamesWin.get(setNum);
    }

}
