package me.px0.rev;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class URLBucket {

    private final String shortURL;
    private final Map<String, String> inputURLs;

    public URLBucket(String shortURL) {
        this.inputURLs = new HashMap<>();
        this.shortURL = shortURL;
    }

    public void put(String key, String url) {
        synchronized (this) {
            if (key.length() > 20)
                throw new IllegalArgumentException("Given key is too long.");
            else if (inputURLs.containsKey(key)) {
                throw new IllegalArgumentException("Given key already exists. Remove it first.");
            }
            inputURLs.put(key, url);
        }
    }

    public URL getLongURL(String key) throws MalformedURLException {
        String toReturn = inputURLs.get(key);
        if (toReturn == null)
            return null;
        else
            return new URL(toReturn);
    }

    public URL getShortURL(String key) throws MalformedURLException {
        return new URL(String.format("%s%s", shortURL, key));
    }
}
