package kdu.backend.hw5.q2;

public class ExecutorFrameworkMessageReceiver implements Runnable{
    ExecutorFrameworkMessageQueue messageQueue;
    public ExecutorFrameworkMessageReceiver(ExecutorFrameworkMessageQueue messageQueue){
        this.messageQueue = messageQueue;
    }
    @Override
    public void run() {
        messageQueue.receiveMessage();
    }
}

