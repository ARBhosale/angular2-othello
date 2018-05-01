package gmu.isa681.project.othelloserver.model.request;

public class UserAccountRequest {
    private Long id;
    private String username;
    private String data;

    public UserAccountRequest() {
        super();
    }

    public UserAccountRequest(Long id, String username, String data)    {
        super();
        this.id = id;
        this.username = username;
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
