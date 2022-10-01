package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();


    /**
     * 회원가입
     * */
    public Long join(Member member){        //  회원가입을 하면 Id를 반환
        // 같은 이름이 있는 중복 회원X  // ctrl + alt + v : 자동 반환 단축키  // Ctrl + T => Ctrl+Alt+Shift+T

        validateduplicationMember(member);  // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateduplicationMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 전페 회원 조회
     * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}