package com.juansecu.opentoonix.config.database;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisConfig {
    private static final Logger CONSOLE_LOGGER = Red5LoggerFactory.getLogger(RedisConfig.class);
    private static final String REDIS_HOST_PROPERTY = "redis.host";
    private static final String REDIS_PORT_PROPERTY = "redis.port";
    private static final String REDIS_SSL_PROPERTY = "redis.ssl";
    private static final String REDIS_TIMEOUT_PROPERTY = "redis.timeout";

    @Bean
    public Jedis getConnection() {
        final Properties properties = this.getProperties();
        final String redisHost = properties.getProperty(
            RedisConfig.REDIS_HOST_PROPERTY,
            null
        );
        final int redisPort = Integer.parseInt(
            properties.getProperty(
                RedisConfig.REDIS_PORT_PROPERTY,
                "6379"
            )
        );
        final boolean redisSsl = Boolean.parseBoolean(
            properties.getProperty(
                RedisConfig.REDIS_SSL_PROPERTY,
                "false"
            )
        );
        final int redisTimeout = Integer.parseInt(
            properties.getProperty(
                RedisConfig.REDIS_TIMEOUT_PROPERTY,
                "5000"
            )
        );

        final Jedis jedis = new Jedis(redisHost, redisPort, redisTimeout, redisSsl);

        try {
            jedis.connect();
        } catch (JedisConnectionException jedisConnectionException) {
            CONSOLE_LOGGER.error(
                "Error connecting to Redis: {}",
                jedisConnectionException.getMessage()
            );
        }

        return jedis;
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        final URL url = ClassLoader.getSystemResource("redis.properties");

        try (final InputStream inputStream = url.openStream()) {
            properties.load(inputStream);
        } catch (IOException e) {
            CONSOLE_LOGGER.error(
                "Error loading redis.properties file: {}",
                e.getMessage()
            );
        }

        return properties;
    }
}
