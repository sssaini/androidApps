package youtube.android.sec.com.ipl.model;

/**
 * Created by shiv on 3/30/2017.
 */

public class Player {
    public String name;
    public String role;
    public String team;
    public int playerId;
    public Player(){}
    public Player(int id,String name, String role, String team){
        this.name=name;
        this.role=role;
        this.team=team;
        playerId=id;
    }

}
