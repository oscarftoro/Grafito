/**
  * Created by oscarftoro on 12-02-17.
  * implementation based on Martin Erwig's Inductive Graph
  * as presented in the Inductive Graphs and Functional Graph Algorithms paper
  *
  */

sealed trait GraphPiece[A,B]
case class  Node[A,B](id: Int) extends GraphPiece[A,B]
case class  Adj[A,B](adjList: Seq[(B,Node[A,B])]) extends GraphPiece[A,B]
case class  Context[A,B](inputs: Adj[A,B],node: Node[A,B],a: A,outputs: Adj[A,B]) extends GraphPiece[A,B]

sealed trait Graph[A,B]
case object Empty extends Graph[Nothing,Nothing]
case class  And[A,B](context: Context[A,B],graph: Graph[A,B]) extends Graph[A,B]

class IGraph[A,B](graph: Graph[A,B]) extends Graph[A,B] {

  def And [C >: A, D >: B](context: Context[C,D]): Graph[C,D] = new And(context,graph)

}

object IGraph {

  def apply[A,B](ig:Graph[A,B]): Graph[A,B] = new IGraph(ig)

}