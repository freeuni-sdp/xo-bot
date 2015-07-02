package ge.freeuni.sdp.xo.bot;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * copied from https://github.com/freeuni-sdp/xo-game/blob/master/src/main/java/ge/edu/freeuni/sdp/xo/game/GameState.java
 */
//should be used for bot's game logic
@XmlRootElement
public class GameState {
	public static final int STATUS_PENDING = 0;
	public static final int STATUS_RUNNING = 1;
	public static final int STATUS_FINISHED = 2;

	private static final Set<Integer> validCells;
	private static final int[] winners;
	static {
		validCells = new HashSet<>();
		for (int i = 0; i < 9; i++)
			validCells.add((int) Math.pow(2, i));

		winners = new int[] { 7, 56, 448, 73, 146, 292, 273, 84 };
	}

	@XmlElement
	public int status;

	@XmlElement
	public List<Integer> table;

	@XmlElement
	public String winner;

	private String player1;
	private String player2;
	private String active;

	public GameState() {
		status = STATUS_PENDING;
		table = new ArrayList<>();
		winner = null;
		player1 = player2 = active = null;
	}
	public Set<Integer> getValidCells(){
		return null;
	}
	public boolean addPlayer(String playerID) {
		if (status == STATUS_PENDING) {
			if (player1 == null)
				player1 = playerID;
			else {
				if (player1.equals(playerID) || player2 != null)
					return false;

				player2 = playerID;
				status = STATUS_RUNNING;
				active = player1;
			}
			return true;
		}
		return false;
	}

	public boolean makeMove(String playerID, int cell) {
		if (status != STATUS_RUNNING)
			return false;
		if (!checkValidCell(cell))
			return false;
		if (!active.equals(playerID))
			return false;
		table.add(cell);
		if (checkWinner(playerID)) {
			winner = playerID;
			status = STATUS_FINISHED;
		} else
			switchActivePlayer();

		return true;
	}

	public String getWinner() {
		return winner;
	}

	protected boolean checkWinner(String player) {
		int i = player.equals(player1) ? 0 : 1;
		int sum = 0;
		for (; i < table.size(); i += 2) {
			sum += table.get(i);
		}
		for (i = 0; i < winners.length; i++) {
			if ((winners[i] & sum) == winners[i])
				return true;
		}
		return false;
	}

	protected void switchActivePlayer() {
		if (active.equals(player1))
			active = player2;
		else
			active = player1;
	}

	private boolean checkValidCell(int cell) {
		return (!table.contains(cell)) && validCells.contains(cell);
	}

	@Override
	public String toString() {
		return status + ", " + player1 + " : " + player2 + ", " + table + ", "
				+ active;
	}

}