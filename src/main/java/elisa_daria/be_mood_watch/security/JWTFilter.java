package elisa_daria.be_mood_watch.security;

import elisa_daria.be_mood_watch.entities.User;
import elisa_daria.be_mood_watch.exceptions.UnAuthorizedEx;
import elisa_daria.be_mood_watch.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerForAuth = request.getHeader("Authorization");
        if (headerForAuth == null || !headerForAuth.startsWith("Bearer "))
            throw new UnAuthorizedEx("Authorization header is missing a token");
        String accessToken = headerForAuth.substring(7);
        jwtTools.verifyToken(accessToken);
        String id=jwtTools.extractIdFromTokenSignature(accessToken);
        User currentU= userService.findById(Long.valueOf(id));
        Authentication authentication=new UsernamePasswordAuthenticationToken(currentU,null,currentU.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication((authentication));
        filterChain.doFilter(request,response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
