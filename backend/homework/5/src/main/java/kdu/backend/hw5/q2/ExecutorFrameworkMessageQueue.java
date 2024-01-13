package kdu.backend.hw5.q2;

import kdu.backend.hw5.Logging;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorFrameworkMessageQueue {
    private final Queue<String> messages = new LinkedList<>();
    private final Logging log = new Logging();

    public static void main(String[] args) {
        ExecutorFrameworkMessageQueue messageQueue = new ExecutorFrameworkMessageQueue();

        ExecutorService senderExecutor = Executors.newFixedThreadPool(3);
        ExecutorService receiverExecutor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            senderExecutor.execute(new ExecutorFrameworkMessageSender(messageQueue));
            receiverExecutor.execute(new ExecutorFrameworkMessageReceiver(messageQueue));
        }

        // Shutdown the executor services when done
        senderExecutor.shutdown();
        receiverExecutor.shutdown();
    }

    public synchronized void receiveMessage() {
        while (messages.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.logError("Thread Interrupted");
            }
        }
        notifyAll();
        String msg = "Message received by: " + Thread.currentThread().getName() + " -->\t" + messages.poll();
        log.logInfo(msg);
    }

    public synchronized void sendMessage(String msg) {
        messages.add(msg);
        log.logInfo("Message sent by " + Thread.currentThread().getName());
        notifyAll();
    }
}

