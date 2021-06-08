package com.kkjk.bloggingsystem.blogObject;


import com.kkjk.bloggingsystem.blogEntry.BlogEntryEntity;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BlogObjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String type;
    private String content;
    private int positionInBlogEntry;

}
