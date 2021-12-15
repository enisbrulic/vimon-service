package io.arrowhacks.vimon.control;

import io.arrowhacks.vimon.entity.Job;
import io.arrowhacks.vimon.entity.Pipeline;
import io.arrowhacks.vimon.entity.Project;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class VimonService {

	@Inject
	GitLabConfig gitLabConfig;

	@RestClient
	GitLabClient gitLabClient;

	public Set<Project> getAllJobs() {
		Set<Project> projects = new HashSet<>();

		String[] projectIds = gitLabConfig.projectIds().split(",");

		Arrays.stream(projectIds).forEach(projectId -> {
			final Project project = gitLabClient.getProject(projectId).await().indefinitely();
			final Set<Pipeline> schedules = gitLabClient.getPipelines(projectId, "schedule", 1).await().indefinitely();

			schedules.forEach(pipeline -> {
				Set<Job> jobs = gitLabClient.getScheduleJobs(projectId, pipeline.id).await().indefinitely();
				project.jobs = jobs;
				projects.add(project);
			});
		});

		return projects;
	}

}
