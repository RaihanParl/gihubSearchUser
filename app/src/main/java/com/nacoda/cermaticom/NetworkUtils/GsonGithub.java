package com.nacoda.cermaticom.NetworkUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by You on 9/1/17.
 */

public class GsonGithub {
    @SerializedName("total_count")
    public Integer totalCount;
    @SerializedName("incomplete_results")
    public Boolean incompleteResults;
    @SerializedName("items")
    public List<Bio> bio = null;

    public class Bio {
        @SerializedName("login")
        public String login;
        @SerializedName("id")
        public Integer id;
        @SerializedName("avatar_url")
        public String avatarUrl;
        @SerializedName("gravatar_id")
        public String gravatarId;
        @SerializedName("url")
        public String url;
        @SerializedName("html_url")
        public String htmlUrl;
        @SerializedName("followers_url")
        public String followersUrl;
        @SerializedName("following_url")
        public String followingUrl;
        @SerializedName("gists_url")
        public String gistsUrl;
        @SerializedName("starred_url")
        public String starredUrl;
        @SerializedName("subscriptions_url")
        public String subscriptionsUrl;
        @SerializedName("organizations_url")
        public String organizationsUrl;
        @SerializedName("repos_url")
        public String reposUrl;
        @SerializedName("events_url")
        public String eventsUrl;
        @SerializedName("received_events_url")
        public String receivedEventsUrl;
        @SerializedName("type")
        public String type;
        @SerializedName("site_admin")
        public Boolean siteAdmin;
        @SerializedName("score")
        public Float score;

    }
}
