package support;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import domain.MemberInfo;
/**
 * AuthenticationSuccessHandler를 구현한
 * org.springframework.security.web.authentication.
 * 	SimpleUrlAuthenticationSuccessHandler,
 * 	SavedRequestAwareAuthenticationSuccessHandler 클래스 등이 있다.
 */
/*
 * Authentication~Handler를 implements해서 customizing하게되면
 * URL이동에 대한 항목들도 이루어지지 않는다.
 * 즉, 데이터 Session 저장등을 하지 않는 것이다.
 * 
 * authentication-failure-handler-ref 속성을 지정하지 않았다면
 * Security는 자체적으로 AuthenticationFailureHandler 인터페이스를 구현한 클래스인
 * org.springframwork.security.web.authentication.ExceptionMappingAuthenticationFailurehandler 클래스를 이용한다.
 */
public class AuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationHandler.class);
	/**
	 * 웹으로 넘어온 HttpServletRequest , HttpServletResponse 객체를 사용할 수 있으며
	 * 로그인 한 회원의 회원정보 (authentication.getPrincipal())를 가져올 수 있다.
	 * 
	 * 회원별 방문수 증가 작업 등을 처리가능한 것이다.
	 */
	/**
	 * org.springframework.security.web.savedrequest.RequestCache
	 * 로그인 화면을 보여주기 전에 사용자 요청을 저장하고 이를 꺼내오는 메카니즘을 정의하는 인터페이스
	 * 사용자 요청은  savedRequest 인터페이스를 구현한 클래스 단위로 저장된다.
	 * 즉, 사용자가 요청했던 request 파라미터 값들, 그 당시의 헤더값 들 등이
	 * SavedRequest 인터페이스를 구현한 클래스에 담겨지게 되는 것이다.
	 * 
	 * Spring Security는 RequestCache 인터페이스를 구현한 클래스로 
	 * org.springframework.security.web.HttpSessionRequestCache 
	 * 클래스를 제공하는데 "사용자 요청을 세션에 저장한다"함은 바로
	 * 이 HttpSessionRequestCache 클래스를 이용해서 사용자 요청 정보들이 들어있는
	 * DefaultSavedRequest(RequestCache 인터페이스를 구현한 클래스) 객체를 세션에 저장한다는 뜻이다. 
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException,ServletException {
		 log.info("onAuthenticationSuccess Called, User : {}", ((MemberInfo)authentication.getPrincipal()).toString());
	}

	

	/**
	 * Spring Security는 로그인 실패에 대한 데이터 Session에 저장한다.
	 * 하지만 악의적인 접근이 발생할 경우, 이러한 세션데이터는
	 * Full GC를 발생시키고, 웹서비스의 성능에 지대한 영향을 줄 수 있다.
	 * 그렇기 때문에 requestAttribute에 데이터를 전달하는 방식으로 변경한다.
	 */
	private String defaultFailureUrl;
	private String exceptionMessageKey;
	
	
	public AuthenticationHandler() {
		this.defaultFailureUrl = "/login";
		this.exceptionMessageKey = "securityException";
	}
	
	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}
	
	public void setExceptionMessageKey(String exceptionMessageKey) {
		this.exceptionMessageKey = exceptionMessageKey;
	}
	
	/**
	 * @see WebAttributes (Well-known keys which are used to store Security information in request or session scope 
	 * http://docs.spring.io/spring-security/site/docs/3.0.x/apidocs/org/springframework/security/web/WebAttributes.html
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)throws IOException, ServletException {
		log.info("onAuthenticationFailure Called, Error : {}", exception.getMessage());
		
		/*
		 * Request 객체의 Attribute에 사용자가 실패시 입력했던 로그인 ID와 비밀번호를
		 * 저장해두어 로그인 페이지에서 이를 다시 접근할 수 있도록 한다.
		 */
		String loginId = request.getParameter("loginId");
		String loginPassword = request.getParameter("loginPw");
		
		request.setAttribute("loginId", loginId);
		request.setAttribute("loginPassword", loginPassword);
		
		//데이터를 request Attribute로 저장
		request.setAttribute(exceptionMessageKey, exception.getMessage());
		
		//redirect하면 request Attribute가 날아가기때문에 forward 해줘야 한다.
		request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
	}

}
