import lombok.SneakyThrows;

import java.io.InputStream;

/**
 * @author by pepsi-wyl
 * @date 2022-12-06 19:04
 */

// 处理请求对象
public class MyHttpRequest {

    // 输入流
    private final InputStream inputStream;

    // 构造方法
    public MyHttpRequest(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    // 解析输入流中的信息
    @SneakyThrows
    public String parse() {
        // 将输入流中的数据存到数组中
        byte[] bytes = new byte[1024];
        inputStream.read(bytes);

        // 解析获得目标资源信息并且返回
        return getUri(new String(bytes));
    }

    // 根据请求获取目标资源信息
    private String getUri(String request) {
        // 获取资源下标
        int index1 = request.indexOf(' ');
        int index2 = request.indexOf(' ', index1 + 1);
        // 返回资源
        return request.substring(index1 + 1, index2);
    }
}