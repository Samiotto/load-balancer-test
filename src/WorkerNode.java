import org.w3c.dom.Node;

import java.util.LinkedList;
import java.util.Queue;

public class WorkerNode extends Thread {
    private String ip;
    private int port;
    private int id = -1;
    private static int SLEEP_MULT = 100;
    private static Queue<Integer> queue = new LinkedList<Integer>();

    public WorkerNode(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public WorkerNode(int id) {
        ip = "127.0.0.1";
        this.port = 8080;
        this.id = id;
    }

    public void run() {
        while (true) {
            if (queue.size() == 0) {
                Thread.yield();
            } else {
                try {
>
                    doWork(queue.poll());
                } catch (InterruptedException e) {
                    System.err.println("Worker ( " + getLocalID() + " ) interrupted");
                    System.err.println(e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public int getLocalID() {
        return id;
    }

    public void addToQueue(int num) {
        queue.add(num);
    }

    public void removeHeadFromQueue() {
        queue.remove();
    }



    public boolean doWork(int time) throws InterruptedException {
        if (time < 0) time = 0;
        // wait specified time
        Thread.sleep(time * SLEEP_MULT);
        return true;
    }
}
