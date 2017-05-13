package youtube.android.sec.com.ipl.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shiv on 4/8/2017.
 */

public class LiveScore {
    public String team;
    public int run;
    public int wicket;
    public int over;
    public int ball;

    public LiveScore() {
    }

    public LiveScore(String team, int run, int wicket, int over, int ball) {
        this.team = team;
        this.run = run;
        this.wicket = wicket;
        this.over = over;
        this.ball = ball;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("team", team);
        map.put("run", run);
        map.put("wicket", wicket);
        map.put("over", over);
        map.put("ball", ball);
        return map;
    }
}

