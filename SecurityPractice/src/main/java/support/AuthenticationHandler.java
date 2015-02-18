package support;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
/**
 * AuthenticationSuccessHandler를 구현한
 * org.springframework.security.web.authentication.
 * 	SimpleUrlAuthenticationSuccessHandler,
 * 	SavedRequestAwareAuthenticationSuccessHandler 클래스 등이 있다.
 */
public class AuthenticationHandler implements AuthenticationSuccessHandler {

	
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
		 System.out.println("onAuthenticationSuccess Called");
	}

}
