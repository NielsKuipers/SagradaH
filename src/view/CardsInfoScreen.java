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

public class CardsInfoScreen extends BorderPane{
	private Button btnCard;
	private Label favorTokens;
	private VBox layout;
	private String tokens;
	
	public CardsInfoScreen(GUI gui, String info, GameController GC) {
		Label l = new Label(info);
		l.setPadding(new Insets(70));
		l.setFont(new Font("Consolas", 18));
		setRight(l);
		setMinSize(100, 200);
		setPrefSize(100, 200);

		btnCard = new Button("Show cards");

		btnCard.setOnAction(e -> gui.handleGoToCards());


		favorTokens = new Label("amount of favor tokens:"+ tokens);
		favorTokens.setFont(new Font("Consolas", 16));

		layout = new VBox(50, btnCard,favorTokens);
		layout.setPadding(new Insets(100,0,0,150));
		setCenter(layout);
	}
	
    public void setAmountFT(String tokens) {
        this.tokens = tokens;
        favorTokens = new Label("amount of favor tokens:"+ tokens);
        favorTokens.setFont(new Font("Consolas", 16));
        
        
        layout = new VBox(50, btnCard,favorTokens);
        layout.setPadding(new Insets(100,0,0,150));
        setCenter(layout);
    }
    public String getAmountFT() {
        return tokens;
    }
}