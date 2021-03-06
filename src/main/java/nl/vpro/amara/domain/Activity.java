package nl.vpro.amara.domain;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * @author joost
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Activity {

    public final static int TYPE_ADD_VIDEO = 1;
    public final static int TYPE_CHANGE_TITLE = 2;
    public final static int TYPE_COMMENT = 3;
    public final static int TYPE_ADD_VERSION = 4;
    public final static int TYPE_ADD_VIDEO_URL = 5;
    public final static int TYPE_ADD_TRANSLATION = 6;
    public final static int TYPE_SUBTITLE_REQUEST = 7;
    public final static int TYPE_APPROVE_VERSION = 8;
    public final static int TYPE_MEMBER_JOINED = 9;
    public final static int TYPE_REJECT_VERSION = 10;
    public final static int TYPE_MEMBER_LEFT = 11;
    public final static int TYPE_REVIEW_VERSION = 12;
    public final static int TYPE_ACCEPT_VERSION = 13;
    public final static int TYPE_DECLINE_VERSION = 14;
    public final static int TYPE_DELETE_VIDEO = 15;

    String id;
    int type;
    Instant created;

    String video;
    String video_uri;
    String language;
    String language_url;
    String user;
    String comment;
    String new_video_title;
    String resource_uri;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideo_uri() {
        return video_uri;
    }

    public void setVideo_uri(String video_uri) {
        this.video_uri = video_uri;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage_url() {
        return language_url;
    }

    public void setLanguage_url(String language_url) {
        this.language_url = language_url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNew_video_title() {
        return new_video_title;
    }

    public void setNew_video_title(String new_video_title) {
        this.new_video_title = new_video_title;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

    @Override
    public String toString() {
        return "AmaraActivity{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", created=" + created +
                ", video='" + video + '\'' +
                ", video_uri='" + video_uri + '\'' +
                ", language='" + language + '\'' +
                ", language_url='" + language_url + '\'' +
                ", user='" + user + '\'' +
                ", comment='" + comment + '\'' +
                ", new_video_title='" + new_video_title + '\'' +
                ", resource_uri='" + resource_uri + '\'' +
                '}';
    }


}
