
package com.kodekonveyor.authentication;

import java.util.Set;

import javax.annotation.Generated;

import lombok.Data;

@Generated("by zenta-tools")
@Data
public class UserDTO {
	private Long id;
	private String login;
	private Set<Long> role;
	
}
