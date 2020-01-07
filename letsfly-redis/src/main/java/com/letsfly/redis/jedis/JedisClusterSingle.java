package com.letsfly.redis.jedis;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import redis.clients.jedis.BitOP;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.ListPosition;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.StreamEntry;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.StreamPendingEntry;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.commands.JedisClusterCommands;
import redis.clients.jedis.commands.JedisClusterScriptingCommands;
import redis.clients.jedis.commands.MultiKeyJedisClusterCommands;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.params.ZAddParams;
import redis.clients.jedis.params.ZIncrByParams;

/**
 * JedisCluster工具类(采用JedisCluster实现)
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public class JedisClusterSingle implements JedisClusterCommands, MultiKeyJedisClusterCommands, 
    JedisClusterScriptingCommands {
    
    private JedisCluster jedisCluster;
    
    @Override
    public Object eval(String script, int keyCount, String... params) {
        Object result = jedisCluster.eval(script, keyCount, params);
        jedisCluster.close();
        return result;
    }

    @Override
    public Object eval(String script, List<String> keys, List<String> args) {
        Object result = jedisCluster.eval(script, keys, args);
        jedisCluster.close();
        return result;
    }

    @Override
    public Object eval(String script, String sampleKey) {
        Object result = jedisCluster.eval(script, sampleKey);
        jedisCluster.close();
        return result;
    }

    @Override
    public Object evalsha(String sha1, String sampleKey) {
        Object result = jedisCluster.evalsha(sha1, sampleKey);
        jedisCluster.close();
        return result;
    }

    @Override
    public Object evalsha(String sha1, List<String> keys, List<String> args) {
        Object result = jedisCluster.evalsha(sha1, keys, args);
        jedisCluster.close();
        return result;
    }

    @Override
    public Object evalsha(String sha1, int keyCount, String... params) {
        Object result = jedisCluster.evalsha(sha1, keyCount, params);
        jedisCluster.close();
        return result;
    }

    @Override
    public Boolean scriptExists(String sha1, String sampleKey) {
        Boolean result = jedisCluster.scriptExists(sha1, sampleKey);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<Boolean> scriptExists(String sampleKey, String... sha1) {
        List<Boolean> result = jedisCluster.scriptExists(sampleKey, sha1);
        jedisCluster.close();
        return result;
    }

    @Override
    public String scriptLoad(String script, String sampleKey) {
        String result = jedisCluster.scriptLoad(script, sampleKey);
        jedisCluster.close();
        return result;
    }

    @Override
    public String scriptFlush(String sampleKey) {
        String result = jedisCluster.scriptFlush(sampleKey);
        jedisCluster.close();
        return result;
    }

    @Override
    public String scriptKill(String sampleKey) {
        String result = jedisCluster.scriptKill(sampleKey);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long del(String... keys) {
        Long result = jedisCluster.del(keys);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long unlink(String... keys) {
        Long result = jedisCluster.unlink(keys);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long exists(String... keys) {
        Long result = jedisCluster.exists(keys);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<String> blpop(int timeout, String... keys) {
        List<String> result = jedisCluster.blpop(timeout, keys);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<String> brpop(int timeout, String... keys) {
        List<String> result = jedisCluster.brpop(timeout, keys);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<String> mget(String... keys) {
        List<String> result = jedisCluster.mget(keys);
        jedisCluster.close();
        return result;
    }

    @Override
    public String mset(String... keysvalues) {
        String result = jedisCluster.mset(keysvalues);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long msetnx(String... keysvalues) {
        Long result = jedisCluster.msetnx(keysvalues);
        jedisCluster.close();
        return result;
    }

    @Override
    public String rename(String oldkey, String newkey) {
        String result = jedisCluster.rename(oldkey, newkey);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long renamenx(String oldkey, String newkey) {
        Long result = jedisCluster.renamenx(oldkey, newkey);
        jedisCluster.close();
        return result;
    }

    @Override
    public String rpoplpush(String srckey, String dstkey) {
        String result = jedisCluster.rpoplpush(srckey, dstkey);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> sdiff(String... keys) {
        Set<String> result = jedisCluster.sdiff(keys);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long sdiffstore(String dstkey, String... keys) {
        Long result = jedisCluster.sdiffstore(dstkey, keys);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> sinter(String... keys) {
        Set<String> result = jedisCluster.sinter(keys);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long sinterstore(String dstkey, String... keys) {
        Long result = jedisCluster.sinterstore(dstkey, keys);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long smove(String srckey, String dstkey, String member) {
        Long result = jedisCluster.smove(srckey, dstkey, member);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long sort(String key, SortingParams sortingParameters, String dstkey) {
        Long result = jedisCluster.sort(key, sortingParameters, dstkey);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long sort(String key, String dstkey) {
        Long result = jedisCluster.sort(key, dstkey);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> sunion(String... keys) {
        Set<String> result = jedisCluster.sunion(keys);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long sunionstore(String dstkey, String... keys) {
        Long result = jedisCluster.sunionstore(dstkey, keys);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zinterstore(String dstkey, String... sets) {
        Long result = jedisCluster.zinterstore(dstkey, sets);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zinterstore(String dstkey, ZParams params, String... sets) {
        Long result = jedisCluster.zinterstore(dstkey, params, sets);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zunionstore(String dstkey, String... sets) {
        Long result = jedisCluster.zunionstore(dstkey, sets);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zunionstore(String dstkey, ZParams params, String... sets) {
        Long result = jedisCluster.zunionstore(dstkey, params, sets);
        jedisCluster.close();
        return result;
    }

    @Override
    public String brpoplpush(String source, String destination, int timeout) {
        String result = jedisCluster.brpoplpush(source, destination, timeout);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long publish(String channel, String message) {
        Long result = jedisCluster.publish(channel, message);
        jedisCluster.close();
        return result;
    }

    @Override
    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        jedisCluster.subscribe(jedisPubSub, channels);
        jedisCluster.close();
    }

    @Override
    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        jedisCluster.psubscribe(jedisPubSub, patterns);
        jedisCluster.close();
    }

    @Override
    public Long bitop(BitOP op, String destKey, String... srcKeys) {
        Long result = jedisCluster.bitop(op, destKey, srcKeys);
        jedisCluster.close();
        return result;
    }

    @Override
    public String pfmerge(String destkey, String... sourcekeys) {
        String result = jedisCluster.pfmerge(destkey, sourcekeys);
        jedisCluster.close();
        return result;
    }

    @Override
    public long pfcount(String... keys) {
        long result = jedisCluster.pfcount(keys);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long touch(String... keys) {
        Long result = jedisCluster.touch(keys);
        jedisCluster.close();
        return result;
    }

    @Override
    public ScanResult<String> scan(String cursor, ScanParams params) {
        ScanResult<String> result = jedisCluster.scan(cursor, params);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> keys(String pattern) {
        Set<String> result = jedisCluster.keys(pattern);
        jedisCluster.close();
        return result;
    }

    @Override
    public String set(String key, String value) {
        String result = jedisCluster.set(key, value);
        jedisCluster.close();
        return result;
    }

    @Override
    public String set(String key, String value, SetParams params) {
        String result = jedisCluster.set(key, value, params);
        jedisCluster.close();
        return result;
    }

    @Override
    public String get(String key) {
        String result = jedisCluster.get(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Boolean exists(String key) {
        Boolean result = jedisCluster.exists(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long persist(String key) {
        Long result = jedisCluster.persist(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public String type(String key) {
        String result = jedisCluster.type(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public byte[] dump(String key) {
        byte[] result = jedisCluster.dump(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public String restore(String key, int ttl, byte[] serializedValue) {
        String result = jedisCluster.restore(key, ttl, serializedValue);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long expire(String key, int seconds) {
        Long result = jedisCluster.expire(key, seconds);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long pexpire(String key, long milliseconds) {
        Long result = jedisCluster.pexpire(key, milliseconds);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        Long result = jedisCluster.expireAt(key, unixTime);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long pexpireAt(String key, long millisecondsTimestamp) {
        Long result = jedisCluster.pexpireAt(key, millisecondsTimestamp);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long ttl(String key) {
        Long result = jedisCluster.ttl(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long pttl(String key) {
        Long result = jedisCluster.pttl(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long touch(String key) {
        Long result = jedisCluster.touch(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Boolean setbit(String key, long offset, boolean value) {
        Boolean result = jedisCluster.setbit(key, offset, value);
        jedisCluster.close();
        return result;
    }

    @Override
    public Boolean setbit(String key, long offset, String value) {
        Boolean result = jedisCluster.setbit(key, offset, value);
        jedisCluster.close();
        return result;
    }

    @Override
    public Boolean getbit(String key, long offset) {
        Boolean result = jedisCluster.getbit(key, offset);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        Long result = jedisCluster.setrange(key, offset, value);
        jedisCluster.close();
        return result;
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        String result = jedisCluster.getrange(key, startOffset, endOffset);
        jedisCluster.close();
        return result;
    }

    @Override
    public String getSet(String key, String value) {
        String result = jedisCluster.getSet(key, value);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long setnx(String key, String value) {
        Long result = jedisCluster.setnx(key, value);
        jedisCluster.close();
        return result;
    }

    @Override
    public String setex(String key, int seconds, String value) {
        String result = jedisCluster.setex(key, seconds, value);
        jedisCluster.close();
        return result;
    }

    @Override
    public String psetex(String key, long milliseconds, String value) {
        String result = jedisCluster.psetex(key, milliseconds, value);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long decrBy(String key, long decrement) {
        Long result = jedisCluster.decrBy(key, decrement);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long decr(String key) {
        Long result = jedisCluster.decr(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long incrBy(String key, long increment) {
        Long result = jedisCluster.incrBy(key, increment);
        jedisCluster.close();
        return result;
    }

    @Override
    public Double incrByFloat(String key, double increment) {
        Double result = jedisCluster.incrByFloat(key, increment);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long incr(String key) {
        Long result = jedisCluster.incr(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long append(String key, String value) {
        Long result = jedisCluster.append(key, value);
        jedisCluster.close();
        return result;
    }

    @Override
    public String substr(String key, int start, int end) {
        String result = jedisCluster.substr(key, start, end);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long hset(String key, String field, String value) {
        Long result = jedisCluster.hset(key, field, value);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long hset(String key, Map<String, String> hash) {
        Long result = jedisCluster.hset(key, hash);
        jedisCluster.close();
        return result;
    }

    @Override
    public String hget(String key, String field) {
        String result = jedisCluster.hget(key, field);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        Long result = jedisCluster.hsetnx(key, field, value);
        jedisCluster.close();
        return result;
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        String result = jedisCluster.hmset(key, hash);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        List<String> result = jedisCluster.hmget(key, fields);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long hincrBy(String key, String field, long value) {
        Long result = jedisCluster.hincrBy(key, field, value);
        jedisCluster.close();
        return result;
    }

    @Override
    public Boolean hexists(String key, String field) {
        Boolean result = jedisCluster.hexists(key, field);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long hdel(String key, String... field) {
        Long result = jedisCluster.hdel(key, field);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long hlen(String key) {
        Long result = jedisCluster.hlen(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> hkeys(String key) {
        Set<String> result = jedisCluster.hkeys(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<String> hvals(String key) {
        List<String> result = jedisCluster.hvals(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Map<String, String> result = jedisCluster.hgetAll(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long rpush(String key, String... string) {
        Long result = jedisCluster.rpush(key, string);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long lpush(String key, String... string) {
        Long result = jedisCluster.lpush(key, string);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long llen(String key) {
        Long result = jedisCluster.llen(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<String> lrange(String key, long start, long stop) {
        List<String> result = jedisCluster.lrange(key, start, stop);
        jedisCluster.close();
        return result;
    }

    @Override
    public String ltrim(String key, long start, long stop) {
        String result = jedisCluster.ltrim(key, start, stop);
        jedisCluster.close();
        return result;
    }

    @Override
    public String lindex(String key, long index) {
        String result = jedisCluster.lindex(key, index);
        jedisCluster.close();
        return result;
    }

    @Override
    public String lset(String key, long index, String value) {
        String result = jedisCluster.lset(key, index, value);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long lrem(String key, long count, String value) {
        Long result = jedisCluster.lrem(key, count, value);
        jedisCluster.close();
        return result;
    }

    @Override
    public String lpop(String key) {
        String result = jedisCluster.lpop(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public String rpop(String key) {
        String result = jedisCluster.rpop(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long sadd(String key, String... member) {
        Long result = jedisCluster.sadd(key, member);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> smembers(String key) {
        Set<String> result = jedisCluster.smembers(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long srem(String key, String... member) {
        Long result = jedisCluster.srem(key, member);
        jedisCluster.close();
        return result;
    }

    @Override
    public String spop(String key) {
        String result = jedisCluster.spop(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> spop(String key, long count) {
        Set<String> result = jedisCluster.spop(key, count);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long scard(String key) {
        Long result = jedisCluster.scard(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Boolean sismember(String key, String member) {
        Boolean result = jedisCluster.sismember(key, member);
        jedisCluster.close();
        return result;
    }

    @Override
    public String srandmember(String key) {
        String result = jedisCluster.srandmember(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<String> srandmember(String key, int count) {
        List<String> result = jedisCluster.srandmember(key, count);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long strlen(String key) {
        Long result = jedisCluster.strlen(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zadd(String key, double score, String member) {
        Long result = jedisCluster.zadd(key, score, member);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zadd(String key, double score, String member, ZAddParams params) {
        Long result = jedisCluster.zadd(key, score, member, params);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        Long result = jedisCluster.zadd(key, scoreMembers);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
        Long result = jedisCluster.zadd(key, scoreMembers, params);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> zrange(String key, long start, long stop) {
        Set<String> result = jedisCluster.zrange(key, start, stop);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zrem(String key, String... members) {
        Long result = jedisCluster.zrem(key, members);
        jedisCluster.close();
        return result;
    }

    @Override
    public Double zincrby(String key, double increment, String member) {
        Double result = jedisCluster.zincrby(key, increment, member);
        jedisCluster.close();
        return result;
    }

    @Override
    public Double zincrby(String key, double increment, String member, ZIncrByParams params) {
        Double result = jedisCluster.zincrby(key, increment, member, params);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zrank(String key, String member) {
        Long result = jedisCluster.zrank(key, member);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zrevrank(String key, String member) {
        Long result = jedisCluster.zrevrank(key, member);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> zrevrange(String key, long start, long stop) {
        Set<String> result = jedisCluster.zrevrange(key, start, stop);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long stop) {
        Set<Tuple> result = jedisCluster.zrangeWithScores(key, start, stop);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String key, long start, long stop) {
        Set<Tuple> result = jedisCluster.zrevrangeWithScores(key, start, stop);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zcard(String key) {
        Long result = jedisCluster.zcard(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Double zscore(String key, String member) {
        Double result = jedisCluster.zscore(key, member);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<String> sort(String key) {
        List<String> result = jedisCluster.sort(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<String> sort(String key, SortingParams sortingParameters) {
        List<String> result = jedisCluster.sort(key, sortingParameters);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zcount(String key, double min, double max) {
        Long result = jedisCluster.zcount(key, min, max);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zcount(String key, String min, String max) {
        Long result = jedisCluster.zcount(key, min, max);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        Set<String> result = jedisCluster.zrangeByScore(key, min, max);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) {
        Set<String> result = jedisCluster.zrangeByScore(key, min, max);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        Set<String> result = jedisCluster.zrevrangeByScore(key, max, min);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        Set<String> result = jedisCluster.zrangeByScore(key, min, max, offset, count);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min) {
        Set<String> result = jedisCluster.zrevrangeByScore(key, max, min);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        Set<String> result = jedisCluster.zrangeByScore(key, min, max, offset, count);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        Set<String> result = jedisCluster.zrevrangeByScore(key, max, min, offset, count);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        Set<Tuple> result = jedisCluster.zrangeByScoreWithScores(key, min, max);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        Set<Tuple> result = jedisCluster.zrevrangeByScoreWithScores(key, max, min);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        Set<Tuple> result = jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        Set<String> result = jedisCluster.zrevrangeByScore(key, max, min, offset, count);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        Set<Tuple> result = jedisCluster.zrangeByScoreWithScores(key, min, max);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        Set<Tuple> result = jedisCluster.zrevrangeByScoreWithScores(key, max, min);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        Set<Tuple> result = jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        Set<Tuple> result = jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        Set<Tuple> result = jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zremrangeByRank(String key, long start, long stop) {
        Long result = jedisCluster.zremrangeByRank(key, start, stop);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zremrangeByScore(String key, double min, double max) {
        Long result = jedisCluster.zremrangeByScore(key, min, max);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zremrangeByScore(String key, String min, String max) {
        Long result = jedisCluster.zremrangeByScore(key, min, max);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zlexcount(String key, String min, String max) {
        Long result = jedisCluster.zlexcount(key, min, max);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max) {
        Set<String> result = jedisCluster.zrangeByLex(key, min, max);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        Set<String> result = jedisCluster.zrangeByLex(key, min, max, offset, count);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min) {
        Set<String> result = jedisCluster.zrevrangeByLex(key, max, min);
        jedisCluster.close();
        return result;
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        Set<String> result = jedisCluster.zrevrangeByLex(key, max, min, offset, count);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long zremrangeByLex(String key, String min, String max) {
        Long result = jedisCluster.zremrangeByLex(key, min, max);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long linsert(String key, ListPosition where, String pivot, String value) {
        Long result = jedisCluster.linsert(key, where, pivot, value);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long lpushx(String key, String... string) {
        Long result = jedisCluster.lpushx(key, string);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long rpushx(String key, String... string) {
        Long result = jedisCluster.rpushx(key, string);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<String> blpop(int timeout, String key) {
        List<String> result = jedisCluster.blpop(timeout, key);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<String> brpop(int timeout, String key) {
        List<String> result = jedisCluster.brpop(timeout, key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long del(String key) {
        Long result = jedisCluster.del(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long unlink(String key) {
        Long result = jedisCluster.unlink(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public String echo(String string) {
        String result = jedisCluster.echo(string);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long bitcount(String key) {
        Long result = jedisCluster.bitcount(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long bitcount(String key, long start, long end) {
        Long result = jedisCluster.bitcount(key, start, end);
        jedisCluster.close();
        return result;
    }

    @Override
    public ScanResult<Entry<String, String>> hscan(String key, String cursor) {
        ScanResult<Entry<String, String>> result = jedisCluster.hscan(key, cursor);
        jedisCluster.close();
        return result;
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor) {
        ScanResult<String> result = jedisCluster.sscan(key, cursor);
        jedisCluster.close();
        return result;
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor) {
        ScanResult<Tuple> result = jedisCluster.zscan(key, cursor);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long pfadd(String key, String... elements) {
        Long result = jedisCluster.pfadd(key, elements);
        jedisCluster.close();
        return result;
    }

    @Override
    public long pfcount(String key) {
        long result = jedisCluster.pfcount(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long geoadd(String key, double longitude, double latitude, String member) {
        Long result = jedisCluster.geoadd(key, longitude, latitude, member);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        Long result = jedisCluster.geoadd(key, memberCoordinateMap);
        jedisCluster.close();
        return result;
    }

    @Override
    public Double geodist(String key, String member1, String member2) {
        Double result = jedisCluster.geodist(key, member1, member2);
        jedisCluster.close();
        return result;
    }

    @Override
    public Double geodist(String key, String member1, String member2, GeoUnit unit) {
        Double result = jedisCluster.geodist(key, member1, member2, unit);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<String> geohash(String key, String... members) {
        List<String> result = jedisCluster.geohash(key, members);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<GeoCoordinate> geopos(String key, String... members) {
        List<GeoCoordinate> result = jedisCluster.geopos(key, members);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius,
            GeoUnit unit) {
        List<GeoRadiusResponse> result = jedisCluster.georadius(key, longitude, latitude, radius, unit);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<GeoRadiusResponse> georadiusReadonly(String key, double longitude, double latitude, double radius,
            GeoUnit unit) {
        List<GeoRadiusResponse> result = jedisCluster.georadiusReadonly(key, longitude, latitude, radius, unit);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit,
            GeoRadiusParam param) {
        List<GeoRadiusResponse> result = jedisCluster.georadius(key, longitude, latitude, radius, unit, param);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<GeoRadiusResponse> georadiusReadonly(String key, double longitude, double latitude, double radius,
            GeoUnit unit, GeoRadiusParam param) {
        List<GeoRadiusResponse> result = jedisCluster.georadius(key, longitude, latitude, radius, unit, param);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        List<GeoRadiusResponse> result = jedisCluster.georadiusByMember(key, member, radius, unit);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMemberReadonly(String key, String member, double radius, GeoUnit unit) {
        List<GeoRadiusResponse> result = jedisCluster.georadiusByMemberReadonly(key, member, radius, unit);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit,
            GeoRadiusParam param) {
        List<GeoRadiusResponse> result = jedisCluster.georadiusByMember(key, member, radius, unit, param);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMemberReadonly(String key, String member, double radius, GeoUnit unit,
            GeoRadiusParam param) {
        List<GeoRadiusResponse> result = jedisCluster.georadiusByMemberReadonly(key, member, radius, unit, param);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<Long> bitfield(String key, String... arguments) {
        List<Long> result = jedisCluster.bitfield(key, arguments);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long hstrlen(String key, String field) {
        Long result = jedisCluster.hstrlen(key, field);
        jedisCluster.close();
        return result;
    }

    @Override
    public StreamEntryID xadd(String key, StreamEntryID id, Map<String, String> hash) {
        StreamEntryID result = jedisCluster.xadd(key, id, hash);
        jedisCluster.close();
        return result;
    }

    @Override
    public StreamEntryID xadd(String key, StreamEntryID id, Map<String, String> hash, long maxLen,
            boolean approximateLength) {
        StreamEntryID result = jedisCluster.xadd(key, id, hash, maxLen, approximateLength);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long xlen(String key) {
        Long result = jedisCluster.xlen(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<StreamEntry> xrange(String key, StreamEntryID start, StreamEntryID end, int count) {
        List<StreamEntry> result = jedisCluster.xrange(key, start, end, count);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<StreamEntry> xrevrange(String key, StreamEntryID end, StreamEntryID start, int count) {
        List<StreamEntry> result = jedisCluster.xrevrange(key, end, start, count);
        jedisCluster.close();
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Entry<String, List<StreamEntry>>> xread(int count, long block,
            Entry<String, StreamEntryID>... streams) {
        List<Entry<String, List<StreamEntry>>> result = jedisCluster.xread(count, block, streams);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long xack(String key, String group, StreamEntryID... ids) {
        Long result = jedisCluster.xack(key, group, ids);
        jedisCluster.close();
        return result;
    }

    @Override
    public String xgroupCreate(String key, String groupname, StreamEntryID id, boolean makeStream) {
        String result = jedisCluster.xgroupCreate(key, groupname, id, makeStream);
        jedisCluster.close();
        return result;
    }

    @Override
    public String xgroupSetID(String key, String groupname, StreamEntryID id) {
        String result = jedisCluster.xgroupSetID(key, groupname, id);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long xgroupDestroy(String key, String groupname) {
        Long result = jedisCluster.xgroupDestroy(key, groupname);
        jedisCluster.close();
        return result;
    }

    @Override
    public String xgroupDelConsumer(String key, String groupname, String consumername) {
        String result = jedisCluster.xgroupDelConsumer(key, groupname, consumername);
        jedisCluster.close();
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Entry<String, List<StreamEntry>>> xreadGroup(String groupname, String consumer, int count, long block,
            boolean noAck, Entry<String, StreamEntryID>... streams) {
        List<Entry<String, List<StreamEntry>>> result = jedisCluster.xreadGroup(groupname, consumer, count, block, noAck, streams);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<StreamPendingEntry> xpending(String key, String groupname, StreamEntryID start, StreamEntryID end,
            int count, String consumername) {
        List<StreamPendingEntry> result = jedisCluster.xpending(key, groupname, start, end, count, consumername);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long xdel(String key, StreamEntryID... ids) {
        Long result = jedisCluster.xdel(key, ids);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long xtrim(String key, long maxLen, boolean approximateLength) {
        Long result = jedisCluster.xtrim(key, maxLen, approximateLength);
        jedisCluster.close();
        return result;
    }

    @Override
    public List<StreamEntry> xclaim(String key, String group, String consumername, long minIdleTime, long newIdleTime,
            int retries, boolean force, StreamEntryID... ids) {
        List<StreamEntry> result = jedisCluster.xclaim(key, group, consumername, minIdleTime, newIdleTime, retries, force, ids);
        jedisCluster.close();
        return result;
    }

    @Override
    public Long waitReplicas(String key, int replicas, long timeout) {
        Long result = jedisCluster.waitReplicas(key, replicas, timeout);
        jedisCluster.close();
        return result;
    }
    
    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }
}
