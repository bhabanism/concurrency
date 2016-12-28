package com.tasks;

/**
 * Created by Mishra on 28-12-2016.
 */
public class NotePadTask implements Runnable {
    //Thread monitorFolderThread;
    /*public com.tasks.NotePadTask(Thread monitorFolderThread) {
        this.monitorFolderThread = monitorFolderThread;
    }*/
    public Process process;

    @Override
    public void run() {
        try {
            process = Runtime.getRuntime().exec("E:\\Workspace\\Concurrency\\batch\\run.bat");
            System.out.println("Notepad launched");
            while(process.isAlive() && !MonitorFolderTask.noDiskSpace) {
                Thread.sleep(3000);
            }
            Process p2 = Runtime.getRuntime().exec("taskkill /IM notepad.exe");
            System.out.println("Waiting for notepad to exit...");
            p2.waitFor();
            //shut down?
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
