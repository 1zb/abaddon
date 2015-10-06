package cn.bzhang.dota2.abaddon.controller;

import cn.bzhang.dota2.abaddon.Main;
import cn.bzhang.dota2.abaddon.parser.ReplayData;
import cn.bzhang.dota2.abaddon.constants.Hero;
import cn.bzhang.dota2.abaddon.constants.Statics;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class PositionsController implements Initializable {

    @FXML
    private VBox vboxHeroes;

    @FXML
    private Canvas canvasMinimap;

    @FXML
    private Pane paneMinimap;

    @FXML
    private Button buttonPlay;

    @FXML
    private Button buttonPause;

    @FXML
    private Slider slider;

    @FXML
    private Label labelCurrentTime;

    @FXML
    private Label labelEndTime;


    private CheckBox cboxPlayers[] = new CheckBox[Statics.NUM_PLAYERS];
    private Circle circlePlayers[] = new Circle[Statics.NUM_PLAYERS];

    private ReplayData replayData;

    PositionsPlayer positionsPlayer;

    private BooleanProperty dataIsNull = new SimpleBooleanProperty(true);

//    private BooleanProperty heroSelected[] = new SimpleBooleanProperty[Statics.NUM_PLAYERS];

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        positionsPlayer = new PositionsPlayer();

        for (int i = 0; i < Statics.NUM_PLAYERS; i++) {
            if (i == 0) vboxHeroes.getChildren().add(new Label("Radiant"));
            if (i == 5) vboxHeroes.getChildren().add(new Label("Dire"));
            cboxPlayers[i] = new CheckBox("[Empty]");
            cboxPlayers[i].setTextFill(Statics.PLAYER_COLOURS[i]);
            vboxHeroes.getChildren().add(cboxPlayers[i]);
//            heroSelected[i] = new SimpleBooleanProperty(false);
//            heroSelected[i].bindBidirectional(cboxPlayers[i].selectedProperty());
//            circlePlayers[i].visibleProperty().bind(heroSelected[i]);
        }

        positionsPlayer.changingProperty().bind(slider.valueChangingProperty());
        positionsPlayer.currentTimeProperty().addListener(((observable, oldValue, newValue) -> {
            if (!slider.isValueChanging())
                slider.setValue(newValue.intValue());
        }));

        labelCurrentTime.textProperty().bindBidirectional(positionsPlayer.currentTimeProperty(), new
                NumberStringConverter());
        labelEndTime.textProperty().bindBidirectional(positionsPlayer.endTimeProperty(), new NumberStringConverter());

        slider.maxProperty().bind(positionsPlayer.endTimeProperty());
        slider.valueProperty().addListener(((observable, oldValue, newValue) -> {
            positionsPlayer.setCurrentTime(newValue.intValue());
        }));

        buttonPlay.disableProperty().bind(dataIsNull.or(positionsPlayer.playingProperty()));
        buttonPause.disableProperty().bind(dataIsNull.or(positionsPlayer.playingProperty().not()));

        slider.disableProperty().bind(dataIsNull);

        dataIsNull.addListener(((observable, oldValue, newValue) -> {
            System.out.println(newValue.toString());
        }));

        Image imageBackground = new Image(Main.class.getResourceAsStream("/images/Minimap.png"), 512.0, 512.0, false, true);
        paneMinimap.setBackground(new Background(new BackgroundImage(imageBackground,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        for (int i = 0; i < Statics.NUM_PLAYERS; i++) {
            circlePlayers[i] = new Circle(Statics.MARKER_RADIUS, Statics.PLAYER_COLOURS[i]);
            circlePlayers[i].visibleProperty().bind(cboxPlayers[i].selectedProperty());

//            circlePlayers[i].setStroke();
            circlePlayers[i].centerXProperty().bind(positionsPlayer.getxCoord()[i]);
            circlePlayers[i].centerYProperty().bind(positionsPlayer.getyCoord()[i]);
            paneMinimap.getChildren().add(circlePlayers[i]);
        }
    }

    public ReplayData getReplayData() {
        return replayData;
    }

    public void setReplayData(ReplayData replayData) {
        this.replayData = replayData;
        dataIsNull.set(false);

        for (int i = 0; i < Statics.NUM_PLAYERS; i++) {
            Hero hero = Hero.get(replayData.getHeroId()[i]);
            cboxPlayers[i].setText(hero.getPublicName() + "(" + replayData.getPlayerNames()[i] + ")");
            cboxPlayers[i].setSelected(true);
        }

        positionsPlayer.setReplayData(replayData);
    }

    public void clickPlay(ActionEvent actionEvent) {
        positionsPlayer.setPlaying(true);
    }

    public void clickPause(ActionEvent actionEvent) {
        positionsPlayer.setPlaying(false);
    }
}
