package com.example.schedule1app.gobal.config;

import com.example.schedule1app.gobal.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 스프링 설정 클래스
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilter() {

        FilterRegistrationBean<LoginFilter> bean = new FilterRegistrationBean<>();

        bean.setFilter(new LoginFilter());  // 우리가 만든 LoginFilter 등록
        bean.addUrlPatterns("/*");          // 모든 URL에 적용 (화이트리스트는 Filter 내부에서 처리)
        bean.setOrder(1);                   // 필터 순서 (여러 필터 있을 때 실행 순서)

        return bean;
    }
}