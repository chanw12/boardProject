package board.boardproject.confing.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;


@Configuration
@EnableMethodSecurity
@Slf4j
public class SpringSecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeRequests()
                .antMatchers("/register","/login","/logincheck","/ws-stomp","/chat","/chat/*").permitAll()
                .anyRequest().authenticated().and()
                .formLogin(login -> login	// form 방식 로그인 사용
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .failureHandler(loginFailHandler())
                        .successHandler(loginSuccessHandler())
                        .permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
                )

                .logout()// 로그아웃은 기본설정으로 (/logout으로 인증해제)
                .logoutUrl("/logout")  /* 로그아웃 url*/
                .logoutSuccessUrl("/login")  /* 로그아웃 성공시 이동할 url */
                .invalidateHttpSession(true)  /*로그아웃시 세션 제거*/
                .deleteCookies("JSESSIONID")  /*쿠키 제거*/
                .clearAuthentication(true).and()    /*권한정보 제거*/
                .sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(false)
                .expiredUrl("/login?expired").sessionRegistry(sessionRegistry());

        return http.build();
    }
    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public LoginFailureHandler loginFailHandler(){
        return new LoginFailureHandler();
    }
    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }

}