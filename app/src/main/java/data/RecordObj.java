package data;

/**
 * Created by matka on 14/01/17.
 */
public class RecordObj implements Comparable<RecordObj> {

    private String userName;
    private int score;
    private String location;


    private String level;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLocation() {
        return location;
    }

    public String getLevel() {
        return level;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public RecordObj(String userName, int score, String location, String level) {

        this.userName = userName;
        this.score = score;
        this.location = location;
        this.level = level;
    }

    @Override
    public String toString() {
        return "RecordObj{" +
                "userName='" + userName + '\'' +
                ", score=" + score +
                ", location='" + location + '\'' +
                ", level='" + level + '\'' +
                '}';
    }

    @Override
    public int compareTo(RecordObj recordObj) {
        if(this.getLevel().equals(recordObj.getLevel()))
            return this.getScore()-recordObj.getScore();
        if(this.getLevel().equals("hard"))
            return -1;
        else if(this.getLevel().equals("medium")){
            if(recordObj.getLevel().equals("hard"))
                return 1;
            else
                return -1;
        }
        else
            return 1;
    }
}
