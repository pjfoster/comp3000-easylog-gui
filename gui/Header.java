package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

public class Header extends FlowPane {

	private final LoggerWindow parent;
	
	private final String onHover = "-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #000000";
	private final String offHover = "-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff";
	private final String optionsButtonStyle = "-fx-background-color: #0258A3; -fx-text-fill: white; -fx-border-color: black";
	
	public Header(final LoggerWindow parent) {
		this.parent = parent;
		
		final Label label = new Label("EASYLOG: Log Navigator");
		label.setFont(new Font("Copperplate", 30));
		
		final Label blankLabel = new Label("");
		blankLabel.setMinWidth(300);
		
		Button createIndex = new Button("CREATE / UPDATE INDEX");
		createIndex.setFont(new Font("Arial", 20));
		createIndex.setStyle(offHover);
	
		Button optionsButton = new Button("...");
		optionsButton.setStyle(optionsButtonStyle);
		
		createIndex.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Button b = (Button)e.getSource();
        		System.out.println("CREATE INDEX BUTTON CLICK");
        		Header p = (Header)b.getParent();
        		p.parent.createIndex();
        	}
        });
		
		createIndex.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
        	public void handle(MouseEvent e) {
        		Button b = (Button)e.getSource();
        		b.setStyle(onHover);
        	}
		});
		
		createIndex.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
        	public void handle(MouseEvent e) {
        		Button b = (Button)e.getSource();
        		b.setStyle(offHover);
        	}
		});
		
		optionsButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		parent.showIndexConfig();
        	}
        });
		
		this.setAlignment(Pos.CENTER_LEFT);
		
		this.setMaxHeight(100);
		this.setMinHeight(100);

		this.setHgap(10);
		this.setPadding(new Insets(5, 5, 5, 40));
		this.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #bacbd9, #0258A3)");

		this.getChildren().addAll(label, blankLabel, createIndex, optionsButton);
		
	}
	
}
