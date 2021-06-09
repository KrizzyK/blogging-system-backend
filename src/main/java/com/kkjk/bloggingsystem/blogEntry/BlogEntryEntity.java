package com.kkjk.bloggingsystem.blogEntry;

import com.kkjk.bloggingsystem.blogObject.BlogObjectEntity;
import lombok.*;

import javax.persistence.*;
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

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="blogpost_id")
    private List<BlogObjectEntity> blogObjects;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="blogpost_id")
    private List<BlogObjectEntity> comments;
}
