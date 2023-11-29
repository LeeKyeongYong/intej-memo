package com.memo.intejmemo.domain.entity;


import com.memo.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;



import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access=PROTECTED)
@AllArgsConstructor(access=PROTECTED)
@EqualsAndHashCode
public class Article {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @ManyToOne
    private Member author;
    private String title;
    private String body;
}
