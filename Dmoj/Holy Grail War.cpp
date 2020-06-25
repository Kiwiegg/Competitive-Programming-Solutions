#include <bits/stdc++.h>
using namespace std;
const int MM=100002;
#define lson rt<<1
#define rson rt<<1|1
#define MID(x, y)  ((x+y)>>1)
typedef long long ll;
struct NODE{ int l, r; ll pre, suf, tot, best; } seg[3*MM];
int N, Q; char op;
void push_up(int rt){
    seg[rt].pre = max(seg[lson].pre, seg[lson].tot + seg[rson].pre);
    seg[rt].suf = max(seg[lson].suf+seg[rson].tot, seg[rson].suf);
    seg[rt].tot = seg[lson].tot + seg[rson].tot;
    seg[rt].best = max(max(seg[lson].best, seg[rson].best), seg[lson].suf + seg[rson].pre);
}
void build(int l, int r, int rt){
    seg[rt].l = l;  seg[rt].r = r;
    if(l == r){
        scanf("%lld", &seg[rt].pre);
        seg[rt].suf=seg[rt].tot=seg[rt].best=seg[rt].pre;
        return;
    }
    build(l, MID(l, r), lson); build(MID(l, r)+1, r, rson);
    push_up(rt);
}
void update(int pos, int val, int rt){
    if(seg[rt].l==seg[rt].r && seg[rt].l == pos){
        seg[rt].pre=seg[rt].suf =seg[rt].tot=seg[rt].best=val;
        return;
    }
    int m = MID(seg[rt].l, seg[rt].r);
    if(pos <= m) update(pos, val, lson);
    else update(pos, val, rson);
    push_up(rt);
}
NODE query(int l, int r, int rt){
    if(seg[rt].l == l && seg[rt].r == r) return seg[rt];
    int m = MID(seg[rt].l, seg[rt].r);
    if(r<=m) return query(l, r, lson);
    else if(l > m) return query(l, r, rson);
    else {
        NODE ll = query(l, m, lson), rr = query(m+1, r, rson), ans;
        ans.pre = max(ll.pre, rr.pre+ll.tot);
        ans.suf = max(ll.suf+rr.tot, rr.suf);
        ans.tot = ll.tot + rr.tot;
        ans.best = max( max(ll.best, rr.best), ll.suf + rr.pre);
        return ans;
    }
}
int main(){
    //freopen("test.txt", "r", stdin);
    scanf("%d %d", &N, &Q);
    build(1, N, 1);
    for(int i=0, l, r; i<Q; i++){
        scanf(" %c %d %d", &op, &l, &r);
        if(op == 'S') update(l, r, 1);
        else printf("%lld\n", query(l, r, 1).best);
    }
}
