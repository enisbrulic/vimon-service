package io.arrowhacks.vimon.control;

import io.arrowhacks.vimon.entity.Job;
import io.arrowhacks.vimon.entity.Pipeline;
import io.arrowhacks.vimon.entity.Project;
import io.arrowhacks.vimon.entity.SchedulePipelines;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.Set;

@Path("/projects")
@RegisterRestClient(configKey = "gitlab-api")
@RegisterClientHeaders(CustomRequestHeaderFactory.class)
public interface GitLabClient {

	/**
	 * curl GET --header "PRIVATE-TOKEN: ${GITLAB_READ_API}" https://gitlab.example.com/api/v4/projects/{PROJECT_ID} | jq
	 *
	 * @param projectId
	 * @return
	 */
	@GET
	@Path("/{projectId}")
	Uni<Project> getProject(String projectId);

	/**
	 * WARNING: request can take several seconds
	 * <p>
	 * curl GET --header "PRIVATE-TOKEN: ${GITLAB_READ_API}" https://gitlab.example.com/api/v4/projects/{PROJECT_ID}/jobs | jq
	 *
	 * @param projectId
	 * @param perPageCount
	 * @return
	 */
	@GET
	@Path("/{projectId}/jobs")
	Uni<Set<Job>> getAllJobsByProject(@PathParam("projectId") String projectId, @QueryParam("per_page") int perPageCount);

	/**
	 * curl GET --header "PRIVATE-TOKEN: ${GITLAB_READ_API}" https://gitlab.example.com/api/v4/projects/{PROJECT_ID}/pipelines/{PIPELINE_ID}/jobs | jq
	 *
	 * @param projectId
	 * @param pipelineId
	 * @return
	 */
	@GET
	@Path("/{projectId}/pipelines/{pipelineId}/jobs")
	Uni<Set<Job>> getScheduleJobs(@PathParam("projectId") String projectId, @PathParam("pipelineId") String pipelineId);

	/**
	 * curl GET --header "PRIVATE-TOKEN: ${GITLAB_READ_API}" https://gitlab.example.com/api/v4/projects/{PROJECT_ID}/pipelines\?source\=schedule&per_page\=1 | jq
	 *
	 * @param projectId
	 * @param source
	 * @param perPageCount
	 * @return
	 */
	@GET
	@Path("/{projectId}/pipelines")
	Uni<Set<Pipeline>> getPipelines(@PathParam("projectId") String projectId, @QueryParam("source") String source, @QueryParam("per_page") int perPageCount);

	/**
	 * curl --header "PRIVATE-TOKEN: ${GITLAB_READ_API}" https://gitlab.example.com/api/v4/projects/{PROJECT_ID}/pipeline_schedules/{SCHEDULE_ID}
	 *
	 * @param projectId
	 * @param scheduleId
	 */
	@GET
	@Path("/{projectId}/pipeline_schedules/{scheduleId}")
	Uni<SchedulePipelines> getSchedulePipelineDetails(@PathParam("projectId") String projectId, @PathParam("scheduleId") String scheduleId);
}
