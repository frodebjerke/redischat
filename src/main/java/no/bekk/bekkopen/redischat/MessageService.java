package no.bekk.bekkopen.redischat;

import java.util.Calendar;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	
	@Autowired
	RedisIntegration redisIntegration;
	
	private static Logger log = Logger.getLogger(MessageService.class.getName());

	public Message[] getMessages() {
		return redisIntegration.getMessages();
	}

	public boolean postMessage(Message m) {
		m.timestamp = Calendar.getInstance().getTimeInMillis();
		return redisIntegration.postMessage(m);
	}

}
