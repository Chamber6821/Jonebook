package com.example.jonebook;

import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestCounter implements HandlerInterceptor {

  private AtomicLong count = new AtomicLong(0);

  public long getCount() { return count.get(); }

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler) {
    count.addAndGet(1);
    return true;
  }
}
