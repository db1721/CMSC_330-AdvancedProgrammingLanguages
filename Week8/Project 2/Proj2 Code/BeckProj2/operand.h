class Operand : public Expression
{
public:
    //addition of stringstream to the parse
   static Expression* parse(stringstream& in);
};
