#include <bits/stdc++.h>
#include <iostream>
#include <vector>
#include <utility>
#include <vector>

using namespace std;

int n;
int dp[2005], pdis[2005], pre[2005];

struct Pair{
  int p1, p2, d;

};

bool comparePair (Pair p1, Pair p2) {
  return (p1.d < p2.d);
}
int main() {
  cin >> n;
  vector<pair<int, int>> points;
  vector<Pair> p;
  points.push_back(make_pair(0,0));
  for (int i=1; i<=n; i++)
  {
    int x, y;
    cin >> x >> y;
    points.push_back(make_pair(x,y));
    for (int j=0; j<i; j++)
    {
      int distance = (points.at(i).first - points.at(j).first)*(points.at(i).first - points.at(j).first) + (points.at(i).second - points.at(j).second)*(points.at(i).second - points.at(j).second);
    	p.push_back({j, i, distance});
    }
  }
  sort(p.begin(), p.end(), comparePair);
  for (int i=0; i<p.size(); i++)
  {
    		int a = p.at(i).p1, b = p.at(i).p2, d = p.at(i).d;
    		if (d != pdis[a])
    		{
    			pdis[a] = d;
    			pre[a] = dp[a];			
    		}
    		if (d != pdis[b])
    		{
    			pdis[b] = d;
    			pre[b] = dp[b];  			
    		}	
    		if (a == 0)
    		{
    			dp[a] = max(dp[a], pre[b] + 1);
    		}
        	else	
        {
    			dp[a] = max(dp[a], pre[b] + 1);
    			dp[b] = max(dp[b], pre[a] + 1);
    		}
    	}
  cout << dp[0];  
}
