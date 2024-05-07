package ac.kr.dankook.ace.dom_t1.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // 스프링의 환경 설정 파일로 지정 -> 싱글톤 : 해당 클래스에서 만들어지는 단일 인스턴스를 통해서 시큐리티를 제어한다.
@EnableWebSecurity // 모든 요청 URL이 Spring Security의 제어를 받도록 만든다.
public class SecurityConfig {
    
    @Bean // SecurityFilterChain 인스턴스를 Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
                .csrf((csrf) ->
                csrf
                    .ignoringRequestMatchers(new AntPathRequestMatcher("/mysql-admin/**")) // MySQL 관리 페이지의 경로를 CSRF 보호 예외로 추가
            ) // https://wikidocs.net/162150 <- 참고
        ;
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 빈 등록을 통해서 암호화가 변경되었을 경우에 프로그램을 일일이 찾지 않고 ( 객체를 하나하나 찾지 않고 ) 빈에서 검색 후 변경
    }
}