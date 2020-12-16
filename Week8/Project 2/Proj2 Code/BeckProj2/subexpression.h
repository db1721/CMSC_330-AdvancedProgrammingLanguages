class SubExpression : public Expression
{
public:
   SubExpression(Expression* left, Expression* right);
   //added stringstream to parse
   static Expression* parse(stringstream& in);
protected:
   Expression* left;
   Expression* right;
};
