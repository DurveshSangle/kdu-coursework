package kdu.backend.hw5.q1;

public class MessageSender implements Runnable{
    MessageQueue messageQueue;

    public MessageSender(MessageQueue messageQueue){
        this.messageQueue = messageQueue;
    }
    @Override
    public void run() {
        String message = "This message is from "+ Thread.currentThread().getName();
        messageQueue.sendMessage(message);
    }

}
