package ac.kr.dankook.ace.dom_t1.Model.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ac.kr.dankook.ace.dom_t1.Model.Entity.SiteuserEntity;
import ac.kr.dankook.ace.dom_t1.Model.repository.SiteuserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Service
public class SiteuserService {

    
    private final SiteuserRepository siteuserRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteuserEntity create(String nickname, String email, String psw) { //  SiteuserRepository를 이용하여 회원 데이터를 생성하는 메서드
        SiteuserEntity user = new SiteuserEntity(); 
        user.setNickname(nickname);
        user.setEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 비크립트(Bycrpt 클래스 : 해시 함수의 일종) : 보안정보를 저장하고 검증할 때 사용하는 암호화 기술
        // PasswordEncoder 는 BCryptPasswordEncoder의 인터페이스
        user.setPsw(passwordEncoder.encode(psw)); // BCryptPasswordEncoder 객체를 직접 사용하지 않고 빈으로 등록한 PassWord Encoder 객체를 주입받아 사용할 수 있도록 수정
        this.siteuserRepository.save(user);
        return user;
    }
    
}
