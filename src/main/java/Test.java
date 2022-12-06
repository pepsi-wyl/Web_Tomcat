/**
 * @author by pepsi-wyl
 * @date 2022-12-06 17:33
 */

// 测试方法
// http://localhost:8080/index.html
public class Test {
    public static void main(String[] args) {
        System.out.println("Server startup successfully......");
        // 创建服务对象
        MyHttpServer server = new MyHttpServer();
        // 启动服务接收请求
        server.receiving();
    }
}
