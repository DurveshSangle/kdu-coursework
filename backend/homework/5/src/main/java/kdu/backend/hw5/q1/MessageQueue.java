package kdu.backend.hw5.q1;

import kdu.backend.hw5.Logging;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private final Queue<String> messages = new LinkedList<>();
    private final Logging log = new Logging();

    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();

        Thread sender1 = new Thread(new MessageSender(messageQueue));
        sender1.setName("Sender One");
        Thread sender2 = new Thread(new MessageSender(messageQueue));
        sender2.setName("Sender Two");
        Thread sender3 = new Thread(new MessageSender(messageQueue));
        sender3.setName("Sender Three");

        Thread receiver1 = new Thread(new MessageReceiver(messageQueue));
        receiver1.setName("Receiver One");
        Thread receiver2 = new Thread(new MessageReceiver(messageQueue));
        receiver2.setName("Receiver Two");
        Thread receiver3 = new Thread(new MessageReceiver(messageQueue));
        receiver3.setName("Receiver Three");

        sender1.start();
        sender2.start();
        sender3.start();

        receiver1.start();
        receiver2.start();
        receiver3.start();
    }

    public synchronized void receiveMessage(){
        while(messages.isEmpty()){
            try{
                wait();
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
                log.logError("Thread Interrupted");
            }
        }
        notifyAll();
        String msg = "Message received by: "+Thread.currentThread().getName()+" -->\t"+messages.poll();
        log.logInfo(msg);
    }

    public synchronized void sendMessage(String msg){
        messages.add(msg);
        log.logInfo("Message sent by "+Thread.currentThread().getName());
        notifyAll();
    }
}




