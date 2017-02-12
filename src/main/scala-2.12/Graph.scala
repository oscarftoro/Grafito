/**
  * Created by oscarftoro on 12-02-17.
  * implementation based on Martin Erwig's Inductive Graph
  * as presented in the Inductive Graphs and Functional Graph Algorithms paper
  *
  */
//Where type C corresponds to an Int type in the original paper

sealed trait GraphPiece[+A,+B,+C]
case class  Node[+A,+B,+C](id:C) extends GraphPiece[A,B,C]
case class  Adj[+A,+B,+C](adjList: Seq[(B,Node[A,B,C])])
  extends GraphPiece[A,B,C]

sealed trait Graph[+A,+B,+C] {
  def And[D >: A, E >: B, F >: C](graph: Graph[D, E, F]): And[D,E,F] =
    new And(graph, this)
}
  case object Empty extends Graph[Nothing, Nothing, Nothing]
  case class  Context[+A,+B,+C](inputs: Adj[A,B,C],node: Node[A,B,C],a: A
                              ,outputs: Adj[A,B,C]) extends Graph[A,B,C]
  case class And[+A, +B, +C](graph: Graph[A, B, C]
                             , graph2: Graph[A, B, C]) extends Graph[A, B, C]


class IGraph[+A,+B,+C](graph: Graph[A,B,C]) extends Graph[A,B,C] {
  override def And[D >: A, E >: B, F >: C](graph2: Graph[D, E, F]): And[D,E,F] =
    new And(graph2, graph)

}
object IGraph {

  def apply[A,B,C](ig:Graph[A,B,C]): Graph[A,B,C] = new IGraph(ig)

}