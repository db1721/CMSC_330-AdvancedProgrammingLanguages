class Plus : public SubExpression
{
public:
   Plus(Expression* left, Expression* right) : SubExpression(left, right)
   {
   }
   //changed value to int
   int evaluate()
   {
       return left->evaluate() + right->evaluate();
   }
};
