package com.sknwl.shareknowledge.repositories.database.relational.model;

import com.sknwl.shareknowledge.domain.entity.enums.ContentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;

@Getter
@Setter
@Entity
@Table(name="content")
public class ContentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated()
    private ContentType contentType;
    private String url;

    @ManyToOne
    @JoinColumn(name = "source_id", referencedColumnName = "id")
    private SourceModel source;

    @ElementCollection
    @CollectionTable(name = "author", joinColumns = @JoinColumn(name = "content_id"))
    @Column(name = "name")
    private List<String> authors;

    @ElementCollection
    @CollectionTable(name = "content_subject", joinColumns = @JoinColumn(name = "content_id"))
    @Column(name = "name")
    private SortedSet<String> subjects;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private LanguageModel language;

    private Long durationMinutes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private MemberModel publisher;

    private LocalDateTime publishedDateTime;
    private Long rating;
    private Long reviewers;
/*
    @OneToMany(mappedBy = "content")
    private List<CommentModel> comments;

 */

}