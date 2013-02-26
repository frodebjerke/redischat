package no.bekk.bekkopen.redischat;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

@Service
public class RedisIntegration {

	@Autowired
	private JedisPool pool;
	
	private static final String MESSAGE_KEY = "messages";
	
	private static Logger log = Logger.getLogger(RedisIntegration.class.getName());
	
	public Message[] getMessages() {
		Jedis jedis = null;

		try {
			jedis = pool.getResource();
			log.info("Jedis handler: " + jedis);	
			if (jedis != null) {
				
				// insert magic here

			} else {
				log.severe("Could not get a connection to Redis.");
			}
		} catch (JedisConnectionException jce) {
			log.severe("Could not connect to Redis: " + jce);
		} finally {
			pool.returnResource(jedis);
		}

		Message messages[] = null;
//		Message messages[] = new Message[.....];
		
		// populate messages[] with all messages stored in Redis
		
		return messages;
	}

	public boolean postMessage(Message m) {
		Jedis jedis = null;
		boolean success = false;
		try {
			jedis = pool.getResource();
			log.info("Jedis connection: " + jedis);	
			if (jedis != null) {
				
				// insert magic here
				
				log.info("Added a message from user " + m.username);
				success = true;
			} else {
				log.severe("Could not get a connection to Redis.");				
			}
		} catch (JedisConnectionException jce) {
			log.severe("Could not connect to Redis: " + jce);
		} finally {
			pool.returnResource(jedis);
		}
		return success;
	}
}
