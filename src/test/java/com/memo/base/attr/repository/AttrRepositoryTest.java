package com.memo.base.attr.repository;


import com.memo.intejmemo.base.attr.entity.Attr;
import com.memo.intejmemo.base.attr.repository.AttrRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AttrRepositoryTest {

    @Autowired
    private AttrRepository attrRepository;

    @DisplayName("attr 저장")
    @Test
    void attr저장(){
        Attr attr = Attr.builder()
                .createDate(LocalDateTime.now())
                .name("age")
                .build();

        attrRepository.save(attr);
        assertThat(attr.getId()).isGreaterThan(0L);
    }

    @DisplayName("attr 저장, 한번 더")
    @Test
    void attr저장_한번더(){
        Attr attr = Attr.builder()
                .createDate(LocalDateTime.now())
                .name("age")
                .build();
        attrRepository.save(attr);
        assertThat(attr.getId()).isGreaterThan(0L);
    }

    @DisplayName("select count(*) from attr")
    @Test
    void 테스트카운트(){
        assertThat(attrRepository.count()).isEqualTo(0);
    }
}
