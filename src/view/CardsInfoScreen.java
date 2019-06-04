package view;

import controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GUI;
import model.FavorToken;

public class CardsInfoScreen extends BorderPane{
	private Button btnCard;
	private FavorTokensScreen favorTokens;
	private VBox layout;
	private Label tokens;
	private HBox hbox;
	
	public CardsInfoScreen(GUI gui, String info, GameController GC) {
		Label l = new Label(info);
		l.setPadding(new Insets(70));
		l.setFont(new Font("Consolas", 18));
		setRight(l);
		setMinSize(100, 200);
		setPrefSize(100, 200);
		 tokens = new Label("Aantal betaalstenen:");
	        tokens.setFont(new Font("Consolas", 16));
		btnCard = new Button("Kaarten tonen");

		btnCard.setOnAction(e -> gui.handleGoToCards());


		favorTokens = new FavorTokensScreen(new FavorToken(0, Color.BLUE));;
		hbox = new HBox(5,tokens,favorTokens);
		layout = new VBox(50, btnCard,hbox);
		layout.setPadding(new Insets(100,0,0,150));
		 
		
		
	        setCenter(layout);
		
	}
	
    public void setAmountFT(int amount) {
       
       favorTokens.getModel().setAmount(amount);
        
        
       
    }
    
}