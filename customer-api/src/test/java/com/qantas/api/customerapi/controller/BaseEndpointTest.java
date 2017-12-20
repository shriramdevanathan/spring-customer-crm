package com.qantas.api.customerapi.controller;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseEndpointTest {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected static final MediaType JSON_MEDIA_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("UTF-8"));
	protected static final MediaType XML_MEDIA_TYPE = new MediaType(MediaType.APPLICATION_XML.getType(), MediaType.APPLICATION_XML.getSubtype(), Charset.forName("UTF-8"));

	@Autowired
    protected WebApplicationContext webApplicationContext;

	@Autowired
	ObjectMapper objectMapper;
	
	protected MockMvc mockMvc;

    protected void setup() throws Exception {

    	this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }
    
	/**
	 * Returns json representation of the object.
	 * @param o instance
	 * @return json
	 * @throws IOException
	 */
	protected String json(Object o) throws IOException {

		return objectMapper.writeValueAsString(o);
	}

	protected String jsonHardCoded() throws  IOException {
		return  "{\n" +
				"  \"addresses\": [\n" +
				"    {\n" +
				"      \"address_type\":\"Office\",\n" +
				"      \"city\": \"North Sydney\",\n" +
				"      \"country\": \"Australia\",\n" +
				"      \"postalCode\": \"2050\",\n" +
				"      \"street\": \"Miller Street\",\n" +
				"      \"complexName\":\"Westfield\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"address_type\":\"Home\",\n" +
				"      \"city\": \"Sydney\",\n" +
				"      \"country\": \"Australia\",\n" +
				"      \"postalCode\": \"2150\",\n" +
				"      \"street\": \"Great Western Highway\",\n" +
				"      \"unitNumber\":\"62\",\n" +
				"      \"apartmentNumber\": \"20\"\n" +
				"    }\n" +
				"  ],\n" +
				"  \"dob\": \"2017-12-18T00:57:34.657Z\",\n" +
				"  \"firstName\": \"ShriramTest\",\n" +
				"\n" +
				"  \"lastName\": \"DevanathanTest\"\n" +
				"}";
	}
	protected String jsonHardCodedWithId(Long id) throws  IOException {
		return  "{\n" +
				"  \"addresses\": [\n" +
				"    {\n" +
				"      \"address_type\":\"Office\",\n" +
				"      \"city\": \"North Sydney\",\n" +
				"      \"country\": \"Australia\",\n" +
				"      \"postalCode\": \"2050\",\n" +
				"      \"street\": \"Miller Street\",\n" +
				"      \"complexName\":\"Westfield\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"address_type\":\"Home\",\n" +
				"      \"city\": \"Sydney\",\n" +
				"      \"country\": \"Australia\",\n" +
				"      \"postalCode\": \"2150\",\n" +
				"      \"street\": \"Great Western Highway\",\n" +
				"      \"unitNumber\":\"62\",\n" +
				"      \"apartmentNumber\": \"20\"\n" +
				"    }\n" +
				"  ],\n" +
				"  \"dob\": \"2017-12-18T00:57:34.657Z\",\n" +
				"  \"firstName\": \"ShriramTest\",\n" +
				"\n" +
				"  \"lastName\": \"DevanathanTest\",\n" +
				"\n" +
				"  \"id\": \""+id+"\"\n" +
				"}";
	}

}