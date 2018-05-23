
package com.dubion.service.dto.NapsterAPI.Artist.images;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "totalCount",
    "returnedCount"
})
public class Meta {

    @JsonProperty("totalCount")
    private Object totalCount;
    @JsonProperty("returnedCount")
    private Integer returnedCount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("totalCount")
    public Object getTotalCount() {
        return totalCount;
    }

    @JsonProperty("totalCount")
    public void setTotalCount(Object totalCount) {
        this.totalCount = totalCount;
    }

    @JsonProperty("returnedCount")
    public Integer getReturnedCount() {
        return returnedCount;
    }

    @JsonProperty("returnedCount")
    public void setReturnedCount(Integer returnedCount) {
        this.returnedCount = returnedCount;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
