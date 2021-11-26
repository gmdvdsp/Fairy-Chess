# Chess With Dragon(s)
### It's like chess but better.

***What will the application do?***  

It's fairy-chess that takes place on an 10 x 8 board rather than a 8 x 8 board, sans castling, checks, or checkmates,
or promotions. These are features that will come later (I ran out of time). To win, you just have to capture the king. 
Two new pieces have been added to either side of the rooks in normal chess. Hence the titular dragons.

**A detailed list of moves**:
- Pawns can, on their first move, move two spaces forward on the same plane, or else move one space forward
  as long as both squares are empty. They capture exactly one square diagonally.


- Bishops can move any amount of squares only diagonally, as long as that diagonal is not blocked by other pieces. They
  capture the square they land on.


- Knights can move exactly two squares in any cardinal direction, and then one square orthogonally of that move. They
  can jump over pieces. They capture the square they land on.


- Rooks can move any amount of squares in a cardinal direction, as long as that file or rank is not blocked by other
  pieces. They capture the square they land on.


- Queens can move either as a bishop, or as a rook, but only as one of them per move.


- Kings can move either exactly one square cardinally or diagonally. They capture the square they land on.


- Dragons start on the left of the kingside Rook. They can move either as a knight or a bishop, but only as one of them
  per move. They capture the square they land on.


- Princesses start on the right of the queenside Rook. They can move either as a knight or a rook, but only as one of
  them per move. They capture the square they land on.


Again, there is no castling, check or checkmate. You must take the opposing king to win.

***Who will use it?***

Gamers. And people who like chess. And maybe even people who like dragons.

***Why is this project of interest to you?***

Chess is a fun game, but it is actually very lame and nerdy because it lacks any dragons. I therefore added dragons and
have thus improved the game by a factor of 35%.

======

# User stories: 
### Stories that users make.

- I should be able to make a new game board that puts all the pieces on their starting squares.


- I should be able to select pieces and, if I then select a legal move, move that piece to that square.


- I should be able to select pieces and, if I then select a legal capture, move that piece to that square and remove
  the opponent piece that was just on it.


- I should be able to view a list of pieces removed from the board.


- I should be able to capture an opponent's king to end the game.


- As a user, I want to be able to save my game to file


- As a user, I want to be able to be able to load my game from file 

# Phase 4:
### EventLog Sample:


The EventLog prints:

- All loaded pieces in the form "color piece loaded on (x,y),) if there were any.


- All captured pieces loaded in the form form "Captured color piece loaded," if there were any.


- All moves made during runtime in the form "color piece to (x,y)."


- All captures made by one piece as "color capturer takes color capturee on (x,y)."


Sample:

white Knight loaded on (2,0). (Thu Nov 25 21:21:08 PST 2021)
white Bishop loaded on (3,0). (Thu Nov 25 21:21:08 PST 2021)
white King loaded on (4,0). (Thu Nov 25 21:21:08 PST 2021)
white Bishop loaded on (6,0). (Thu Nov 25 21:21:08 PST 2021)
white Knight loaded on (7,0). (Thu Nov 25 21:21:08 PST 2021)
white Rook loaded on (8,0). (Thu Nov 25 21:21:08 PST 2021)
white Princess loaded on (9,0). (Thu Nov 25 21:21:08 PST 2021)
white Pawn loaded on (0,1). (Thu Nov 25 21:21:08 PST 2021)
white Pawn loaded on (1,1). (Thu Nov 25 21:21:08 PST 2021)
white Pawn loaded on (2,1). (Thu Nov 25 21:21:08 PST 2021)
white Pawn loaded on (3,1). (Thu Nov 25 21:21:08 PST 2021)
white Pawn loaded on (5,1). (Thu Nov 25 21:21:08 PST 2021)
white Pawn loaded on (6,1). (Thu Nov 25 21:21:08 PST 2021)
white Pawn loaded on (7,1). (Thu Nov 25 21:21:08 PST 2021)
white Pawn loaded on (8,1). (Thu Nov 25 21:21:08 PST 2021)
white Pawn loaded on (9,1). (Thu Nov 25 21:21:08 PST 2021)
white Queen loaded on (0,4). (Thu Nov 25 21:21:08 PST 2021)
black Queen loaded on (5,4). (Thu Nov 25 21:21:08 PST 2021)
white Pawn loaded on (0,6). (Thu Nov 25 21:21:08 PST 2021)
black Pawn loaded on (1,6). (Thu Nov 25 21:21:08 PST 2021)
black Pawn loaded on (2,6). (Thu Nov 25 21:21:08 PST 2021)
black Pawn loaded on (3,6). (Thu Nov 25 21:21:08 PST 2021)
black Pawn loaded on (4,6). (Thu Nov 25 21:21:08 PST 2021)
black Pawn loaded on (6,6). (Thu Nov 25 21:21:08 PST 2021)
black Pawn loaded on (7,6). (Thu Nov 25 21:21:08 PST 2021)
black Pawn loaded on (8,6). (Thu Nov 25 21:21:08 PST 2021)
black Pawn loaded on (9,6). (Thu Nov 25 21:21:08 PST 2021)
black Dragon loaded on (0,7). (Thu Nov 25 21:21:08 PST 2021)
black Rook loaded on (1,7). (Thu Nov 25 21:21:08 PST 2021)
black Knight loaded on (2,7). (Thu Nov 25 21:21:08 PST 2021)
black Bishop loaded on (3,7). (Thu Nov 25 21:21:08 PST 2021)
black King loaded on (4,7). (Thu Nov 25 21:21:08 PST 2021)
black Bishop loaded on (6,7). (Thu Nov 25 21:21:08 PST 2021)
black Knight loaded on (7,7). (Thu Nov 25 21:21:08 PST 2021)
black Rook loaded on (8,7). (Thu Nov 25 21:21:08 PST 2021)
black Princess loaded on (9,7). (Thu Nov 25 21:21:08 PST 2021)
Captured black Pawn loaded. (Thu Nov 25 21:21:08 PST 2021)
Captured white Pawn loaded. (Thu Nov 25 21:21:08 PST 2021)
Captured black Dragon loaded. (Thu Nov 25 21:21:08 PST 2021)
Captured white Rook loaded. (Thu Nov 25 21:21:08 PST 2021)
Captured black Queen loaded. (Thu Nov 25 21:21:08 PST 2021)
black Queen takes white Queen at (0,4). (Thu Nov 25 21:21:10 PST 2021)
white Pawn to (2,2). (Thu Nov 25 21:21:13 PST 2021)
black Queen takes white Pawn at (2,2). (Thu Nov 25 21:21:14 PST 2021)


