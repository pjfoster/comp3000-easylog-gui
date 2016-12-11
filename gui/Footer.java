package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Footer extends VBox {

	private int numLogs = 0;
	private final LoggerWindow parent;
	
	public Footer(final LoggerWindow parent) {
		this.parent = parent;
		final Label label = new Label(numLogs + " logs displayed");
		label.setFont(new Font("Arial", 12));
		
		this.setAlignment(Pos.CENTER_LEFT);
		
		this.setMaxHeight(100);
		this.setMinHeight(50);
		this.setPadding(new Insets(5, 5, 5, 40));
		this.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #bacbd9, #0258A3)");
		this.getChildren().addAll(label);
	}
	
}
