/* Generated By:JJTree: Do not edit this line. ASTRecipient1.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=BaseNode,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package logic.parse.SOP;

public
class ASTRecipient1 extends SimpleNode {
  public ASTRecipient1(int id) {
    super(id);
  }

  public ASTRecipient1(parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(parserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=22842eb741d94f50ad7dee1e2e12a7eb (do not edit this line) */
