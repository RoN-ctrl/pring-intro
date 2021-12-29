package com.learn;

import com.learn.config.AppConfig;
import com.learn.facade.BookingFacade;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BookingFacade bookingFacade = context.getBean(BookingFacade.class);

        bookingFacade.createUser("John", "jo");
        System.out.println(bookingFacade.getUserById(1L));
    }
}
