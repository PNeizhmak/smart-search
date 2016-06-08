package com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Pavel Neizhmak
 */
public class ExtraParamsDto {

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("anotherOneParam")
    @Expose
    private String anotherOneParam;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAnotherOneParam() {
        return anotherOneParam;
    }

    public void setAnotherOneParam(String anotherOneParam) {
        this.anotherOneParam = anotherOneParam;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
