package src.tech.zxuuu.server.opencourse;

import tech.zxuuu.server.opencourse.ChatSocket;

/**
 * 黑科技
 * 
 * @author LongChen
 */
public interface ReceivePredicate {
	void callResponse(String str, ChatSocket chatSocket);
}
