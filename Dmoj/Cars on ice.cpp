
using namespace std;

char grid[2001][2001];
int R, C;

void move(int r, int c) {
    if (grid[r][c] == 'E') {
        for (int i = c + 1; i < C; i++) {
            if (grid[r][i] != '.') {move(r, i);}
        }
    } else if (grid[r][c] == 'W') {
        for (int i = c - 1; i >= 0; i--) {
            if (grid[r][i] != '.') {move(r, i);}
        }
    } else if (grid[r][c] == 'N') {
        for (int i = r - 1; i >= 0; i--) {
            if (grid[i][c] != '.') {move(i, c);}
        }
    } else if (grid[r][c] == 'S') {
        for (int i = r + 1; i < R; i++) {
            if (grid[i][c] != '.') {move(i, c);}
        }
    }
    
    grid[r][c] = '.';
    cout << "(" << r << "," << c << ")\n";
}

int main() {
    cin >> R >> C;
    for (int i = 0; i < R; i++) {
        for (int j = 0; j < C; j++) {
            cin >> grid[i][j];
        }
    }
    
    for (int i = 0; i < R; i++) {
        for (int j = 0; j < C; j++) {
            if (grid[i][j] != '.') {
                move(i, j);
            }
        }
    }
    return 0;
}
