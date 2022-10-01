package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemberRepository;

import javax.swing.text.html.Option;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository {   // Alt+enter : 메소드 자동 구현

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 0,1,2 자동생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence);     // ID를셋팅하고
        store.put(member.getId(), member);  // store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));    // null일때 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream()
                .filter(member -> member.getName().equals(name))   //람다식
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}



















