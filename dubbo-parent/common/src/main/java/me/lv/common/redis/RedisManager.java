package me.lv.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands.GeoLocation;
import org.springframework.data.redis.connection.RedisGeoCommands.GeoRadiusCommandArgs;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisManager {

	/* 防止被实例化 */
	private RedisManager() {

	}

	@Autowired
	private RedisTemplate<String, Object> redisService;

	private static RedisManager instance;

	@PostConstruct
	public void afterPropertiesSet() {
		instance = this;
	}

	public static RedisManager getInstance() {
		return instance;
	}

	/**
	 * HASH 添加
	 * 
	 * @param key
	 * @param hashKey
	 * @param e
	 */
	public <K> void HashSetValue(String key, K hashKey,String e) {
		redisService.opsForHash().put(key, hashKey.toString(), e);
	}

	/**
	 * HASH 获取
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public <K> String HashGetValue(String key, K hashKey) {
		Long size = redisService.opsForHash().size(key);
		if (size != 0 && hashHasKey(key, hashKey)) {
			return  (String) redisService.opsForHash().get(key, hashKey.toString());
		}
		return null;
	}

	public Integer HashGetSize(String key) {
		return redisService.opsForHash().size(key).intValue();
	}

	/**
	 * HASH 批量获取
	 * 
	 * @param key
	 * @param hashKeys
	 *            ID数组
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <K> List<K> HashGetValueMulti(String key, Collection<Object> hashKeys) {
		return (List<K>) redisService.opsForHash().multiGet(key, hashKeys);
	}

	/**
	 * 获取HASH里面所有数据
	 * 
	 * @param key
	 * @return
	 */
	public Map<Object, Object> HashAllValue(String key) {

		return redisService.opsForHash().entries(key);

	}

	/**
	 * HASH 是否存在
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public <K> Boolean hashHasKey(String key, K hashKey) {
		return redisService.opsForHash().hasKey(key, hashKey.toString());
	}

	/**
	 * HASH 删除
	 * 
	 * @param key
	 * @param hashKey
	 */
	public <K> void hashDeleteHashKey(String key, K hashKey) {
		redisService.opsForHash().delete(key, hashKey.toString());
	}

	/**
	 * HASH 长度
	 * 
	 * @param key
	 * @return
	 */
	public long hashGetLength(String key) {
		return redisService.opsForHash().keys(key).size();

	}

	/**
	 * HASH 删除
	 * 
	 * @param key
	 * @param hashkey
	 */
	public <K> void removeHashKey(String key, K hashkey) {
		redisService.opsForHash().delete(key, hashkey.toString());
	}

	// end
	// list start
	public <E> Long push(String key, E e) {
		return redisService.opsForList().leftPush(key, e);
	}

	/**
	 * 出栈
	 * 
	 * @param key
	 * @return
	 */

	public byte[] pop(String key) {
		return (byte[]) redisService.opsForList().leftPop(key);
	}

	/**
	 * 入队
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long in(String key, byte[] e) {
		return redisService.opsForList().rightPush(key, e);
	}

	/**
	 * 出队
	 * 
	 * @param key
	 * @return
	 */
	public byte[] out(String key) {
		return (byte[]) redisService.opsForList().leftPop(key);
	}

	public byte[] out(String key, Long index) {
		return (byte[]) redisService.opsForList().index(key, index);
	}

	/**
	 * 栈/队列长
	 * 
	 * @param key
	 * @return
	 */
	public Long length(String key) {
		return redisService.opsForList().size(key);
	}

	/**
	 * 范围检索
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> range(String key, int start, int end) {
		return redisService.opsForList().range(key, start, end);
	}

	/**
	 * 移除
	 * 
	 * @param key
	 * @param i
	 * @param value
	 */
	public void remove(String key, long i, byte[] e) {

		redisService.opsForList().remove(key, i, e);
	}

	/**
	 * 检索
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public byte[] index(String key, long index) {
		return (byte[]) redisService.opsForList().index(key, index);
	}

	/**
	 * LIST 置值
	 * 
	 * @param key
	 * @param index
	 * @param value
	 */
	public void set(String key, long index, byte[] e) {
		redisService.opsForList().set(key, index, e);
	}

	/**
	 * LIST 裁剪
	 * 
	 * @param key
	 * @param start
	 * @param end
	 */
	public void trim(String key, long start, int end) {
		redisService.opsForList().trim(key, start, end);
	}

	/**
	 * 设置VALUE 有效时间
	 * 
	 * @param key
	 * @param e
	 * @param time
	 * @param unit
	 */
	public void SetValueIntime(String key, byte[] e, long time, TimeUnit unit) {
		redisService.opsForValue().set(key, e, time, unit);
	}

	/**
	 * 获取VALUE
	 * 
	 * @param key
	 * @return
	 */
	public byte[] getValueIntime(String key) {
		Object value = redisService.opsForValue().get(key);
		if (value == null) {
			return null;
		}
		return (byte[]) value;
	}

	/**
	 * VALUE 添加
	 * 
	 * @param key
	 * @param e
	 */
	public void ValueSetValue(String key, byte[] e) {
		redisService.opsForValue().set(key, e);
	}

	/**
	 * ZSET 添加
	 * 
	 * @param key
	 * @param bytes
	 * @param score
	 * @return
	 */
	public int zsetAdd(String key, byte[] bytes, double score) {
		boolean ret = redisService.opsForZSet().add(key, bytes, score);
		if (ret) {
			return 1;
		}
		return 0;
	}

	/**
	 * ZSET 在原有的分数上添加
	 * 
	 * @param key
	 * @param bytes
	 * @param score
	 * @return
	 */
	public int zsetIncre(String key, byte[] bytes, double score) {
		redisService.opsForZSet().incrementScore(key, bytes, score);
		return 1;
	}

	/**
	 * ZSET 判断值是否存在
	 * 
	 * @param key
	 * @param bytes
	 * @return
	 */
	public boolean zsetRank(String key, byte[] bytes) {
		Long ret = redisService.opsForZSet().rank(key, bytes);
		if (ret != null) {
			return true;
		}
		return false;
	}

	public Long zsetRankIndex(String key, byte[] bytes) {
		return redisService.opsForZSet().rank(key, bytes);

	}

	public Long zsetrevRankIndex(String key, byte[] bytes) {
		return redisService.opsForZSet().reverseRank(key, bytes);

	}

	/**
	 * ZSET 分数值递增(从小到大)
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <K> Set<K> zsetRange(String key, long start, long end) {
		return (Set<K>) redisService.opsForZSet().range(key, start, end);
	}

	/**
	 * ZSET 判断值是否存在并返回分数
	 * 
	 * @param key
	 * @param bytes
	 * @return
	 */
	public Double zsetScore(String key, byte[] bytes) {
		Double ret = redisService.opsForZSet().score(key, bytes);
		return ret;
	}

	/**
	 * ZSET 分数值递增(从大到小)
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <K> Set<K> zsetreverseRange(String key, long start, long end) {
		return (Set<K>) redisService.opsForZSet().reverseRange(key, start, end);
	}

	/**
	 * ZSET 分数值递增(从大到小) 带分数
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <K> Set<K> zsetReverseRangeWithScores(String key, long start, long end) {
		return (Set<K>) redisService.opsForZSet().reverseRangeWithScores(key, start, end);
	}

	/**
	 * ZSET 分数值递增(从大到小) 取分数区间分页
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <K> Set<K> zsetReverseRangeByScorePage(String key, long start, long end, Integer pageIndex,
			Integer pageSize) {
		return (Set<K>) redisService.opsForZSet().reverseRangeByScore(key, start, end, pageIndex, pageSize);
	}

	/**
	 * ZSET 分数值递增(从大到小) 取分数区间不分页
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <K> Set<K> zsetReverseRangeByScore(String key, long start, long end) {
		return (Set<K>) redisService.opsForZSet().reverseRangeByScore(key, start, end);
	}

	/**
	 * ZSET 分数值递增(从小到大) 取分数区间
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <K> Set<K> zsetRangeByScore(String key, long start, long end) {
		return (Set<K>) redisService.opsForZSet().rangeByScore(key, start, end);
	}

	/**
	 * ZSET 分数值递增(从小到大) 带分数
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <K> Set<K> zsetRangeWithScores(String key, long start, long end) {
		return (Set<K>) redisService.opsForZSet().rangeWithScores(key, start, end);
	}

	/**
	 * ZSET 大小
	 * 
	 * @param key
	 * @return
	 */
	public Long zsetSize(String key) {
		return redisService.opsForZSet().size(key);
	}

	/**
	 * zset获取指定范围内的数量
	 * 
	 * @param key
	 * @param min
	 *            最小0
	 * @param max
	 *            最大-1
	 * @return
	 */
	public Long zsetCount(String key, Long min, Long max) {
		return redisService.opsForZSet().count(key, min, max);
	}

	/**
	 * ZSET 删除
	 * 
	 * @param key
	 * @param bytes
	 * @return
	 */
	public long zsetRemove(String key, byte[] bytes) {
		return redisService.opsForZSet().remove(key, bytes);
	}

	/**
	 * 往SET里面添加数据
	 * 
	 * @param key
	 * @param bytes
	 */
	public void setAdd(String key, byte[] bytes) {
		redisService.opsForSet().add(key, bytes);

	}

	/**
	 * 获取SET 大小
	 * 
	 * @param key
	 * @return
	 */
	public long setSize(String key) {
		return redisService.opsForSet().size(key);
	}

	/**
	 * 删除SET
	 * 
	 * @param key
	 * @param bytes
	 * @return
	 */
	public long setRemove(String key, byte[] bytes) {
		return redisService.opsForSet().remove(key, bytes);
	}

	/**
	 * 判断是否包含KEY
	 * 
	 * @param key
	 * @return
	 */
	public boolean hasKey(String key) {
		return redisService.hasKey(key);
	}

	/**
	 * 批量添加到缓存
	 * 
	 * @param callBack
	 */
	public void pipe(RedisCallback<Boolean> callBack) {
		redisService.execute(callBack, false, true);
	}

	/**
	 * 序列化key
	 * 
	 * @return
	 */
	public RedisSerializer<String> keySerializer() {
		return redisService.getStringSerializer();
	}

	/**
	 * 检索redis key
	 * 
	 * @param key
	 *            关键字+*
	 * @return
	 */
	public Set<String> getKeys(String key) {
		return redisService.keys(key);
	}

	/**
	 * 从LIST里面读取数据
	 * 
	 * @param key
	 * @param start
	 *            开始位置 0 第一个
	 * @param end
	 *            结束位置 -1 最后一个
	 * @return
	 */
	public List<Object> lrange(String key, long start, long end) {
		return redisService.opsForList().range(key, start, end);
	}

	/**
	 * 删除缓存
	 * 
	 * @param key
	 */
	public void removeKeys(String key) {
		redisService.delete(key);
	}

	/**
	 * 拷贝缓存
	 * 
	 * @param key
	 */
	public void renameKey(String oldKey, String newKey) {
		redisService.rename(oldKey, newKey);
	}

	/**
	 * 随机从SET里面读取一条数据
	 * 
	 * @param key
	 * @return
	 */
	public byte[] srandmember(String key) {
		return (byte[]) redisService.opsForSet().randomMembers(key, 1).get(0);
	}

	public List<Object> srandmember(String key, Long size) {
		return redisService.opsForSet().randomMembers(key, size);
	}

	/**
	 * 获取LIST 里面某一个对象
	 * 
	 * @param key
	 * @param index
	 *            位置
	 * @return
	 */
	public byte[] lindex(String key, long index) {
		return (byte[]) redisService.opsForList().index(key, index);
	}

	/**
	 * 添加对象经纬度信息
	 * 
	 * @param key
	 * @param merber
	 * @param longitude
	 *            经度
	 * @param latitude
	 *            纬度
	 * @return
	 */
	public Long geoAdd(String key, Object merber, Double longitude, Double latitude) {

		Point point = new Point(latitude, longitude);
		return redisService.opsForGeo().geoAdd(key, point, merber);
	}

	/**
	 * 删除对象经纬度信息
	 * 
	 * @param key
	 * @param merber
	 * @return
	 */
	public Long geoRemove(String key, Object merber) {
		return redisService.opsForGeo().geoRemove(key, merber);
	}

	/**
	 * 获取当前经纬度周边
	 * 
	 * @param key
	 * @param longitude
	 *            经度
	 * @param latitude
	 *            纬度
	 * @param metric
	 *            距离(单位公里)
	 * @return
	 */
	public GeoResults<GeoLocation<Object>> geoDistinct(String key, Double longitude, Double latitude, Integer metric) {
		Point point = new Point(latitude, longitude);
		Distance distance = new Distance(metric, Metrics.KILOMETERS);
		Circle circle = new Circle(point, distance);
		GeoRadiusCommandArgs args = GeoRadiusCommandArgs.newGeoRadiusArgs();
		args.sortAscending();
		return redisService.opsForGeo().geoRadius(key, circle, args);
	}

	public void valueIncr(String key, Long value) {
		redisService.opsForValue().increment(key, value);
	}

	public long valueGet(String key) {
		byte[] allCount = (byte[]) redisService.opsForValue().get(key);
		return Long.parseLong(new String(allCount));
	}
}
