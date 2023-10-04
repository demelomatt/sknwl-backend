package com.sknwl.shareknowledge.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Comment {
    private Long id;
    private Comment parent;
    private List<Comment> children;
    private Member member;
    private LocalDateTime publishedDateTime;
    private String text;
    private Content content;
    private StudyGuide studyGuide;
}
