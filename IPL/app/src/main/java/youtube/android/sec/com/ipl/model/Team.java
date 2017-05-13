package youtube.android.sec.com.ipl.model;

/**
 * Created by shiv on 3/30/2017.
 */

public class Team {
    public String name;
    public String id;
    public Team(){}
    public Team(String name , String id){
        this.name= name;
        this.id=id;
    }
    public String  getName(){
        return this.name;
    }

    public String getId(){
        return this.id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setId( String id){
        this.id=id;
    }
}
