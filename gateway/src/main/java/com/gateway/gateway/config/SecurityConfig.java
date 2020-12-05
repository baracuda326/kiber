package com.gateway.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userDetailServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable().cors().disable()
                .authorizeRequests()
                .antMatchers("/user/registration", "/user/login", "/apartment/get").permitAll()
                .antMatchers("/image/upload", "/images/upload").permitAll()
                .antMatchers(HttpMethod.POST, "/feedback/add", "/subscribe/news").permitAll()
                .antMatchers(HttpMethod.GET, "/search/all", "/search/base", "/search/location", "/filters"
                        , "/search/text", "/filters/engine/countries", "/filters/engine", "/filters/engine/cities"
                        , "/news/getall", "popular/places/getall", "apartments/latest/getall", "apartments/latest/view").permitAll()
                .antMatchers(HttpMethod.DELETE, "/user/remove").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "admin/user/remove/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/user/wish/getall").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/admin/user/getall").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/user/wish/remove/**").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/user/wish/removeall").hasAuthority("USER")
                .antMatchers(HttpMethod.POST, "/user/wish/add").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/image/remove").hasAuthority("USER")
                .antMatchers(HttpMethod.PUT, "/user/update").hasAuthority("USER")
                .antMatchers(HttpMethod.PUT, "/user/activate").hasAuthority("USER")
                .antMatchers(HttpMethod.POST, "/user/send/code").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/post/get").hasAuthority("USER")
                .antMatchers(HttpMethod.POST, "/post/add").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/post/messages/delete/**").hasAuthority("USER")
                .antMatchers(HttpMethod.POST, "/apartment/add"
                        , "/apartment/add_photo"
                        , "/apartment/add_photos_list"
                        , "/apartment/reservation"
                        , "/apartment/reservation/open_close",
                        "/apartment/reservation/cancel").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/apartment/remove", "/apartment/remove_photo").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/apartment/get_all_by_user"
                        , "/apartment/reservation/{apartment_uuid}").hasAuthority("USER")
                .antMatchers(HttpMethod.PUT, "/apartment/update").hasAuthority("USER")
                .antMatchers(HttpMethod.PUT, "/comment/update").hasAuthority("USER")
                .antMatchers(HttpMethod.POST, "/comment/add").hasAuthority("USER")
                .antMatchers(HttpMethod.POST, "/user/criteria/add").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/user/criteria/remove/**").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/user/subscribe/remove/**").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/user/criteria/removeall").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/user/subscribe/removeall").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/user/criteria/getall").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/user/criteria/get/**").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/user/subscribe/get").hasAuthority("USER")
                .antMatchers(HttpMethod.POST, "/news/create").hasAuthority("ADMIN")
                .anyRequest().permitAll()
                .and()
                .httpBasic();
    }
}

