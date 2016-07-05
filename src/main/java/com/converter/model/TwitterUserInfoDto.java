package com.converter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Neizhmak
 */
public class TwitterUserInfoDto {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("id_str")
    @Expose
    public String idStr;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("screen_name")
    @Expose
    public String screenName;
    @SerializedName("location")
    @Expose
    public String location;
    @SerializedName("profile_location")
    @Expose
    public Object profileLocation;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("entities")
    @Expose
    public Entities entities;
    @SerializedName("protected")
    @Expose
    public Boolean _protected;
    @SerializedName("followers_count")
    @Expose
    public Integer followersCount;
    @SerializedName("friends_count")
    @Expose
    public Integer friendsCount;
    @SerializedName("listed_count")
    @Expose
    public Integer listedCount;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("favourites_count")
    @Expose
    public Integer favouritesCount;
    @SerializedName("utc_offset")
    @Expose
    public Integer utcOffset;
    @SerializedName("time_zone")
    @Expose
    public String timeZone;
    @SerializedName("geo_enabled")
    @Expose
    public Boolean geoEnabled;
    @SerializedName("verified")
    @Expose
    public Boolean verified;
    @SerializedName("statuses_count")
    @Expose
    public Integer statusesCount;
    @SerializedName("lang")
    @Expose
    public String lang;
    @SerializedName("status")
    @Expose
    public Status status;
    @SerializedName("contributors_enabled")
    @Expose
    public Boolean contributorsEnabled;
    @SerializedName("is_translator")
    @Expose
    public Boolean isTranslator;
    @SerializedName("is_translation_enabled")
    @Expose
    public Boolean isTranslationEnabled;
    @SerializedName("profile_background_color")
    @Expose
    public String profileBackgroundColor;
    @SerializedName("profile_background_image_url")
    @Expose
    public String profileBackgroundImageUrl;
    @SerializedName("profile_background_image_url_https")
    @Expose
    public String profileBackgroundImageUrlHttps;
    @SerializedName("profile_background_tile")
    @Expose
    public Boolean profileBackgroundTile;
    @SerializedName("profile_image_url")
    @Expose
    public String profileImageUrl;
    @SerializedName("profile_image_url_https")
    @Expose
    public String profileImageUrlHttps;
    @SerializedName("profile_banner_url")
    @Expose
    public String profileBannerUrl;
    @SerializedName("profile_link_color")
    @Expose
    public String profileLinkColor;
    @SerializedName("profile_sidebar_border_color")
    @Expose
    public String profileSidebarBorderColor;
    @SerializedName("profile_sidebar_fill_color")
    @Expose
    public String profileSidebarFillColor;
    @SerializedName("profile_text_color")
    @Expose
    public String profileTextColor;
    @SerializedName("profile_use_background_image")
    @Expose
    public Boolean profileUseBackgroundImage;
    @SerializedName("has_extended_profile")
    @Expose
    public Boolean hasExtendedProfile;
    @SerializedName("default_profile")
    @Expose
    public Boolean defaultProfile;
    @SerializedName("default_profile_image")
    @Expose
    public Boolean defaultProfileImage;
    @SerializedName("following")
    @Expose
    public Boolean following;
    @SerializedName("follow_request_sent")
    @Expose
    public Boolean followRequestSent;
    @SerializedName("notifications")
    @Expose
    public Boolean notifications;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Object getProfileLocation() {
        return profileLocation;
    }

