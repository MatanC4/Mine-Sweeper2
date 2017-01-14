package components;

/**
 * Created by matka on 13/01/17.
 */
public class ListItem {


    private String playerName;
    private String score;
    private String level;

    public ListItem(String playerName, String score, String level) {
        this.playerName = playerName;
        this.score = score;
        this.level = level;

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
