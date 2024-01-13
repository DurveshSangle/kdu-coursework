package kdu.backend.hw5.q1;

public class MessageReceiver implements Runnable{
    MessageQueue messageQueue;
    public MessageReceiver(MessageQueue messageQueue){
        this.messageQueue = messageQueue;
    }
    @Override
    public void run() {
        messageQueue.receiveMessage();
    }
}