    public void setProfileLocation(Object profileLocation) {
        this.profileLocation = profileLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    public Boolean get_protected() {
        return _protected;
    }

    public void set_protected(Boolean _protected) {
        this._protected = _protected;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public Integer getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(Integer friendsCount) {
        this.friendsCount = friendsCount;
    }

    public Integer getListedCount() {
        return listedCount;
    }

    public void setListedCount(Integer listedCount) {
        this.listedCount = listedCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getFavouritesCount() {
        return favouritesCount;
    }

    public void setFavouritesCount(Integer favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    public Integer getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(Integer utcOffset) {
        this.utcOffset = utcOffset;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Boolean getGeoEnabled() {
        return geoEnabled;
    }

    public void setGeoEnabled(Boolean geoEnabled) {
        this.geoEnabled = geoEnabled;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Integer getStatusesCount() {
        return statusesCount;
    }

    public void setStatusesCount(Integer statusesCount) {
        this.statusesCount = statusesCount;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getContributorsEnabled() {
        return contributorsEnabled;
    }

    public void setContributorsEnabled(Boolean contributorsEnabled) {
        this.contributorsEnabled = contributorsEnabled;
    }

    public Boolean getIsTranslator() {
        return isTranslator;
    }

    public void setIsTranslator(Boolean isTranslator) {
        this.isTranslator = isTranslator;
    }

    public Boolean getIsTranslationEnabled() {
        return isTranslationEnabled;
    }

    public void setIsTranslationEnabled(Boolean isTranslationEnabled) {
        this.isTranslationEnabled = isTranslationEnabled;
    }

    public String getProfileBackgroundColor() {
        return profileBackgroundColor;
    }

    public void setProfileBackgroundColor(String profileBackgroundColor) {
        this.profileBackgroundColor = profileBackgroundColor;
    }

    public String getProfileBackgroundImageUrl() {
        return profileBackgroundImageUrl;
    }

    public void setProfileBackgroundImageUrl(String profileBackgroundImageUrl) {
        this.profileBackgroundImageUrl = profileBackgroundImageUrl;
    }

    public String getProfileBackgroundImageUrlHttps() {
        return profileBackgroundImageUrlHttps;
    }

    public void setProfileBackgroundImageUrlHttps(String profileBackgroundImageUrlHttps) {
        this.profileBackgroundImageUrlHttps = profileBackgroundImageUrlHttps;
    }

    public Boolean getProfileBackgroundTile() {
        return profileBackgroundTile;
    }

    public void setProfileBackgroundTile(Boolean profileBackgroundTile) {
        this.profileBackgroundTile = profileBackgroundTile;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileImageUrlHttps() {
        return profileImageUrlHttps;
    }

    public void setProfileImageUrlHttps(String profileImageUrlHttps) {
        this.profileImageUrlHttps = profileImageUrlHttps;
    }

    public String getProfileBannerUrl() {
        return profileBannerUrl;
    }

    public void setProfileBannerUrl(String profileBannerUrl) {
        this.profileBannerUrl = profileBannerUrl;
    }

    public String getProfileLinkColor() {
        return profileLinkColor;
    }

    public void setProfileLinkColor(String profileLinkColor) {
        this.profileLinkColor = profileLinkColor;
    }

    public String getProfileSidebarBorderColor() {
        return profileSidebarBorderColor;
    }

    public void setProfileSidebarBorderColor(String profileSidebarBorderColor) {
        this.profileSidebarBorderColor = profileSidebarBorderColor;
    }

    public String getProfileSidebarFillColor() {
        return profileSidebarFillColor;
    }

    public void setProfileSidebarFillColor(String profileSidebarFillColor) {
        this.profileSidebarFillColor = profileSidebarFillColor;
    }

    public String getProfileTextColor() {
        return profileTextColor;
    }

    public void setProfileTextColor(String profileTextColor) {
        this.profileTextColor = profileTextColor;
    }

    public Boolean getProfileUseBackgroundImage() {
        return profileUseBackgroundImage;
    }

    public void setProfileUseBackgroundImage(Boolean profileUseBackgroundImage) {
        this.profileUseBackgroundImage = profileUseBackgroundImage;
    }

    public Boolean getHasExtendedProfile() {
        return hasExtendedProfile;
    }

    public void setHasExtendedProfile(Boolean hasExtendedProfile) {
        this.hasExtendedProfile = hasExtendedProfile;
    }

    public Boolean getDefaultProfile() {
        return defaultProfile;
    }

    public void setDefaultProfile(Boolean defaultProfile) {
        this.defaultProfile = defaultProfile;
    }

    public Boolean getDefaultProfileImage() {
        return defaultProfileImage;
    }

    public void setDefaultProfileImage(Boolean defaultProfileImage) {
        this.defaultProfileImage = defaultProfileImage;
    }

    public Boolean getFollowing() {
        return following;
    }

    public void setFollowing(Boolean following) {
        this.following = following;
    }

    public Boolean getFollowRequestSent() {
        return followRequestSent;
    }

    public void setFollowRequestSent(Boolean followRequestSent) {
        this.followRequestSent = followRequestSent;
    }

    public Boolean getNotifications() {
        return notifications;
    }

    public void setNotifications(Boolean notifications) {
        this.notifications = notifications;
    }

    public class Description {

        @SerializedName("urls")
        @Expose
        public List<Object> urls = new ArrayList<>();

        public List<Object> getUrls() {
            return urls;
        }

        public void setUrls(List<Object> urls) {
            this.urls = urls;
        }
    }

    public class Entities {

        @SerializedName("url")
        @Expose
        public Url url;
        @SerializedName("description")
        @Expose
        public Description description;

        public Url getUrl() {
            return url;
        }

        public void setUrl(Url url) {
            this.url = url;
        }

        public Description getDescription() {
            return description;
        }

        public void setDescription(Description description) {
            this.description = description;
        }
    }

    public class Entities_ {

        @SerializedName("hashtags")
        @Expose
        public List<Object> hashtags = new ArrayList<>();
        @SerializedName("symbols")
        @Expose
        public List<Object> symbols = new ArrayList<>();
        @SerializedName("user_mentions")
        @Expose
        public List<Object> userMentions = new ArrayList<>();
        @SerializedName("urls")
        @Expose
        public List<Url_> urls = new ArrayList<>();

        public List<Object> getHashtags() {
            return hashtags;
        }

        public void setHashtags(List<Object> hashtags) {
            this.hashtags = hashtags;
        }

        public List<Object> getSymbols() {
            return symbols;
        }

        public void setSymbols(List<Object> symbols) {
            this.symbols = symbols;
        }

        public List<Object> getUserMentions() {
            return userMentions;
        }

        public void setUserMentions(List<Object> userMentions) {
            this.userMentions = userMentions;
        }

        public List<Url_> getUrls() {
            return urls;
        }

        public void setUrls(List<Url_> urls) {
            this.urls = urls;
        }
    }

    public class Status {

        @SerializedName("created_at")
        @Expose
        public String createdAt;
        @SerializedName("id")
        @Expose
        public Long id;
        @SerializedName("id_str")
        @Expose
        public String idStr;
        @SerializedName("text")
        @Expose
        public String text;
        @SerializedName("truncated")
        @Expose
        public Boolean truncated;
        @SerializedName("entities")
        @Expose
        public Entities_ entities;
        @SerializedName("source")
        @Expose
        public String source;
        @SerializedName("in_reply_to_status_id")
        @Expose
        public Object inReplyToStatusId;
        @SerializedName("in_reply_to_status_id_str")
        @Expose
        public Object inReplyToStatusIdStr;
        @SerializedName("in_reply_to_user_id")
        @Expose
        public Object inReplyToUserId;
        @SerializedName("in_reply_to_user_id_str")
        @Expose
        public Object inReplyToUserIdStr;
        @SerializedName("in_reply_to_screen_name")
        @Expose
        public Object inReplyToScreenName;
        @SerializedName("geo")
        @Expose
        public Object geo;
        @SerializedName("coordinates")
        @Expose
        public Object coordinates;
        @SerializedName("place")
        @Expose
        public Object place;
        @SerializedName("contributors")
        @Expose
        public Object contributors;
        @SerializedName("is_quote_status")
        @Expose
        public Boolean isQuoteStatus;
        @SerializedName("retweet_count")
        @Expose
        public Integer retweetCount;
        @SerializedName("favorite_count")
        @Expose
        public Integer favoriteCount;
        @SerializedName("favorited")
        @Expose
        public Boolean favorited;
        @SerializedName("retweeted")
        @Expose
        public Boolean retweeted;
        @SerializedName("possibly_sensitive")
        @Expose
        public Boolean possiblySensitive;
        @SerializedName("lang")
        @Expose
        public String lang;

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getIdStr() {
            return idStr;
        }

        public void setIdStr(String idStr) {
            this.idStr = idStr;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Boolean getTruncated() {
            return truncated;
        }

        public void setTruncated(Boolean truncated) {
            this.truncated = truncated;
        }

        public Entities_ getEntities() {
            return entities;
        }

        public void setEntities(Entities_ entities) {
            this.entities = entities;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Object getInReplyToStatusId() {
            return inReplyToStatusId;
        }

        public void setInReplyToStatusId(Object inReplyToStatusId) {
            this.inReplyToStatusId = inReplyToStatusId;
        }

        public Object getInReplyToStatusIdStr() {
            return inReplyToStatusIdStr;
        }

        public void setInReplyToStatusIdStr(Object inReplyToStatusIdStr) {
            this.inReplyToStatusIdStr = inReplyToStatusIdStr;
        }

        public Object getInReplyToUserId() {
            return inReplyToUserId;
        }

        public void setInReplyToUserId(Object inReplyToUserId) {
            this.inReplyToUserId = inReplyToUserId;
        }

        public Object getInReplyToUserIdStr() {
            return inReplyToUserIdStr;
        }

        public void setInReplyToUserIdStr(Object inReplyToUserIdStr) {
            this.inReplyToUserIdStr = inReplyToUserIdStr;
        }

        public Object getInReplyToScreenName() {
            return inReplyToScreenName;
        }

        public void setInReplyToScreenName(Object inReplyToScreenName) {
            this.inReplyToScreenName = inReplyToScreenName;
        }

        public Object getGeo() {
            return geo;
        }

        public void setGeo(Object geo) {
            this.geo = geo;
        }

        public Object getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(Object coordinates) {
            this.coordinates = coordinates;
        }

        public Object getPlace() {
            return place;
        }

        public void setPlace(Object place) {
            this.place = place;
        }

        public Object getContributors() {
            return contributors;
        }

        public void setContributors(Object contributors) {
            this.contributors = contributors;
        }

        public Boolean getIsQuoteStatus() {
            return isQuoteStatus;
        }

        public void setIsQuoteStatus(Boolean isQuoteStatus) {
            this.isQuoteStatus = isQuoteStatus;
        }

        public Integer getRetweetCount() {
            return retweetCount;
        }

        public void setRetweetCount(Integer retweetCount) {
            this.retweetCount = retweetCount;
        }

        public Integer getFavoriteCount() {
            return favoriteCount;
        }

        public void setFavoriteCount(Integer favoriteCount) {
            this.favoriteCount = favoriteCount;
        }

        public Boolean getFavorited() {
            return favorited;
        }

        public void setFavorited(Boolean favorited) {
            this.favorited = favorited;
        }

        public Boolean getRetweeted() {
            return retweeted;
        }

        public void setRetweeted(Boolean retweeted) {
            this.retweeted = retweeted;
        }

        public Boolean getPossiblySensitive() {
            return possiblySensitive;
        }

        public void setPossiblySensitive(Boolean possiblySensitive) {
            this.possiblySensitive = possiblySensitive;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }
    }

    public class Url {

        @SerializedName("urls")
        @Expose
        public List<Url_> urls = new ArrayList<>();

        public List<Url_> getUrls() {
            return urls;
        }

        public void setUrls(List<Url_> urls) {
            this.urls = urls;
        }
    }

    public class Url_ {

        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("expanded_url")
        @Expose
        public String expandedUrl;
        @SerializedName("display_url")
        @Expose
        public String displayUrl;
        @SerializedName("indices")
        @Expose
        public List<Integer> indices = new ArrayList<>();

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getExpandedUrl() {
            return expandedUrl;
        }

        public void setExpandedUrl(String expandedUrl) {
            this.expandedUrl = expandedUrl;
        }

        public String getDisplayUrl() {
            return displayUrl;
        }

        public void setDisplayUrl(String displayUrl) {
            this.displayUrl = displayUrl;
        }

        public List<Integer> getIndices() {
            return indices;
        }

        public void setIndices(List<Integer> indices) {
            this.indices = indices;
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
