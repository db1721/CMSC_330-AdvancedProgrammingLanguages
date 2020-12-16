#include <strstream>
#include <vector>
using namespace std;

#include "expression.h"
#include "operand.h"
#include "variable.h"
#include "symboltable.h"

extern SymbolTable symbolTable;

//changed type to int
int Variable::evaluate()
{
   return symbolTable.lookUp(name);
}