package chess3;

import java.io.IOException;

public interface Playable {
	public void makeMove(Player player, String piece, String square) throws IOException;
	public void resign(Player player);
	public void rewind(Player player, int howMany) throws IOException;
	public void help(Player player);
}
