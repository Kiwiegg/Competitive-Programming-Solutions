import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
    	int t = readInt(), n = readInt();
    	int[] arr = new int[n+1]; 
    	for (int i=1; i<=n; i++) {
    		arr[i] = readInt();
    	}
    	boolean[] sum = new boolean[t+1];
    	sum[0] = true; int max = 0;
    	for (int i=1; i<=n; i++) {
    		for (int j= t-arr[i]; j>=0; j--) {
    			if (sum[j]) {
    				sum[arr[i] + j] = true;
    				max = Math.max(max, arr[i] + j);
    			}
    		}
    	}
    	boolean[][] table = new boolean[max+1][n+1]; 
    	for (int j=0; j<=n; j++) {
    		table[0][j] = true;
    	}
    	for (int i=1; i<=max; i++) {
    		for (int j=1; j<=n; j++) {
    			table[i][j] = table[i][j-1];
    			if (i >= arr[j]) {
    				table[i][j] = (table[i][j] || table[i-arr[j]][j-1]);
    			}
    		}
    	}
    	boolean[] schedule = new boolean[n+1];
    	int cnt1 = max, cnt2 = n;
    	while (cnt1 > 0) {
    		if (table[cnt1][cnt2-1]) {
    			cnt2 --;
    		}
    		else {
    			cnt1 -= arr[cnt2]; 			
    			schedule[cnt2] = true;
    			cnt2 --;
    		}
    	}
    	int sum1 = 0, sum2 = 0;
    	for (int i=1; i<=n; i++) {
    		if (schedule[i]) {
    			System.out.print(sum1 + " ");
    			sum1 += arr[i];
    		}
    		else {
    			System.out.print(sum2 + " ");
    			sum2 += arr[i];
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
