package gmu.isa681.project.othelloserver.model.request.game.play;

public class NewGameRequest {
	
	private Long requestSenderPlayerId;
	private boolean isBlack;
	private int gameTimeLimitInMinutes;
	
	public Long getRequestSenderPlayerId() {
		return requestSenderPlayerId;
	}
	public void setRequestSenderPlayerId(Long requestSenderPlayerId) {
		this.requestSenderPlayerId = requestSenderPlayerId;
	}
	public boolean isBlack() {
		return isBlack;
	}
	
	public void setIsBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}
	public int getGameTimeLimitInMinutes() {
		return gameTimeLimitInMinutes;
	}
	public void setGameTimeLimitInMinutes(int gameTimeLimitInMinutes) {
		this.gameTimeLimitInMinutes = gameTimeLimitInMinutes;
	}
	public NewGameRequest() {
		super();
	}
	
}
