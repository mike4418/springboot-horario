package com.mike.curso.springboot.calendar.interceptor.springboot_horario.interceptor;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CalendarInterceptor implements HandlerInterceptor {

    @Value("${config.calendar.open}")
    private Integer open;

    @Value("${config.calendar.close}")
    private Integer close;

    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        System.out.println("Hora: " + hour);

        if (hour >= open && hour <close){
            StringBuilder message = new StringBuilder("Welcome to the service customer schedule"); 
            message.append("We are open since: ");
            message.append(open);
            message.append(" hrs. ");
            message.append("until: ");
            message.append(close);
            message.append(" hrs. ");
            message.append(" Thanks for visiting us!");

            request.setAttribute("message", message.toString());
            
            return true;     
        } 

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<>();

        StringBuilder message = new StringBuilder("Closed! you are out of our open hours. "); 
        message.append("Please vist us since: ");
        message.append(open);
        message.append(" hrs. ");
        message.append("until: ");
        message.append(close);
        message.append(" hrs. ");
        message.append(" Thanks for visiting us!");

        map.put("Message", message.toString());
        map.put("date", new Date().toString());

        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(mapper.writeValueAsString(map));

        return false;
        

       
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    
    }



    
}
