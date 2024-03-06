//package com.deploy.demo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
////import com.deploy.demo.security.CustomAuthenticationProvider;
//
////@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	// [自訂AuthenticationProvider]
////	@Autowired
////	private CustomAuthenticationProvider customAuthenticationProvider;
//
//	/**
//	 * [管理請求URL的授權]部門主管能管理群組、經理能管理人員、資訊人員能管理權限
//	 */
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
////		super.configure(http);
//
//		// [管理請求的授權身份]部門主管管理群組、經理管理人員、資訊人員管理權限
//		// 不攔截的URL請求{ "/","/userlogin" }
//		// 其他URL請求需要通過身分驗證
//		http.authorizeRequests().antMatchers("/", "/userlogin").permitAll().antMatchers("/group/**")
//				.hasRole("supervisor").antMatchers("/user/**").hasRole("manager").antMatchers("/authority/**")
//				.hasRole("admin").antMatchers("/css/**").permitAll()
////				.antMatchers("/**").fullyAuthenticated();
//				.anyRequest().authenticated();
//
//		// [開啟SpringSecurity預設的登入功能]
//		http.formLogin().defaultSuccessUrl("/main", true).usernameParameter("account").passwordParameter("password")
//				.loginPage("/userlogin");
////    		.loginProcessingUrl("/userlogin");
//		// [登入功能]查看formLogin()更多相關功能
//		// [登入設定1]若無定義loginPage則使用預設的頁面/login；預設post方法來處理/login的請求；
//		// [登入設定2]若有設定loginPage則該URL的post方法為登入作用，亦能自定義loginProcessingUrl來處理post登入請求
//		// [登入設定3]Java配置會使Spring Security的CSRF防護啟用，在受攔截的頁面會已受到防護
//		// [登入設定4]讓對應的Parameter在自訂登入頁使用
//
//		// [開啟SpringSecurity預設的登出功能]
//		http.logout().logoutSuccessUrl("/information").invalidateHttpSession(true);
//		// [登出設定1]請求/logout，登出後轉向到自訂的登出頁
//		// [登出設定2]登出成功，清空session
//
//		// [開啟SpringSecurity的記住我功能]
//		// 在Session便會設置cookie remember-me
//		http.rememberMe().rememberMeParameter("remember");
//		// [記住我設定1]使用預設的remember-me
//		// [記住我設定2]使用自訂的remember
//
//		// [限制同時登入的使用者數量]
//		http.sessionManagement().sessionFixation().migrateSession() // 避免 session 固定攻擊
//				.maximumSessions(1).expiredUrl("/login?expired");
//	}
//
//	/**
//	 * [定義驗證規則]帳密符合則獲取該授權身分
//	 */
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		super.configure(auth);
//		
//		//[Web應用程式的預設帳號，用作測試、開發]
//		auth.inMemoryAuthentication().withUser("zac@mail.com").password(passwordEncoder().encode("Test1234"))
//				.roles("admin", "manager", "supervisor").and().withUser("chad@mail.com")
//				.password(passwordEncoder().encode("Test1234")).roles("admin", "manager").and()
//				.withUser("account@mail.com").password(passwordEncoder().encode("Password1234")).roles("supervisor");
//				
//		
//		//[使用自訂動態使用者驗證]
////		auth.authenticationProvider(customAuthenticationProvider);
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//}
