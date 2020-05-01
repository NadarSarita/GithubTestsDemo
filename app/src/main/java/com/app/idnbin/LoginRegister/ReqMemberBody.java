package com.app.idnbin.LoginRegister;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqMemberBody {

        @SerializedName("email_address")
        @Expose
        private String emailAddress;
        @SerializedName("status")
        @Expose
        private String status;
   @SerializedName("merge_fields")
        @Expose
        private MergeFields mergeFields;

        /**
         * No args constructor for use in serialization
         *
         */
        public ReqMemberBody() {
        }

        /**
         *
         * @param emailAddress
         * @param mergeFields
         * @param status
         */
        public ReqMemberBody(String emailAddress, String status, MergeFields mergeFields) {
            super();
            this.emailAddress = emailAddress;
            this.status = status;
            this.mergeFields = mergeFields;
        }
        public ReqMemberBody(String emailAddress, String status) {
            super();
            this.emailAddress = emailAddress;
            this.status = status;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

      public MergeFields getMergeFields() {
            return mergeFields;
        }

        public void setMergeFields(MergeFields mergeFields) {
            this.mergeFields = mergeFields;
        }


}
