//addition of ternary evaluation
class Ternary : public SubExpression
{
public:
   Ternary(Expression* left, Expression* right, Expression* condition) : SubExpression(left, right)
   {
       this->condition = condition;
   }
   int evaluate()
   {
       return condition->evaluate() ? left->evaluate() : right->evaluate();
   }//end int evaluate()

private:
   Expression* condition;
};
