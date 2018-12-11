package net.pushover.client;

import java.nio.charset.Charset;

/**
 *
 * @author Sean Scanlon <sean.scanlon@gmail.com>
 *
 * @since Dec 18, 2012
 */
public class PushoverMessage {

    public static class Builder {

        private PushoverMessage msg;

        public Builder() {
            msg = new PushoverMessage();
        }

        public PushoverMessage build() {
            // TODO: validate message!
            return msg;
        }

        /**
         * (required) - your application's API token
         */
        public Builder setApiToken(String apiToken) {
            msg.apiToken = apiToken;
            return this;
        }

        /**
         * (optional) - charset for your message. if not set then the program uses the default.
         */
        public Builder setCharset(Charset charset) {
            msg.charset = charset;
            return this;
        }

        /**
         * (optional) - your user's device identifier to send the message directly to that device, rather than all of the user's devices
         */
        public Builder setDevice(String device) {
            msg.device = device;
            return this;
        }

        /**
         * The expire parameter specifies how many seconds your notification will continue to be retried for (every retry seconds).
         */
        public Builder setEmergencyExpire(Integer emergencyExpire) {
            msg.emergencyExpire = emergencyExpire;
            return this;
        }

        /**
         * The retry parameter specifies how often (in seconds) the Pushover servers will send the same notification to the user.
         */
        public Builder setEmergenyRetry(Integer emergenyRetry) {
            msg.emergenyRetry = emergenyRetry;
            return this;
        }

        public Builder setHtmlMessage(String htmlMessage) {
            msg.htmlMessage = htmlMessage;
            return this;
        }

        /**
         * (required) - your message
         */
        public Builder setMessage(String message) {
            msg.message = message;
            return this;
        }

        /**
         * (optional) - set to MessagePriority.HIGH to display as high-priority and bypass quiet hours, or MessagePriority.QUIET to always
         * send as a quiet notification
         */
        public Builder setPriority(MessagePriority priority) {
            msg.priority = priority;
            return this;
        }

        /**
         * (optional) - set to the name of one of the sounds supported by device clients to override the user's default sound choice
         */
        public Builder setSound(String sound) {
            msg.sound = sound;
            return this;
        }

        /**
         * (optional) - set to a Unix timestamp to have your message show with a particular time, rather than now
         */
        public Builder setTimestamp(Long timestamp) {
            msg.timestamp = timestamp;
            return this;
        }

        /**
         * (optional) - your message's title, otherwise uses your app's name
         */
        public Builder setTitle(String title) {
            msg.title = title;
            return this;
        }

        /**
         * (optional) - a title for your supplementary URL
         */
        public Builder setTitleForURL(String titleForURL) {
            msg.titleForURL = titleForURL;
            return this;
        }

        /**
         * (optional) - a supplementary URL to show with your message
         */
        public Builder setUrl(String url) {
            msg.url = url;
            return this;
        }

        /**
         * (required) - the user key (not e-mail address) of your user (or you), viewable when logged into the dashboard
         */
        public Builder setUserId(String userId) {
            msg.userId = userId;
            return this;
        }
    }

    public static Builder builderWithApiToken(String token) {
        return new Builder().setApiToken(token);
    }

    private String apiToken;

    private String userId;

    private String message;

    private String htmlMessage;

    private String device;

    private String title;

    private String url;

    private String titleForURL;

    private MessagePriority priority = MessagePriority.NORMAL;

    private Long timestamp;

    private String sound;

    private Charset charset;

    private Integer emergenyRetry;

    private Integer emergencyExpire;

    private PushoverMessage() {
        // use the builder
    }

    public String getApiToken() {
        return apiToken;
    }

    public Charset getCharset() {
        return charset;
    }

    public String getDevice() {
        return device;
    }

    public Integer getEmergencyExpire() {
        return emergencyExpire;
    }

    public Integer getEmergenyRetry() {
        return emergenyRetry;
    }

    public String getHtmlMessage() {
        return htmlMessage;
    }

    public String getMessage() {
        return message;
    }

    public MessagePriority getPriority() {
        return priority;
    }

    public String getSound() {
        return sound;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleForURL() {
        return titleForURL;
    }

    public String getUrl() {
        return url;
    }

    public String getUserId() {
        return userId;
    }

}
