package com.app.store.dto;

import lombok.*;
import org.springframework.security.core.Authentication;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthResp {
	private String message;
	private String jwt;
	private Authentication authenticatedDetails;
}
