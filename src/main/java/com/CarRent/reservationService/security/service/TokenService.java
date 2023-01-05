package com.CarRent.reservationService.security.service;

import io.jsonwebtoken.Claims;

public interface TokenService {

    String generate(Claims claims);

    Claims parseToken(String jwt);

    Long parseId(String jwt);
}
