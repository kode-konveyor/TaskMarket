
package com.kodekonveyor.market.project;

import java.util.Set;

import javax.annotation.Generated;

import lombok.Data;

@Generated("by zenta-tools")
@Data
public class ProjectModelDTO {
	private Long id;
	private Set<Long> milestone;
	private Set<Long> task;
	
}
