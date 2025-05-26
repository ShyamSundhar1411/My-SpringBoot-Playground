package com.axionlabs.arkive.config;


import com.axionlabs.arkive.dto.ErrorResponseDto;
import com.axionlabs.arkive.service.impl.IJWTService;
import com.axionlabs.arkive.service.impl.IUserService;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
@Component
@RequiredArgsConstructor
public class JWTAuthenticatIonFilter extends OncePerRequestFilter {
    private final IJWTService ijwtService;
    private final IUserService iUserService;
    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request - HttpRequest
     * @param response - HttpResponse
     * @param filterChain - FilterChain
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userName;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        jwtToken = authHeader.substring(7);
        try{
            userName = ijwtService.extractUserName(jwtToken,true);
            if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = iUserService.loadUserByUsername(userName);
                if (ijwtService.isTokenValid(jwtToken, userDetails, true)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request,response);
        } catch (SignatureException e){
            String path = request.getRequestURI().substring(request.getContextPath().length());
            ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                    path,
                    HttpStatus.UNAUTHORIZED,
                    "Invalid or expired token",
                    LocalDateTime.now()
            );
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write(errorResponseDto.toJson());
        }
    }
}
