package com.memo.intejmemo.base.attr.system.service;


import com.memo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemService {

    private final MemberRepository memberRepository;

    public boolean isSampleDataCreated(){
        return memberRepository.count()>0;
    }
}
