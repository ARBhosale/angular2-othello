package gmu.isa681.othello.model.request;

public class GameRequest {
	private Long playerId;

	public GameRequest() {
		super();
	}

	public GameRequest(Long playerId) {
		super();
		this.playerId = playerId;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
}
