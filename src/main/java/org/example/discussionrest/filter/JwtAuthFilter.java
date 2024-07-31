package org.example.discussionrest.filter;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.example.discussionrest.entity.User;
import org.example.discussionrest.service.JwtService;
import org.example.discussionrest.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authHeader = getAuthHeader(request);
        if (isAuthHeaderInvalid(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = getJwt(authHeader);
        String email = jwtService.extractEmail(jwt);

        if (needAuthentication(email)) {
            User user = userService.findByEmail(email);
            if (jwtService.isTokenValid(jwt, user)) {
                applyAuthentication(request, user);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getAuthHeader(HttpServletRequest request) {
        return request.getHeader(HEADER_NAME);
    }

    private boolean isAuthHeaderInvalid(String authHeader) {
        return StringUtils.isEmpty(authHeader) || !authHeader.startsWith(BEARER_PREFIX);
    }

    private String getJwt(String authHeader) {
        return authHeader.substring(BEARER_PREFIX.length());
    }

    private boolean needAuthentication(String email) {
        return isEmailValid(email) && SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private boolean isEmailValid(String email) {
        return StringUtils.isNotEmpty(email);
    }

    private void applyAuthentication(HttpServletRequest request, User user) {
        Collection<? extends GrantedAuthority> authorities = getAuthorities(user);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return Collections.singletonList(() -> user.getRole().name());
    }
}
