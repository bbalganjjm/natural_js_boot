package common.interceptor;

import java.lang.invoke.MethodHandles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */
public class PermissionCheckInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public String[] excludeURLs;

    public String[] getExcludeURLs() {
        return excludeURLs;
    }

    public void setExcludeURLs(String[] excludeURLs) {
        this.excludeURLs = excludeURLs;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (excludeURLs != null) {
            for (int i = 0; i < getExcludeURLs().length; i++) {
                AntPathMatcher m = new AntPathMatcher();
                if (m.match(getExcludeURLs()[i], request.getRequestURI())) {
                    return true;
                }
            }
        }

        // JSON 요청일때 RequestBody의 값을 얻어오기 위해 request.getInputStream()하여 JSON 문자열을 Map으로 변환하면
        // Controller에서 Map으로 받을 수 없음. 스트림을 이미 소비 했기 때문에  Stream closed 에러가 발생 함.
        // JSON이나 일반 웹 요청에서 권한 체크를 처리하기 위한 파라미터는 HTTP GET 메서드로 전달하고 받기 바람.
        logger.debug("TODO 세션 및 권한체크 구현 바람");

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

}