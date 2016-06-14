package com.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AccessTokenResponseBean {
    @XmlElement
    public String access_token;
    @XmlElement
    public long expires_in;
    @XmlElement
    public long user_id;
}
