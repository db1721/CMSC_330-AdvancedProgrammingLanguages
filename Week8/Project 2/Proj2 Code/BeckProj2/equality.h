//addition of equality condition
class Equality : public SubExpression
{
public:
   Equality(Expression* left, Expression* right) : SubExpression(left, right)
   {
   }
   int evaluate()
   {
       return left->evaluate() == right->evaluate();
   }//end int evaluate()
};
