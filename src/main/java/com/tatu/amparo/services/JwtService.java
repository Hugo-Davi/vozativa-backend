package com.tatu.amparo.services;

import org.springframework.stereotype.Component;

@Component
public class JwtService {

//    @Value("${jwt.public.key}")
//    private RSAPublicKey publicKey;
//    @Value("${jwt.private.key}")
//    private RSAPrivateKey privateKey;
//
//    public JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder.withPublicKey(publicKey).build();
//    }
//
//    public JwtEncoder jwtEncoder() {
//        JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(privateKey).build();
//        var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
//        return new NimbusJwtEncoder(jwks);
//    }
//    public static final String SECRET = "AbrnosrmBF9Kka5LGLs3GcHjOKqWi1g0lYlk32YEp7K5W9tZqr2x3Xlz";
//
//    public String generateToken(String userName){
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, userName);
//    }
//
//    private String createToken(Map<String, Object> claims, String userName) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(userName)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
//                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
//    }
//
//    private Key getSignKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }

}