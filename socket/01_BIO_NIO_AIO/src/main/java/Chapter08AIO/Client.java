
package Chapter08AIO;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/***********************************************************
 * @note      : AIO的客户端
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/9/18 16:14
 ***********************************************************/
public class Client {
    private String host;
    private int port;

    private AsynchronousSocketChannel clientSocketChannel;

    /**
     * 关闭资源类
     */
    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        try {
            // 创建channel
            clientSocketChannel = AsynchronousSocketChannel.open();
            Future<Void> future = clientSocketChannel.connect(new InetSocketAddress(host, port));
            future.get();

            // 等待用户的输入
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String input = consoleReader.readLine();

                byte[] inputBytes = input.getBytes();
                ByteBuffer buffer = ByteBuffer.wrap(inputBytes);
                Future<Integer> writeResult = clientSocketChannel.write(buffer);

                writeResult.get();
                buffer.flip();
                Future<Integer> readResult = clientSocketChannel.read(buffer);

                readResult.get();
                String echo = new String(buffer.array());
                buffer.clear();

                System.out.println(echo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            close(clientSocketChannel);
        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 9527);
        client.start();
    }
}
