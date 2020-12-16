class SymbolTable
{
public:
   SymbolTable() {}
   //changed type of value to int
   void insert(string variable, int value);
   //changed type of lookUp to an int
   int lookUp(string variable) const;
   void init();
private:
   struct Symbol
   {
       //changed type of value to int
       Symbol(string variable, int value)
       {
           this->variable = variable;
           this->value = value;
       }
       string variable;
       //changed type to int
       int value;
   };
   vector<Symbol> elements;
};
