package io.arrowhacks.vimon.entity;

import java.util.Set;

public class SchedulePipelines {

	public String id;
	public String description;
	public String ref;
	public Pipeline last_pipeline;
	public Set<Variable> variables;

	@Override
	public String toString() {
		return "SchedulePipelines{" +
			"id='" + id + '\'' +
			", description='" + description + '\'' +
			", ref='" + ref + '\'' +
			", last_pipeline=" + last_pipeline +
			", variables=" + variables +
			'}';
	}
}
