package src.tech.zxuuu.client.messageQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import tech.zxuuu.net.Response;

/**
 * 客户端请求消息队列（生产者-消费者模式）
 * 
 * @author z0gSh1u
 */
public class ResponseQueue {

	private static ResponseQueue responseQueue = new ResponseQueue();
	public static ResponseQueue getInstance() {
		return responseQueue;
	}

	private ConcurrentMap<String, Response> mp; // 使用ConcurrentHashMap保证线程安全

	protected ResponseQueue() {
		mp = new ConcurrentHashMap<>();
	}

	public void produce(Response response) {
		mp.put(response.getHash(), response);
	}

	public Response consume(String hashCode) {
		Response response = mp.get(hashCode);
		mp.remove(hashCode);
		return response;
	}

	public boolean contain(String hash) {
		return mp.containsKey(hash);
	}
}
