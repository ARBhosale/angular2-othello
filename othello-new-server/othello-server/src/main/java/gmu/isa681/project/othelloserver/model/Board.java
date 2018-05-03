package gmu.isa681.project.othelloserver.model;

import java.util.ArrayList;

import gmu.isa681.project.othelloserver.entity.GameEntity;

public class Board {
	public static final int NUMBER_OF_ROWS = 8;
	public static final int NUMBER_OF_COLUMNS = 8;
	private ArrayList<ArrayList<Disc>> values;
	private ArrayList<ArrayList<Disc>> previousValues;
	private GameEntity gameEntity;
	private String moveResultMessage = "";

	public Board(GameEntity gameEntity, Board board) {
		super();
		this.gameEntity = gameEntity;
		this.initializeValues(board);
	}

	public Board getCopy() {
		return new Board(this.gameEntity, this);
	}

	public void loadValuesFromString(String valueString, ArrayList<ArrayList<Disc>> valuesToLoadInto) {
		int valueStringIndex = 0;
		for (int row = 0; row < Board.NUMBER_OF_ROWS; row++) {
			for (int col = 0; col < Board.NUMBER_OF_COLUMNS; col++) {
				char charInValueString = valueString.charAt(valueStringIndex);
				if (charInValueString == '0') {
					valuesToLoadInto.get(row).set(col, new Disc(null, row, col));
				} else if (charInValueString == '1') {
					valuesToLoadInto.get(row).set(col, new Disc(DiscType.Black, row, col));
				} else {
					valuesToLoadInto.get(row).set(col, new Disc(DiscType.White, row, col));
				}
				valueStringIndex++;
			}
		}
	}

	public String getValuesAsString(ArrayList<ArrayList<Disc>> valuesToConvert) {
		String convertedValues = "";
		for (int row = 0; row < Board.NUMBER_OF_ROWS; row++) {
			for (int col = 0; col < Board.NUMBER_OF_COLUMNS; col++) {
				Disc disc = valuesToConvert.get(row).get(col);
				if (null == disc) {
					convertedValues += "0";
				} else if (disc.getDiscType() == DiscType.Black) {
					convertedValues += "1";
				} else {
					convertedValues += "2";
				}
			}
		}
		return convertedValues;
	}

	private void initializeValues(Board boardToInitializeFrom) {
		this.values = new ArrayList();
		for (int row = 0; row < Board.NUMBER_OF_ROWS; row++) {
			this.values.add((new ArrayList()));
			for (int col = 0; col < Board.NUMBER_OF_COLUMNS; col++) {
				if (null != boardToInitializeFrom && null != boardToInitializeFrom.getValues().get(row).get(col)) {
					this.values.get(row).set(col, boardToInitializeFrom.getValues().get(row).get(col).getCopy());
				} else {
					this.values.get(row).add(null);
				}
			}
		}
	}

	public void initializeStartingPositions() {
		this.values.get(3).set(3, new Disc(DiscType.White, 3, 3));
		this.values.get(4).set(4, new Disc(DiscType.White, 4, 4));
		this.values.get(4).set(3, new Disc(DiscType.Black, 4, 3));
		this.values.get(3).set(4, new Disc(DiscType.Black, 3, 4));
	}

	public void flipDiscs(ArrayList<Disc> discs, DiscType discType) {
		for (int i = 0; i < discs.size(); i++) {
			discs.get(i).setDiscType(discType);
		}
	}

	public ArrayList<Disc> getSameDiscs(ArrayList<Disc> discsToGet) {
		ArrayList<Disc> discs = new ArrayList();
		for (int i = 0; i < discsToGet.size(); i++) {
			Disc discToGet = discsToGet.get(i);
			if (discsToGet.isEmpty()) {
				discs.add(null);
			}
			discs.add(this.values.get(discToGet.getRow()).get(discToGet.getCol()));
		}
		return discs;
	}

	private ArrayList<Disc> getRowOfDiscs(int rowNumber) {
		if (rowNumber >= 0 && rowNumber < Board.NUMBER_OF_ROWS) {
			return this.values.get(rowNumber);
		} else {
			return null;
		}
	}

