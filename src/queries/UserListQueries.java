package queries;

import java.util.ArrayList;

public class UserListQueries {
    private StandardQueries queries;

    public UserListQueries(StandardQueries queries){ this.queries = queries; }

    public ArrayList<ArrayList<Object>> getUserList(){
       return queries.selectQuery("SELECT username FROM account");
    }

    /**
     * gets all stats belonging to a single player, since this is multiple queries we add them into 1 arraylist
     * for easier iteration later
     * @return arraylist of users with their stats
     */
    public ArrayList<ArrayList<Object>> getUserStats(){
        ArrayList<ArrayList<Object>> gameStats = new ArrayList<>(
                queries.selectQuery("SELECT p.username, MAX(p.score), COUNT(p2.game_idgame), COUNT(p.game_idgame) - COUNT(p2.game_idgame)" +
                " FROM player p LEFT JOIN (SELECT game_idgame, MAX(score) as max FROM player GROUP BY game_idgame) p2" +
                " ON p.game_idgame = p2.game_idgame AND p2.max = p.score WHERE playstatus_playstatus = 'uitgespeeld'" +
                " GROUP BY p.username"));

        ArrayList<ArrayList<Object>> dieColors = new ArrayList<>(
                queries.selectQuery("SELECT s.username, s.diecolor FROM (" +
                " SELECT p.username, g.diecolor, COUNT(g.diecolor) as diecount FROM player p" +
                " INNER JOIN playerframefield p2 ON p.idplayer = p2.player_idplayer" +
                " INNER JOIN gamedie g on p2.idgame = g.idgame and p2.dienumber = g.dienumber and p2.diecolor = g.diecolor" +
                " GROUP BY p.username, g.diecolor) s" +
                " INNER JOIN (SELECT s.username, MAX(s.diecount) as maxDieColor" +
                " FROM (SELECT p.username, g.diecolor, COUNT(*) as diecount FROM player p" +
                " INNER JOIN playerframefield p2 on p.idplayer = p2.player_idplayer" +
                " INNER JOIN gamedie g on p2.idgame = g.idgame and p2.dienumber = g.dienumber and p2.diecolor = g.diecolor" +
                " GROUP BY p.username, g.diecolor) s" +
                " GROUP BY s.username) as m ON s.username = m.username AND s.diecount = m.maxDieColor GROUP BY s.username"));

        ArrayList<ArrayList<Object>> eyes = new ArrayList<>(
                queries.selectQuery("SELECT s.username, s.eyes FROM (" +
                " SELECT p.username, g.eyes, COUNT(g.eyes) as diecount FROM player p" +
                " INNER JOIN playerframefield p2 ON p.idplayer = p2.player_idplayer" +
                " INNER JOIN gamedie g on p2.idgame = g.idgame and p2.dienumber = g.dienumber and p2.diecolor = g.diecolor" +
                " GROUP BY p.username, g.eyes) s" +
                " INNER JOIN (SELECT s.username, MAX(s.diecount) as maxEyes" +
                " FROM (SELECT p.username, g.eyes, COUNT(g.eyes) as diecount FROM player p" +
                " INNER JOIN playerframefield p2 on p.idplayer = p2.player_idplayer" +
                " INNER JOIN gamedie g on p2.idgame = g.idgame and p2.dienumber = g.dienumber and p2.diecolor = g.diecolor" +
                " GROUP BY p.username, g.eyes) s" +
                " GROUP BY s.username) as m ON s.username = m.username AND s.diecount = m.maxEyes GROUP BY s.username"));

        ArrayList<ArrayList<Object>> names = getUserList();
        ArrayList<ArrayList<Object>> uniquePlayers = new ArrayList<>();

            for(Object name : names){
                String nameVal = name.toString().replace("[", "").replace("]", "");
                uniquePlayers.addAll(queries.selectQuery("SELECT DISTINCT (SELECT username FROM account WHERE username =?)," +
                        " COUNT(DISTINCT p.username) FROM player p" +
                        " INNER JOIN (SELECT p2.game_idgame FROM player p2 WHERE username=? AND playstatus_playstatus = 'uitgespeeld')" +
                        " AS games ON p.game_idgame = p.game_idgame" +
                        " WHERE p.playstatus_playstatus = 'uitgespeeld' AND username !=?", " ", "" + nameVal +"\0"+ nameVal + "\0" + nameVal));
            }

        ArrayList<ArrayList<Object>> grouped = new ArrayList<>();
        grouped.addAll(dieColors);
        grouped.addAll(eyes);
        grouped.addAll(uniquePlayers);

        for(ArrayList stat : gameStats){
            for(ArrayList group : grouped){
                if(group.get(0).equals(stat.get(0))){
                    stat.add(group.get(1));
                }
            }
        }
        return gameStats;
    }

    public ArrayList<ArrayList<Object>> getUserList(Object sort){
        String sortVal = sort.toString();
        switch (sortVal){
            case "Gewonnen potjes": return queries.selectQuery("SELECT p.username" +
                    " FROM player p LEFT JOIN (SELECT game_idgame, MAX(score) as max FROM player GROUP BY game_idgame) p2" +
                    " ON p.game_idgame = p2.game_idgame AND p2.max = p.score WHERE playstatus_playstatus = 'uitgespeeld'" +
                    " GROUP BY p.username ORDER BY COUNT(p2.game_idgame) DESC");
            case "Alfabetisch": return queries.selectQuery("SELECT username FROM account");
            default: return null;
        }
    }
}
