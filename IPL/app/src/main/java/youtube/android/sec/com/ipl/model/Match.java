package youtube.android.sec.com.ipl.model;

/**
 * Created by shiv on 3/30/2017.
 */

public class Match {
    public String teamFirst;
    public String teamSecond;
    public String vanue;
    public String time;
    public int matchId;

    public Match() {
    }

    public Match(int id, String teamFirst, String teamSecond, String time, String vanue) {
        matchId = id;
        this.teamFirst = teamFirst;
        this.teamSecond = teamSecond;
        this.time = time;
        this.vanue = vanue;
    }
}
