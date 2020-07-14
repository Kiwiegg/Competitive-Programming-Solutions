import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	static int n, t, dist[], cost[][];
	static boolean[] visited;
	
	public static class Store {
		int index; int price;
	}
	
	static int findMin() {
		int min = Integer.MAX_VALUE, node = -1;
		for (int i=1; i<=n; i++) {
			if (!visited[i] && dist[i] < min) {
				min = dist[i];
				node = i;
			}
		}
		return node;
	}
    public static void main(String[] args) throws IOException {
    	n = readInt(); t = readInt();
    	int[][] cost = new int[n+1][n+1];
    	for (int i=0; i<t; i++) {
    		int x = readInt(), y = readInt(), c = readInt();
    		if (cost[x][y] == 0) {
    			cost[x][y] = cost[y][x] = c;
    		}
    		else {
    			cost[x][y] = Math.min(cost[x][y], c);
    			cost[y][x] = cost[x][y];
    		}
    	}
    	int k = readInt();
    	Store[] options = new Store[k];
    	for (int i=0; i<k; i++) {
    		options[i] = new Store();
    		options[i].index = readInt(); options[i].price = readInt();
    	}
    	int d = readInt(); 
    	dist = new int[n+1]; visited = new boolean[n+1];
    	Arrays.fill(dist, Integer.MAX_VALUE);
    	dist[d] = 0;
    	for (int cnt=0; cnt<n-1; cnt++) {
    		int u = findMin();
    		if (u == -1) {
    			break;
    		}
    		visited[u] = true;
    		for (int i=1; i<=n; i++) {
    			if (!visited[i] && cost[i][u] != 0) {
    				dist[i] = Math.min(dist[i], dist[u] + cost[i][u]);
    			}
    		}
    	}
    	int minCost = Integer.MAX_VALUE;
    	for (int i=0; i<k; i++) {
    		if (options[i].index == d) {
    			minCost = Math.min(minCost, options[i].price);
    		}
    		else {
    			if (dist[options[i].index] != 0) {
    				minCost = Math.min(dist[options[i].index] + options[i].price, minCost);
    			}
    		}
    	}
    	System.out.println(minCost);
    } 
    
 	
    final private static int BUFFER_SIZE = 1 << 16;
    private static DataInputStream din = new DataInputStream(System.in);
    private static byte[] buffer = new byte[BUFFER_SIZE];
    private static int bufferPointer = 0, bytesRead = 0;
    static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public static String readLine() throws IOException {
        byte[] buf = new byte[1000000]; // line length
        int cnt = 0, c;
        while ((c = Read()) != -1) {
            if (c == '\n') {
                break;
            }
            buf[cnt++] = (byte) c;
        }
        return new String(buf, 0, cnt);
    }

    public static String read() throws IOException {
        byte[] ret = new byte[100000];
        int idx = 0;
        byte c = Read();
        while (c <= ' ') {
            c = Read();
        }
        do {
            ret[idx++] = c;
            c = Read();
        } while (c != -1 && c != ' ' && c != '\n' && c != '\r');
        return new String(ret, 0, idx);
    }

    public static int readInt() throws IOException {
        int ret = 0;
        byte c = Read();
        while (c <= ' ') {
            c = Read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = Read();
        }
        do {
            ret = ret * 10 + c - '0';
        } while ((c = Read()) >= '0' && c <= '9');

        if (neg) {
            return -ret;
        }
        return ret;
    }

    public static long readLong() throws IOException {
        long ret = 0;
        byte c = Read();
        while (c <= ' ') {
            c = Read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = Read();
        }
        do {
            ret = ret * 10 + c - '0';
        } while ((c = Read()) >= '0' && c <= '9');
        if (neg) {
            return -ret;
        }
        return ret;
    }

    public static double readDouble() throws IOException {
        double ret = 0, div = 1;
        byte c = Read();
        while (c <= ' ') {
            c = Read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = Read();
        }

        do {
            ret = ret * 10 + c - '0';
        } while ((c = Read()) >= '0' && c <= '9');

        if (c == '.') {
            while ((c = Read()) >= '0' && c <= '9') {
                ret += (c - '0') / (div *= 10);
            }
        }

        if (neg) {
            return -ret;
        }
        return ret;
    }

    private static void fillBuffer() throws IOException {
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1) {
            buffer[0] = -1;
        }
    }

    private static byte Read() throws IOException {
        if (bufferPointer == bytesRead) {
            fillBuffer();
        }
        return buffer[bufferPointer++];
    }

    public void close() throws IOException {
        if (din == null) {
            return;
        }
        din.close();
    }

    static void print(Object o) {
        pr.print(o);
    }

    static void println(Object o) {
        pr.println(o);
    }

    static void flush() {
        pr.flush();
    }

    static void println() {
        pr.println();
    }

    static void exit() throws IOException {
        din.close();
        pr.close();
        System.exit(0);
    }
}
