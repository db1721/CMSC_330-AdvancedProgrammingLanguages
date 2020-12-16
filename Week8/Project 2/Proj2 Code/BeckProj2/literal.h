class Literal : public Operand
{
public:
   Literal(int value)
   {
       this->value = value;
   }
   //changed type to int
   int evaluate()
   {
       return value;
   }
private:
   int value;
};
