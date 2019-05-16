package queries;

public class CardQueries {
	StandardQuerie standardQuerie;
	
	
	public CardQueries(StandardQuerie standardQuerie) {
		this.standardQuerie = standardQuerie;
	}
	
	public void updateGameTC(int tc,int intTC,int GameID) {
		
		standardQuerie.updateQuery("INSERT INTO gametoolcard VALUES(?,?,?)", ""+tc+"\0"+intTC+"\0"+GameID+"");
		
	}
	
}
