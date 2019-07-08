package com.byb.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis 配置
 */
@Configuration
public class RedisConfigure {

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.database}")
	private int database;

	@Value("${spring.redis.pool.max-active}")
	private int max_active;

	@Value("${spring.redis.pool.max-wait}")
	private int max_wait;

	@Value("${spring.redis.pool.max-idle}")
	private int max_idle;

	@Value("${spring.redis.pool.min-idle}")
	private int min_idle;

	@Value("${spring.redis.timeout}")
	private int time_out;

	/**
	 * redis模板，存储关键字是字符串，值是Jdk序列化
	 *
	 * @param factory
	 * @return
	 * @Description:
	 */
	@Bean
	public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		// key序列化方式;但是如果方法上有Long等非String类型的话，会报类型转换错误；
		// Long类型不可以会出现异常信息;
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(redisSerializer);
		redisTemplate.setHashKeySerializer(redisSerializer);
		redisTemplate.setStringSerializer(redisSerializer);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	/**
	 * redis连接的基础设置
	 *
	 * @return
	 * @Description:
	 */
	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(host);
		factory.setPort(port);
		factory.setPassword(password);
		factory.setDatabase(database);
		// 设置连接超时时间
		factory.setTimeout(time_out);
		factory.setUsePool(true);
		factory.setPoolConfig(jedisPoolConfig());
		return factory;
	}

	/**
	 * 连接池配置
	 *
	 * @return
	 * @Description:
	 */
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(max_idle);
		jedisPoolConfig.setMaxWaitMillis(max_wait);
		jedisPoolConfig.setMinIdle(min_idle);
		jedisPoolConfig.setMaxTotal(max_active);
		// jedisPoolConfig.set ...
		return jedisPoolConfig;
	}
}
