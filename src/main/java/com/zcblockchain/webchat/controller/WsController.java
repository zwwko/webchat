package com.zcblockchain.webchat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * web服务映射
 *
 * @author zhouww
 * @version 1.2
 * @Copyright 2011-2018 zcblockchain
 */
@Controller
public class WsController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg) {
        if (principal.getName().equals("sang")) {
            messagingTemplate.convertAndSendToUser("lenve", "/queue/notifications", principal.getName() + "给您发来了消息：" + msg);
        } else {
            messagingTemplate.convertAndSendToUser("sang", "/queue/notifications", principal.getName() + "给您发来了消息：" + msg);
        }
    }
}
