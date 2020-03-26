
package com.wubi.nasathingy.beans;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "sol",
    "camera",
    "img_src",
    "earth_date",
    "rover"
})
public class Photo {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("sol")
    private Integer sol;
    @JsonProperty("camera")
    private Camera camera;
    @JsonProperty("img_src")
    private String imgSrc;
    @JsonProperty("earth_date")
    private String earthDate;
    @JsonProperty("rover")
    private Rover rover;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("sol")
    public Integer getSol() {
        return sol;
    }

    @JsonProperty("sol")
    public void setSol(Integer sol) {
        this.sol = sol;
    }

    @JsonProperty("camera")
    public Camera getCamera() {
        return camera;
    }

    @JsonProperty("camera")
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    @JsonProperty("img_src")
    public String getImgSrc() {
        return imgSrc;
    }

    @JsonProperty("img_src")
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    @JsonProperty("earth_date")
    public String getEarthDate() {
        return earthDate;
    }

    @JsonProperty("earth_date")
    public void setEarthDate(String earthDate) {
        this.earthDate = earthDate;
    }

    @JsonProperty("rover")
    public Rover getRover() {
        return rover;
    }

    @JsonProperty("rover")
    public void setRover(Rover rover) {
        this.rover = rover;
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
    	return new ToStringBuilder(this).append("id", id).append("sol", sol).append("camera", camera).append("imgSrc", imgSrc).append("earthDate", earthDate).append("rover", rover).append("additionalProperties", additionalProperties).toString();
    }

}
