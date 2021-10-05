# Vector Chess on Three Planes
### It's like chess but better.

***What will the application do?***  

It's chess, but instead of taking place on a single plane in two-space, it takes place on three three-by
eight planes in three-space. Pieces move accordant to both their original movement style
and their original restrictions on moves. For example, bishops can move in any direction diagonally,
but in Vector Chess on Three Planes, they are able to move diagonally on the planes as well; that is, 
they can move to the diagonal squares on the planes above and below it.

Importantly, moving to another plane counts as moving one square. Pieces cannot go from the top plane
directly to the bottom plane; they must go through the middle plane.

That probably doesn't make much sense. 

**A detailed list of moves**:
- Pawns can, on their first move, move two spaces forward on the same plane, or move one space forward
  on the same plane, or move up to two squares directly upwards or downwards. After their first move, they
  can only move one space forward on the same plane, or move up to two squares directly upwards or downwards. 
  They can capture one square diagonally on the same plane, or capture one square forward in a plane directly above or below it. 


- Bishops can move any amount of squares only diagonally. They can move diagonally upwards or downwards as many planes
  as they wish.


- Knights can move either move exactly once upwards or downwards and then exactly two spaces in a cardinal
  direction in a plane, or move exactly two spaces upwards and downwards and then exactly one space in a 
  cardinal direction on a plane. They can jump over pieces like in normal chess.


- Rooks can move any amount of squares in a cardinal direction. They can move directly upwards or downwards
  as many planes as they wish.


- Queens can move either as a bishop, or as a rook, but only as one of them per move.


- Kings can move either one square cardinally or diagonally on a plane, or move directly upwards or downwards one plane,
  or move directly upwards or downwards one plane, and then once in any cardinal direction.

There is no castling, check or checkmate. You must take the opposing king to win.

***Who will use it?***

Gamers.

***Why is this project of interest to you?***

Chess is a fun game, but it would really benefit from some modern changes to make it more exciting. Because adding swords,
lances, realistic political maneuvering and court intrigue among the pieces would take too much time, I moved it to three boards instead.

======

# User stories: 
### Stories that users make.

- I should be able to make a new game board that puts all the pieces on their starting squares.


- I should be able to select pieces and, if I then select a legal move, move that piece to that square.


- I should be able to select pieces and, if I then select a legal capture, move that piece to that square and remove
  the opponent piece that was just on it.


- I should be able to move (add) pieces to a different plane.

