package com.matty.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(prefix = {"f"})
public class RegisterRequest {
	private String fFirstName;
	private String fLastName;
	private String fEmail;
	private String fPassword;

}
