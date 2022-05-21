package coursework.security;

import coursework.security.jwt.JwtSecurityConfigurer;
import coursework.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/works/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/works/new").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/works").permitAll()
                .antMatchers(HttpMethod.GET, "/cars/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/cars/new").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/cars").permitAll()
                .antMatchers(HttpMethod.GET, "/services/new").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/services").permitAll()
                .antMatchers(HttpMethod.GET, "/masters/new").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/masters").permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                /*.anyRequest().authenticated()*/
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider));
    }

}
