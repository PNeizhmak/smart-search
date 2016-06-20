package com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Neizhmak
 */
public class GoogleUserInfoDto {

    @SerializedName("kind")
    @Expose
    public String kind;
    @SerializedName("etag")
    @Expose
    public String etag;
    @SerializedName("nickname")
    @Expose
    public String nickname;
    @SerializedName("occupation")
    @Expose
    public String occupation;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("urls")
    @Expose
    public List<Url> urls = new ArrayList<Url>();
    @SerializedName("objectType")
    @Expose
    public String objectType;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("displayName")
    @Expose
    public String displayName;
    @SerializedName("name")
    @Expose
    public Name name;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("image")
    @Expose
    public Image image;
    @SerializedName("organizations")
    @Expose
    public List<Organization> organizations = new ArrayList<Organization>();
    @SerializedName("placesLived")
    @Expose
    public List<PlacesLived> placesLived = new ArrayList<PlacesLived>();
    @SerializedName("isPlusUser")
    @Expose
    public Boolean isPlusUser;
    @SerializedName("circledByCount")
    @Expose
    public Integer circledByCount;
    @SerializedName("verified")
    @Expose
    public Boolean verified;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<PlacesLived> getPlacesLived() {
        return placesLived;
    }

    public void setPlacesLived(List<PlacesLived> placesLived) {
        this.placesLived = placesLived;
    }

    public Boolean getIsPlusUser() {
        return isPlusUser;
    }

    public void setIsPlusUser(Boolean isPlusUser) {
        this.isPlusUser = isPlusUser;
    }

    public Integer getCircledByCount() {
        return circledByCount;
    }

    public void setCircledByCount(Integer circledByCount) {
        this.circledByCount = circledByCount;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    public class Image {

        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("isDefault")
        @Expose
        public Boolean isDefault;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Boolean getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(Boolean isDefault) {
            this.isDefault = isDefault;
        }
    }

    public class Name {

        @SerializedName("familyName")
        @Expose
        public String familyName;
        @SerializedName("givenName")
        @Expose
        public String givenName;

        public String getFamilyName() {
            return familyName;
        }

        public void setFamilyName(String familyName) {
            this.familyName = familyName;
        }

        public String getGivenName() {
            return givenName;
        }

        public void setGivenName(String givenName) {
            this.givenName = givenName;
        }
    }

    public class Organization {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("primary")
        @Expose
        public Boolean primary;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Boolean getPrimary() {
            return primary;
        }

        public void setPrimary(Boolean primary) {
            this.primary = primary;
        }
    }

    public class PlacesLived {

        @SerializedName("value")
        @Expose
        public String value;
        @SerializedName("primary")
        @Expose
        public Boolean primary;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Boolean getPrimary() {
            return primary;
        }

        public void setPrimary(Boolean primary) {
            this.primary = primary;
        }
    }

    public class Url {

        @SerializedName("value")
        @Expose
        public String value;
        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("label")
        @Expose
        public String label;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
