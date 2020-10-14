package dev.pa1007.game.draw;


import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.text.Text;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StopwatchTimer extends Thread {

    private final SimpleDateFormat     sdf = new SimpleDateFormat("mm:ss:S");
    private       SimpleStringProperty min, sec, millis, sspTime;
    private long    time;
    private Text    clock;
    private boolean stop;

    public StopwatchTimer() {
        min = new SimpleStringProperty("00");
        sec = new SimpleStringProperty("00");
        millis = new SimpleStringProperty("00");
        sspTime = new SimpleStringProperty("00:00:00");
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                setTime(time);
                Thread.sleep(10);
                if (stop) {
                    interrupt();
                }
                time = time + 10;
            }
        }
        catch (Exception e) {

        }
    }

    public void startTimer(long time) {
        this.time = time;
        setPriority(Thread.MIN_PRIORITY);
        start();
    }

    public void stopTimer(long time) {
        this.stop = true;
        this.time = time;
        setTime(time);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
        String[] split = sdf.format(new Date(time)).split(":");
        min.set(split[0]);
        sec.set(split[1]);

        if (split[2].length() == 1) {
            split[2] = "0" + split[2];
        }
        millis.set(split[2].substring(0, 2));

        sspTime.set(min.get() + ":" + sec.get() + ":" + millis.get());
        Platform.runLater((() -> clock.setText(sspTime.getValue())));
    }

    public SimpleStringProperty getSspTime() {
        return sspTime;
    }

    public void setText(Text clock) {
        this.clock = clock;
        Platform.runLater((() -> clock.setText(sspTime.getValue())));
    }
}
