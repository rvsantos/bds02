package com.devsuperior.bds02.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
    private String path;
    private String error;
    private Integer status;
    private Instant timestamp;

    public StandardError() {
    }

    private StandardError(String message, String path, String error, Integer status, Instant timestamp) {
        this.message = message;
        this.path = path;
        this.error = error;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStaus(Integer status) {
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public static class StandardErrorBuilder {
        private String message;
        private String path;
        private String error;
        private Integer status;
        private Instant timestamp;

        public StandardErrorBuilder message(String message) {
            this.message = message;
            return this;
        }

        public StandardErrorBuilder path(String path) {
            this.path = path;
            return this;
        }

        public StandardErrorBuilder error(String error) {
            this.error = error;
            return this;
        }

        public StandardErrorBuilder status(Integer status) {
            this.status = status;
            return this;
        }

        public StandardErrorBuilder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public StandardError build() {
            return new StandardError(message, path, error, status, timestamp);
        }
    }
}
