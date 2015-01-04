package pl.codeleak.support;

import com.fasterxml.jackson.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "totalElements",
        "totalPages",
        "size",
        "number",
        "content",
        "sort",
        "first",
        "last",
        "numberOfElements"
})
public class Page<T> {

    @JsonProperty("totalElements")
    private int totalElements;
    @JsonProperty("totalPages")
    private int totalPages;
    @JsonProperty("size")
    private int size;
    @JsonProperty("number")
    private int number;
    @JsonProperty("content")
    @Valid
    private List<T> content = new ArrayList<>();
    @JsonProperty("sort")
    @Valid
    private List<Sort> sort = new ArrayList<>();
    @JsonProperty("first")
    private boolean first;
    @JsonProperty("last")
    private boolean last;
    @JsonProperty("numberOfElements")
    private int numberOfElements;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The totalElements
     */
    @JsonProperty("totalElements")
    public int getTotalElements() {
        return totalElements;
    }

    /**
     * @param totalElements The totalElements
     */
    @JsonProperty("totalElements")
    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    /**
     * @return The totalPages
     */
    @JsonProperty("totalPages")
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages The totalPages
     */
    @JsonProperty("totalPages")
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * @return The size
     */
    @JsonProperty("size")
    public int getSize() {
        return size;
    }

    /**
     * @param size The size
     */
    @JsonProperty("size")
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return The number
     */
    @JsonProperty("number")
    public int getNumber() {
        return number;
    }

    /**
     * @param number The number
     */
    @JsonProperty("number")
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return The content
     */
    @JsonProperty("content")
    public List<T> getContent() {
        return content;
    }

    /**
     * @param content The content
     */
    @JsonProperty("content")
    public void setContent(List<T> content) {
        this.content = content;
    }

    /**
     * @return The sort
     */
    @JsonProperty("sort")
    public List<Sort> getSort() {
        return sort;
    }

    /**
     * @param sort The sort
     */
    @JsonProperty("sort")
    public void setSort(List<Sort> sort) {
        this.sort = sort;
    }

    /**
     * @return The first
     */
    @JsonProperty("first")
    public boolean isFirst() {
        return first;
    }

    /**
     * @param first The first
     */
    @JsonProperty("first")
    public void setFirst(boolean first) {
        this.first = first;
    }

    /**
     * @return The last
     */
    @JsonProperty("last")
    public boolean isLast() {
        return last;
    }

    /**
     * @param last The last
     */
    @JsonProperty("last")
    public void setLast(boolean last) {
        this.last = last;
    }

    /**
     * @return The numberOfElements
     */
    @JsonProperty("numberOfElements")
    public int getNumberOfElements() {
        return numberOfElements;
    }

    /**
     * @param numberOfElements The numberOfElements
     */
    @JsonProperty("numberOfElements")
    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
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