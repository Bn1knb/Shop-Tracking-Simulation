# shopTrackingSimulation
This program simulates work of the shop merchandiser and track all of his moves.

The first solution about paking items on the shelf (in our case <b>2D bin packing problem</b>) is to use shelf algorithm 
wich is more preferable for perfomance at my point of view. (in the next parts i'll use the genetic algorithm to achive "<b>the best fit</b>" 
the shelf algorithm in my case is :
<ol>
<li>For each rectangle in the list of strings to pack:

<ol>
<li>If the rectangle does not fit in the current shelf:

<ol>
<li>Close the current shelf.</li>
<li>Open a new shelf with self height 0 and position of previous shelf level.</li>
</ol>
</li>
<li>Add the rectangle to the current shelf.

<ol>
<li>If the rectangle’s height is greater than the shelf’s height:

<ol>
<li>Set the shelf height to the rectangle’s height.</li>
</ol></li>
</ol></li>
</ol></li>

