package cn.bzhang.dota2.abaddon.controller;

import cn.bzhang.dota2.abaddon.Main;
import cn.bzhang.dota2.abaddon.parser.ReplayData;
import cn.bzhang.dota2.abaddon.parser.ReplayLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Tab positionsTab;

    ReplayLoader replayLoader;

    FXMLLoader positionsLoader;
    PositionsController positionsController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        positionsLoader = new FXMLLoader(Main.class.getResource("/fxml/positions.fxml"));

        try {
            positionsTab.setContent(positionsLoader.load());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        positionsController = positionsLoader.<PositionsController>getController();

//        try {
//            positionsTab.setContent(FXMLLoader.load(Main.class.getResource("/fxml/positions.fxml")));
//        } catch (IOException e) {
//            System.out.println("can not set positions tab: " + e.toString());
//        }
    }

    public void actionOpen(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load a replay");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Replay files", "*.dem"),
                new FileChooser.ExtensionFilter("All files", "*")
        );
        File file = fileChooser.showOpenDialog(Main.primaryStage);
        if (file == null) {
            System.out.println("cannot open this file");
            return;
        }
        try {
            replayLoader = new ReplayLoader(MainController.this, file);

//            positionsController.setReplayData(replayLoader.getReplayData());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setReplayData(ReplayData replayData) {
        positionsController.setReplayData(replayData);
    }
}
