# learn4j WIP
Machine Learning Algorithm written in Java.

## Architecture
The Learn4J algorithms are based on TWO graphs: an Expression graph and a Compute Graph, similar to Google's Tensorflow.

The Expression Graph is where the user specifies all the mathematical expressions to form an algorithm.

The Compute Graph is generated internally and organized for efficient computation.


## Expression interface
The Expression interface is the base interface for all Math Expressions.

All subclasses should implement this interface, and all normal operations should refer to Expression.

### Expression API
Expression interface contains very primitive methods:
  * getName
  * getComputeNode
  * accept(ExpressionVisitor)

Note: There is NO .children() method available. To iterate through all the child nodes,
use the visitor pattern and apply it to the node required.


### Graph API
Graph is the container class for all Expressions trees. i.e. Graph is the "forest"

Graph may contain multiple independent Expression trees.

Graph contains the full picture of the Expressions, thus can optimize the operation order to best suit.

API:
  * add(Expression)
  * get(String)
  * accept(ExpressionVisitor)

in _accept_ method, the Graph is responsible for finding the root nodes to each expression tree,
then pass the visitor to each root node