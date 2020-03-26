package com.wubi.nasathingy.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "requiredEarthDates" })
public class RequiredEarthDates {

	@JsonProperty("requiredEarthDates")
	private List<String> requiredEarthDates = new ArrayList<String>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("requiredEarthDates")
	public List<String> getRequiredEarthDates() {
		return requiredEarthDates;
	}

	@JsonProperty("requiredEarthDates")
	public void setRequiredEarthDates(List<String> requiredEarthDates) {
		this.requiredEarthDates = requiredEarthDates;
	}
	
	/**
	 * Adding the newDate to the list, a convenient method.
	 * @param newDate
	 */
	public void addRequiredEarthDate(String newDate) {
		requiredEarthDates.add(newDate);
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("requiredEarthDates", requiredEarthDates)
				.append("additionalProperties", additionalProperties).toString();
	}

}