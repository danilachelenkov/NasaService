package ru.netology;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NasaInfo {
    private final String copyright;
    private final String date;
    private final String explanation;
    private final String hDurl;
    private final String mediaType;
    private final String serviceVersion;
    private final String title;
    private final String url;

    public NasaInfo(
            @JsonProperty("copyright") String copyright,
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String hDurl,
            @JsonProperty("media_type") String mediaType,
            @JsonProperty("service_version") String serviceVersion,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url
    ) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hDurl = hDurl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.url = url;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdurl() {
        return hDurl;
    }

    public String getMedia_type() {
        return mediaType;
    }

    public String getService_version() {
        return serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "\n NASA_INFO = " +
                "\n copyright=" + copyright +
                "\n date=" + date +
                "\n explanation=" + explanation +
                "\n hdurl=" + hDurl +
                "\n media_type=" + mediaType +
                "\n service_version=" + serviceVersion +
                "\n title=" + title +
                "\n url=" + url;
    }
}
