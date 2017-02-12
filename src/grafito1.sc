

val n1 = Node(1)
val n2 = Node(2)
val n3 = Node(3)
val inputs = Adj(List(("left",n2),("up",n3)))
val outputs = Adj(List(("right",n2)))

val context1 = Context(inputs,n1,'a',outputs)
val context2 = Context(Adj(List()),n2,'b',Adj(List(("down",n3))))
val context3 = Context(Adj(List()),n3,'c',Adj(List()))

val contexts = And(context1,And(context2,And(context3,Empty)))
val g1 = IGraph(And(context1,Empty))

val g2 = And(context1.And(context2)
      .And(context3),Empty)

val g3 = context1 And(context2) And(context3) And(Empty)

