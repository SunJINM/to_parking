package sun.project.to_parking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.project.to_parking.service.AdminService;

/**
 * 配置Security-AOP
 */
@Configuration
@EnableWebSecurity //开启Spring Security 的权限控制和认证功能
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AdminService adminService;
    /**基于内存认证*/
    //链式编程
    /**
     * 授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录页所有人可以访问，功能页管理员可以访问
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/page/views/**").hasRole("admin1")
                .and()
                .formLogin().loginPage("/page/toLogin")
                .usernameParameter("admin")
                .passwordParameter("password")
                .permitAll()
                .defaultSuccessUrl("/page/views/menu")
                .failureUrl("/page/toLogin")
                .and().logout().logoutSuccessUrl("/page/toLogin")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();


    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /**
     * 认证
     * 密码编码：PasswordEncoder
     * 在Spring Security 5.0+中新增了很多的加密方法
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //基于数据库认证
        auth.userDetailsService(adminService).passwordEncoder(passwordEncoder());
//        //这些数据正常从数据库认证，也可以从内存认证
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin")
//                .password(new BCryptPasswordEncoder().encode("123456"))
//                .roles("admin1");
    }
    /**配置登录成功及失败得的事项*/
//    /登录成功的处理器
//            .successHandler(new AuthenticationSuccessHandler() {
//
//        @Override
//        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
//                HttpServletResponse httpServletResponse,
//                Authentication authentication)
//                            throws IOException, ServletException {
//
//        }
//    })
//            .failureHandler(new AuthenticationFailureHandler() {
//
//        @Override
//        public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
//                HttpServletResponse httpServletResponse,
//                AuthenticationException e)
//                            throws IOException, ServletException {
//
//        }
//    })
}
