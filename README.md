# Shop Tracking Simulation

<img src="https://img.shields.io/badge/coverage-34%25-yellow"> <img src="https://img.shields.io/badge/version-1.0-blue"> <img src="https://img.shields.io/badge/sonarQube%20code%20quality-A-brightgreen">
</br>
This program simulates work of the shop merchandiser and track all of his moves.

The first solution about paking items on the shelf (in our case <b>2D bin packing problem</b>) is to use shelf algorithm 
wich is more preferable for perfomance at my point of view.
<p><b>Technology stack:</b></p>

<ul>
  <li><code>Spring boot</code></li> 
  <li><code>Google Guava lib</code></li>
  <li><code>Spring data JPA</code></li>
  
  <ol>Database:</ol><ul>
            <li><code>Postgresql</code></li>
            <li><code>Liqubase</code></li>
            <li><code>Hibernate envers</code></li>
  </ul>
  
  <ol>Logging:</ol><ul>
            <li><code>Log4j</code></li>
            <li><code>SL4j</code></li>
  </ul>
  <ol>Spicing:</ol><ul>
            <li><code>Lombok</code></li>
  </ul>
  <ol>Testing:</ol><ul>
            <li><code>JUnit 5</code></li>
            <li><code>Mockito</code></li>
  </ul>
  <ol>CI CD tools used:</ol><ul>
            <li><code>Jenkins</code></li>
            <li><code>SonarQube</code></li>
  </ul>
  
 <ol>Documentation:</ol><ul>
            <li><code>Swagger</code></li>
  </ul>
</ul>


The algorithm i used in my case is customised NFDH (Next Fit Decreasing High) packing algorithm: 
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


