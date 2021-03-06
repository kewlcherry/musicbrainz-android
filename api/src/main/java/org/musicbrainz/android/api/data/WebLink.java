package org.musicbrainz.android.api.data;

import org.musicbrainz.android.api.util.StringFormat;

/**
 * Link URL and type description pair.
 */
public class WebLink implements Comparable<WebLink> {

    private String target;
    private String type;

    public String getUrl() {
        return target;
    }

    public String getPrettyUrl() {
        // Remove "http://"
        String prettyUrl = target.substring(7);
        if (prettyUrl.endsWith("/")) {
            prettyUrl = prettyUrl.substring(0, prettyUrl.length() - 1);
        }
        return prettyUrl;
    }

    public void setUrl(String url) {
        this.target = url;
    }

    public String getType() {
        return type;
    }

    public String getPrettyType() {
        type = type.replace('_', ' ');
        return StringFormat.initialCaps(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public int compareTo(WebLink another) {
        return getPrettyType().compareTo(another.getPrettyType());
    }

}
