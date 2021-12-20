package io.arrowhacks.vimon.control;

import io.smallrye.config.ConfigMapping;

import java.util.Map;

@ConfigMapping(prefix = "gitlab")
public interface GitLabConfig {

	Map<String, String> projects();

	String accessToken();

	int pullIntervalMinutes();
}