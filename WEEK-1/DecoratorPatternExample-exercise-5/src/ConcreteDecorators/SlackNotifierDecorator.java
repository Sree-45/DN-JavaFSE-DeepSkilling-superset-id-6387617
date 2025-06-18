package ConcreteDecorators;

import AbstractDecorator.NotifierDecorator;
import TargetInterface.Notifier;

public class SlackNotifierDecorator extends NotifierDecorator {
    
    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }
    
    @Override
    public void send(String message) {
        super.send(message);
        sendSlack(message);
    }
    
    private void sendSlack(String message) {
        System.out.println("Slack Notification: " + message);
    }
}