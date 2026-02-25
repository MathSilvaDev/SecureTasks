package com.msilva.secureList.security.jwt;

import com.msilva.secureList.security.authentication.CustomUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    private static final long EXPIRES_AT = 300L;

    private final JwtEncoder jwtEncoder;

    public JwtService(JwtEncoder jwtEncoder){
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(CustomUserDetails customUserDetails){

        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("BACKEND_JAVA(MatheusSilva)")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(EXPIRES_AT))
                .subject(customUserDetails.getId().toString())
                .claim("roles",
                        customUserDetails.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .toList()
                )
                .build();

        return jwtEncoder
                .encode(JwtEncoderParameters.from(claimsSet))
                .getTokenValue();
    }
}
