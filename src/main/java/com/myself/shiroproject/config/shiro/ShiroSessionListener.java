package com.myself.shiroproject.config.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author MrBird
 * 配置session监听器
 */

public class ShiroSessionListener implements SessionListener {

	private final AtomicInteger sessionCount = new AtomicInteger(0);

	/**
	 * 创建会话时触发
	 * @param session
	 */
	@Override
	public void onStart(Session session) {
		sessionCount.incrementAndGet();
	}

	/**
	 * 退出会话是触发
	 * @param session
	 */
	@Override
	public void onStop(Session session) {
		sessionCount.decrementAndGet();
	}

	/**
	 * 会话过期时触发
	 * @param session
	 */
	@Override
	public void onExpiration(Session session) {
		sessionCount.decrementAndGet();
	}

	/**
	 * 获取在线人数
	 * @return
	 */
	public AtomicInteger getSessionCount(){
		return sessionCount;
	}

}
