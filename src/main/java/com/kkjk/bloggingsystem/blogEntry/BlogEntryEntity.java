package com.kkjk.bloggingsystem.blogEntry;

import com.kkjk.bloggingsystem.blogObject.BlogObjectEntity;
import com.kkjk.bloggingsystem.user.UserEntity;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UserEntity author;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="blogpost_id")
    private List<BlogObjectEntity> blogObjects;
}
