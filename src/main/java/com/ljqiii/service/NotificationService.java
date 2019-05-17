package com.ljqiii.service;

import com.ljqiii.dao.NotificationRepository;
import com.ljqiii.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationSevice {
    @Autowired
    NotificationRepository notificationRepository;

    public boolean insertSystemNotification(String toopenid, String msg) {
        return insertNotification("0", toopenid, msg);
    }

    public boolean insertNotification(String fromopenid, String toopenid, String msg) {
        Notification notification = new Notification();
        notification.setFromopenid(fromopenid);
        notification.setToopenid(toopenid);
        notification.setMsg(msg);

        notificationRepository.insertByNotification(notification);
        return true;
    }
}
