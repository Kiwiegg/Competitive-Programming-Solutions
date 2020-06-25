#pragma GCC optimize "Ofast"
#pragma GCC optimize "unroll-loops"
#pragma GCC target "sse,sse2,sse3,sse4,abm,avx,mmx,popcnt,tune=native"
#include <bits/stdc++.h>
#define scan(x) do{while((x=getchar_unlocked())<'0'); for(x-='0'; '0'<=(_=getchar_unlocked()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;
#define ll long long
#define MAXN 210
#define INF 0x3f3f3f3f
#define MOD 1000000007
#define min(a, b) (a) < (b) ? (a) : (b)
#define max(a, b) (a) < (b) ? (b) : (a)
#define vi vector<int>
#define pb push_back
#define pii pair<int, int>
#define mp make_pair
#define f first
#define s second
#define mii map<int, int>
#define umii unordered_map<int, int>
#ifdef DEBUG
	#define D(x...) printf(x)
#else
	#define D(x...)
#endif
using namespace std;

int N, W, M, a, b;
ll iswolf, notwolf;
char op;
vi accus_adj[MAXN], defend_adj[MAXN];
int indeg[MAXN], outdeg[MAXN];
ll DP[2][MAXN][MAXN]; // [iswolf][node ID][# of wolves]

inline ll Solve () {
	for (int i=N; i>=0; i--) {
		DP[0][i][0] = DP[1][i][1] = 1;
		for (size_t j=0; j<accus_adj[i].size(); j++) {
			int &next = accus_adj[i][j];
			for (int k=W; k>=0; k--) {
				iswolf = notwolf = 0LL;
				for (int l=0; l<=k; l++) {
					iswolf = (iswolf + DP[1][i][k - l] * DP[0][next][l]) % MOD;
					notwolf = (notwolf + DP[0][i][k - l] * (DP[0][next][l] + DP[1][next][l]) % MOD) % MOD;
				}
				DP[0][i][k] = notwolf;
				DP[1][i][k] = iswolf;
			}
		}
		for (size_t j=0; j<defend_adj[i].size(); j++) {
			int &next = defend_adj[i][j];
			for (int k=W; k>=0; k--) {
				iswolf = notwolf = 0LL;
				for (int l=0; l<=k; l++) {
					iswolf = (iswolf + DP[1][i][k - l] * DP[1][next][l]) % MOD;
					notwolf = (notwolf + DP[0][i][k - l] * (DP[0][next][l] + DP[1][next][l]) % MOD) % MOD;
				}
				DP[0][i][k] = notwolf;
				DP[1][i][k] = iswolf;
			}
		}
	}
	return DP[0][0][W];
}

int main (int argc, char const *argv[]) {
	#ifdef NOT_DMOJ
	freopen("in.txt", "r", stdin);
	freopen("out.txt", "w", stdout);
	#endif // NOT_DMOJ
	cin.sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> W >> M;
	while (M--) {
		cin >> op >> a >> b;
		if (op == 'A') accus_adj[a].pb(b);
		else defend_adj[a].pb(b);
		indeg[b]++;
		outdeg[a]++;
	}
	for (int i=1; i<=N; i++) {
		if (!indeg[i]) accus_adj[0].pb(i);
	}
	cout << Solve() << "\n";
	return 0;
}
