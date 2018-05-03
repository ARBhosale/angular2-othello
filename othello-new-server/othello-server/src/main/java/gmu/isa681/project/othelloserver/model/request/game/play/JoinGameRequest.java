package gmu.isa681.project.othelloserver.model.request.game.play;

public class JoinGameRequest {

	private Long gameId;
	private Long requestSenderPlayerId;
	
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public Long getRequestSenderPlayerId() {
		return requestSenderPlayerId;
	}
	public void setRequestSenderPlayerId(Long requestSenderPlayerId) {
		this.requestSenderPlayerId = requestSenderPlayerId;
	}

}
