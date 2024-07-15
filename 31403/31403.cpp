#include <iostream>

using namespace std;

int main() {
    long long a ,b ,c;
    cin >> a;
    cin >> b;
    cin >> c;

    cout << a + b - c<< "\n";
    if (b < 10) cout << a*10 + b - c;
    else if (b < 100) cout << a * 100 + b - c;
    else if (b < 1000) cout << a * 1000 + b - c;
    else cout << a * 10000 + b - c;
    return 0;
}