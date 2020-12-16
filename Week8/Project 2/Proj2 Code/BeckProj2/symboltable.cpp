#include <string>
#include <vector>
using namespace std;

#include "symboltable.h"

//changed type of value to int
void SymbolTable::insert(string variable, int value)
{
   const Symbol& symbol = Symbol(variable, value);
   elements.push_back(symbol);
}

//changed type to int
int SymbolTable::lookUp(string variable) const
{
   for (int i = 0; i < elements.size(); i++)
       if (elements[i].variable == variable)
           return elements[i].value;
   return -1;
}

//addition of symbolTable initialization
void SymbolTable::init()
{
   if (elements.size() > 0)
   {
       for (int i = elements.size(); i > 0; i--) {
           elements.pop_back();
       }
   }
}
