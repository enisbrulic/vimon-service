package io.arrowhacks.vimon.boundary;

import io.arrowhacks.vimon.control.GitLabConfig;
import io.arrowhacks.vimon.control.VimonService;
import io.arrowhacks.vimon.entity.Project;
import io.smallrye.mutiny.Multi;
import org.jboss.resteasy.reactive.RestSseElementType;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.util.Set;

@Path("/api")
public class VimonResource {

    @Inject
    VimonService vimonService;

    @Inject
    GitLabConfig gitLabConfig;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestSseElementType(MediaType.APPLICATION_JSON)
    @Path("/jobs")
    public Multi<Set<Project>> getJobsByProject() {
        return Multi.createFrom().ticks().every(Duration.ofMinutes(gitLabConfig.pullIntervalMinutes()))
                .onItem().transform(n -> vimonService.getAllJobs())
                .map(jobs -> jobs);
    }

}
