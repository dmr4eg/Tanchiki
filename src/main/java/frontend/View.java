package frontend;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import structure.Controller;
import structure.Model;
import structure.modules.LevelContainer;

import java.util.ArrayList;
import java.util.HashMap;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;

public class View{
    private static Stage STAGE;
    private GraphicsContext spgc;
    //    private GraphicsContext mpgc;
//    private GraphicsContext legc;
    private Canvas SpCanvas;
    //    private Canvas MpCanvas;
//    private Canvas LeCanvas;
    //----------------------------------------------
//    private static Scene MultiPlayerScene;
//    private static StackPane MPGameGamePane;
    //----------------------------------------------
    private static Scene SinglePlayerScene;
    private static StackPane SPGamePane;
    private int WIDTH, HEIGHT;
    //----------------------------------------------
    private Controller controller;
    private static HashMap<String, Scene> Game_scenes = new HashMap<>();
    private static ArrayList<Scene> sceneStack = new ArrayList<>();
    //----------------------------------------------
    public static void setStage(Stage stage){
        STAGE = stage;
    }
    public void setSPGamePane(Canvas canvas){
        SPGamePane = new StackPane(canvas);
    }
    public static StackPane getSPGamePane() {
        return SPGamePane;
    }
    public static void addToScenes(Scene scene){
        sceneStack.add(scene);
        STAGE.setScene(scene);
    }
    public static void setScene(String name){
        STAGE.setScene(Game_scenes.get(name));
    }

    public static void putOnGame_scenes(String key, Scene scene){
        Game_scenes.put(key, scene);
    }

    public static void previousScene(){
        if(!sceneStack.isEmpty()){
            sceneStack.remove(sceneStack.size()-1);
            Scene prevScene = sceneStack.get(sceneStack.size()-1);
            STAGE.setScene(prevScene);
        }
    }
    private void setSinglePlayerScene(Scene scene){
        SinglePlayerScene = scene;
    }
    //    public Scene getMultiplayerScene(){
//        return MultiPlayerScene;
//    }
//    public void setMPGamePane(Canvas canvas){
//        MPGameGamePane = new StackPane(canvas);
//    }
//    public StackPane getMPGamePane(){
//        return MPGameGamePane;
//    }
    public static Scene getSinglePlayerScene(){
        return SinglePlayerScene;
    }



    public View(Stage stage, int WIDTH,int  HEIGHT) {
        setStage(stage);
        this.SpCanvas = new Canvas(WIDTH, HEIGHT);
        setSPGamePane(SpCanvas);
//        this.MpCanvas = new Canvas(WIDTH, HEIGHT);
//        setMPGamePane(MpCanvas);
        this.spgc = SpCanvas.getGraphicsContext2D();
//        this.mpgc = MpCanvas.getGraphicsContext2D();
//        MultiPlayerScene = new Scene(MPGameGamePane);
        SinglePlayerScene = new Scene(SPGamePane);
        Game_scenes.put("SP", SinglePlayerScene);
//        Game_scenes.put("MP", MultiPlayerScene);
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    public void drawSPPane(Model model){
        spgc.setFill(BLACK);
        spgc.fillRect(0, 0, WIDTH - 200, HEIGHT);
        spgc.setFill(WHITE);
        spgc.fillRect(WIDTH-200, 0, 200, HEIGHT );
        spgc.setFill(BLACK);
        spgc.setFont(Font.loadFont(getClass().getResourceAsStream("/CCOverbyteOffW00-Regular.ttf"), 20));
        spgc.fillText(String.format("Player 1 HP: %d", model.getPlayer1().getHP()), 625,100, 300);
        if(model.getPlayer2()!=null)spgc.fillText(String.format("Player 2 HP: %d", model.getPlayer2().getHP()), 625,150, 300);
        spgc.fillText(String.format("Enemies left: %d", model.getEnemies()), 625,200, 300);
        //model.UpdateModel();
    }

    public void drawEnd(){
        spgc.setFill(BLACK);
        spgc.fillRect(0, 0 , WIDTH, HEIGHT);
        spgc.setFill(WHITE);
        spgc.setFont(Font.loadFont(getClass().getResourceAsStream("/CCOverbyteOffW00-Regular.ttf"), 50));
        spgc.fillText("GGWP Loshara", 250,300, 300);
    }


    public static void pause(Model model, HBox hbox, StackPane gamepane){
        if (model.getGameIsStart())gamepane.getChildren().add(hbox);
        else gamepane.getChildren().remove(hbox);
    }

    public static HBox pauseHbox(){

        VBox vbox = new VBox(20,
                new MenuItem("Save",()->{}, "levelEditor" ),
                new MenuItem("Menu", View::previousScene, "levelEditor")
        );
        vbox.setPrefSize(250, 250);
        vbox.setAlignment(Pos.CENTER);
        HBox hbox2 = new HBox(vbox);
        hbox2.setBackground(new Background(new BackgroundFill(BLACK, null, null)));
        hbox2.setPrefSize(800, 600);
        hbox2.setTranslateX(0);
        hbox2.setTranslateY(0);
        hbox2.setAlignment(Pos.CENTER);
        return hbox2;
    }

    public GraphicsContext getSPGc() {
        return spgc;
    }

    public StackPane buildNewStackPaneWithGc(GraphicsContext gc){
        //canvas = new GridPane();
        StackPane canvas = new StackPane();
        canvas.setStyle("-fx-background-color: black;");
//        this.canvas.setPrefSize(800, 600);
        canvas.getChildren().add(gc.getCanvas());
        SPGamePane = canvas;
        return canvas;
    }

    public Scene buildNewSceneWithStackPane(StackPane stackPane){
        Scene scene = new Scene(stackPane);
        SinglePlayerScene = scene;
        return SinglePlayerScene;
    }

}
