package sun.project.to_parking.mqtt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import sun.project.to_parking.pojo.Car;
import sun.project.to_parking.service.CarService;

import java.util.Date;

@Configuration
@Slf4j
@IntegrationComponentScan
public class MqttService {

    @Autowired
    private CarService carService;

    private Date date;


    //此处填DNS解析后对应的ip地址
    private String hostUrl = "tcp://101.133.196.127:1883";
    //剩下部分填写其余对应的信息即可
    private String username = "Aliyun_toparking&iljf3EjdQ0W";
    private String password = "2e1f0dafb309bc6fd2124cd7b6f1740716267b5b328e2a82047a43e0b6e52bdc";
    private String clientId = "iljf3EjdQ0W.Aliyun_toparking|securemode=2,signmethod=hmacsha256,timestamp=1683601682070|";
    private String recvTopic = "/sys/iljf3EjdQ0W/Aliyun_toparking/thing/service/property/set";

    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);
        mqttConnectOptions.setConnectionTimeout(10);
        mqttConnectOptions.setKeepAliveInterval(60);
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{hostUrl});
        return mqttConnectOptions;
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {

        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(clientId, mqttClientFactory(), recvTopic);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    /**
     * 阿里云消息处理
     * 将收到的信息更新到MySQL的车位状态表中
     * @return
     */


    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {


        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {


                //此处添加处理方法
                //传入的消息是用message.getPayload().toString()得到

                String data = message.getPayload().toString();
                System.out.println("收到的数据为");
                log.info("收到的数据为：{}",data);


                JSONObject jsonObject = JSON.parseObject(data);

                Car car = new Car();

                car.setCarId(jsonObject.getLong("id"));
                car.setName(jsonObject.getString("name"));
                car.setState(jsonObject.getString("value"));
                car.setStartDate(null);
                car.setEndDate(null);
                if(jsonObject.getString("value").equals("1")){
                    date = new Date();
                    car.setStartDate(date);
                }else{
                    date = new Date();
                    car.setEndDate(date);
                }

                carService.UpdateCarById(car);


            }
        };
    }

}

