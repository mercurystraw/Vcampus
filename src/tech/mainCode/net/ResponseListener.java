package tech.mainCode.net;

import com.alibaba.fastjson.JSON;
import tech.mainCode.client.main.App;

/**
 * 响应监听器
 * 

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
			Response response = JSON.parseObject(line, Response.class);
			App.responseQueue.produce(response);
		}
	}

}
