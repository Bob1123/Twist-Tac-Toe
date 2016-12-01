# Twist-Tac-Toe
CSE 271 Project with John Biggs and Bob Krueger; game is played on a 6x6 board in an attempt to get 5 in a row, where corners of the board may twist.

Here is a list of dependencies and methods that our classes have.

GameObject implements Drawable:

x, y, width, height

drawFillRect, drawFillOval

draw

----------------------------------------------------------------------------

GameBoard extends GameObject:

CompBoards: NE, NW, SE, SW

TwistArrows: NEN, NEE, NWW, NWN, SEE, SES, SWS, SWW

draw

checkWin (recursive)

-----------------------------------------------------------------------------

CompBoard extends GameObject:

GamePiece double array board

draw

-----------------------------------------------------------------------------

GamePiece extends GameObject:

type

draw

-----------------------------------------------------------------------------

TwistArrow extends GameObject:

used

draw

----------------------------------------------------------------------------

PieceType:

Enum for GamePiece's type

---------------------------------------------------------------------------

Drawable:

Interface contains draw

----------------------------------------------------------------------------

Player:
?

-------------------------------------------------------------------------------

CompType:
? Enum for CompBoard

-----------------------------------------------------------------------------------

TwistType:
? Enum for TwistArrow

--------------------------------------------------------------------------------

Movable:
? For animations

---------------------------------------------------------------------------------

CompPlayer:
?

-----------------------------------------------------------------------------------

GameMove:
? Object for storing game moves for the purposes of undoing