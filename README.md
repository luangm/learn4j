# learn4j WIP
Machine Learning Algorithm written in Java.

## Architecture
The Learn4J architecture is based on an Graph of expressions.


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




# Graph Serialization

Graph is serialized as a JSON formatted file.
All the nodes (not the ones from gradient) are stored. If a node has a fixed tensor, it is stored in place.
The order of nodes are done by topological sort

String json = Learn4j.saveGraph(Graph)


# Implemented Expressions
| Op            | Eval  | Grad  |
| ---           | :---: | :---: |
| Abs           | Y     | Y     |
| Add           | Y     | Y     |
| Assign        |       |       |
| Cos           | Y     | Y     |
| Divide        | Y     | Y     |
| Exp           | Y     | Y     |
| Fill          | Y     |       |
| Group         | Y     |       |
| Log           | Y     | Y     |
| MatMul        | Y     | Y     |
| Mult          | Y     | Y     |
| Negate        | Y     | Y     |
| ReduceSum     | Y     | Y     |
| ReduceMean    | Y     | Y     |
| ReduceMin     | Y     |       |
| ReduceMax     | Y     |       |
| Relu          | Y     | Y     |
| Sigmoid       | Y     | Y     |
| Sign          | Y     |       |
| Sine          | Y     | Y     |
| Square        | Y     | Y     |
| SquareRoot    | Y     |       |
| Step          | Y     |       |
| Subtract      | Y     | Y     |
| Tangent       | Y     | Y     |
| Tanh          | Y     |       |
| Conv2d        | Y     |       |
| MaxPool       |       |       |
| Reciprocal    | Y     |       |
| Softmax       | Y     | Y     |
| LogSumExp     |       |       |
| AddN          |       |       |