	private ArrayList<Disc> getColumnOfDiscs(int colNumber) {
		if (colNumber >= 0 && colNumber < Board.NUMBER_OF_COLUMNS) {
			ArrayList<Disc> discs = new ArrayList();
			for (int i = 0; i < Board.NUMBER_OF_ROWS; i++) {
				discs.add(this.values.get(i).get(colNumber));
			}
			return discs;
		} else {
			return null;
		}
	}

	private ArrayList<Disc> reverseArrayList(ArrayList<Disc> list) {
		ArrayList<Disc> reversedList = new ArrayList();
		for (int i = list.size() - 1; i >= 0; i--) {
			reversedList.add(list.get(i));
		}
		return reversedList;
	}

	private ArrayList<Disc> getRightLeftDiagonalDiscs(int rowNumber, int colNumber) {
		ArrayList<Disc> diagonalDiscs = new ArrayList();
		int maxNumberOfDiagonalPositions = 8;
		// Northeast including current disc
		for (int i = 0; i < maxNumberOfDiagonalPositions; i++) {
			int discRow = rowNumber - i;
			int discCol = colNumber + i;
			if (discRow >= 0 && discRow < Board.NUMBER_OF_ROWS && discCol >= 0 && discCol < Board.NUMBER_OF_COLUMNS) {
				diagonalDiscs.add(this.values.get(discRow).get(discCol));
			}
		}
		diagonalDiscs = reverseArrayList(diagonalDiscs);
		// Southwest
		for (int i = 1; i < maxNumberOfDiagonalPositions; i++) {
			int discRow = rowNumber + i;
			int discCol = colNumber - i;
			if (discRow >= 0 && discRow < Board.NUMBER_OF_ROWS && discCol >= 0 && discCol < Board.NUMBER_OF_COLUMNS) {
				diagonalDiscs.add(this.values.get(discRow).get(discCol));
			}
		}

		return diagonalDiscs;
	}

	private ArrayList<Disc> getLeftRightDiagonalDiscs(int rowNumber, int colNumber) {
		ArrayList<Disc> diagonalDiscs = new ArrayList();
		int maxNumberOfDiagonalPositions = 8;
		// Northwest including current disc
		for (int i = 0; i < maxNumberOfDiagonalPositions; i++) {
			int discRow = rowNumber - i;
			int discCol = colNumber - i;
			if (discRow >= 0 && discRow < Board.NUMBER_OF_ROWS && discCol >= 0 && discCol < Board.NUMBER_OF_COLUMNS) {
				diagonalDiscs.add(this.values.get(discRow).get(discCol));
			}
		}
		diagonalDiscs = reverseArrayList(diagonalDiscs);
		// Southeast
		for (int i = 1; i < maxNumberOfDiagonalPositions; i++) {
			int discRow = rowNumber + i;
			int discCol = colNumber + i;
			if (discRow >= 0 && discRow < Board.NUMBER_OF_ROWS && discCol >= 0 && discCol < Board.NUMBER_OF_COLUMNS) {
				diagonalDiscs.add(this.values.get(discRow).get(discCol));
			}
		}
		return diagonalDiscs;
	}

	private ArrayList<Disc> getOutFlankedDiscsOutOfList(int userMoveRow, int userMovCol, DiscType userDiscType,
			ArrayList<Disc> listOfDiscs) {
		if (listOfDiscs.isEmpty()) {
			return new ArrayList();
		}
		int index = this.getIndexOfMoveDisc(userMoveRow, userMovCol, userDiscType, listOfDiscs);
		if (index < 0) {
			return new ArrayList();
		} else {
			ArrayList<Disc> leftApparentOutflankedDiscs = new ArrayList();
			// left of current move
			for (int i = index - 1; i >= 0; i--) {
				Disc boardDisc = listOfDiscs.get(i);

				if (null != boardDisc && i == 0) {
					leftApparentOutflankedDiscs = new ArrayList();
				}
				if (null != boardDisc) {
					leftApparentOutflankedDiscs = new ArrayList();
					break;
				}

				if (boardDisc.getDiscType() != userDiscType) {
					if (i == 0) {
						leftApparentOutflankedDiscs = new ArrayList();
					} else {
						leftApparentOutflankedDiscs.add(boardDisc);
					}
				} else if (boardDisc.getDiscType() == userDiscType) {
					break;
				}
			}
			// right of current move
			ArrayList<Disc> rightApparentOutflankedDiscs = new ArrayList();
			for (int i = index + 1; i < listOfDiscs.size(); i++) {
				Disc boardDisc = listOfDiscs.get(i);

				if (null != boardDisc && i == listOfDiscs.size() - 1) {
					rightApparentOutflankedDiscs = new ArrayList();
					continue;
				}
				if (null != boardDisc) {
					rightApparentOutflankedDiscs = new ArrayList();
					break;
				}

				if (boardDisc.getDiscType() != userDiscType) {
					if (i == listOfDiscs.size() - 1) {
						rightApparentOutflankedDiscs = new ArrayList();
					} else {
						rightApparentOutflankedDiscs.add(boardDisc);
					}
				} else if (boardDisc.getDiscType() == userDiscType) {
					break;
				}
			}

			leftApparentOutflankedDiscs.addAll(rightApparentOutflankedDiscs);

			return leftApparentOutflankedDiscs;
		}
	}

