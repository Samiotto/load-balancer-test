import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static int MAX_QUEUE_SIZE = 10;
    public static int MIN_QUEUE_SIZE = 1;
    public static int NUM_WORKERS = 5;

    public static void main(String[] args) {

        int nextID = 1;
        WorkerNode[] workers = new WorkerNode[NUM_WORKERS];
        Map<Integer, Integer> idToQueue = new HashMap<>();
        int[] activeIDs = new int[NUM_WORKERS];
        Queue[] queues = new Queue[NUM_WORKERS];

        for (int i = 0; i < NUM_WORKERS; i++) {
            workers[i] = new WorkerNode(nextID++);
            activeIDs[i] = workers[i].getLocalID();
        }

        for (int i = 0; i < NUM_WORKERS; i++) {
            queues[i] = new PriorityQueue(MAX_QUEUE_SIZE);
            idToQueue.put(workers[i].getLocalID(), i);
        }

        // dummy code, each number represents wait times to simulate task length
        int[] backlog = new int[100];
        Random generator = new Random(8675309);
        for (int i = 0; i < backlog.length; i++) {
            backlog[i] = generator.nextInt(1, 11); // pick num 1 through 10
        }



        // fill queues, prioritize filling queue before moving to next worker
        int idNum = activeIDs[0];
        for (int i = 0; i < backlog.length; i++) {
            if (queues[idToQueue.get(idNum)].size() < MIN_QUEUE_SIZE) {
                queues[idToQueue.get(idNum)].add(backlog[i]);
            }
        }
    }
}