package com.tasks;

import java.io.File;
import java.util.concurrent.ExecutorService;

/**
 * Created by Mishra on 28-12-2016.
 */
public class MonitorFolderTask implements Runnable {
    public static boolean noDiskSpace = false;
    long diskSpaceLimit = 10;
    @Override
    public void run() {
        long currentDiskSpace = getFolderSize(new File("E:\\Workspace\\Concurrency\\monitorFolder"))/(1024*1024);
        System.out.println("Current Disk Space " + currentDiskSpace);
        if(diskSpaceLimit < currentDiskSpace) {
            noDiskSpace = true;
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
