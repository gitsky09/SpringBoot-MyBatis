package com.deploy.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.deploy.demo.security.CustomAuthenticationProvider;
import com.deploy.demo.security.UserLoginService;

@Configuration
@EnableWebSecurity
public class SecurityDynamicConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	@Lazy
    private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl ();
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
//		jdbcTokenRepositoryImpl.setCreateTableOnStartup(true);
		return jdbcTokenRepositoryImpl;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userLoginService).passwordEncoder(passwordEncoder());
		auth.authenticationProvider(customAuthenticationProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage("/userlogin")
//			.successHandler(yourCustomSuccessHandler)
//            .failureHandler(yourCustomFailureHandler)
//			.usernameParameter("account")
			.loginProcessingUrl("/security/login") //security會自動配置
			.defaultSuccessUrl("/main")
			.and().authorizeRequests()
				.antMatchers("/","/userlogin").permitAll()
				.antMatchers("/authority").hasAuthority("ROLE_admin")
				.antMatchers("/group","/user").hasAnyRole("supervisor","admin")
			.anyRequest().authenticated();
//			.and().csrf().disable();
		
		http.logout()
        .logoutUrl("/logout") // 指定登出URL
        .permitAll();
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
		//設置靜態資源
        web
           .ignoring()
           .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**");
    }

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
}
