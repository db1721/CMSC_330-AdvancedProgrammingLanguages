class Times : public SubExpression
{
public:
   Times(Expression* left, Expression* right) : SubExpression(left, right)
   {
   }
   //changed type to int
   int evaluate()
   {
       return left->evaluate() * right->evaluate();
   }
};
