package com.tasks;

import java.io.File;
import java.util.concurrent.ExecutorService;

/**
 * Created by Mishra on 28-12-2016.
 */
public class MonitorFolderTask_Old implements Runnable {
    public static boolean noDiskSpace = false;
    long diskSpaceLimit = 10;
    Thread notePadThread;
    ExecutorService executorService;

    public MonitorFolderTask_Old(Thread notePadThread) {
        this.notePadThread = notePadThread;
    }

    public MonitorFolderTask_Old(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void run() {
        while(!noDiskSpace && isNotepadOpen()) {
            long currentDiskSpace = getFolderSize(new File("E:\\Workspace\\Concurrency\\monitorFolder"))/(1024*1024);
            System.out.println("Current Disk Space " + currentDiskSpace);
            if(diskSpaceLimit < currentDiskSpace) {
                noDiskSpace = true;
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isNotepadOpen() {
        if(notePadThread!=null) {
            return notePadThread.isAlive();
        } else if(executorService!=null) {
            return !executorService.isShutdown();
        } else {
            return false;
        }

    }

    public static long getFolderSize(File dir) {
        long size = 0;
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                // System.out.println(file.getName() + " " + file.length());
                size += file.length();
            } else
                size += getFolderSize(file);
        }
        return size;
    }
}
