package com.app.idnbin.LoginRegister;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResMemberBody {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("email_address")
        @Expose
        private String emailAddress;
        @SerializedName("unique_email_id")
        @Expose
        private String uniqueEmailId;
        @SerializedName("web_id")
        @Expose
        private Integer webId;
        @SerializedName("email_type")
        @Expose
        private String emailType;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("merge_fields")
        @Expose
        private MergeFields mergeFields;
        @SerializedName("stats")
        @Expose
        private Stats stats;
        @SerializedName("ip_signup")
        @Expose
        private String ipSignup;
        @SerializedName("timestamp_signup")
        @Expose
        private String timestampSignup;
        @SerializedName("ip_opt")
        @Expose
        private String ipOpt;
        @SerializedName("timestamp_opt")
        @Expose
        private String timestampOpt;
        @SerializedName("member_rating")
        @Expose
        private Integer memberRating;
        @SerializedName("last_changed")
        @Expose
        private String lastChanged;
        @SerializedName("language")
        @Expose
        private String language;
        @SerializedName("vip")
        @Expose
        private Boolean vip;
        @SerializedName("email_client")
        @Expose
        private String emailClient;
        @SerializedName("location")
        @Expose
        private Location location;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("tags_count")
        @Expose
        private Integer tagsCount;
        @SerializedName("tags")
        @Expose
        private List<Object> tags = null;
        @SerializedName("list_id")
        @Expose
        private String listId;
        @SerializedName("_links")
        @Expose
        private List<Link> links = null;

        /**
         * No args constructor for use in serialization
         *
         */
        public ResMemberBody() {
        }

        /**
         *
         * @param tagsCount
         * @param uniqueEmailId
         * @param emailClient
         * @param timestampSignup
         * @param lastChanged
         * @param language
         * @param source
         * @param tags
         * @param ipSignup
         * @param listId
         * @param emailAddress
         * @param webId
         * @param emailType
         * @param stats
         * @param ipOpt
         * @param timestampOpt
         * @param memberRating
         * @param location
         * @param links
         * @param id
         * @param vip
         * @param mergeFields
         * @param status
         */
        public ResMemberBody(String id, String emailAddress, String uniqueEmailId, Integer webId, String emailType, String status, MergeFields mergeFields, Stats stats, String ipSignup, String timestampSignup, String ipOpt, String timestampOpt, Integer memberRating, String lastChanged, String language, Boolean vip, String emailClient, Location location, String source, Integer tagsCount, List<Object> tags, String listId, List<Link> links) {
            super();
            this.id = id;
            this.emailAddress = emailAddress;
            this.uniqueEmailId = uniqueEmailId;
            this.webId = webId;
            this.emailType = emailType;
            this.status = status;
            this.mergeFields = mergeFields;
            this.stats = stats;
            this.ipSignup = ipSignup;
            this.timestampSignup = timestampSignup;
            this.ipOpt = ipOpt;
            this.timestampOpt = timestampOpt;
            this.memberRating = memberRating;
            this.lastChanged = lastChanged;
            this.language = language;
            this.vip = vip;
            this.emailClient = emailClient;
            this.location = location;
            this.source = source;
            this.tagsCount = tagsCount;
            this.tags = tags;
            this.listId = listId;
            this.links = links;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public String getUniqueEmailId() {
            return uniqueEmailId;
        }

        public void setUniqueEmailId(String uniqueEmailId) {
            this.uniqueEmailId = uniqueEmailId;
        }

        public Integer getWebId() {
            return webId;
        }

        public void setWebId(Integer webId) {
            this.webId = webId;
        }

        public String getEmailType() {
            return emailType;
        }

        public void setEmailType(String emailType) {
            this.emailType = emailType;
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

        public Stats getStats() {
            return stats;
        }

        public void setStats(Stats stats) {
            this.stats = stats;
        }

        public String getIpSignup() {
            return ipSignup;
        }

        public void setIpSignup(String ipSignup) {
            this.ipSignup = ipSignup;
        }

        public String getTimestampSignup() {
            return timestampSignup;
        }

        public void setTimestampSignup(String timestampSignup) {
            this.timestampSignup = timestampSignup;
        }

        public String getIpOpt() {
            return ipOpt;
        }

        public void setIpOpt(String ipOpt) {
            this.ipOpt = ipOpt;
        }

        public String getTimestampOpt() {
            return timestampOpt;
        }

        public void setTimestampOpt(String timestampOpt) {
            this.timestampOpt = timestampOpt;
        }

        public Integer getMemberRating() {
            return memberRating;
        }

        public void setMemberRating(Integer memberRating) {
            this.memberRating = memberRating;
        }

        public String getLastChanged() {
            return lastChanged;
        }

        public void setLastChanged(String lastChanged) {
            this.lastChanged = lastChanged;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public Boolean getVip() {
            return vip;
        }

        public void setVip(Boolean vip) {
            this.vip = vip;
        }

        public String getEmailClient() {
            return emailClient;
        }

        public void setEmailClient(String emailClient) {
            this.emailClient = emailClient;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Integer getTagsCount() {
            return tagsCount;
        }

        public void setTagsCount(Integer tagsCount) {
            this.tagsCount = tagsCount;
        }

        public List<Object> getTags() {
            return tags;
        }

        public void setTags(List<Object> tags) {
            this.tags = tags;
        }

        public String getListId() {
            return listId;
        }

        public void setListId(String listId) {
            this.listId = listId;
        }

        public List<Link> getLinks() {
            return links;
        }

        public void setLinks(List<Link> links) {
            this.links = links;
        }


    public class Link {

        @SerializedName("rel")
        @Expose
        private String rel;
        @SerializedName("href")
        @Expose
        private String href;
        @SerializedName("method")
        @Expose
        private String method;
        @SerializedName("targetSchema")
        @Expose
        private String targetSchema;
        @SerializedName("schema")
        @Expose
        private String schema;

        /**
         * No args constructor for use in serialization
         *
         */
        public Link() {
        }

        /**
         *
         * @param schema
         * @param targetSchema
         * @param method
         * @param rel
         * @param href
         */
        public Link(String rel, String href, String method, String targetSchema, String schema) {
            super();
            this.rel = rel;
            this.href = href;
            this.method = method;
            this.targetSchema = targetSchema;
            this.schema = schema;
        }

        public String getRel() {
            return rel;
        }

        public void setRel(String rel) {
            this.rel = rel;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getTargetSchema() {
            return targetSchema;
        }

        public void setTargetSchema(String targetSchema) {
            this.targetSchema = targetSchema;
        }

        public String getSchema() {
            return schema;
        }

        public void setSchema(String schema) {
            this.schema = schema;
        }

    }


    public class Location {

        @SerializedName("latitude")
        @Expose
        private Integer latitude;
        @SerializedName("longitude")
        @Expose
        private Integer longitude;
        @SerializedName("gmtoff")
        @Expose
        private Integer gmtoff;
        @SerializedName("dstoff")
        @Expose
        private Integer dstoff;
        @SerializedName("country_code")
        @Expose
        private String countryCode;
        @SerializedName("timezone")
        @Expose
        private String timezone;

        /**
         * No args constructor for use in serialization
         *
         */
        public Location() {
        }

        /**
         *
         * @param dstoff
         * @param countryCode
         * @param timezone
         * @param latitude
         * @param gmtoff
         * @param longitude
         */
        public Location(Integer latitude, Integer longitude, Integer gmtoff, Integer dstoff, String countryCode, String timezone) {
            super();
            this.latitude = latitude;
            this.longitude = longitude;
            this.gmtoff = gmtoff;
            this.dstoff = dstoff;
            this.countryCode = countryCode;
            this.timezone = timezone;
        }

        public Integer getLatitude() {
            return latitude;
        }

        public void setLatitude(Integer latitude) {
            this.latitude = latitude;
        }

        public Integer getLongitude() {
            return longitude;
        }

        public void setLongitude(Integer longitude) {
            this.longitude = longitude;
        }

        public Integer getGmtoff() {
            return gmtoff;
        }

        public void setGmtoff(Integer gmtoff) {
            this.gmtoff = gmtoff;
        }

        public Integer getDstoff() {
            return dstoff;
        }

        public void setDstoff(Integer dstoff) {
            this.dstoff = dstoff;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

    }


    public class MergeFields {

        @SerializedName("FNAME")
        @Expose
        private String fNAME;
        @SerializedName("LNAME")
        @Expose
        private String lNAME;
        @SerializedName("ADDRESS")
        @Expose
        private String aDDRESS;
        @SerializedName("PHONE")
        @Expose
        private String pHONE;
        @SerializedName("BIRTHDAY")
        @Expose
        private String bIRTHDAY;

        /**
         * No args constructor for use in serialization
         *
         */
        public MergeFields() {
        }

        /**
         *
         * @param fNAME
         * @param lNAME
         * @param pHONE
         * @param bIRTHDAY
         * @param aDDRESS
         */
        public MergeFields(String fNAME, String lNAME, String aDDRESS, String pHONE, String bIRTHDAY) {
            super();
            this.fNAME = fNAME;
            this.lNAME = lNAME;
            this.aDDRESS = aDDRESS;
            this.pHONE = pHONE;
            this.bIRTHDAY = bIRTHDAY;
        }

        public String getFNAME() {
            return fNAME;
        }

        public void setFNAME(String fNAME) {
            this.fNAME = fNAME;
        }

        public String getLNAME() {
            return lNAME;
        }

        public void setLNAME(String lNAME) {
            this.lNAME = lNAME;
        }

        public String getADDRESS() {
            return aDDRESS;
        }

        public void setADDRESS(String aDDRESS) {
            this.aDDRESS = aDDRESS;
        }

        public String getPHONE() {
            return pHONE;
        }

        public void setPHONE(String pHONE) {
            this.pHONE = pHONE;
        }

        public String getBIRTHDAY() {
            return bIRTHDAY;
        }

        public void setBIRTHDAY(String bIRTHDAY) {
            this.bIRTHDAY = bIRTHDAY;
        }

    }


    public class Stats {

        @SerializedName("avg_open_rate")
        @Expose
        private Integer avgOpenRate;
        @SerializedName("avg_click_rate")
        @Expose
        private Integer avgClickRate;

        /**
         * No args constructor for use in serialization
         *
         */
        public Stats() {
        }

        /**
         *
         * @param avgClickRate
         * @param avgOpenRate
         */
        public Stats(Integer avgOpenRate, Integer avgClickRate) {
            super();
            this.avgOpenRate = avgOpenRate;
            this.avgClickRate = avgClickRate;
        }

        public Integer getAvgOpenRate() {
            return avgOpenRate;
        }

        public void setAvgOpenRate(Integer avgOpenRate) {
            this.avgOpenRate = avgOpenRate;
        }

        public Integer getAvgClickRate() {
            return avgClickRate;
        }

        public void setAvgClickRate(Integer avgClickRate) {
            this.avgClickRate = avgClickRate;
        }

    }


}