package queries;

import java.util.ArrayList;

public class UserListQueries {
    private StandardQueries queries;

    public UserListQueries(StandardQueries queries){ this.queries = queries; }

    public ArrayList<ArrayList<Object>> getUserList(){
       return queries.selectQuery("SELECT username FROM account");
    }

    public ArrayList<ArrayList<Object>> getMaxScore(){
        return null; //SELECT MAX(score) FROM player WHERE username = 'niels'
    }

    public ArrayList<ArrayList<Object>> getUserStats(){
        return queries.selectQuery("SELECT p.username, MAX(p.score), (SELECT p2.diecolor ORDER BY COUNT(p2.diecolor) LIMIT 1), " +
                                    "(SELECT gd.eyes FROM gamedie ORDER BY COUNT(eyes)) FROM player p " +
                                    "INNER JOIN playerframefield p2 on p.idplayer = p2.player_idplayer " +
                                    "INNER JOIN gamedie gd on p2.dienumber = gd.dienumber " +
                                    "GROUP BY username");
    }

    public ArrayList<ArrayList<Object>> getUserList(Object sort){
        String sortVal = sort.toString();
        switch (sortVal){
            case "Gewonnen potjes": return queries.selectQuery("SELECT username FROM account ORDER BY username DESC");
            case "Alfabetisch": return queries.selectQuery("SELECT username FROM account");
            default: return null;
        }
    }

    public ArrayList<ArrayList<Object>> h(){
        return null; //SELECT MAX(score) FROM player WHERE username = 'niels'
    }
}
