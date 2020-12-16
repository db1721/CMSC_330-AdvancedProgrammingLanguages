//addition of greater than condition
class GreaterThan : public SubExpression
{
public:
   GreaterThan(Expression* left, Expression* right) : SubExpression(left, right)
   {
   }
   int evaluate()
   {
       return left->evaluate() > right->evaluate();
   }//int evaluate()
};
