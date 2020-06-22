import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;

public class Main {
	
	static int n, m, q;
	static long[] arr;
	static Node[] seg;
	public static class Node
	{
		int l, r; 
		long lz, sum;
		public Node (int l, int r)
		{
			this.l = l;
			this.r = r;
			this.lz = 0;
			this.sum = 0;
		}
	}
	
	static void build (int l, int r, int id) throws IOException
	{
		seg[id].l = l; seg[id].r = r;
		if (l == r)
		{
			seg[id].sum = readLong();
			return;
		}
		int mid = (l + r) / 2;
		build(l, mid, 2*id); 
		build(mid+1, r, 2*id + 1);
		seg[id].sum = seg[2*id].sum + seg[2*id+1].sum;
	}
	
	static void update(int l, int r, int val, int id)
	{
		if (seg[id].l == l && seg[id].r == r)
		{
			seg[id].lz += val; seg[id].sum += (long)val * (r-l+1); return;
		}
		if (seg[id].lz != 0) 
		{
			seg[2*id].lz += seg[id].lz; seg[2*id+1].lz += seg[id].lz;
			seg[2*id].sum += seg[id].lz * (seg[2*id].r - seg[2*id].l + 1); 
			seg[2*id+1].sum += seg[id].lz * (seg[2*id+1].r - seg[2*id+1].l + 1); 
			seg[id].lz = 0;
		}
		int mid = (seg[id].l + seg[id].r) / 2;
		if (r <= mid) update(l, r, val, 2*id);
		else if (l > mid) update(l, r, val, 2*id+1);
		else
		{
			update(l, mid, val, 2*id); update(mid+1, r, val, 2*id+1);
		}
		seg[id].sum = seg[2*id].sum + seg[2*id+1].sum;
	}
	
	static long query(int l, int r, int id)
	{
		if (seg[id].l == l && seg[id].r == r)
			return seg[id].sum;
		if (seg[id].lz != 0)
		{
			seg[2*id].lz += seg[id].lz; seg[2*id+1].lz += seg[id].lz;
			seg[2*id].sum += seg[id].lz * (seg[2*id].r - seg[2*id].l + 1); 
			seg[2*id+1].sum += seg[id].lz * (seg[2*id+1].r - seg[2*id+1].l + 1); 
			seg[id].lz = 0;
		}
		int mid = (seg[id].l + seg[id].r) / 2;
		if (r <= mid) return query(l, r, 2*id);
		else if (l > mid) return query(l, r, 2*id+1);
		else
		{
			return query(l, mid, 2*id) + query(mid+1, r, 2*id+1);
		}
	}
    public static void main(String[] args) throws IOException {
    	m = readInt(); n = readInt(); q = readInt();
    	seg = new Node[3*n]; arr = new long[n+1];
    	for (int i=1; i<3*n; i++)
    	{
    		seg[i] = new Node(0,0);
    	}
    	build(1, n ,1);
    	for (int i=1, op, x, y, val; i<=q; i++)
    	{
    		op = readInt(); x = readInt(); y = readInt();
    		if (op == 1)
    		{
    			val = readInt();
    			update(x, y, val, 1);
    		}
    		else
    		{
    			System.out.println(query(x, y, 1)%m);
    		}
    	}
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
