package com.dc.kq.pinche.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器，拦截所有请求，实现对微信的认证
 * 
 * @author xiaogang
 *
 */
public class WeXinInterceptor implements HandlerInterceptor {
	// 重写 preHandle()方法，在业务处理器处理请求之前对该请求进行拦截处理
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("拦截器   -------前-------------");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView mav)
			throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception excptn)
			throws Exception {
	}

}