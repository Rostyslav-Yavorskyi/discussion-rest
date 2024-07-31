package org.example.discussionrest.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.example.discussionrest.controller.GlobalExceptionHandler;
import org.example.discussionrest.dto.ExceptionDto;
import org.example.discussionrest.entity.User;
import org.example.discussionrest.exception.TokenExpiredException;
import org.example.discussionrest.service.JwtService;
import org.example.discussionrest.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    private final JwtService jwtService;
    private final UserService userService;
    private final GlobalExceptionHandler globalExceptionHandler;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = getAuthHeader(request);
            if (isAuthHeaderInvalid(authHeader)) {
                filterChain.doFilter(request, response);
                return;
            }
            String jwt = getJwt(authHeader);
            String email = jwtService.extractEmail(jwt);

            if (needAuthentication(email)) {
                User user = userService.findByEmail(email);
                if (jwtService.isTokenInvalid(jwt, user)) {
                    throw new TokenExpiredException();
                }
                applyAuthentication(request, user);
            }
            filterChain.doFilter(request, response);
        } catch (UsernameNotFoundException ex) {
            handleException(globalExceptionHandler::handleUsernameNotFoundException, ex, response);
        } catch (TokenExpiredException ex) {
            handleException(globalExceptionHandler::handleTokenExpiredException, ex, response);
        }
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

    private <T extends Exception> void handleException(Function<T, ExceptionDto> exceptionHandlerFunction, T exception, HttpServletResponse response) throws IOException {
        ExceptionDto exceptionDto = exceptionHandlerFunction.apply(exception);
        String json = new ObjectMapper().writeValueAsString(exceptionDto);
        response.setStatus(exceptionDto.getStatus());
        response.setHeader("Content-Type", "application/json");
        response.getWriter().write(json);
    }
}
