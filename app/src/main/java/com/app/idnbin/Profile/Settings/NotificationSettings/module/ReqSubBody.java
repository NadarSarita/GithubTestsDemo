package com.app.idnbin.Profile.Settings.NotificationSettings.module;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqSubBody {

        @SerializedName("status")
        @Expose
        private String status;

        /**
         * No args constructor for use in serialization
         *
         */
        public ReqSubBody() {
        }

        /**
         *
         * @param status
         */
        public ReqSubBody(String status) {
            super();
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }


}
