package com.sns.board.model;

import java.util.Objects;

public class PostPostRequestBody {
    private String body;

    public PostPostRequestBody(String body) {
        this.body = body;
    }
    public PostPostRequestBody() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostPostRequestBody that)) return false;
        return Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(body);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
