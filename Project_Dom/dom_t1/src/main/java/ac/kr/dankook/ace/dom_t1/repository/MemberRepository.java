package ac.kr.dankook.ace.dom_t1.repository;

import ac.kr.dankook.ace.dom_t1.domain.Member;
import java.util.*;

public interface MemberRepository

{
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();


}
