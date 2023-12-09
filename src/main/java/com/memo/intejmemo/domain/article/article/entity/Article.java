package com.memo.intejmemo.domain.article.article.entity;


import com.memo.intejmemo.domain.member.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@EqualsAndHashCode
public class Article {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id") // 이 부분을 추가
    private Member author;
    private String title;
    private String body;
}