#include <bits/stdc++.h>

using namespace std;

int N, splits[100005][205];
long long psa[100005], dpPrev[100005], dp[100005];

long long C(int k, int j)
{
  return psa[k] * (psa[j] - psa[k]);
}

void compute(int l, int r, int optl, int optr, int k)
{
    if (l > r)
        return;
    int mid = (l + r) >> 1;
    pair<long long, int> best = {0, -1};

    for (int k = optl; k <= min(mid, optr); k++) {
        if (best.first <= dpPrev[k] + C(k, mid)) {
            best = {dpPrev[k] + C(k, mid), k};
        }   
    }

    dp[mid] = best.first;
    int opt = best.second;
    splits[mid][k] = opt;

    compute(l, mid - 1, optl, opt, k);
    compute(mid + 1, r, opt, optr, k);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int K;
    cin >> N >> K;
    int arr[N + 1];
    for (int i = 1; i <= N; i++) {
        cin >> arr[i];
    }
    psa[0] = 0;
    for (int i=1; i<=N; i++) {
        psa[i] = psa[i - 1] + arr[i];
    }

    if (N > 200 && N <= 10000)
    {
    for (int k = 1; k <=K; k++) {
        compute(1, N, 1, N, k);
        for (int i=1; i<=N; i++){
            dpPrev[i] = dp[i];
        }
    }
    }
    else
    {
     for (int k = 1; k <=K; k++) {
        int count = 1;
        for (int i=1; i<=N; i++) {
            while (count < i-1 && dpPrev[count] + psa[count] * (psa[i] - psa[count]) <= dpPrev[count+1] + psa[count+1] * (psa[i] - psa[count+1])) {
                count++;
            }
            dp[i] = dpPrev[count] + psa[count] * (psa[i] - psa[count]);
            splits[i][k] = count;
        }
        for (int i=1; i<=N; i++){
            dpPrev[i] = dp[i];
        }
    }
    }


    cout << dp[N] << endl;
    int splitPts[K + 1];
    int pt = N;
    for (int i=0; i < K; i++) {
        pt = splits[pt][K - i];
        splitPts[i] = pt;
    }

     for (int i= K - 1; i >= 0; i--) {
        cout << splitPts[i] << " ";
    }
}
