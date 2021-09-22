package com.example.springchallenge.Logs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "logindex")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogEntity {

    @Id
    @JsonProperty("@timestamp")
    private String timestamp;
    
    @Field(type = FieldType.Text, name = "message")
    private String message;
    @Field(type = FieldType.Text, name = "logger_name")
    private String logger_name;
    @Field(type = FieldType.Text, name = "level")
    private String level;

    @JsonProperty("X-Request-ID")
    @Field(type = FieldType.Text, name = "XRequestID")
    private String XRequestID;
    @Field(type = FieldType.Integer, name = "statusCode")
    private int statusCode; 

    @JsonProperty("Os_browser_info")
    @Field(type = FieldType.Text, name = "os_browser_info")
    private String os_browser_info;
    
    @Field(type = FieldType.Text, name = "reqHeaders")
    private String reqHeaders;
    @Field(type = FieldType.Text, name = "resHeaders")
    private String resHeaders;
    @Field(type = FieldType.Text, name = "reqParams")
    private String reqParams;

    public LogEntity() {
    }

    public String getReqParams() {
        return reqParams;
    }

    public void setReqParams(String reqParams) {
        this.reqParams = reqParams;
    }

    public String getResHeaders() {
        return resHeaders;
    }

    public void setResHeaders(String resHeaders) {
        this.resHeaders = resHeaders;
    }

    public String getReqHeaders() {
        return reqHeaders;
    }

    public void setReqHeaders(String reqHeaders) {
        this.reqHeaders = reqHeaders;
    }

    public LogEntity(String timestamp, String message, String logger_name, String level, String xRequestID,
            int statusCode, String os_browser_info, String reqHeaders, String resHeaders, String reqParams) {
        this.timestamp = timestamp;
        this.message = message;
        this.logger_name = logger_name;
        this.level = level;
        this.XRequestID = xRequestID;
        this.statusCode = statusCode;
        this.os_browser_info = os_browser_info;
        this.setReqHeaders(reqHeaders);
        this.setResHeaders(resHeaders);
        this.setReqParams(reqParams);
    }



    public String getOs_browser_info() {
        return os_browser_info;
    }
    public void setOs_browser_info(String os_browser_info) {
        this.os_browser_info = os_browser_info;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getXRequestID() {
        return XRequestID;
    }
    public void setXRequestID(String xRequestID) {
        this.XRequestID = xRequestID;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getLogger_name() {
        return logger_name;
    }
    public void setLogger_name(String logger_name) {
        this.logger_name = logger_name;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
    
    @Override
    public String toString() {
        return "LogEnitty{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", logger_name='" + logger_name + '\'' +
                ", level='" + level + '\'' +
                ", XRequestID=" + XRequestID +
                ", statusCode=" + statusCode +
                ", Os_browser_info=" + os_browser_info +                
                '}';
    }


    
}
