package io.arrowhacks.vimon.control;

import io.arrowhacks.vimon.entity.Job;
import io.arrowhacks.vimon.entity.Project;
import io.arrowhacks.vimon.entity.SchedulePipelines;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ApplicationScoped
public class VimonService {

	@Inject
	GitLabConfig gitLabConfig;

	@RestClient
	GitLabClient gitLabClient;

	public Set<Project> getAllJobs() {
		Set<Project> projects = new HashSet<>();

		final Map<String, String> gitlabProjectMap = gitLabConfig.projects();
		final Set<Map.Entry<String, String>> projectScheduleEntrySet = gitlabProjectMap.entrySet();

		projectScheduleEntrySet.forEach(entry -> {
			final String projectId = entry.getKey();
			final String scheduleId = entry.getValue();

			final Project project = gitLabClient.getProject(projectId).await().indefinitely();
			final SchedulePipelines schedulePipeline = gitLabClient.getSchedulePipelineDetails(projectId, scheduleId).await().indefinitely();

			final String lastSchedulePipelineId = schedulePipeline.last_pipeline.id;
			Set<Job> jobs = gitLabClient.getScheduleJobs(projectId, lastSchedulePipelineId).await().indefinitely();
			project.jobs = jobs;
			projects.add(project);
		});

		return projects;
	}
}
