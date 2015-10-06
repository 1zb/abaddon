package cn.bzhang.dota2.abaddon.controller;

import cn.bzhang.dota2.abaddon.parser.ReplayData;
import cn.bzhang.dota2.abaddon.constants.Statics;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PositionsPlayer {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private ReplayData replayData;

    private BooleanProperty playing = new SimpleBooleanProperty(false);
    private BooleanProperty changing = new SimpleBooleanProperty(false);

    private IntegerProperty currentTime = new SimpleIntegerProperty(0);
    private IntegerProperty endTime = new SimpleIntegerProperty(0);

    private IntegerProperty xCoord[] = new IntegerProperty[Statics.NUM_PLAYERS];
    private IntegerProperty yCoord[] = new IntegerProperty[Statics.NUM_PLAYERS];

    public PositionsPlayer() {

        for (int i = 0; i < Statics.NUM_PLAYERS; i++) {
            xCoord[i] = new SimpleIntegerProperty(0);
            yCoord[i] = new SimpleIntegerProperty(0);
        }

        changing.addListener(((observable, oldValue, newValue) -> {
        }));

        playing.addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
            } else {
                timeline.stop();
            }
        }));
    }
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
        if (!changing.get()) {
            if (getCurrentTime() > getEndTime()) {
                setPlaying(false);
                return;
            }
            currentTime.set(currentTime.get() + 1);
            for (int i = 0; i < Statics.NUM_PLAYERS; i++) {
                xCoord[i].set(replayData.getHeroPositions()[i].get(getCurrentTime() + replayData.getGameStartTime())[0]);
                yCoord[i].set(replayData.getHeroPositions()[i].get(getCurrentTime() + replayData.getGameStartTime())[1]);
            }
        }
    }));

    public ReplayData getReplayData() {
        return replayData;
    }

    public void setReplayData(ReplayData replayData) {
        this.replayData = replayData;
        endTime.set(replayData.getGameEndTime() - replayData.getGameStartTime());
    }

    public boolean getPlaying() {
        return playing.get();
    }

    public BooleanProperty playingProperty() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing.set(playing);
    }

    public boolean getChanging() {
        return changing.get();
    }

    public BooleanProperty changingProperty() {
        return changing;
    }

    public void setChanging(boolean changing) {
        this.changing.set(changing);
    }

    public int getCurrentTime() {
        return currentTime.get();
    }

    public IntegerProperty currentTimeProperty() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime.set(currentTime);

        for (int i = 0; i < Statics.NUM_PLAYERS; i++) {
            xCoord[i].set(replayData.getHeroPositions()[i].get(getCurrentTime() + replayData.getGameStartTime())[0]);
            yCoord[i].set(replayData.getHeroPositions()[i].get(getCurrentTime() + replayData.getGameStartTime())[1]);
        }
    }

    public int getEndTime() {
        return endTime.get();
    }

    public IntegerProperty endTimeProperty() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime.set(endTime);
    }

    public IntegerProperty[] getxCoord() {
        return xCoord;
    }

    public IntegerProperty[] getyCoord() {
        return yCoord;
    }

}
