package ac.kr.dankook.ace.dom_t1.repository;


import ac.kr.dankook.ace.dom_t1.domain.Member;


import java.util.*;


public class MemoryMemberRepository implements MemberRepository {


    private static Map<Long,Member> store = new HashMap<>();
    private static Long sequence = 0l;

    @Override
    public List findAll() {
        Member.setId(++sequence);
        store.put(Member.getId(),Member);

    }

    @Override
    public Optional findById(Long id) {
        
        return null;
    }

    @Override
    public Optional findByName(String name) {
        
        return null;
    }

    @Override
    public Object save(Object member) {
        
        return null;
    }

    
    
}
