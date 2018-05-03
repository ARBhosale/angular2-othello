package gmu.isa681.project.othelloserver.model.response;

import gmu.isa681.project.othelloserver.model.Links;

public class UserAccountResponse {

	private long id;
	private String username;
	private String password;
	private Links links;
	
	public UserAccountResponse() {
		super();
	}
	
	
	public UserAccountResponse(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
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


	public void setLinks(Links links) {
		this.links = links;
	}
		
	public Links getLinks() {
		return links;
	}
}
