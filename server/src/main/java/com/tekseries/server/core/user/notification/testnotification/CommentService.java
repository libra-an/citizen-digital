//package com.tekseries.server.core.user.notification.testnotification;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CommentService {
//
//    @Autowired
//    private ApplicationEventPublisher eventPublisher;
//
//    public void createComment(String postId, String commenterId, String postOwnerId, String content) {
//        System.out.println("Comment created: " + content);
//
//        CommentCreatedEvent event = new CommentCreatedEvent(this, postId, commenterId, postOwnerId, content);
//        eventPublisher.publishEvent(event);
//    }
//}
//
