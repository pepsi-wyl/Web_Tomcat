import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * @author by pepsi-wyl
 * @date 2022-12-06 21:30
 */

// 处理响应对象
public class MyHttpResponse {

    // 输入流
    private final OutputStream outputStream;

    // 构造方法
    public MyHttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    // 响应
    @SneakyThrows
    public void sendRedirect(String uri) {

        // 获取资源保存路径
        String path = System.getProperty("user.dir") + "/src" + "/main" + "/resources" + "/web";
        // 获取资源文件
        File file = new File(path + uri);

        // 处理返回对象
        if (!file.exists()) { // 不存在 则返回不存在 404
            // 要返回信息
            String error = "404 File Not Found!";
            // 返回
            this.outputStream.write(getResponseMessage("404", error).getBytes());
        } else {              // 存在  则返回目标资源 200
            // 读取资源文件
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStream.read(bytes);
            // 返回信息
            String result = new String(bytes);
            // 返回
            this.outputStream.write(getResponseMessage("200", result).getBytes());
        }
    }

    // 封装响应信息
    public String getResponseMessage(String code, String message) {
        return "HTTP/1.1 " + code
                + "\r\n"
                + "Content-type: " + "text/html"
                + "\r\n"
                + "Content-Length: " + message.length()
                + "\r\n"
                + "\r\n"
                + message;
    }
}