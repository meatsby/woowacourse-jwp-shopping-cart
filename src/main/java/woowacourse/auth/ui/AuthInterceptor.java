package woowacourse.auth.ui;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import woowacourse.auth.support.AuthorizationExtractor;
import woowacourse.auth.support.JwtTokenProvider;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthInterceptor(final JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) {
        if (isPreflight(request)) {
            return true;
        }
        String accessToken = AuthorizationExtractor.extract(Objects.requireNonNull(request));
        jwtTokenProvider.validateToken(accessToken);
        return true;
    }

    private boolean isPreflight(final HttpServletRequest request) {
        return HttpMethod.OPTIONS.matches(request.getMethod());
    }
}
