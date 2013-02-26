package no.bekk.bekkopen.redischat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
public class JedisConfig {
	
	@Bean
	public JedisPool getJedisPool() {
		return new JedisPool(new JedisPoolConfig(), "127.0.0.1");
	}
}
