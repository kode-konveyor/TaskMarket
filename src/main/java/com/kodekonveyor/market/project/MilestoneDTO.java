package com.kodekonveyor.market.project;

import java.util.Set;

import javax.annotation.Generated;

import lombok.Data;

@Generated("by zenta-tools")
@Data
public class MilestoneDTO {
	private Long id;
	private Boolean isActive;
	private String name;
	private Long order;
	private Set<Long> task;
	
}
