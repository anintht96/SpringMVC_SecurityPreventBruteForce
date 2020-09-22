package spring.mvc.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Users",uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private boolean enable;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.PERSIST)
	private Set<UsersRoles> usersRoles;

	public User() {
		super();
	}

	public User(Integer id, String username, String password, boolean enable, Set<UsersRoles> usersRoles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.usersRoles = usersRoles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Set<UsersRoles> getUsersRoles() {
		return usersRoles;
	}

	public void setUsersRoles(Set<UsersRoles> usersRoles) {
		this.usersRoles = usersRoles;
	}

}
