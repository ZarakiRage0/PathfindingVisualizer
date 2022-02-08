# PathfindingVisualizer
A Java-FX interface to help visualize pathfinding algorithms

# About 
The app displays pathfinding algorithms in a grid map in an animation.  
An MVP model was used to code the app ( Model, View, Presenter).

![App Demo](https://github.com/ZarakiRage0/PathfindingVisualizer/blob/main/pathfindingVisualizer.gif)

# Graph
The graph is a 2D grid map. 
Each node is represented by its cordinates (x,y) and its type ( Start, Goal, Empty, Wall ...).  


# Algorithms  
The Search Algorithm finds the path and then sends to the presenter the list of visited nodes during the search and the path to the goal.  

Currently, only Dijkstra Search ( Uniform Cost Search ) is implemented.  
You can add other search algorithms such as A* search or Greedy algo by relying on the interfaces provided for the algorithms in the program.
