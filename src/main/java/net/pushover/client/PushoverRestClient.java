package net.pushover.client;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 * Implementation of {@link PushoverClient}
 *
 * @author Sean Scanlon <sean.scanlon@gmail.com>
 *
 * @since Dec 18, 2012
 */
public class PushoverRestClient implements PushoverClient {

    public static String PUSH_MESSAGE_URL = "https://api.pushover.net/1/messages.json";
    public static final String SOUND_LIST_URL = "https://api.pushover.net/1/sounds.json";

    private static final HttpUriRequest SOUND_LIST_REQUEST = new HttpGet(SOUND_LIST_URL);

    private static final AtomicReference<Set<PushOverSound>> SOUND_CACHE = new AtomicReference<Set<PushOverSound>>();

    private HttpClient httpClient = HttpClients.custom().useSystemProperties().build();

    @Override
    public Set<PushOverSound> getSounds() throws PushoverException {

        Set<PushOverSound> cachedSounds = SOUND_CACHE.get();
        if (cachedSounds == null) {
            try {
                cachedSounds = PushoverResponseFactory.createSoundSet(httpClient.execute(SOUND_LIST_REQUEST));
            } catch (Exception e) {
                throw new PushoverException(e.getMessage(), e.getCause());
            }
            SOUND_CACHE.set(cachedSounds);
        }
        return cachedSounds;
    }

    @Override
    public Status pushMessage(PushoverMessage msg) throws PushoverException {

        final HttpPost post = new HttpPost(PUSH_MESSAGE_URL);

        final List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("token", msg.getApiToken()));
        nvps.add(new BasicNameValuePair("user", msg.getUserId()));
        if ((msg.getHtmlMessage() != null) && (msg.getHtmlMessage().trim().length() > 0)) {
            nvps.add(new BasicNameValuePair("message", msg.getHtmlMessage()));
        } else {
            nvps.add(new BasicNameValuePair("message", msg.getMessage()));
        }

        addPairIfNotNull(nvps, "title", msg.getTitle());

        addPairIfNotNull(nvps, "url", msg.getUrl());
        addPairIfNotNull(nvps, "url_title", msg.getTitleForURL());

        addPairIfNotNull(nvps, "device", msg.getDevice());
        addPairIfNotNull(nvps, "timestamp", msg.getTimestamp());
        addPairIfNotNull(nvps, "sound", msg.getSound());

        if ((msg.getHtmlMessage() != null) && (msg.getHtmlMessage().trim().length() > 0)) {
            addPairIfNotNull(nvps, "html", "1");
        }

        if (!MessagePriority.NORMAL.equals(msg.getPriority())) {
            addPairIfNotNull(nvps, "priority", msg.getPriority());
            if (MessagePriority.EMERGENCY.equals(msg.getPriority())) {
                addPairIfNotNull(nvps, "retry", msg.getEmergenyRetry());
                addPairIfNotNull(nvps, "expire", msg.getEmergencyExpire());
            }
        }

        post.setEntity(new UrlEncodedFormEntity(nvps, (msg.getCharset() == null ? Charset.defaultCharset() : msg.getCharset())));

        try {
            HttpResponse response = httpClient.execute(post);
            Status status = PushoverResponseFactory.createStatus(response);
            if (status != null) {
                status.setRequestParameters(nvps.toString());
            }
            return status;
        } catch (Exception e) {
            throw new PushoverException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Optionally provide an alternative {@link HttpClient}
     *
     * @param httpClient
     */
    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    private void addPairIfNotNull(List<NameValuePair> nvps, String key, Object value) {
        if (value != null) {
            nvps.add(new BasicNameValuePair(key, value.toString()));
        }
    }

}
