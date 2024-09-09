package tech.zxuuu.client.chat;

import cn.hutool.core.convert.ConvertException;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class Chat
{
    // 交互端点
    String chatEndpoint = "https://api.openai.com/v1/chat/completions";

    // API接口密钥
    String apiKey = "Bearer 你的实际API密钥";

    // 对于请求问题给出相应回答
    public static String chat(String question_txt)
    {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("model", "gpt-3.5-turbo");

        List<Map<String, String>> dataList = new ArrayList<>();
        dataList.add(new HashMap<String, String>() {{
            put("role", "user");
            put("content", question_txt);
        }});
        paramMap.put("messages", dataList);

        try {
            String body = HttpRequest.post(chatEndpoint)
                    .header("Authorization", apiKey)
                    .header("Content-Type", "application/json")
                    .body(JSONUtil. toJsonStr(paramMap))
                    .execute()
                    .body();

            JSONObject jsonObject = JSONUtil.parseObj(body);
            JSONArray choices = jsonObject.getJSONArray("choices");

            if (choices.size() > 0)
            {
                JSONObject result = choices.get(0, JSONObject.class, Boolean.TRUE);
                JSONObject message = result.getJSONObject("message");
                return message.getStr("content");
            }
            else
            {
                return "没有返回内容，请稍后再试。";
            }
        }
        catch (HttpException e)
        {
            return "出现了网络异常: " + e.getMessage();
        }
        catch (ConvertException e)
        {
            return "出现了转换异常: " + e.getMessage();
        }
        catch (Exception e)
        {
            return "发生了一般异常: " + e.getMessage();
        }
    }
}