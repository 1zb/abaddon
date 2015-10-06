package cn.bzhang.dota2.abaddon.parser;

import cn.bzhang.dota2.abaddon.controller.MainController;
import javafx.concurrent.Task;
import org.controlsfx.dialog.ProgressDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import skadistats.clarity.processor.runner.ControllableRunner;
import skadistats.clarity.processor.runner.SimpleRunner;
import skadistats.clarity.source.MappedFileSource;
import skadistats.clarity.source.Source;

import java.io.File;
import java.io.IOException;


public class ReplayLoader {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private File replayFile;

    private ControllableRunner runner;

    private ReplayData replayData;

    private Source source;

    public ReplayLoader(MainController mainController, File replayFile) {
//        this.replayFile = replayFile;

        try {
            source = new MappedFileSource(replayFile);
            runner = new ControllableRunner(source);

            replayData = new ReplayData();
            runner.runWith(this, replayData);

//            while (!runner.isAtEnd()) {
//                runner.tick();
//                log.info("{}/{}", runner.getTick(), runner.getLastTick());
//            }
            Task<Void> taskProgress = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    while (!runner.isAtEnd()) {
                        int tick = runner.getTick();
                        int lastTick = runner.getLastTick();

                        updateProgress(tick, lastTick);
                        updateMessage("Progress: " + tick * 100 / lastTick + "%");

                        runner.tick();
                    }
                    return null;
                }

                @Override
                protected void succeeded() {
                    mainController.setReplayData(replayData);
                }
            };

            ProgressDialog dlg = new ProgressDialog(taskProgress);
            new Thread(taskProgress).start();

        } catch (IOException e) {
//            System.out.println(e);
        }

    }

    public ReplayData getReplayData() {
        return replayData;
    }
}
