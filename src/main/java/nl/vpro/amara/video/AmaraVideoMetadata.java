package nl.vpro.amara.video;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author joost
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class AmaraVideoMetadata {

    @JsonProperty("speaker-name")
    public String speaker_name;
    public String location; // set Poms mid

    public AmaraVideoMetadata() {
    }

    public AmaraVideoMetadata(String speaker_name, String location) {
        this.speaker_name = speaker_name;
        this.location = location;
    }

    @Override
    public String toString() {
        return "AmaraVideoMetadata{" +
                "speaker_name='" + speaker_name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}