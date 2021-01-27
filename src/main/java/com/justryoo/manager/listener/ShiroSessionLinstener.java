package com.justryoo.manager.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

public class ShiroSessionLinstener extends SessionListenerAdapter {
    public static int online = 0;
    @Override
    public void onStart(Session session) {
        super.onStart(session);
        System.out.println("session创建，sessionId：" + session.getId());
        online ++;
    }

    @Override
    public void onStop(Session session) {
        super.onStop(session);
        System.out.println("session停止，sessionId:" + session.getId() +"，用户user：" + session.getAttribute("user"));
    }

    @Override
    public void onExpiration(Session session) {
        super.onExpiration(session);
        System.out.println("session失效，sessionId:" + session.getId() +"，用户user：" + session.getAttribute("user"));
        online --;
    }
}
