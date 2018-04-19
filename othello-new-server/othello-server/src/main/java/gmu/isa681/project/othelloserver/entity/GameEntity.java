package gmu.isa681.project.othelloserver.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Game")
public class GameEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
//	@NotNull
//	private PlayerEntity playerBlack;
//	@NotNull
//	private PlayerEntity playerWhite;
	
//	@NotNull
//	private Integer playerBlackScore;
//	@NotNull
//	private Integer playerWhiteScore;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public PlayerEntity getPlayerBlack() {
//		return playerBlack;
//	}
//	public void setPlayerBlack(PlayerEntity playerBlack) {
//		this.playerBlack = playerBlack;
//	}
//	public PlayerEntity getPlayerWhite() {
//		return playerWhite;
//	}
//	public void setPlayerWhite(PlayerEntity playerWhite) {
//		this.playerWhite = playerWhite;
//	}
//	public Integer getPlayerBlackScore() {
//		return playerBlackScore;
//	}
//	public void setPlayerBlackScore(Integer playerBlackScore) {
//		this.playerBlackScore = playerBlackScore;
//	}
//	public Integer getPlayerWhiteScore() {
//		return playerWhiteScore;
//	}
//	public void setPlayerWhiteScore(Integer playerWhiteScore) {
//		this.playerWhiteScore = playerWhiteScore;
//	}
//	public GameEntity(@NotNull PlayerEntity playerBlack, @NotNull PlayerEntity playerWhite) {
//		super();
//		this.playerBlack = playerBlack;
//		this.playerWhite = playerWhite;
//	}
	public GameEntity() {
		super();
	}

}
