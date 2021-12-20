package io.arrowhacks.vimon.entity;

public class Pipeline {

	public String id;
	public String project_id;
	public String ref;
	public String status;
	public String source;
	public String web_url;

	@Override
	public String toString() {
		return "Pipeline{" +
			"id='" + id + '\'' +
			", project_id='" + project_id + '\'' +
			", ref='" + ref + '\'' +
			", status='" + status + '\'' +
			", source='" + source + '\'' +
			", web_url='" + web_url + '\'' +
			'}';
	}
}
