class Variable : public Operand
{
public:
   Variable(string name)
   {
       this->name = name;
   }
   //changed value to int
   int evaluate();
private:
   string name;
};
