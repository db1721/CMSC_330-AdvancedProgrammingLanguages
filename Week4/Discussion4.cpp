#include <iostream>
using namespace std;

int main()
{
    int a;
    cout << "1st Number: ";
    cin >> a;

    int b;
    cout << "2nd Number: ";
    cin >> b;

    int c;
    cout << "3rd Number: ";
    cin >> c;

    int d;
    cout << "4th Number: ";
    cin >> d;

    int total = a + b * c / d;

    cout << total;

    return 0;
}
