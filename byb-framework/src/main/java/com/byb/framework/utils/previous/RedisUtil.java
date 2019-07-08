package com.byb.framework.utils.previous;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Redis常用操作类
 *
 * @author Alex
 */
public class RedisUtil {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    private static String PONG = "PONG";

    /**
     * Get a Jedis Object from JedisPool.
     *
     * @param jedisPool
     * @return
     */
    public synchronized static Jedis getJedisObject(JedisPool jedisPool) {
        if (jedisPool != null) {
            Jedis jedis = jedisPool.getResource();
            String pong = jedis.ping();
            //检测redis连接可用性
            if (StringUtils.isNotBlank(pong) && PONG.equals(pong.toUpperCase())) {
                return jedis;
            }
        }
        return null;
    }

    /**
     * @param jedis
     * @return
     */
    public static Boolean checkJedisValid(final Jedis jedis) {
        if (jedis != null && jedis.isConnected() && PONG.equals(jedis.ping().toUpperCase())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


//    /**
//     * 清除任务计数
//     * @param jedis
//     * @param taskId
//     */
//    public static void hdelCountInfo(Jedis jedis, int taskId){
//        jedis.hdel(CrawlStatusRedisCountEnum.elise_crawler_crawl_success.getValue(),String.valueOf(taskId));
//        jedis.hdel(CrawlStatusRedisCountEnum.elise_crawler_crawl_failed.getValue(),String.valueOf(taskId));
//        jedis.hdel(CrawlStatusRedisCountEnum.elise_crawler_download_success.getValue(),String.valueOf(taskId));
//        jedis.hdel(CrawlStatusRedisCountEnum.elise_crawler_download_failed.getValue(),String.valueOf(taskId));
//        jedis.hdel(CrawlStatusRedisCountEnum.elise_crawler_extract_success.getValue(),String.valueOf(taskId));
//        jedis.hdel(CrawlStatusRedisCountEnum.elise_crawler_extract_failed.getValue(),String.valueOf(taskId));
//        jedis.hdel(CrawlStatusRedisCountEnum.elise_crawler_pipeline_success.getValue(),String.valueOf(taskId));
//        jedis.hdel(CrawlStatusRedisCountEnum.elise_crawler_pipeline_failed.getValue(),String.valueOf(taskId));
//        jedis.hdel(CrawlStatusRedisCountEnum.elise_crawler_schduled.getValue(),String.valueOf(taskId));
//        jedis.hdel(CrawlStatusRedisCountEnum.elise_crawler_totaltask.getValue(),String.valueOf(taskId));
//    }


    /**
     * Return the jedis object to JedisPool.
     *
     * @param jedis
     * @param jedisPool
     */
    @SuppressWarnings("deprecation")
    public synchronized static void close(final Jedis jedis, JedisPool jedisPool) {
        if (jedis != null) {
            try {
                // jedisPool.returnResource(jedis);
                jedis.close();
            } catch (Exception e) {
                jedisPool.returnBrokenResource(jedis);
                logger.error("close jedis error, jedis: " + jedis, e);
            }
        }
    }

//    /**
//     * 采集的配置项更新了，也在对应的配置项版本key的field对应version +1
//     *
//     * @param configureKey
//     * @param field
//     * @param jedis
//     * @return
//     */
//    public synchronized static Long updateCrawlerConfigureVersion(String configureKey, String field, final Jedis jedis) {
//        String version = jedis.hget(configureKey, field);
//        if (StringUtils.isEmpty(version)) {
//            return jedis.hset(configureKey, field, "1");
//        } else {
//            int v = Integer.parseInt(version) + 1;
//            return jedis.hset(configureKey, field, String.valueOf(v));
//        }
//    }

    /**
     * @param key
     * @param value
     * @param expiretime
     * @param jedisPool
     * @return
     */
    public static String set(String key, String value, int expiretime, JedisPool jedisPool) {
        Jedis jedis = getJedisObject(jedisPool);
        String str = null;
        if (jedis != null) {
            try {
                str = jedis.set(key, value);
                if (expiretime > 0) {
                    expire(key, expiretime, jedis);
                }
            } catch (Exception e) {
                throw new JedisException("Set key occurs Exception....", e);
            } finally {
                close(jedis, jedisPool);
            }
        } else {
            logger.error("Jedis is null, set key failed");
        }
        return str;
    }

    /**
     * @param key
     * @param value
     * @param jedis
     * @return
     */
    public static String set(String key, String value, int expiretime, final Jedis jedis) {
        String str = null;
        if (jedis != null) {
            try {
                str = jedis.set(key, value);
                if (expiretime > 0) {
                    expire(key, expiretime, jedis);
                }
            } catch (Exception e) {
                throw new JedisException("Set key occurs Exception....", e);
            }
        } else {
            logger.error("Jedis is null, set key failed!");
        }
        return str;
    }

    /**
     * @param key
     * @return
     */
    public static String get(String key, JedisPool jedisPool) {
        Jedis jedis = getJedisObject(jedisPool);
        String str = null;
        if (jedis != null) {
            try {
                str = jedis.get(key);
            } catch (Exception e) {
                throw new JedisException("Get key occurs Exception....", e);
            } finally {
                close(jedis, jedisPool);
            }
        } else {
            logger.error("Jedis is null, Get key failed");
        }
        return str;
    }

    /**
     * @param key
     * @param jedis
     * @return
     */
    public static String get(String key, Jedis jedis) {
        String str = null;
        if (jedis != null) {
            try {
                str = jedis.get(key);
            } catch (Exception e) {
                throw new JedisException("Get key occurs Exception....", e);
            }
        } else {
            logger.error("Jedis is null, Get key failed");
        }
        return str;
    }

    /**
     * @param key
     * @param filed
     * @param jedisPool
     * @return
     */
    public static String hget(String key, String filed, JedisPool jedisPool) {
        Jedis jedis = getJedisObject(jedisPool);
        String str = null;
        if (jedis != null) {
            try {
                str = jedis.hget(key, filed);
            } catch (Exception e) {
                throw new JedisException("Hget key occurs Exception....", e);
            } finally {
                close(jedis, jedisPool);
            }
        } else {
            logger.error("Jedis is null, Hget key failed");
        }
        return str;
    }

    /**
     * @param key
     * @param filed
     * @param jedis
     * @return
     */
    public static String hget(String key, String filed, final Jedis jedis) {
        String str = null;
        if (jedis != null) {
            try {
                str = jedis.hget(key, filed);
            } catch (Exception e) {
                throw new JedisException("Hget key occurs Exception....", e);
            }
        } else {
            logger.error("Jedis is null, Hget key failed");
        }
        return str;
    }

    /**
     * @param key
     * @param filed
     * @param value
     * @param expiretime
     * @param jedisPool
     * @return
     */
    public static Long hset(String key, String filed, String value, int expiretime, JedisPool jedisPool) {
        Jedis jedis = getJedisObject(jedisPool);
        Long result = -130L;
        if (jedis != null) {
            try {
                result = jedis.hset(key, filed, value);
                if (expiretime > 0) {
                    expire(key, expiretime, jedis);
                }
            } catch (Exception e) {
                throw new JedisException("Hset key occurs Exception....", e);
            } finally {
                close(jedis, jedisPool);
            }
        } else {
            logger.error("Jedis is null, Hset key failed");
        }
        return result;
    }

    /**
     * @param key
     * @param filed
     * @param value
     * @param expiretime
     * @param jedis
     * @return
     */
    public static Long hset(String key, String filed, String value, int expiretime, final Jedis jedis) {
        Long result = -140L;
        if (jedis != null) {
            try {
                result = jedis.hset(key, filed, value);
                if (expiretime > 0) {
                    expire(key, expiretime, jedis);
                }
            } catch (Exception e) {
                throw new JedisException("Hset filed occurs Exception....", e);
            }
        } else {
            logger.error("Jedis is null, Hset filed failed");
        }
        return result;
    }

    /**
     * @param key
     * @param map
     * @param jedis
     * @return
     */
    public static String hmset(String key, Map<String, String> map, int expiretime, final Jedis jedis) {
        String result = "-1";
        if (jedis != null) {
            try {
                jedis.hmset(key, map);
                if (expiretime > 0) {
                    result = String.valueOf(expire(key, expiretime, jedis));
                }
            } catch (Exception e) {
                throw new JedisException("Hset key occurs Exception....", e);
            }
        } else {
            logger.error("Jedis is null, Hset key failed");
        }
        return result;
    }

    /**
     * @param key
     * @param jedis
     * @return
     */
    public static Map<String, String> hgetall(String key, final Jedis jedis) {
        Map<String, String> map = new HashMap<String, String>();
        if (jedis != null) {
            map = jedis.hgetAll(key);
        } else {
            logger.error("Jedis is null, Hset key failed");
        }
        return map;
    }

    /**
     * @param key
     * @param jedisPool
     * @return
     */
    public static Map<String, String> hgetall(String key, JedisPool jedisPool) {
        Jedis jedis = getJedisObject(jedisPool);
        Map<String, String> map = new HashMap<String, String>();
        try {
            if (jedis != null) {
                map = jedis.hgetAll(key);
            } else {
                logger.error("Jedis is null, Hset key failed");
            }
        } catch (Exception e) {
            throw new JedisException("Incr key occurs Exception....", e);
        } finally {
            close(jedis, jedisPool);
        }
        return map;
    }

    /**
     * @param key
     * @param jedisPool
     * @return
     */
    public static long incr(String key, JedisPool jedisPool) {
        Jedis jedis = getJedisObject(jedisPool);
        Long result = -150L;
        if (jedis != null) {
            try {
                result = jedis.incr(key);
            } catch (Exception e) {
                throw new JedisException("Incr key occurs Exception....", e);
            } finally {
                close(jedis, jedisPool);
            }
        } else {
            logger.error("Jedis is null, Incr key failed");
        }
        return result;
    }

    /**
     * @param key
     * @param filed
     * @param increment
     * @param jedis
     * @return
     */
    public static long hincrBy(String key, String filed, long increment, final Jedis jedis) {
        Long result = -149L;
        result = jedis.hincrBy(key, filed, increment);
        return result;
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param jedis
     * @return
     */
    private static Long expire(String key, int expiretime, final Jedis jedis) {
        Long result = -120L;
        if (jedis != null) {
            try {
                result = jedis.expire(key, expiretime);
            } catch (Exception e) {
                throw new JedisException("Expire key occurs Exception....", e);
            }
        } else {
            logger.error("jedis is null, expire key failed");
        }
        return result;
    }

    /**
     * TTL, time to live
     *
     * @param key
     * @param jedisPool
     * @return
     */
    public static long ttl(String key, JedisPool jedisPool) {
        Jedis jedis = getJedisObject(jedisPool);
        Long result = -160L;
        if (jedis != null) {
            try {
                result = jedis.ttl(key);
            } catch (Exception e) {
                throw new JedisException("Ttl key occurs Exception....", e);
            } finally {
                close(jedis, jedisPool);
            }
        } else {
            logger.error("Jedis is null, Ttl key failed");
        }
        return result;
    }

    /**
     * TTL, time to live
     *
     * @param key
     * @param jedis
     * @return
     */
    public static long ttl(String key, final Jedis jedis) {
        Long result = -170L;
        if (jedis != null) {
            try {
                result = jedis.ttl(key);
            } catch (Exception e) {
                throw new JedisException("Ttl key occurs Exception....", e);
            }
        } else {
            logger.error("Jedis is null, Ttl key failed");
        }
        return result;
    }

    /**
     * @param key
     * @param jedisPool
     * @return
     */
    public static long del(String key, JedisPool jedisPool) {
        Jedis jedis = getJedisObject(jedisPool);
        Long result = -180L;
        if (jedis != null) {
            try {
                result = jedis.del(key);
            } catch (Exception e) {
                throw new JedisException("Del key occurs Exception....", e);
            } finally {
                close(jedis, jedisPool);
            }
        } else {
            logger.error("Jedis is null, Del key failed");
        }
        return result;
    }

    /**
     * @param key
     * @param jedis
     * @return
     */
    public static long del(String key, final Jedis jedis) {
        Long result = -190L;
        if (jedis != null) {
            try {
                result = jedis.del(key);
            } catch (Exception e) {
                throw new JedisException("Del key occurs Exception....", e);
            }
        } else {
            logger.error("Jedis is null, Del key failed");
        }
        return result;
    }

    /**
     * @param key
     * @param filed
     * @return
     */
    public static long hdel(String key, String filed, JedisPool jedisPool) {
        Jedis jedis = getJedisObject(jedisPool);
        Long result = -200L;
        if (jedis != null) {
            try {
                result = jedis.hdel(key, filed);
            } catch (Exception e) {
                throw new JedisException("Hdel key occurs Exception....", e);
            } finally {
                close(jedis, jedisPool);
            }
        } else {
            logger.error("Jedis is null, Hdel key failed");
        }
        return result;
    }

    /**
     * Get keys by using regular pattern.
     *
     * @param pattern
     * @param jedis
     * @return
     */
    public static Set<String> getKeySetByPattern(String pattern, final Jedis jedis) {
        Set<String> keySet = new HashSet<String>();
        keySet = jedis.keys(pattern);
        return keySet;
    }

    /**
     * Handle jedisException, write log and return whether the connection is
     * broken.
     * https://my.oschina.net/zhuguowei/blog/406807
     *
     * @param jedisException
     */
    private synchronized static boolean handleJedisException(final Exception jedisException) {
        if (jedisException instanceof JedisConnectionException) {
            logger.error("Redis connection lost.", jedisException);
        } else if (jedisException instanceof JedisDataException) {
            if ((jedisException.getMessage() != null) && (jedisException.getMessage().indexOf("READONLY") != -1)) {
                logger.error("Redis connection are read-only slave.", jedisException);
            } else {
                return false;
            }
        } else {
            logger.error("Jedis exception happen.", jedisException);
        }
        return true;
    }

    @SuppressWarnings({"deprecation", "unused"})
    private synchronized static void returnBrokenResource(Exception e, Jedis jedis, JedisPool jedisPool) {
        boolean conectionBroken = handleJedisException(e);
        if (conectionBroken) {
            jedisPool.returnBrokenResource(jedis);
        } else {
            logger.error("Return back jedis failed.", e);
        }
    }

    // /**
    // * Return jedis connection to the pool, call different return methods
    // * depends on the conectionBroken status.
    // */
    // @SuppressWarnings("deprecation")
    // private static void closeResource(Jedis jedis, boolean conectionBroken) {
    // try {
    // if (conectionBroken) {
    // jedisPool.returnBrokenResource(jedis);
    // } else {
    // jedis.close();
    // }
    // } catch (Exception e) {
    // LOG.error("Return back jedis failed, will force close the jedis.", e);
    // }
    // }

    /**************** 直接序列化城对象 *******************************//*
    *//**
     * 设置 list
     *
     * @param <T>
     * @param key
     * @param value
     *//*
    public static <T> void setEntity(String key, T t, JedisPool jedisPool) {

		if (Configure.getProperty(Const.ENABLE_REDIS) == null
				|| Configure.getProperty(Const.ENABLE_REDIS).equals("false"))
			return;

		Jedis jedis = getJedisObject(jedisPool);
		try {
			if (jedis != null)
				jedis.set(key.getBytes(), ObjectTranscoder.serialize(t));
		} catch (Exception e) {
			logger.error("setEntity(String key, T t, JedisPool jedisPool) error : " + e);
		} finally {
			close(jedis, jedisPool);
		}
	}

	*//**
     * 获取list
     *
     * @param <T>
     * @param key
     * @return list
     *//*
    public static <T> T getEntity(String key, JedisPool jedisPool) {

		if (Configure.getProperty(Const.ENABLE_REDIS) == null
				|| Configure.getProperty(Const.ENABLE_REDIS).equals("false"))
			return null;

		Jedis jedis = getJedisObject(jedisPool);
		T t = null;
		try {
			if (jedis != null) {
				byte[] in = jedis.get(key.getBytes());
				t = (T) ObjectTranscoder.deserialize(in);
			}
		} catch (Exception e) {
			logger.error("getEntity(String key, JedisPool jedisPool) key error : " + e);
		} finally {
			close(jedis, jedisPool);
		}
		return t;
	}

	public static <T> Long hsetEntity(String hkey, String key, T item, final Jedis jedis) {

		if (Configure.getProperty(Const.ENABLE_REDIS) == null
				|| Configure.getProperty(Const.ENABLE_REDIS).equals("false"))
			return null;

		Long result = -130L;
		if (jedis != null) {
			try {
				result = jedis.hset(hkey, key, JSON.toJSONString(item));
			} catch (Exception e) {
				throw new JedisException("hsetEntity(String hkey, String key, T item, final Jedis jedis)", e);
			} finally {

			}
		} else {
			logger.error("hsetEntity(String hkey, String key, T item, final Jedis jedis)");
		}
		return result;
	}

	public static <T> Long hsetEntity(String hkey, String key, T item, JedisPool jedisPool) {

		if (Configure.getProperty(Const.ENABLE_REDIS) == null
				|| Configure.getProperty(Const.ENABLE_REDIS).equals("false"))
			return null;

		Long result = -130L;
		Jedis jedis = getJedisObject(jedisPool);
		try {
			if (jedis != null)
				result = jedis.hset(hkey, key, JSON.toJSONString(item));
		} catch (Exception e) {
			throw new JedisException("hsetEntity(String hkey, String key, T item, JedisPool jedisPool)", e);
		} finally {
			close(jedis, jedisPool);
		}
		return result;
	}

	public static <T> Long hsetEntity(String hkey, String key, T item, Jedis jedis, JedisPool jedisPool) {

		if (Configure.getProperty(Const.ENABLE_REDIS) == null
				|| Configure.getProperty(Const.ENABLE_REDIS).equals("false"))
			return null;

		Long result = -130L;
		try {
			if (jedis != null) {
				result = jedis.hset(hkey, key, JSON.toJSONString(item));
			}
		} catch (Exception e) {
			throw new JedisException("hsetEntity(String hkey, String key, T item, Jedis jedis, JedisPool jedisPool)",
					e);
		} finally {
		}
		return result;
	}

	public static <T> T hgetEntity(String hkey, String key, Class<T> cls, JedisPool jedisPool) {

		if (Configure.getProperty(Const.ENABLE_REDIS) == null
				|| Configure.getProperty(Const.ENABLE_REDIS).equals("false"))
			return null;
		T t = null;
		Jedis jedis = getJedisObject(jedisPool);
		try {
			if (jedis != null) {
				String item = jedis.hget(hkey, key);
				t = JSON.parseObject(item, cls);
			}
		} catch (Exception e) {
			throw new JedisException("hgetEntity(String hkey, String key, Class<T> cls, JedisPool jedisPool)", e);
		} finally {
			close(jedis, jedisPool);
		}
		return t;
	}

	public static String hgetEntity(String hkey, String filed, JedisPool jedisPool) {
		if (Configure.getProperty(Const.ENABLE_REDIS) == null
				|| Configure.getProperty(Const.ENABLE_REDIS).equals("false"))
			return null;

		String item = "";
		Jedis jedis = getJedisObject(jedisPool);
		try {
			if (jedis != null)
				item = jedis.hget(hkey, filed);
		} catch (Exception e) {
			throw new JedisException(" hgetEntity(String hkey, String key, JedisPool jedisPool)", e);
		} finally {
			close(jedis, jedisPool);
		}
		return item;
	}

	public static <T> List<T> hgetList(String hkey, Class<T> cls, JedisPool jedisPool) {

		if (Configure.getProperty(Const.ENABLE_REDIS) == null
				|| Configure.getProperty(Const.ENABLE_REDIS).equals("false"))
			return null;

		List<T> lstData = new ArrayList<T>();
		Jedis jedis = getJedisObject(jedisPool);
		try {
			if (jedis != null) {
				Map<String, String> map = jedis.hgetAll(hkey);
				if (map == null)
					return lstData;

				Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = it.next();
					T t = JSON.parseObject(entry.getValue(), cls);
					lstData.add(t);
				}
			}
		} catch (Exception e) {
			throw new JedisException("hgetList(String hkey, Class<T> cls, JedisPool jedisPool)", e);
		} finally {
			close(jedis, jedisPool);
		}
		return lstData;
	}

	*//**
     * 设置 list
     *
     * @param <T>
     * @param key
     * @param value
     *//*
    public static <T> void setList(String key, List<T> list, JedisPool jedisPool) {

		if (Configure.getProperty(Const.ENABLE_REDIS) == null
				|| Configure.getProperty(Const.ENABLE_REDIS).equals("false"))
			return;
		Jedis jedis = getJedisObject(jedisPool);
		try {
			if (jedis != null)
				jedis.set(key.getBytes(), ObjectTranscoder.serialize(list));
		} catch (Exception e) {
			logger.error("setList(String key, List<T> list, JedisPool jedisPool) error : " + e);
		} finally {
			close(jedis, jedisPool);
		}
	}

	*//**
     * 获取list
     *
     * @param <T>
     * @param key
     * @return list
     *//*
    public static <T> List<T> getList(String key, JedisPool jedisPool) {

		if (Configure.getProperty(Const.ENABLE_REDIS) == null
				|| Configure.getProperty(Const.ENABLE_REDIS).equals("false"))
			return null;

		Jedis jedis = getJedisObject(jedisPool);
		List<T> list = null;
		try {
			if (jedis != null) {
				byte[] in = jedis.get(key.getBytes());
				list = (List<T>) ObjectTranscoder.deserialize(in);
			}
		} catch (Exception e) {
			logger.error("getList(String key, JedisPool jedisPool)  error : " + e);
		} finally {
			close(jedis, jedisPool);
		}
		return list;
	}

	*//**
     * 设置 map
     *
     * @param <T>
     * @param key
     * @param value
     *//*
    public static <T> void setMap(String key, Map<String, T> map, JedisPool jedisPool) {

		if (Configure.getProperty(Const.ENABLE_REDIS) == null
				|| Configure.getProperty(Const.ENABLE_REDIS).equals("false"))
			return;

		Jedis jedis = getJedisObject(jedisPool);
		try {
			if (jedis != null)
				jedis.set(key.getBytes(), ObjectTranscoder.serialize(map));
		} catch (Exception e) {
			logger.error("setMap(String key, Map<String, T> map, JedisPool jedisPool) error : " + e);
		} finally {
			close(jedis, jedisPool);
		}
	}

	*//**
     * 获取list
     *
     * @param <T>
     * @param key
     * @return list
     *//*
    public static <T> Map<String, T> getMap(String key, JedisPool jedisPool) {

		if (Configure.getProperty(Const.ENABLE_REDIS) == null
				|| Configure.getProperty(Const.ENABLE_REDIS).equals("false"))
			return null;
		Jedis jedis = getJedisObject(jedisPool);
		Map<String, T> map = null;

		try {
			if (jedis != null) {
				byte[] in = jedis.get(key.getBytes());
				map = (Map<String, T>) ObjectTranscoder.deserialize(in);
			}
		} catch (Exception e) {
			logger.error("getMap(String key, JedisPool jedisPool) error : " + e);
		} finally {
			close(jedis, jedisPool);
		}
		return map;
	}*/
}
