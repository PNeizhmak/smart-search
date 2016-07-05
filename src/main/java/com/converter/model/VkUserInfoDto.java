package com.converter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Neizhmak
 */
public class VkUserInfoDto {

    @Expose
    @SerializedName("response")
    private List<Response> response = new ArrayList<>();

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public class Response {
        @SerializedName("uid")
        @Expose
        private Integer uid;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("city")
        @Expose
        private Integer city;
        @SerializedName("twitter")
        @Expose
        private String twitter;
        @SerializedName("instagram")
        @Expose
        private String instagram;
        @SerializedName("site")
        @Expose
        private String site;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("university")
        @Expose
        private Integer university;
        @SerializedName("university_name")
        @Expose
        private String universityName;
        @SerializedName("faculty")
        @Expose
        private Integer faculty;
        @SerializedName("faculty_name")
        @Expose
        private String facultyName;
        @SerializedName("graduation")
        @Expose
        private Integer graduation;

        public Integer getUid() {
            return uid;
        }

        public void setUid(Integer uid) {
            this.uid = uid;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Integer getCity() {
            return city;
        }

        public void setCity(Integer city) {
            this.city = city;
        }

        public String getTwitter() {
            return twitter;
        }

        public void setTwitter(String twitter) {
            this.twitter = twitter;
        }

        public String getInstagram() {
            return instagram;
        }

        public void setInstagram(String instagram) {
            this.instagram = instagram;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getUniversity() {
            return university;
        }

        public void setUniversity(Integer university) {
            this.university = university;
        }

        public String getUniversityName() {
            return universityName;
        }

        public void setUniversityName(String universityName) {
            this.universityName = universityName;
        }

        public Integer getFaculty() {
            return faculty;
        }

        public void setFaculty(Integer faculty) {
            this.faculty = faculty;
        }

        public String getFacultyName() {
            return facultyName;
        }

        public void setFacultyName(String facultyName) {
            this.facultyName = facultyName;
        }

        public Integer getGraduation() {
            return graduation;
        }

        public void setGraduation(Integer graduation) {
            this.graduation = graduation;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }
}
