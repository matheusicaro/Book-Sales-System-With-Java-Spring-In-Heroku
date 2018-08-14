package matheusicaro.com.github.store.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String name;
	
	@Override
	public String getAuthority() {
		return this.name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Role(){
	}

	public Role(String name) {
	  this.name = name;
	}
	
	
}
