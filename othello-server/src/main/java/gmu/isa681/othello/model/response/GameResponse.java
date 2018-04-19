package gmu.isa681.othello.model.response;

public class GameResponse {
	private Long id;
	private Long playerIdBlack;
	private Long playerIdWhite;
	private Long playerScoreBlack;
	private Long playerScoreWhite;
	
	public GameResponse() {
		super();
	}
	public GameResponse(Long id, Long playerIdBlack, Long playerIdWhite, Long playerScoreBlack, Long playerScoreWhite) {
		super();
		this.id = id;
		this.playerIdBlack = playerIdBlack;
		this.playerIdWhite = playerIdWhite;
		this.playerScoreBlack = playerScoreBlack;
		this.playerScoreWhite = playerScoreWhite;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPlayerIdBlack() {
		return playerIdBlack;
	}
	public void setPlayerIdBlack(Long playerIdBlack) {
		this.playerIdBlack = playerIdBlack;
	}
	public Long getPlayerIdWhite() {
		return playerIdWhite;
	}
	public void setPlayerIdWhite(Long playerIdWhite) {
		this.playerIdWhite = playerIdWhite;
	}
	public Long getPlayerScoreBlack() {
		return playerScoreBlack;
	}
	public void setPlayerScoreBlack(Long playerScoreBlack) {
		this.playerScoreBlack = playerScoreBlack;
	}
	public Long getPlayerScoreWhite() {
		return playerScoreWhite;
	}
	public void setPlayerScoreWhite(Long playerScoreWhite) {
		this.playerScoreWhite = playerScoreWhite;
	}
	
	
}
