BrainFlow
===

An esoteric language that is an extension to BrainF**k.

How is it different?
--

BrainFlow adds the following 3 commands in addition to the default commands from BrainF**k:

**^** Moves the pointer to the value indicated by the value at the current cell.

Ex: `We are at a cell that contains the value 4, calling ^ will move the pointer to the 4th cell.`

<br>

**=** Sets the value of the pointer to our current pointer index.

Ex: `We are at cell #4, calling = will set the value at this cell to 4.`

<br>

**&** Sets the value at the current pointer to the value contained in the pointer denoted by the pointer's current value.

Ex: `We are at cell 33 that contains the value 4, calling & will set the value in cell 33 to whatever the value in cell 4 is.`