import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author by pepsi-wyl
 * @date 2022-12-06 17:21
 */

// Socket服务
public class MyHttpServer {

    // 端口号
    private final int port = 8080;

    // 接收请求
    @SneakyThrows
    public void receiving() {

        // 创建Socket服务
        ServerSocket serverSocket = new ServerSocket(port);

        // 循环接收请求
        while (true) {
            // 获取连接对象
            Socket socket = serverSocket.accept();
            // 获取连接对象的输入流和输出流
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // 创建Request
            MyHttpRequest request = new MyHttpRequest(inputStream);
            // 在Request中解析输入流对象，获取目标资源信息
            String uri = request.parse();

            // 创建Response
            MyHttpResponse response = new MyHttpResponse(outputStream);
            // 在Response中
            response.sendRedirect(uri);
        }
    }
}