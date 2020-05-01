package com.app.idnbin.Profile.Settings.NotificationSettings.module;

import com.app.idnbin.Profile.Settings.NotificationSettings.TagBody;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReqTagBody {

        @SerializedName("tags")
        @Expose
        private List<TagBody> tags = null;

        /**
         * No args constructor for use in serialization
         *
         */
        public ReqTagBody() {
        }

        /**
         *
         * @param tags
         */
        public ReqTagBody(List<TagBody> tags) {
            super();
            this.tags = tags;
        }

        public List<TagBody> getTags() {
            return tags;
        }

        public void setTags(List<TagBody> tags) {
            this.tags = tags;
        }

    }


