package io.redspark.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHandler {

	private final String secret;
	private final UserDetailsService userService;

	public TokenHandler(String secret, UserDetailsService userService) {
		this.secret = secret;
		this.userService = userService;
	}

	public User parseUserFromToken(String token) {
		String username = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
		return (User) userService.loadUserByUsername(username);
	}

	public String createTokenForUser(User user) {
		return Jwts.builder().setSubject(user.getUsername()).signWith(SignatureAlgorithm.HS256, secret).compact();
	}
}