======

### Refactoring:

There is much more refactoring that I would like to do, given time.

- The most glaring is that there are currently 8 different Piece classes that all abstract a BasePiece. This is 
  inherently suspicious. It would likely be better if they were all compacted into some enum to facilitate adding new
  pieces to the game.


- Each piece has it's own toJSON and print methods when this should really just compacted to a single non-abstract
  .getClass.getSimpleName() method in the abstract class.


- The glaringly ugly if cases in the Game and JSonReader classes for judging which piece should be re-examined. One
  approach could be to pull them up into the abstract method, but this sacrifices the abstract method knowing about the
  board which is dangerous. Another approach could be to pull movement into some PieceRules class that each Piece then
  has, and then switch this into each piece's function in their own classes. But this is still somewhat dangerous 
  because it implies that pieces know how to move, when they really shouldn't.


- The game currently processes captures every turn no matter if a capture was made. This is easy enough to fix, and just
  involves making it so there must be a piece both on the from and to squares to actually process a capture.


- The Player and Game association is inherently suspicious. Currently, all requested moves are processed through the 
  players, and then they tell the Game to move pieces, which are then moved on the Board. However, this doesn't really 
  respect the fact that a game has two players. It might be prudent to make Game and Player a bidirectional association
  with two players with a color to a game, and ensuring that they then request moves only for their own color. This also
  fixes the worrisome problem of the UI currently splitting the two captured piece colors inside the UI class. Really, 
  this functionality seems like it should go inside the Players classes.


- The functions inside the Board that test diagonal movement are somewhat suspicious. With some method refactoring, it's 
  definitely possible to pull the isDiagonalUpperLeftLowerRight and isDiagonalUpperRightLowerLeft methods into a single 
  function.


- Tests do not respect a single point of control for board limits (although really, this will never change), and are 
  woefully in-efficient. They don't make use of a runBefore to instantiate some sample squares.


- The main Chess class which handles the parent JFrame has a GamePanel, TopPanel, and CapturedPiecePanel, but GamePanel
  also has the same TopPanel and CapturedPanel, which then updates these panels. TopPanel and CapturedPanel should
  extend some Observer class so that they can be updated from GamePanel.


- Every UI Panel *should* really only have access to the Player, the universal top level of entry into the game, and 
  shouldn't really have associations with anything below this. The single worrisome class that breaks this idea is the
  capturedPiecePanel, which relies on a list of an old captured list to compare with the current captured list. This is 
  bad, and really, update should just assume that something was actually captured. This bleeds into the next bad design
  point.


- The Game cannot discriminate between a capture and a move. It only checks for move legality, when really it should 
  also inform any classes that would like to know whether a capture was made or not. This would allow capturedPiecePanel 
  to better simplify it's update method, as captured lists are only changing if something was actually captured.  


- The only piece that captures differently from how it moves is Pawns. So the captureLegality method in Game should not
  need a giant if case to switch into different capture methods, although I suppose this helps if we decide to change 
  how pieces capture in the future. See bullet point three.

CREDITS:

Princess icons: By Original: LithiumFlashThis version: CheChe - This file was derived from: Chess Mlt45.png:, CC BY-SA 4.0, https://commons.wikimedia.org/w/index.php?curid=57145018
Every Other Icon: https://commons.wikimedia.org/wiki/Category:SVG_chess_pieces


