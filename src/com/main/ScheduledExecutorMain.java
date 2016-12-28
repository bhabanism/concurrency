package com.main;

import com.tasks.MonitorFolderTask;
import com.tasks.NotePadTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mishra on 28-12-2016.
 */
public class ScheduledExecutorMain {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService notepadExecutorService = Executors.newSingleThreadExecutor();
        NotePadTask notePadTask = new NotePadTask();
        notepadExecutorService.execute(notePadTask);


        ScheduledExecutorService monitorScheduledExecutorService = Executors.newScheduledThreadPool(1);
        MonitorFolderTask monitorFolderTask = new MonitorFolderTask();
        monitorScheduledExecutorService.scheduleAtFixedRate(monitorFolderTask, 500 ,2000, TimeUnit.MILLISECONDS);

        while(!notepadExecutorService.isShutdown() || !monitorScheduledExecutorService.isShutdown()) {
            Thread.sleep(3000);
            if(MonitorFolderTask.noDiskSpace || !notePadTask.process.isAlive())  {
                notepadExecutorService.shutdown();
                monitorScheduledExecutorService.shutdown();
            }
        }

        System.out.println("Main Thread ends");
    }
}
