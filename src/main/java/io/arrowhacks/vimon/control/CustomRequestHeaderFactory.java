package io.arrowhacks.vimon.control;

import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

public class CustomRequestHeaderFactory implements ClientHeadersFactory {

	@Inject
	GitLabConfig gitLabConfig;

	@Override
	public MultivaluedMap<String, String> update(MultivaluedMap<String, String> multivaluedMap, MultivaluedMap<String, String> multivaluedMap1) {
		MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
		result.add("PRIVATE-TOKEN", gitLabConfig.accessToken());
		return result;
	}

}
