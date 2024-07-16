#include <iostream>

using namespace std;

long long N;
long long Tshirts[7];
long long T;
long long P;
long long bundle;

int main (){
  cin >> N ;
  for(int i = 1 ; i < 7 ; i++) cin >> Tshirts[i];
  cin >> T >> P;

  for (int i = 1 ; i < 7 ; i++) {
    bundle += (Tshirts[i] / T) ;
    if (Tshirts[i] % T != 0) bundle++;
  }

  cout << bundle << "\n";
  cout << N / P << " " << N % P;
  return 0;
}