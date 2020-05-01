package com.app.idnbin.Profile.Settings.NotificationSettings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TagBody {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("status")
        @Expose
        private String status;

        /**
         * No args constructor for use in serialization
         *
         */
        public TagBody() {
        }

        /**
         *
         * @param name
         * @param status
         */
        public TagBody(String name, String status) {
            super();
            this.name = name;
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

}
