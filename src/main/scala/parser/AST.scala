package parser

sealed abstract class AST

case class FuncDecl(rtnTy: Ty, name: String, param: List[VarDecl], body: Block) extends AST

sealed abstract class Ty        extends AST // TODO add ArrayTy
object IntTy                    extends Ty
object DoubleTy                 extends Ty
object BoolTy                   extends Ty
object CharTy                   extends Ty
object VoidTy                   extends Ty

object NumTy                    extends Ty // Used in TypeChecking, is a supertype for IntTy and DoubleTy

sealed abstract class Expr()                extends AST
sealed abstract class Literal()             extends Expr // TODO add ArrayLit
case class NullLit()                        extends Literal
case class IntLit(i: Int)                   extends Literal
case class DoubleLit(d: Double)             extends Literal
case class BoolLit(b: Boolean)              extends Literal
case class CharLit(ch: Char)                extends Literal

// environment
case class Var(name: String)                extends Expr
case class Eval(call: Call)                 extends Expr

// uniop
case class NegateOp(expr: Expr)             extends Expr
case class NotOp(expr: Expr)                extends Expr

// binop
case class AddOp(left: Expr, right: Expr)   extends Expr
case class SubOp(left: Expr, right: Expr)   extends Expr
case class MultOp(left: Expr, right: Expr)  extends Expr
case class DivOp(left: Expr, right: Expr)   extends Expr
case class ModOp(left: Expr, right: Expr)   extends Expr
case class AndOp(left: Expr, right: Expr)   extends Expr
case class OrOp(left: Expr, right: Expr)    extends Expr
case class LtOp(left: Expr, right: Expr)    extends Expr
case class GtOp(left: Expr, right: Expr)    extends Expr
case class LeqOp(left: Expr, right: Expr)   extends Expr
case class GeqOp(left: Expr, right: Expr)   extends Expr
case class EqOp(left: Expr, right: Expr)    extends Expr
case class NeqOp(left: Expr, right: Expr)   extends Expr

//case class SubscriptOp(left: Expr, right: Expr)  extends Expr

sealed abstract class Statement                                                             extends AST
case class VarDecl(ty: Ty, name: String)                                                    extends Statement
case class Block(body: List[Statement])                                                     extends Statement // AST?
case class IfThenElse(cond: Expr, bodyTrue: Block, bodyFalse: Block)                        extends Statement
case class Call(name: String, args: List[Expr])                                             extends Statement
case class For(init: Statement, cond: Expr, inc: Statement, body: Block)                    extends Statement
case class While(cond: Expr, body: Block)                                                   extends Statement
case class Return(expr: Expr)                                                               extends Statement
case class Break()                                                                          extends Statement

// side effect op
case class Assign(name: String, expr: Expr)                                                 extends Statement
case class DeclAssign(decl: VarDecl, expr: Expr)                                            extends Statement
case class AddAssign(name: String, expr: Expr)                                              extends Statement
case class SubAssign(name: String, expr: Expr)                                              extends Statement
case class MultAssign(name: String, expr: Expr)                                             extends Statement
case class DivAssign(name: String, expr: Expr)                                              extends Statement
case class ModAssign(name: String, expr: Expr)                                              extends Statement
case class PreIncOp(name: String)                                                           extends Statement
case class PreDecOp(name: String)                                                           extends Statement
case class PostIncOp(name: String)                                                          extends Statement
case class PostDecOp(name: String)                                                          extends Statement

