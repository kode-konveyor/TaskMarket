package com.kodekonveyor.market.project;

import java.util.Set;

import javax.annotation.Generated;

import lombok.Data;

@Generated("by zenta-tools")
@Data
public class ProjectDTO {
	private Long id;
	private Long budgetInCents;
	private Boolean isPublic;
	private String name;
	private String description;
	private String projectId;
	private String url;
	private Set<Long> pullRequest;
	private Set<Long> role;
	private Set<Long> milestone;
	
}
