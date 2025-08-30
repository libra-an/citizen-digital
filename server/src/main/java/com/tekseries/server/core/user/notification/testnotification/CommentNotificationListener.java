//package com.tekseries.server.core.user.notification.testnotification;
//
//
//import com.tekseries.server.core.user.notification.service.NotificationService;
//import com.tekseries.server.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CommentNotificationListener {
//
//    @Autowired
//    private NotificationService notificationService;
//
//    @EventListener
//    public void handleCommentEvent(CommentCreatedEvent event) {
//        User user = new User();
//        user.setId(event.getPostOwnerId());
//
//        notificationService.createNotification(
//                user,
//                "COMMENT",
//                "Có người bình luận: " + event.getCommentContent(),
//                event.getPostId()
//        );
//    }
//}