	public ArrayList<Disc> getOutflankedDiscs(int moveRow, int moveCol) {
		ArrayList<Disc> outFlankedDisks = new ArrayList();
		DiscType currentUserDiscType = this.getGameEntity().getCurrentTurn();
		this.values.get(moveRow).set(moveCol, new Disc(DiscType.Move, moveRow, moveCol));

		// horizontal outflanked disks
		ArrayList<Disc> horizontalDiscs = this.getOutFlankedDiscsOutOfList(moveRow, moveCol, currentUserDiscType,
				this.getRowOfDiscs(moveRow));
		// vertical outflanked disks
		ArrayList<Disc> verticalDiscs = this.getOutFlankedDiscsOutOfList(moveRow, moveCol, currentUserDiscType,
				this.getColumnOfDiscs(moveCol));
		// leftRight diagonal outflanked disks
		ArrayList<Disc> leftRightDiagonalDiscs = this.getOutFlankedDiscsOutOfList(moveRow, moveCol, currentUserDiscType,
				this.getLeftRightDiagonalDiscs(moveRow, moveCol));
		// rightLeft diagonal outflanked disks
		ArrayList<Disc> rightLeftDiagonalDiscs = this.getOutFlankedDiscsOutOfList(moveRow, moveCol, currentUserDiscType,
				this.getRightLeftDiagonalDiscs(moveRow, moveCol));

		this.values.get(moveRow).get(moveCol).setDiscType(null);

		outFlankedDisks.addAll(horizontalDiscs);
		outFlankedDisks.addAll(verticalDiscs);
		outFlankedDisks.addAll(leftRightDiagonalDiscs);
		outFlankedDisks.addAll(rightLeftDiagonalDiscs);

		return outFlankedDisks;
	}

	public boolean isAnyMovePossible() {
		// checking for non-zero outflanked disks in all positions. Breaks when atleast
		// one position found.
		for (int row = 0; row < Board.NUMBER_OF_ROWS; row++) {
			for (int col = 0; col < Board.NUMBER_OF_COLUMNS; col++) {
				Disc disc = this.values.get(row).get(col);
				if (null == disc) {
					continue;
				}
				ArrayList<Disc> outFlankedDiscs = this.getOutflankedDiscs(row, col);
				if (!outFlankedDiscs.isEmpty()) {
					return true;
				}
			}
		}
		return false;
	}

	private int getIndexOfMoveDisc(int userMoveRow, int userMovCol, DiscType userDiscType,
			ArrayList<Disc> listOfDiscs) {
		for (int i = 0; i < listOfDiscs.size(); i++) {
			if (listOfDiscs.get(i) == null) {
				continue;
			}
			if (listOfDiscs.get(i).getDiscType() == DiscType.Move) {
				return i;
			}
		}
		return -1;
	}

	public String getMoveResultMessage() {
		return moveResultMessage;
	}

	public void setMoveResultMessage(String moveResultMessage) {
		this.moveResultMessage = moveResultMessage;
	}

	public ArrayList<ArrayList<Disc>> getValues() {
		return values;
	}

	public void setValues(ArrayList<ArrayList<Disc>> values) {
		this.values = values;
	}

	public GameEntity getGameEntity() {
		return gameEntity;
	}

	public void setGameEntity(GameEntity gameEntity) {
		this.gameEntity = gameEntity;
	}

}
