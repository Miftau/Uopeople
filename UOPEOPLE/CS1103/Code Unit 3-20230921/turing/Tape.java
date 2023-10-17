package turing;

/**
 * Represents Turing machine tapes..
 */

class Tape {

	private Cell currentCell;

	Tape() {
		this.currentCell = new Cell();
	}

	/**
	 * returns the pointer that points to the current cell.
	 * 
	 * @return Cell current cell
	 */
	public Cell getCurrentCell() {
		return currentCell;
	}

	/**
	 * returns the char from the current cell.
	 */

	public char getContent() {
		return currentCell.content;
	}

	/**
	 * changes the char in the current cell to the specified value.
	 * 
	 * @param ch
	 */

	public void setContent(char ch) {
		currentCell.content = ch;
	}

	/**
	 * public void moveLeft() -- moves the current cell one position to the left
	 * along the tape. Note that if the current cell is the leftmost cell that
	 * exists, then a new cell must be created and added to the tape at the left of
	 * the current cell, and then the current cell pointer can be moved to point to
	 * the new cell. The content of the new cell should be a blank space. (Remember
	 * that the Turing machine's tape is conceptually infinite, so your linked list
	 * must be prepared to expand on-demand when the machine wants to move past the
	 * current end of the list.)
	 */

	public void moveLeft() {
		if (currentCell.prev == null) {
			Cell newCell = new Cell();
			newCell.next = currentCell;
			currentCell.prev = newCell;
			currentCell = newCell;
		} else {
			currentCell = currentCell.prev;
		}
	}

	/**
	 * public void moveRight() -- moves the current cell one position to the right
	 * along the tape. Note that if the current cell is the rightmost cell that
	 * exists, then a new cell must be created and added to the tape at the right of
	 * the current cell, and then the current cell pointer can be moved to point to
	 * the new cell. The content of the new cell should be a blank space.
	 * public String getTapeContents() -- returns a String consisting of the chars
	 * from all the cells on the tape, read from left to right, except that leading
	 * or trailing blank characters should be discarded. The current cell pointer
	 * should not be moved by this method; it should point to the same cell after
	 * the method is called as it did before. You can create a different pointer to
	 * move along the tape and get the full contents. (This method is the hardest
	 * one to implement.)
	 */

	public void moveRight() {
		if (currentCell.next == null) {
			Cell newCell = new Cell();
			newCell.prev = currentCell;
			currentCell.next = newCell;
			currentCell = newCell;
		} else {
			currentCell = currentCell.next;
		}
	}

	/**
	 * returns a String consisting of the chars from all the cells on the tape,
	 * read from left to right, except that leading or trailing blank characters
	 * should be
	 * discarded. The current cell pointer should not be moved by this method;
	 * it should point to the same cell after the method is called as it did before.
	 * You can create a different pointer to move along the tape and get the full
	 * contents.
	 * (This method is the hardest one to implement.)
	 * 
	 * @return String content of the tap
	 */

	public String getTapeContents() {
		Cell runner = new Cell();
		runner = currentCell;
		String str = "";
		while (runner.prev != null) {
			runner = runner.prev;
		}

		do {
			str += runner.content;
			runner = runner.next;

		} while (runner != null);

		return str;
	}
}
