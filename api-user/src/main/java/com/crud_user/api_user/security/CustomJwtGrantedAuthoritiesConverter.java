package com.crud_user.api_user.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

        @Override
        public Collection<GrantedAuthority> convert(Jwt jwt) {
            // Sua lógica para extrair authorities de acordo com seu token JWT
            // Exemplo:
            return jwt.getClaimAsStringList("roles").stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
    }


