package com.goout.springsecurity.securityConfig;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goout.train.model.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            /**
             * @param charSequence ???????????????
             * @param s ????????????????????????
             * @return
             */
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                System.out.println("matches:-???charSequence: "+charSequence.toString()+" ,S: "+s);
                BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
                boolean f = bcryptPasswordEncoder.matches(charSequence,s);//????????????
                System.out.println(f);
                return f;
            }
        });


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // ????????????????????????url???????????????
                .antMatchers(
                        "/assets/**",
                        "/img/**",
                        "/register",
                        "/train/getTicketList",
                        "/bus/getBusList",
                        "/transportApi/getTransportList",
                        "/cityStationApi/getAllCity",
                        "/cityStationApi/getAllStation",
                        "/air/getAirList",
                        "/ship/getShipList",
                        "/comprehensive/getComprehensiveByFilter",
                        "/advert/getAdList").permitAll()
                .anyRequest().authenticated()
                .and()
                // ?????????????????????????????????
                .httpBasic().authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                         AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        RestResponse rm = RestResponse.fail("???????????????");
                        httpServletResponse.getWriter().write(JSON.toJSONString(rm));
                    }
                })
                .and()
                // ????????????
                .formLogin().failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp,
                                                        AuthenticationException e) throws IOException {
                        resp.setContentType("application/json;charset=utf-8");
                        RestResponse rm = null;
                        if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
                            rm = RestResponse.fail("?????????????????????????????????!");
                        } else if (e instanceof LockedException) {
                            rm = RestResponse.fail("????????????????????????????????????!");
                        } else if (e instanceof CredentialsExpiredException) {
                            rm = RestResponse.fail("?????????????????????????????????!");
                        } else if (e instanceof AccountExpiredException) {
                            rm = RestResponse.fail("?????????????????????????????????!");
                        } else if (e instanceof DisabledException) {
                            rm = RestResponse.fail("????????????????????????????????????!");
                        } else {
                            rm = RestResponse.fail("????????????!");
                        }
                        PrintWriter out = resp.getWriter();
                        out.write(JSON.toJSONString(rm));
                        out.flush();
                        out.close();
                    }
                })
                // ????????????
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth)
                            throws IOException {
                        resp.setContentType("application/json;charset=utf-8");
                        RestResponse rm = RestResponse.succuess("????????????!");
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = resp.getWriter();
                        out.write(om.writeValueAsString(rm));
                        out.flush();
                        out.close();
                    }
                }).permitAll()
                // ???????????????????????????????????????????????????username???password
//                .usernameParameter("username")
//                .passwordParameter("password")
                .and()
                // ??????????????????
//                .exceptionHandling().accessDeniedPage("/500")
//                .and()
                .logout().logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        RestResponse rm = RestResponse.succuess("????????????!");
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = response.getWriter();
                        out.write(om.writeValueAsString(rm));
                        out.flush();
                        out.close();
                    }
                }).permitAll()
                .and().
                //????????????????????????
                exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                         AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        RestResponse rm = RestResponse.fail("???????????????");
                        httpServletResponse.getWriter().write(JSON.toJSONString(rm));
                    }
                });
        //?????????????????????
//         http.rememberMe().rememberMeParameter("remeber");

        //?????????????????????????????????????????????????????????session???????????????????????????????????????
        http.sessionManagement().maximumSessions(1).expiredUrl("/login");

         // ??????CSRF??????
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // ?????????????????????????????????????????????????????????
        web.ignoring().antMatchers("/css/**", "/js/**", "/assets/**", "/index.html");
    }


}

