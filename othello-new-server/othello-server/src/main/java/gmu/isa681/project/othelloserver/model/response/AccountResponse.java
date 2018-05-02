package gmu.isa681.project.othelloserver.model.response;

import gmu.isa681.project.othelloserver.model.Links;

public class AccountResponse {
	private Long id;
	private String userName;
	private String fullName;
	private Links links;
	
	public Links getLinks() {
		return links;
	}
	public void setLinks(Links links) {
		this.links = links;
	}
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
	public AccountResponse() {
		super();
	}
}
