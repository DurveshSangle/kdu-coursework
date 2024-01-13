package kdu.backend.hw5.q2;

public class ExecutorFrameworkMessageSender implements Runnable{
    ExecutorFrameworkMessageQueue messageQueue;

    public ExecutorFrameworkMessageSender(ExecutorFrameworkMessageQueue messageQueue){
        this.messageQueue = messageQueue;
    }
    @Override
    public void run() {
        String message = "This message is from "+ Thread.currentThread().getName();
        messageQueue.sendMessage(message);
    }
}
