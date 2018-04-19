package gmu.isa681.project.othelloserver.model.response;

import gmu.isa681.project.othelloserver.model.Links;

public class PlayingResponse {
	private Long id;
	private Long gameNumber;
	private boolean hasBlack;
	private Links links;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGameNumber() {
		return gameNumber;
	}

	public void setGameNumber(Long gameNumber) {
		this.gameNumber = gameNumber;
	}

	public boolean isHasBlack() {
		return hasBlack;
	}

	public void setHasBlack(boolean hasBlack) {
		this.hasBlack = hasBlack;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public PlayingResponse(Long gameNumber) {
		super();
		this.gameNumber = gameNumber;
	}

	public PlayingResponse() {
		super();
	}

}
