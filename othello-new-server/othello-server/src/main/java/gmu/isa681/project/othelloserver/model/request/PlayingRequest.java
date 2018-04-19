package gmu.isa681.project.othelloserver.model.request;

public class PlayingRequest {
	private Long id;
	private boolean isBlack;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isBlack() {
		return isBlack;
	}

	public void setBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}

	public PlayingRequest(Long id, boolean isBlack) {
		super();
		this.id = id;
		this.isBlack = isBlack;
	}

	public PlayingRequest() {
		super();
	}

}
