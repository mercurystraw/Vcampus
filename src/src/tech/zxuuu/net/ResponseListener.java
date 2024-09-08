package src.tech.zxuuu.net;

import com.alibaba.fastjson.JSON;
import tech.zxuuu.client.main.App;
import tech.zxuuu.net.ConnectionToServer;
import tech.zxuuu.net.Response;

/**
 * 响应监听器
 * 
 * @author z0gSh1u
 */
public class ResponseListener extends Thread {

	private ConnectionToServer connectionToServer;

	public ResponseListener() {
	}

	public ResponseListener(ConnectionToServer connectionToServer) {
		this.connectionToServer = connectionToServer;
	}

	@Override
	public void run() {
		String line;
		while ((line = connectionToServer.readLine()) != null) {
			tech.zxuuu.net.Response response = JSON.parseObject(line, Response.class);
			App.responseQueue.produce(response);
		}
	}

}
