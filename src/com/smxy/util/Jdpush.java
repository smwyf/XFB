package com.smxy.util;
import com.smxy.pojo.Sensor;
import com.smxy.service.SensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import cn.jpush.api.JPushClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class Jdpush{
	 protected static final Logger LOG = LoggerFactory.getLogger(Jdpush.class);
	 // demo App defined in resources/jpush-api.conf
	public static final String TITLE = "消防背心";
    //public static final String ALERT = null;
    private static final String appKey ="8aa505cdfcdcc3303b280172";
    private static final String masterSecret = "ed4895c6ca5f8fdaafb1a854";
    public static final String MSG_CONTENT = "祝新老客户新春快乐";
    public static final String REGISTRATION_ID = "0900e8d85ef";
    public static final String TAG = "tag_api";
    public  static JPushClient jpushClient=null;
    @Autowired
	public static void testSendPush(String appKey ,String masterSecret,String ALERT) {
		
		
		
		 jpushClient = new JPushClient(masterSecret, appKey, 3);
		
	    // HttpProxy proxy = new HttpProxy("localhost", 3128);
	    // Can use this https proxy: https://github.com/Exa-Networks/exaproxy
       
        
        // For push, all you need do is to build PushPayload object.
        //PushPayload payload = buildPushObject_all_all_alert();
		 //生成推送的内容，这里我们先测试全部推送
        PushPayload payload=buildPushObject_all_alias_alert(ALERT);
        
        
        try {
        	System.out.println(payload.toString());
            PushResult result = jpushClient.sendPush(payload);
            System.out.println(result+"................................");
            
            LOG.info("Got result - " + result);
            
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
	}
	
//	public static PushPayload buildPushObject_all_all_alert() {
//        return PushPayload.alertAll(ALERT);
//    }

    public static PushPayload buildPushObject_all_alias_alert(String ALERT) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())//设置接受的平台
                .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                .setNotification(Notification.alert(ALERT))
                .build();
    }

//    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
//        return PushPayload.newBuilder()
//                .setPlatform(Platform.android())
//                .setAudience(Audience.all())
//                .setNotification(Notification.android(ALERT, TITLE, null))
//                .build();
//    }

//    public static PushPayload buildPushObject_android_and_ios() {
//        return PushPayload.newBuilder()
//                .setPlatform(Platform.android_ios())
//                .setAudience(Audience.tag("tag1"))
//                .setNotification(Notification.newBuilder()
//                		.setAlert("alert content")
//                		.addPlatformNotification(AndroidNotification.newBuilder()
//                				.setTitle("Android Title").build())
//                		.addPlatformNotification(IosNotification.newBuilder()
//                				.incrBadge(1)
//                				.addExtra("extra_key", "extra_value").build())
//                		.build())
//                .build();
//    }
//
//    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
//        return PushPayload.newBuilder()
//                .setPlatform(Platform.ios())
//                .setAudience(Audience.tag_and("tag1", "tag_all"))
//                .setNotification(Notification.newBuilder()
//                        .addPlatformNotification(IosNotification.newBuilder()
//                                .setAlert(ALERT)
//                                .setBadge(5)
//                                .setSound("happy")
//                                .addExtra("from", "JPush")
//                                .build())
//                        .build())
//                 .setMessage(Message.content(MSG_CONTENT))
//                 .setOptions(Options.newBuilder()
//                         .setApnsProduction(true)
//                         .build())
//                 .build();
//    }
//
//    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
//        return PushPayload.newBuilder()
//                .setPlatform(Platform.android_ios())
//                .setAudience(Audience.newBuilder()
//                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
//                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
//                        .build())
//                .setMessage(Message.newBuilder()
//                        .setMsgContent(MSG_CONTENT)
//                        .addExtra("from", "JPush")
//                        .build())
//                .build();
//    }
}