#include <iostream>

using namespace std;

int main() {
    long long number[3];

    for(int i = 0 ; i < 3; i++) {
        cin >> number[i];
    }

    cout << number[0] + number[1] - number[2] << "\n";
    if (number[1] < 10) cout << number[0]*10 + number[1] - number[2];
    else if (number[1] < 100) cout << number[0]*100 + number[1] - number[2];
    else if (number[1] < 1000) cout << number[0]*1000 + number[1] - number[2];
    else cout << number[0]*10000 + number[1] - number[2];
    return 0;
}