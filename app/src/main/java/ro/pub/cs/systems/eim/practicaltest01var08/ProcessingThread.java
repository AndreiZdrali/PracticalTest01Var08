package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;
import java.util.Random;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;
    private String answer;

    private Random random = new Random();

    public ProcessingThread(Context context, String answer) {
        this.context = context;
        this.answer = answer;
    }

    @Override
    public void run() {
        while (isRunning) {
            sendMessage();
            sleep10s();
        }
    }

    private void sendMessage() {
        Intent intent = new Intent();
        StringBuilder hintb = new StringBuilder();
        int randPos = random.nextInt(answer.length());
        for (int i = 0; i < answer.length(); i++) {
            if (i == randPos) {
                hintb.append(answer.charAt(i));
            } else {
                hintb.append("*");
            }
        }
        intent.putExtra("HINT", hintb.toString());
        context.sendBroadcast(intent);
    }

    private void sleep10s() {
        try { Thread.sleep(10000); }
        catch (InterruptedException e) { e.printStackTrace(); }
    }

    public void stopThread() { isRunning = false; }
}

