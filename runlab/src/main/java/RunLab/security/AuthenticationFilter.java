package RunLab.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.filter.OncePerRequestFilter;

import RunLab.utility.jwtTokenUtil;

public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private jwtTokenUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String token = jwtUtil.resolveToken(req);

        try {
            if (token != null && jwtUtil.validateToken(token)) {
                Authentication auth = jwtUtil.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
            res.sendError(400, ex.getMessage()); // ex.getHttpStatus().value()
        }
        chain.doFilter(req, res);
    }
}
