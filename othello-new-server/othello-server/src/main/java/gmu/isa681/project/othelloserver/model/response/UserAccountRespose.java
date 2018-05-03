package gmu.isa681.project.othelloserver.model.response;

import gmu.isa681.project.othelloserver.model.Links;

public class UserAccountRespose {
    private Long id;
    private String username;
    private String data;
    private Links links;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public UserAccountRespose() {
        super();
    }

    public UserAccountRespose(String data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
