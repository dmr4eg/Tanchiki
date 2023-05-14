package game;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class MenuItem extends StackPane {
    public MenuItem(String name, Runnable action){
        LinearGradient gradient = new LinearGradient(
                0, 0.5, 1, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0.1, Color.web("black", 0.75)),
                new Stop(1.0, Color.web("black", 0.15))
        );
        Rectangle bg = new Rectangle(250, 30, gradient);
        Rectangle line = new Rectangle(5, 30 );

        line.fillProperty().bind(
                Bindings.when(hoverProperty()).then(Color.RED).otherwise(Color.GRAY)
        );
        Text text = new Text(name);
        text.fillProperty().bind(
                Bindings.when(hoverProperty()).then(Color.WHITE).otherwise(Color.GRAY)
        );
        text.setFont(Font.font(16.0));

        setOnMouseClicked(e -> action.run());

        setAlignment(Pos.CENTER_LEFT);

        HBox box = new HBox(20, line, text);
        box.setAlignment(Pos.CENTER_LEFT);

        getChildren().addAll(bg, box);
    }
}