package com.kkjk.bloggingsystem.blogEntry;

import com.kkjk.bloggingsystem.blogObject.BlogObjectEntity;
import com.kkjk.bloggingsystem.user.UserEntity;
import com.kkjk.bloggingsystem.comment.CommentEntity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BlogEntryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private Instant createdDate;
    private Instant modifiedDate;
    private Integer viewCount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UserEntity author;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="blogpost_id")
    private List<BlogObjectEntity> blogObjects;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="blogpost_id")
    private List<CommentEntity> comments;
}
