package bitlab.g111.springsecurity.config;

import bitlab.g111.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
  @Autowired
  private UserService userService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    AuthenticationManagerBuilder authenticationManagerBuilder =
        http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());

    http.formLogin()
        .loginPage("/signin") // страница авторизации
        .usernameParameter("user_email") // email
        .passwordParameter("user_password") // password
        .loginProcessingUrl("/enter") // процесс проверки
        .defaultSuccessUrl("/profile") // при удачной авторизации
        .failureUrl("/signin?error"); // при введении неверных данных

    http.logout()
        .logoutUrl("/exit") // для выхода из аккаунта
        .logoutSuccessUrl("/signin"); // после выхода

    return http.build();
  }
}