import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Bank {
	
	static int n, k, a[], pmx[], smx[], day[];
	static long[] dp, t1, t2;
	
    public static void main(String[] args) throws IOException {
    	n = readInt(); k = readInt();
    	dp = new long[n+1]; t1 = new long[n+1]; t2 = new long[n+1];
    	a = new int[n+1]; pmx = new int[n+1]; smx = new int[n+1]; day = new int[n+2];
    	for (int i=1; i<=n; i++) {
    		a[i] = readInt();
    		day[i] = (i+k-1)/k;
    		if (day[i] != day[i-1]) pmx[i] = a[i];
    		else pmx[i] = Math.max(pmx[i-1], a[i]);
    	}
    	for (int i=n; i>=1; i--) {
    		if (day[i] != day[i+1]) {
    			smx[i] = a[i];
    		}
    		else {
    			smx[i] = Math.max(smx[i], a[i]);
    		}
    	}
    	for (int i=1; i<=k; i++) {
    		dp[i] = pmx[i];
    	}
    	for (int i=2; i<=day[n]; i++) {
    		int fst = (i-2)*k+1, lst = (i-1)*k;
    		for (int j=lst; j>=fst; j--) {
    			t1[j] = dp[j] + (j==lst? 0 : smx[j+1]);
    			t2[j] = dp[j];
    			if (j != lst) {
    				t1[j] = Math.max(t1[j], t1[j+1]);
    				t2[j] = Math.max(t2[j], t2[j+1]);
    			}
    		}
    		for (int j=lst+1; j<=Math.min(i*k, n); j++) {
    			dp[j] = Math.max(t1[j-k], t2[j-k] + pmx[j]);
    		}
    	}
    	System.out.println(dp[n]);
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
