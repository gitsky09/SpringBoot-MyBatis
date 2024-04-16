package com.deploy.demo.security;

import java.io.Serializable;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtil{
	
	private static final long EXPIRATION_TIME = 5 * 60 * 1000;
	
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	private static final Key key = MacProvider.generateKey();
	
	/**
     * [簽發JWT]
     */
    public String generateToken(HttpServletResponse response, Authentication auth) {
    	JwtBuilder jwt = Jwts.builder();
    	//header,payload,sign
    	jwt.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(key);
    	//header
    	jwt
    	.setHeaderParam("alg", "HS256")
    	.setHeaderParam("typ", "JWT")
    	
    	//payload
    	.claim("nickname", "Zac")
    	.claim("avatar", "1.jpg")
    	.claim("role","admin")
    	
    	//default
    	.claim("sub","subject")
    	.claim("iss", "org");
    	
    	return jwt.compact();
    }

    /**
     * [驗證JWT]
     */
    public Authentication validateToken(HttpServletRequest request) throws AuthException {
    	// 從request的header拿回token
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // 解析 Token
            try {
                Claims claims = Jwts.parser()
                        // 驗證
                        .setSigningKey(key)
                        // 去掉 Bearer
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();

                // 拿用户名
                String account = claims.getSubject();
                // 得到權限
                List<GrantedAuthority> authorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList((String) claims.get("authorize"));
                return StringUtils.hasText(account)
                        ? new UsernamePasswordAuthenticationToken(account, null, authorities) : null;
            } catch (JwtException e) {
                log.error("JWT 驗證發生錯誤", e);
            }
        }
        return null;
    }

}
