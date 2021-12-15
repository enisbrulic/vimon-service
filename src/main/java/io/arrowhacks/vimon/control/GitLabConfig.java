package io.arrowhacks.vimon.control;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "gitlab")
public interface GitLabConfig {

    String projectIds();

    String accessToken();

    int pullIntervalMinutes();
}