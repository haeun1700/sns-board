package com.sns.board.model;

import java.time.ZonedDateTime;
import java.util.Objects;

public record Post(Long postId, String body, ZonedDateTime createAt){}
