package com.bayzdelivery.jobs;
import com.bayzdelivery.model.Delivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

@Component
public class DelayedDeliveryNotifier {

    private static final Logger LOG = LoggerFactory.getLogger(DelayedDeliveryNotifier.class);

    /**
     *  Use this method for the TASK 3
     */
    @Scheduled(fixedDelay = 30000)
    public void checkDelayedDeliveries(Delivery delivery) {

        //To find out the delayed delivery time of 45 minutes
        if(delivery.getStartTime()!=null&&delivery.getEndTime()!=null) {
            long minutes = ChronoUnit.MINUTES.between(delivery.getStartTime().toInstant(), delivery.getEndTime().toInstant());
            long hours = ChronoUnit.HOURS.between(delivery.getStartTime().toInstant(), delivery.getEndTime().toInstant());

            System.out.println("================Delivery Time in minutes and hours"+"===="+minutes+"====="+hours);

            if(hours>0.75||minutes>45)
            {
                notifyCustomerSupport();
            }

        }
    }


    /**
     * This method should be called to notify customer support team
     * It just writes notification on console but it may be email or push notification in real.
     * So that this method should run in an async way.
     */
    @Async
    public void notifyCustomerSupport() {
        LOG.info("Customer support team is notified!");
    }
}
