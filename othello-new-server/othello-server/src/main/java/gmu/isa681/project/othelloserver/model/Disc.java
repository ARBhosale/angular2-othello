package gmu.isa681.project.othelloserver.model;

public class Disc {
	private DiscType discType;
	private int row;
	private int col;

	public DiscType getDiscType() {
		return discType;
	}

	public void setDiscType(DiscType discType) {
		this.discType = discType;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Disc(DiscType discType, int row, int col) {
		super();
		this.discType = discType;
		this.row = row;
		this.col = col;
	}

	public Disc getCopy() {
		return new Disc(this.discType, this.row, this.col);
	}

}
