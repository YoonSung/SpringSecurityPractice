package domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

public class MemberInfo implements UserDetails {

	private static final long serialVersionUID = 8038974744490492054L;

	@Override
	public String toString() {
		return "MemberInfo [id=" + id + ", name=" + name + ", authorities=" + authorities + "]";
	}

	/**
	 * Make by Programmer Start
	 */
	//계정 아이디
	private String id;
	
	//계정 비밀번호
	private String password;
	
	//계정 사용자의 이름
	//Spring Security에서 제공하지 않는 Custom Data
	private String name;
	
	//계정이 가지고 있는 권한 목록
	/*
	 * Spring Security에서 권한 객체는 org.springframework.security.core.GrantedAuthority 
	 * 인터페이스를 구현한 클래스 객체로 만들면 된다. 
	 * Spring Security에서는 이를 구현한 클래스를 4가지 제공하고 있으며
	 * 이중 org.springframework.security.core.SimpleGrantedAuthority클래스를 사용할 것이다.
	 * 권한을 저장하기 위한 구조는 단순하게 되어 있다.
	 * 예를 들어 "사용자 권한"이란 권한이 있다면 문자열 "사용자 권한"값을
	 * SimpleGrantedAuthority의 생성자 파라미터에 넣어주는 것으로 권한 객체 생성은 끝이 난다.
	 */
	private Set<GrantedAuthority> authorities;
	
	public MemberInfo(String id, String password, String name, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
	}
	
	//Get Source From import org.springframework.security.core.userdetails.User;
	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        // Ensure array iteration order is predictable (as per UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities =
            new TreeSet<GrantedAuthority>(new AuthorityComparator());

        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

	//Get Source From import org.springframework.security.core.userdetails.User;
    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to the set.
            // If the authority is null, it is a custom authority and should precede others.
            if (g2.getAuthority() == null) {
                return -1;
            }

            if (g1.getAuthority() == null) {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }
	
	/**
	 * Make by Programmer End
	 **/
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return getId();
	}

	/**
	 * 패스워드 만료를 체크하거나 계정 사용 가능 여부 등의 기능은 
	 * 사용하지 않을 것이기 때문에 무조건 true를 리턴하게끔 만들었다.
	 * 만약 사용해야 한다면 이것을 검사하는 로직을 넣어서 구현하면 된다.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
