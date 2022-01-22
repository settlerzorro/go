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
             * @param charSequence 输入的密码
             * @param s 数据库加密的密码
             * @return
             */
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                System.out.println("matches:-》charSequence: "+charSequence.toString()+" ,S: "+s);
                BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
                boolean f = bcryptPasswordEncoder.matches(charSequence,s);//比较密码
                System.out.println(f);
                return f;
            }
        });


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // 如果有允许匿名的url，填在下面
                .antMatchers(
                        "/assets/**",
                        "/register",
                        "/train/getTicketList",
                        "/bus/getBusList",
                        "/transportApi/getTransportList",
                        "/cityStationApi/getAllCity",
                        "/cityStationApi/getAllStation",
                        "/air/getAirList",
                        "/ship/getShipList",
                        "/advert/getAdList").permitAll()
                .anyRequest().authenticated()
                .and()
                // 配置未登录自定义处理类
                .httpBasic().authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                         AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        RestResponse rm = RestResponse.fail("请重新登录");
                        httpServletResponse.getWriter().write(JSON.toJSONString(rm));
                    }
                })
                .and()
                // 登录验证
                .formLogin().failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp,
                                                        AuthenticationException e) throws IOException {
                        resp.setContentType("application/json;charset=utf-8");
                        RestResponse rm = null;
                        if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
                            rm = RestResponse.fail("账户名或者密码输入错误!");
                        } else if (e instanceof LockedException) {
                            rm = RestResponse.fail("账户被锁定，请联系管理员!");
                        } else if (e instanceof CredentialsExpiredException) {
                            rm = RestResponse.fail("密码过期，请联系管理员!");
                        } else if (e instanceof AccountExpiredException) {
                            rm = RestResponse.fail("账户过期，请联系管理员!");
                        } else if (e instanceof DisabledException) {
                            rm = RestResponse.fail("账户被禁用，请联系管理员!");
                        } else {
                            rm = RestResponse.fail("登录失败!");
                        }
                        PrintWriter out = resp.getWriter();
                        out.write(JSON.toJSONString(rm));
                        out.flush();
                        out.close();
                    }
                })
                // 成功登录
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth)
                            throws IOException {
                        resp.setContentType("application/json;charset=utf-8");
                        Object curUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        RestResponse rm = RestResponse.succuess("登录成功!");
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = resp.getWriter();
                        out.write(om.writeValueAsString(rm));
                        out.flush();
                        out.close();
                    }
                }).permitAll()
                // 自定义登陆用户名和密码参数，默认为username和password
//                .usernameParameter("username")
//                .passwordParameter("password")
                .and()
                // 异常处理跳转
//                .exceptionHandling().accessDeniedPage("/500")
//                .and()
                .logout().permitAll()
                .and().
                //屏蔽默认登陆页面
                exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                         AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        RestResponse rm = RestResponse.fail("请重新登录");
                        httpServletResponse.getWriter().write(JSON.toJSONString(rm));
                    }
                });
        //开启记住我功能
//         http.rememberMe().rememberMeParameter("remeber");

        //以下这句就可以控制单个用户只能创建一个session，也就只能在服务器登录一次
        http.sessionManagement().maximumSessions(1).expiredUrl("/login");

         // 关闭CSRF跨域
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**", "/assets/**", "/index.html");
    }


}

