package queries;

import java.util.ArrayList;

public class CardQueries {
	StandardQuerie standardQuerie;
	
	
	public CardQueries(StandardQuerie standardQuerie) {
		this.standardQuerie = standardQuerie;
	}
	
	public void updateGameTC(int tc,int intTC,int GameID) {
		
		standardQuerie.updateQuery("INSERT INTO gametoolcard VALUES(?,?,?)", ""+tc+"\0"+intTC+"\0"+GameID+"");
		
	}
	
public void updatePUBOBJC(int GameID,int IDCard) {
		
		standardQuerie.updateQuery("INSERT INTO sharedpublic_objectivecard VALUES(?,?)", ""+GameID+"\0"+IDCard+"");
		
	}

	public ArrayList<ArrayList<Object>> CheckTCBought(int tc, int idGame) {
				return standardQuerie.selectQuery("Select gametoolcard from gamefavortoken", "where gametoolcard=? and idGame=?",""+tc+"\0"+idGame+"");
				
	}
	
	
	
	
}
