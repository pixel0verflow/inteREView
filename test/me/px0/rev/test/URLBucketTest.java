package me.px0.rev.test;

import me.px0.rev.URLBucket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class URLBucketTest {

    String testShortURL;
    String testKey;
    String testLongURL;
    String testTooLongKey;
    URLBucket bucket;

    @BeforeEach
    void setup() {
        testShortURL = "https://www.example.com/";
        testKey = "ggl";
        testLongURL = "https://google.com/";
        testTooLongKey = "thisKeyIsProbablyTooLongToBeSEOKey";

        bucket = new URLBucket(testShortURL);
    }

    @Test
    void testPut() {
        bucket.put(testKey, testLongURL);
    }

    @Test
    void testGetLongURL() throws MalformedURLException {
        URL expected = new URL(testLongURL);
        bucket.put(testKey, testLongURL);
        URL received = bucket.getLongURL(testKey);

        assertEquals(expected, received);
    }

    @Test
    void testTooLongURL() {
        try {
            bucket.put(testTooLongKey, testLongURL);
            fail();
        } catch (IllegalArgumentException ignored) {
            // pass the test
        }
    }

    @Test
    void testGetShortURL() throws MalformedURLException {
        URL expected = new URL(String.format("%s%s", testShortURL, testKey));
        bucket.put(testKey, testLongURL);
        URL received = bucket.getShortURL(testKey);

        assertEquals(expected, received);
    }
}
