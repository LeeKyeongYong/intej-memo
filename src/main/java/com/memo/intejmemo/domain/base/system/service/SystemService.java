package com.memo.intejmemo.base.system.service;


import com.memo.intejmemo.member.repository.MemberRepository;
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
