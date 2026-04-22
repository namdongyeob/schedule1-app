package com.example.schedule1app.gobal.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 서비스의 인증 여부를 확인하는 문지기 클래스입니다.
 * 모든 HTTP 요청이 컨트롤러에 도달하기 전에 이 필터를 먼저 거치게 됩니다.
 */
@Slf4j
public class LoginFilter implements Filter {

    /**
     * 로그인 없이도 접근할 수 있는 '화이트리스트' 경로 모음입니다.
     * 로그인을 처리하는 경로(/auth/login)나 회원가입(/users)은 인증 체크를 하면 안 되기 때문에 제외합니다.
     */
    private static final String[] WHITE_LIST = {"/auth/login", "/auth/logout", "/users"};

    /**
     * 필터의 핵심 로직이 실행되는 메서드입니다.
     *
     * @param request  사용자의 요청 정보 (ServletRequest)
     * @param response 서버의 응답 정보 (ServletResponse)
     * @param chain    다음 필터나 컨트롤러로 요청을 넘겨주는 연결 고리
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        log.info("인증 체크 필터 시작: {}", requestURI);

        // 2. 화이트리스트(인증 면제 구역) 확인 로직
        // 사용자가 요청한 주소가 로그인/회원가입 등 '누구나 들어올 수 있는 곳'인지 확인합니다.
        if (isWhiteList(requestURI)) {
            // 화이트리스트라면 chain.doFilter를 호출해 "통과!" 시켜줍니다.
            // 이 코드가 실행되면 다음 필터가 있으면 다음 필터로, 없으면 컨트롤러로 넘어갑니다.
            chain.doFilter(request, response);
            return; // 중요: 통과시켰으니 여기서 메서드를 종료합니다.
        }

        // 3. 세션 체크 (인증 확인 로직)
        // getSession(false)는 "세션이 이미 있으면 가져오고, 없으면 새로 만들지 말고 null을 줘"라는 뜻입니다.
        HttpSession session = httpRequest.getSession(false);

        // 세션 자체가 없거나, 세션은 있는데 우리가 저장한 "userId"라는 이름표(Attribute)가 없다면 미인증 사용자입니다.
        if (session == null || session.getAttribute("userId") == null) {
            log.info("미인증 사용자 요청 차단: {}", requestURI);

            // 401 Unauthorized 에러를 응답에 담아 보냅니다.
            // sendError를 호출하면 컨트롤러까지 요청이 가지 않고 여기서 바로 사용자에게 에러를 보냅니다.
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");
            return; // 중요: 차단했으므로 여기서 메서드를 종료합니다.
        }

        // 4. 인증 통과
        // 세션에 정보가 있는 확인된 사용자이므로 다음 단계로 보내줍니다.
        log.info("인증 성공: {}", requestURI);
        chain.doFilter(request, response);
    }

    /**
     * 현재 요청 주소가 화이트리스트에 포함되는지 확인하는 보조 메서드입니다.
     */
    private boolean isWhiteList(String requestURI) {
        // equals를 사용하여 정확히 일치하는지 확인합니다.
        return requestURI.equals("/auth/login") ||
                requestURI.equals("/auth/logout") ||
                requestURI.equals("/users");
    }
}