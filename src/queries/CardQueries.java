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
				return standardQuerie.selectQuery("Select gametoolcard from gamefavortoken", " where gametoolcard=? and idGame=?",""+tc+"\0"+idGame+"");
				//
	}
	
public void BuyTC(int tc,int FT,int GameID,int playerID) {
		
		standardQuerie.updateQuery("update gamefavortoken Set gametoolcard=?, round=?", ""+tc+"\0"+FT+""," where idfavortoken=?  and idgame=? and idplayer=?", ""+FT+"\0"+GameID+"\0"+playerID+"");
		
	}

public void BuyTCPrice2(int tc,int FT,int FT2,int GameID,int playerID) {
	
	standardQuerie.updateQuery("update gamefavortoken Set gametoolcard=?, round=?", ""+tc+"\0"+FT+""," where idfavortoken=? or idfavortoken=?  and idgame=? and idplayer=?", ""+FT+"\0"+FT2+"\0"+GameID+"\0"+playerID+"");
	
}

public ArrayList<ArrayList<Object>> CheckAmountFTonTC(int tc, int playerID,int idgame) {
	return standardQuerie.selectQuery("Select idfavortoken from gamefavortoken", " where gametoolcard=? and idGame=? and idplayer=?",""+tc+"\0"+idgame+"\0"+playerID+"");
	//
}
	
	
	
	
	
	
}
