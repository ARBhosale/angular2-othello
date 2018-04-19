package gmu.isa681.project.othelloserver.model.response;

public class AccountResponse {
	private Long id;
	private String userName;
	private String fullName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public AccountResponse(String userName, String fullName) {
		super();
		this.userName = userName;
		this.fullName = fullName;
	}
	public AccountResponse() {
		super();
	}
}
