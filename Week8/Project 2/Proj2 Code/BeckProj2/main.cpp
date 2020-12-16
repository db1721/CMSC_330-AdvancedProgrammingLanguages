#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

#include "expression.h"
#include "subexpression.h"
#include "symboltable.h"
#include "parse.h"

/* File: Project 2
 * Author: Dan Beck
 * Date: December 15, 2020
 * Purpose: Program the evaluates statements from a file
 */

void parseAssignments(stringstream& in);

SymbolTable symbolTable;

int main()
{
   Expression* expression;
   char paren, comma;

   //creates strings from file
   string lineFromFile;

   //Name of file being read
   ifstream readFile("input.txt");

   //Error check for the file
   if (!readFile.is_open())
       perror("Error: File Not Found");

    //read file while there are lines left to be read
   while (getline(readFile, lineFromFile))
   {
       symbolTable.init();
       if (!readFile)
           break;
       stringstream in(lineFromFile, ios_base::in);
       in >> paren;
       cout << lineFromFile << " ";
       expression = SubExpression::parse(in);
       in >> comma;
       parseAssignments(in);
       int result = expression->evaluate();
       cout << "Value = " << result << endl;
   }//end while (getline(readFile, lineFromFile))
   return 0;
}//end int main()

void parseAssignments(stringstream& in)
{
   char assignOperand, delimiter;
   string variable;
   int value;
   do
   {
       variable = parseName(in);
       in >> ws >> assignOperand >> value >> delimiter;
       symbolTable.insert(variable, value);
   }//end do
   while (delimiter == ',');
}//end void parseAssignments(stringstream& in)
