package com.main;

import com.tasks.MonitorFolderTask_Old;
import com.tasks.NotePadTask;

/**
 * Created by Mishra on 28-12-2016.
 */
public class BaseThreadMain {
    public static void main(String[] args) throws InterruptedException {
        /*try {
            Process p = Runtime.getRuntime().exec("E:\\Workspace\\Concurrency\\batch\\run.bat");
            System.out.println("Waiting for notepad to exit...");
            System.out.println("Exited with code " + p.waitFor());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        Thread notePadThread = new Thread(new NotePadTask());
        notePadThread.start();

        Thread monitorFolderThread = new Thread(new MonitorFolderTask_Old(notePadThread));
        monitorFolderThread.start();

        while(notePadThread.isAlive()) {
            Thread.sleep(3000);
        }
        System.out.println("Main Thread ends");
    }
}
