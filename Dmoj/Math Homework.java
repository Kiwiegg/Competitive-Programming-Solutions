import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Main {

	static final long MOD = 1000000007;
	static int choose[][];
	
	static long exp (long base, long exp) {
		if (exp == 0) {
			return 1;
		}
		if (exp == 1) {
			return base % MOD;
		}
		long t = exp(base, exp/2);
		t = (t*t) % MOD;
		if (exp %2 == 0) {
			return t;
		}
		else {
			return (base*t)%MOD;
		}
	}	
	public static int choose (int x, int y){
		if (choose[x][y] != 0) {
			return choose[x][y];
		}
		if (y == 0 || x == y) {
			return choose[x][y] = 1;
		}
		if (x-y < y) {
			return choose(x, x-y);
		}
		choose[x][y] = ((choose(x-1, y-1) + choose(x-1, y)) % (int)MOD);
		return choose[x][y];
	}
	
    public static void main(String[] args) throws IOException {
    	int t = readInt();
    	long r; int c;
    	choose = new int[3001][3001];
    	for (int i=0; i<t; i++) {
    		r = readLong(); c = readInt();
    		long[] dp = new long[c+1]; dp[1] = 1;  		
    		for (int j=2; j<=c; j++) {
    			long ex = 0;
    			for (int k=1; k<=j-1; k++) {
    				ex = (ex + (choose(j, k) * dp[j-k])) % MOD;
    			}
    			long in = exp(exp(2, j)-1, r); 
    			dp[j] = (in - ex + 100*MOD)%MOD;
    		}
    		System.out.println(dp[c]%MOD);
    	}
    } 
    
 	
    final private static int BUFFER_SIZE = 1 << 16;
    private static DataInputStream din = new DataInputStream(System.in);
    private static byte[] buffer = new byte[BUFFER_SIZE];
    private static int bufferPointer = 0, bytesRead = 0;
    static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public static String readLine() throws IOException {
        byte[] buf = new byte[1000]; // line length
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
        byte[] ret = new byte[1000];
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
