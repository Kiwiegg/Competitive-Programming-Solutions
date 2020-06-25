import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.Math;

public class Main {
	
    public static void main(String[] args) throws IOException{
    	int n = readInt(), K = readInt();
    	int[][] grid = new int[n][n];
    	for (int i=0; i<n; i++)
    	{
    		for (int j=0; j<=i; j++)
    		{
    			grid[i][j] = readInt();
    		}
    	}
    	if (K!= 1) {
    	for (int j=0; j<n-1; j++)
		{
			for (int m=0; m<=j; m++)
			{
				int a=Math.max(grid[j+1][m], grid[j+1][m+1]);
				grid[j][m] = Math.max(grid[j][m], a);
			}
		}
    	for (int k=2; k<K; k=k*3/2)
    	{
    		if (k*3/2 <= K)
    		{
    			for (int j=0; j<n-k*3/2+1; j++)
    			{
    				for (int m=0; m<=j; m++)
    				{
    					int a=Math.max(grid[j+k/2][m], grid[j+k/2][m+k/2]);
    					grid[j][m] = Math.max(grid[j][m], a);
    				}
    			}
    		}
    		else 
    		{
    			int a = K-k;
    			for (int j=0; j<n-k-a+1; j++)
        		{
        			for (int m=0; m<=j; m++)
        			{
        				int b=Math.max(grid[j+a][m], grid[j+a][m+a]);
        				grid[j][m] = Math.max(grid[j][m], b);
        			}
        		}
    			break;
    		}
    	}
    	}
    	long sum = 0;
    	for (int i=0; i<n-K+1; i++)
    	{
    		for (int j=0; j<=i; j++)
    		{
    			sum += grid[i][j];
    		}
    	}
    	System.out.println(sum);
    }
    
 	
    final private static int BUFFER_SIZE = 1 << 16;
    private static DataInputStream din = new DataInputStream(System.in);
    private static byte[] buffer = new byte[BUFFER_SIZE];
    private static int bufferPointer = 0, bytesRead = 0;
    static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public static String readLine() throws IOException {
        byte[] buf = new byte[64]; // line length
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
