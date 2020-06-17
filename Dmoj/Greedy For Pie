import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
	
	static int n, m;
	static int[] p, newP;
	static int[][][][] dp;
	
	static int f(int pos, int prev, int l, int r)
	{
		if (dp[pos][prev][l][r] != -1) return dp[pos][prev][l][r];
		if (pos == n+1)
		{
			if (l<=r)
			{
				if (prev == 0)
				{
					return dp[pos][prev][l][r] = f(pos, 1, l+1, r);
				}
				return dp[pos][prev][l][r] = newP[r] + f(pos, 0, l, r-1);
			}
			return dp[pos][prev][l][r] = 0;
		}
		if (prev == 1)
		{
			dp[pos][prev][l][r] = Math.max(f(pos, 0, l, r), p[pos] + f(pos+1, 0, l, r));
			if (l<=r)
			{
				dp[pos][prev][l][r] = Math.max(dp[pos][prev][l][r], newP[r] + f(pos, 0, l, r-1));
			}
		}
		else
		{
			dp[pos][prev][l][r] = f(pos+1, 1, l ,r);
			if (l<=r)
			{
				dp[pos][prev][l][r] = Math.max(dp[pos][prev][l][r], p[pos] + f(pos+1, 0, l+1, r));
			}
		}
		return dp[pos][prev][l][r];
	}
	
    public static void main(String[] args) throws IOException {
    	n = readInt(); 
    	p = new int[n+1]; 
    	for (int i=1; i<=n; i++)
    	{
    		p[i] = readInt();
    	}
    	m = readInt();
    	newP = new int[m+1];
    	for (int i=1; i<=m; i++)
    	{
    		newP[i] = readInt();
    	}
    	Arrays.sort(newP);
    	dp = new int[n+5][2][m+5][m+5];
    	for (int i=0; i<=n+4; i++)
    	{
    		for (int j=0; j<=m+4; j++)
    		{
    			for (int k=0; k<=m+4; k++)
    			{
    				dp[i][0][j][k] = -1; dp[i][1][j][k] = -1;
    			}
    		}
    	}
    	System.out.println(f(1, 1, 1, m));
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
