package nl.vpro.amara.domain;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import nl.vpro.amara_poms.Config;
import nl.vpro.amara.Utils;

/**
 * @author joost
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityCollection {
    private final static Logger LOG = LoggerFactory.getLogger(ActivityCollection.class);

    public Meta meta;

    @JsonProperty("objects")
    List<Activity> amaraActivities;

    public static List<Activity> getList() {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ActivityCollection> request = new HttpEntity<>(Utils.getGetHeaders());
        ResponseEntity<ActivityCollection> response = restTemplate.exchange(Utils.getUriForPath("api/activity"), HttpMethod.GET, request, ActivityCollection.class);
        ActivityCollection amaryActivityCollection = response.getBody();

        HttpStatus httpStatus = response.getStatusCode();

        LOG.info(String.valueOf(response));

        return  amaryActivityCollection.amaraActivities;
    }

    public static List<Activity> getListForType(int activityType, long afterTimestampInSeconds) {

        // build url
        String url = Config.getRequiredConfig("amara.api.url") + "api/activity/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("team", Config.getRequiredConfig("amara.api.team"))
                .queryParam("type", activityType)
                .queryParam("after", afterTimestampInSeconds);
        URI uri = builder.build().encode().toUri();

        // do request
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ActivityCollection> request = new HttpEntity<>(Utils.getGetHeaders());
        ResponseEntity<ActivityCollection> response = restTemplate.exchange(uri, HttpMethod.GET, request, ActivityCollection.class);
        ActivityCollection amaryActivityCollection = response.getBody();

        HttpStatus httpStatus = response.getStatusCode();
        LOG.info(String.valueOf(response));

        return  amaryActivityCollection.amaraActivities;
    }


}