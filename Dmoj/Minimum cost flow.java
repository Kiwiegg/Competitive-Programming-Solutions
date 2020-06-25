import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class MinimumCost {

	static int[] id;
	static int n,m,d;
	static int heavy = Integer.MIN_VALUE;
	static class Edge implements Comparable<Edge>
	{
		int c; int a; int b; boolean act;
		public Edge(int a, int b, int c, boolean act)
		{
			this.a = a;
			this.b = b;
			this.c = c;
			this.act = act;
		}
		public int compareTo(Edge o) {
			return c - o.c;
		}
	}
	
	static int root(int x)
	{
		while(id[x] != x)
		{
			id[x] = id[id[x]];
			x = id[x];
		}
		return x;
	}
	
	static void union(int x, int y)
	{
		int p = root(x);
		int q = root(y);
		id[p] = id[q];
	}
	
	static int kruskal (ArrayList<Edge> p)
    {
        int x,y;
        int days = 0;
        int i;
        for (i=0; i < p.size(); i++)
        {
            x = p.get(i).a;
            y = p.get(i).b;
            int cost = p.get(i).c;
            if (root(x) != root(y))
            {
                union(x,y);
                heavy = Math.max(heavy, cost);
                if (!p.get(i).act) days ++;
            }
        }

        for (int u = 1; u <= n; u++) {id[u] = u;}
        for (int v = 0; v < p.size(); v++) {
            int g = p.get(v).a; int h = p.get(v).b; int z = p.get(v).c;
            if (root(g) != root(h)) {
                if (z < heavy || (z == heavy && p.get(v).act)) {union(g, h);}
                else if (p.get(v).act && z <= d) {return days - 1;}
            }
        }
        return days;
    }
	
    public static void main(String[] args) throws IOException {
    	n = readInt(); m = readInt(); d = readInt();
    	ArrayList<Edge> p = new ArrayList<Edge>();
    	id = new int[n+1];
    	for (int i=1; i<=n; i++)
    	{
    		id[i] = i;
    	}
    	for (int i=1; i<=m; i++)
    	{
    		if (i <= n-1) p.add(new Edge(readInt(), readInt(), readInt(), true));
    		else p.add(new Edge(readInt(), readInt(), readInt(), false));
    	}
    	Collections.sort(p);
    	System.out.println(kruskal(p));
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
        byte[] ret = new byte[1024];
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
