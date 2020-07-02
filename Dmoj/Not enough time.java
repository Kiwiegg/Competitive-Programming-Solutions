import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws IOException {
    	int n = readInt(), t = readInt();
    	int[] p1 = new int[n+1], p2 = new int[n+1], p3 = new int[n+1], v1 = new int[n+1], v2 = new int[n+1], v3 = new int[n+1];
    	for (int i=1; i<=n; i++) {
    		p1[i] = readInt(); v1[i] = readInt(); p2[i] = readInt(); v2[i] = readInt(); p3[i] = readInt(); v3[i] = readInt();
    	}
    	long[] dp = new long[t+1], prev = new long[t+1];
    	for (int i=1; i<=n; i++)  {
    		for (int time=1; time<=t; time++) {
    			dp[time] = prev[time];
    			if (time >= p1[i]) {
    				dp[time] = Math.max(dp[time], prev[time-p1[i]] + v1[i]);
    			}
    			if (time >= p2[i]) {
    				dp[time] = Math.max(dp[time], prev[time-p2[i]] + v2[i]);
    			}
    			if (time >= p3[i]) {
    				dp[time] = Math.max(dp[time], prev[time-p3[i]] + v3[i]);
    			}
    		}
    		for (int time=1; time<=t; time++) {
    			prev[time] = dp[time];
    		} 		
    	}
    	System.out.println(dp[t]);
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
