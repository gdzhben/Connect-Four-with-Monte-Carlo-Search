# Connect-Four-with-Monte-Carlo-Search
The modified Connect-Four game Connect-Four in its standard form has a grid containing seven columns and six rows. In turn, players drop a disc (yellow for one player, blue for the other) into one of the columns (provided it is not full): it falls to the lowest row possible in that column. If a player gets four discs in an uninterrupted line, vertically or horizontally or diagonally, they win. If the grid is full and there is no winner, the game is drawn.

## The game you are to implement has three modifications.
1. The number of columns in any particular game may be any integer from six to eleven; the
experimenter must be able to choose how many columns to play with.
2. The columns are not all of the same height; the experimenter must be able to choose the
heights of the columns.
3. Two possible locations (row/column pairs) for discs do not count for making a line of four; the
experimenter must be able to choose any two locations.
For example, it should be possible to setup and play on a grid such as the one below left, where
pink "no" symbols are nonexistent parts of short columns, and green "star" symbols are locations
that do exist but do not count for making a line of four.

## Move input for human
If the experimenter is playing and it is their turn, they should be prompted for which column (A B
C etc) they wish to play in. If the column is full, their move should be rejected and another
prompt given.
## End of game detection
If a move by either player has resulted in a line of four that does not pass through a don't-count
cell, the game is over and - in a human-vs-computer game - the winner should be announced. If a
move results in the grid being full, a draw should similarly be announced.
## Computer choice of move
When the computer has to choose a move, it should play the move that wins immediately if there
is one. Otherwise, it should use a Monte Carlo technique. This means playing many out many
random trial sequences of moves for each player, until the end of the game. (Again, it should be
assumed that if at any point there is a move that wins immediately, it will be chosen. The trials
can therefore often stop just short of the end of the game.) Each such trial results in a win, a loss,
or a draw for the computer player. Each possible first move will have a ratio of wins:trials, the
move with the best ratio should be chosen.
