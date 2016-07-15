package com.db.model;

/**
 * @author Pavel Neizhmak
 */
public enum SocialNetwork {

    VK("1"),
    FB("2"),
    INSTAGRAM("3"),
    GITHUB("4"),
    TWITTER("5"),
    GOOGLE_PLUS("6"),
    FORSQUARE("7");

    private final String networkDbId;

    SocialNetwork(final String networkDbId) {
        this.networkDbId = networkDbId;
    }

    public String getNetworkDbId() {
        return networkDbId;
    }
}
