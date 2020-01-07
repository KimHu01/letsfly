package com.letsfly.redis.jedis;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import redis.clients.jedis.BitOP;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.ListPosition;
import redis.clients.jedis.Module;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.StreamEntry;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.StreamPendingEntry;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.commands.ModuleCommands;
import redis.clients.jedis.commands.MultiKeyCommands;
import redis.clients.jedis.commands.ScriptingCommands;
import redis.clients.jedis.commands.SentinelCommands;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.params.ZAddParams;
import redis.clients.jedis.params.ZIncrByParams;

/**
 * Jedis工具类(哨兵方式:采用JedisSentinelPool实现)
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public class JedisSentinelSingle implements JedisCommands, MultiKeyCommands, ScriptingCommands, 
    SentinelCommands, ModuleCommands {
    
    private JedisSentinelPool jedisSentinelPool;
    
    @Override
    public String moduleLoad(String path) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.moduleLoad(path);
        jedis.close();
        return result;
    }

    @Override
    public String moduleUnload(String name) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.moduleUnload(name);
        jedis.close();
        return result;
    }

    @Override
    public List<Module> moduleList() {
        Jedis jedis = jedisSentinelPool.getResource();
        List<Module> result = jedis.moduleList();
        jedis.close();
        return result;
    }

    @Override
    public Object eval(String script, int keyCount, String... params) {
        Jedis jedis = jedisSentinelPool.getResource();
        Object result = jedis.eval(script, keyCount, params);
        jedis.close();
        return result;
    }

    @Override
    public Object eval(String script, List<String> keys, List<String> args) {
        Jedis jedis = jedisSentinelPool.getResource();
        Object result = jedis.eval(script, keys, args);
        jedis.close();
        return result;
    }

    @Override
    public Object eval(String script) {
        Jedis jedis = jedisSentinelPool.getResource();
        Object result = jedis.eval(script);
        jedis.close();
        return result;
    }

    @Override
    public Object evalsha(String sha1) {
        Jedis jedis = jedisSentinelPool.getResource();
        Object result = jedis.evalsha(sha1);
        jedis.close();
        return result;
    }

    @Override
    public Object evalsha(String sha1, List<String> keys, List<String> args) {
        Jedis jedis = jedisSentinelPool.getResource();
        Object result = jedis.evalsha(sha1, keys, args);
        jedis.close();
        return result;
    }

    @Override
    public Object evalsha(String sha1, int keyCount, String... params) {
        Jedis jedis = jedisSentinelPool.getResource();
        Object result = jedis.evalsha(sha1, keyCount, params);
        jedis.close();
        return result;
    }

    @Override
    public Boolean scriptExists(String sha1) {
        Jedis jedis = jedisSentinelPool.getResource();
        Boolean result = jedis.scriptExists(sha1);
        jedis.close();
        return result;
    }

    @Override
    public List<Boolean> scriptExists(String... sha1) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<Boolean> result = jedis.scriptExists(sha1);
        jedis.close();
        return result;
    }

    @Override
    public String scriptLoad(String script) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.scriptLoad(script);
        jedis.close();
        return result;
    }

    @Override
    public Long del(String... keys) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.del(keys);
        jedis.close();
        return result;
    }

    @Override
    public Long unlink(String... keys) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.unlink(keys);
        jedis.close();
        return result;
    }

    @Override
    public Long exists(String... keys) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.exists(keys);
        jedis.close();
        return result;
    }

    @Override
    public List<String> blpop(int timeout, String... keys) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<String> result = jedis.blpop(timeout, keys);
        jedis.close();
        return result;
    }

    @Override
    public List<String> brpop(int timeout, String... keys) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<String> result = jedis.brpop(timeout, keys);
        jedis.close();
        return result;
    }

    @Override
    public List<String> blpop(String... args) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<String> result = jedis.blpop(args);
        jedis.close();
        return result;
    }

    @Override
    public List<String> brpop(String... args) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<String> result = jedis.brpop(args);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> keys(String pattern) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.keys(pattern);
        jedis.close();
        return result;
    }

    @Override
    public List<String> mget(String... keys) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<String> result = jedis.mget(keys);
        jedis.close();
        return result;
    }

    @Override
    public String mset(String... keysvalues) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.mset(keysvalues);
        jedis.close();
        return result;
    }

    @Override
    public Long msetnx(String... keysvalues) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.msetnx(keysvalues);
        jedis.close();
        return result;
    }

    @Override
    public String rename(String oldkey, String newkey) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.rename(oldkey, newkey);
        jedis.close();
        return result;
    }

    @Override
    public Long renamenx(String oldkey, String newkey) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.renamenx(oldkey, newkey);
        jedis.close();
        return result;
    }

    @Override
    public String rpoplpush(String srckey, String dstkey) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.rpoplpush(srckey, dstkey);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> sdiff(String... keys) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.sdiff(keys);
        jedis.close();
        return result;
    }

    @Override
    public Long sdiffstore(String dstkey, String... keys) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.sdiffstore(dstkey, keys);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> sinter(String... keys) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.sinter(keys);
        jedis.close();
        return result;
    }

    @Override
    public Long sinterstore(String dstkey, String... keys) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.sinterstore(dstkey, keys);
        jedis.close();
        return result;
    }

    @Override
    public Long smove(String srckey, String dstkey, String member) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.smove(srckey, dstkey, member);
        jedis.close();
        return result;
    }

    @Override
    public Long sort(String key, SortingParams sortingParameters, String dstkey) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.sort(dstkey, sortingParameters, dstkey);
        jedis.close();
        return result;
    }

    @Override
    public Long sort(String key, String dstkey) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.sort(key, dstkey);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> sunion(String... keys) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.sunion(keys);
        jedis.close();
        return result;
    }

    @Override
    public Long sunionstore(String dstkey, String... keys) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.sunionstore(dstkey, keys);
        jedis.close();
        return result;
    }

    @Override
    public String watch(String... keys) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.watch(keys);
        jedis.close();
        return result;
    }

    @Override
    public String unwatch() {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.unwatch();
        jedis.close();
        return result;
    }

    @Override
    public Long zinterstore(String dstkey, String... sets) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zinterstore(dstkey, sets);
        jedis.close();
        return result;
    }

    @Override
    public Long zinterstore(String dstkey, ZParams params, String... sets) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zinterstore(dstkey, params, sets);
        jedis.close();
        return result;
    }

    @Override
    public Long zunionstore(String dstkey, String... sets) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zunionstore(dstkey, sets);
        jedis.close();
        return result;
    }

    @Override
    public Long zunionstore(String dstkey, ZParams params, String... sets) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zunionstore(dstkey, params, sets);
        jedis.close();
        return result;
    }

    @Override
    public String brpoplpush(String source, String destination, int timeout) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.brpoplpush(source, destination, timeout);
        jedis.close();
        return result;
    }

    @Override
    public Long publish(String channel, String message) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.publish(channel, message);
        jedis.close();
        return result;
    }

    @Override
    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        Jedis jedis = jedisSentinelPool.getResource();
        jedis.subscribe(jedisPubSub, channels);
        jedis.close();
    }

    @Override
    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        Jedis jedis = jedisSentinelPool.getResource();
        jedis.psubscribe(jedisPubSub, patterns);
        jedis.close();
        
    }

    @Override
    public String randomKey() {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.randomKey();
        jedis.close();
        return result;
    }

    @Override
    public Long bitop(BitOP op, String destKey, String... srcKeys) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.bitop(op, destKey, srcKeys);
        jedis.close();
        return result;
    }

    @Override
    public ScanResult<String> scan(String cursor) {
        Jedis jedis = jedisSentinelPool.getResource();
        ScanResult<String> result = jedis.scan(cursor);
        jedis.close();
        return result;
    }

    @Override
    public ScanResult<String> scan(String cursor, ScanParams params) {
        Jedis jedis = jedisSentinelPool.getResource();
        ScanResult<String> result = jedis.scan(cursor, params);
        jedis.close();
        return result;
    }

    @Override
    public String pfmerge(String destkey, String... sourcekeys) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.pfmerge(destkey, sourcekeys);
        jedis.close();
        return result;
    }

    @Override
    public long pfcount(String... keys) {
        Jedis jedis = jedisSentinelPool.getResource();
        long result = jedis.pfcount(keys);
        jedis.close();
        return result;
    }

    @Override
    public Long touch(String... keys) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.touch(keys);
        jedis.close();
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Entry<String, List<StreamEntry>>> xread(int count, long block,
            Entry<String, StreamEntryID>... streams) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<Entry<String, List<StreamEntry>>> result = jedis.xread(count, block, streams);
        jedis.close();
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Entry<String, List<StreamEntry>>> xreadGroup(String groupname, String consumer, int count, long block,
            boolean noAck, Entry<String, StreamEntryID>... streams) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<Entry<String, List<StreamEntry>>> result = jedis.xreadGroup(groupname, consumer, count, block, noAck, streams);
        jedis.close();
        return result;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.set(key, value);
        jedis.close();
        return result;
    }

    @Override
    public String set(String key, String value, SetParams params) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.set(key, value, params);
        jedis.close();
        return result;
    }

    @Override
    public String get(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Boolean result = jedis.exists(key);
        jedis.close();
        return result;
    }

    @Override
    public Long persist(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.persist(key);
        jedis.close();
        return result;
    }

    @Override
    public String type(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.type(key);
        jedis.close();
        return result;
    }

    @Override
    public byte[] dump(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        byte[] result = jedis.dump(key);
        jedis.close();
        return result;
    }

    @Override
    public String restore(String key, int ttl, byte[] serializedValue) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.restore(key, ttl, serializedValue);
        jedis.close();
        return result;
    }

    @Override
    public String restoreReplace(String key, int ttl, byte[] serializedValue) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.restoreReplace(key, ttl, serializedValue);
        jedis.close();
        return result;
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.expire(key, seconds);
        jedis.close();
        return result;
    }

    @Override
    public Long pexpire(String key, long milliseconds) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.pexpire(key, milliseconds);
        jedis.close();
        return result;
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.expireAt(key, unixTime);
        jedis.close();
        return result;
    }

    @Override
    public Long pexpireAt(String key, long millisecondsTimestamp) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.pexpireAt(key, millisecondsTimestamp);
        jedis.close();
        return result;
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    @Override
    public Long pttl(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.pttl(key);
        jedis.close();
        return result;
    }

    @Override
    public Long touch(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.touch(key);
        jedis.close();
        return result;
    }

    @Override
    public Boolean setbit(String key, long offset, boolean value) {
        Jedis jedis = jedisSentinelPool.getResource();
        Boolean result = jedis.setbit(key, offset, value);
        jedis.close();
        return result;
    }

    @Override
    public Boolean setbit(String key, long offset, String value) {
        Jedis jedis = jedisSentinelPool.getResource();
        Boolean result = jedis.setbit(key, offset, value);
        jedis.close();
        return result;
    }

    @Override
    public Boolean getbit(String key, long offset) {
        Jedis jedis = jedisSentinelPool.getResource();
        Boolean result = jedis.getbit(key, offset);
        jedis.close();
        return result;
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.setrange(key, offset, value);
        jedis.close();
        return result;
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.getrange(key, startOffset, endOffset);
        jedis.close();
        return result;
    }

    @Override
    public String getSet(String key, String value) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.getSet(key, value);
        jedis.close();
        return result;
    }

    @Override
    public Long setnx(String key, String value) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.setnx(key, value);
        jedis.close();
        return result;
    }

    @Override
    public String setex(String key, int seconds, String value) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.setex(key, seconds, value);
        jedis.close();
        return result;
    }

    @Override
    public String psetex(String key, long milliseconds, String value) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.psetex(key, milliseconds, value);
        jedis.close();
        return result;
    }

    @Override
    public Long decrBy(String key, long decrement) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.decrBy(key, decrement);
        jedis.close();
        return result;
    }

    @Override
    public Long decr(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.decr(key);
        jedis.close();
        return result;
    }

    @Override
    public Long incrBy(String key, long increment) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.incrBy(key, increment);
        jedis.close();
        return result;
    }

    @Override
    public Double incrByFloat(String key, double increment) {
        Jedis jedis = jedisSentinelPool.getResource();
        Double result = jedis.incrByFloat(key, increment);
        jedis.close();
        return result;
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    @Override
    public Long append(String key, String value) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.append(key, value);
        jedis.close();
        return result;
    }

    @Override
    public String substr(String key, int start, int end) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.substr(key, start, end);
        jedis.close();
        return result;
    }

    @Override
    public Long hset(String key, String field, String value) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.hset(key, field, value);
        jedis.close();
        return result;
    }

    @Override
    public Long hset(String key, Map<String, String> hash) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.hset(key, hash);
        jedis.close();
        return result;
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.hget(key, field);
        jedis.close();
        return result;
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.hsetnx(key, field, value);
        jedis.close();
        return result;
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.hmset(key, hash);
        jedis.close();
        return result;
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<String> result = jedis.hmget(key, fields);
        jedis.close();
        return result;
    }

    @Override
    public Long hincrBy(String key, String field, long value) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.hincrBy(key, field, value);
        jedis.close();
        return result;
    }

    @Override
    public Double hincrByFloat(String key, String field, double value) {
        Jedis jedis = jedisSentinelPool.getResource();
        Double result = jedis.hincrByFloat(key, field, value);
        jedis.close();
        return result;
    }

    @Override
    public Boolean hexists(String key, String field) {
        Jedis jedis = jedisSentinelPool.getResource();
        Boolean result = jedis.hexists(key, field);
        jedis.close();
        return result;
    }

    @Override
    public Long hdel(String key, String... field) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.hdel(key, field);
        jedis.close();
        return result;
    }

    @Override
    public Long hlen(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.hlen(key);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> hkeys(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.hkeys(key);
        jedis.close();
        return result;
    }

    @Override
    public List<String> hvals(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<String> result = jedis.hvals(key);
        jedis.close();
        return result;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Map<String, String> result = jedis.hgetAll(key);
        jedis.close();
        return result;
    }

    @Override
    public Long rpush(String key, String... string) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.rpush(key, string);
        jedis.close();
        return result;
    }

    @Override
    public Long lpush(String key, String... string) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.lpush(key, string);
        jedis.close();
        return result;
    }

    @Override
    public Long llen(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.llen(key);
        jedis.close();
        return result;
    }

    @Override
    public List<String> lrange(String key, long start, long stop) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<String> result = jedis.lrange(key, start, stop);
        jedis.close();
        return result;
    }

    @Override
    public String ltrim(String key, long start, long stop) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.ltrim(key, start, stop);
        jedis.close();
        return result;
    }

    @Override
    public String lindex(String key, long index) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.lindex(key, index);
        jedis.close();
        return result;
    }

    @Override
    public String lset(String key, long index, String value) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.lset(key, index, value);
        jedis.close();
        return result;
    }

    @Override
    public Long lrem(String key, long count, String value) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.lrem(key, count, value);
        jedis.close();
        return result;
    }

    @Override
    public String lpop(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.lpop(key);
        jedis.close();
        return result;
    }

    @Override
    public String rpop(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.rpop(key);
        jedis.close();
        return result;
    }

    @Override
    public Long sadd(String key, String... member) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.sadd(key, member);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> smembers(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.smembers(key);
        jedis.close();
        return result;
    }

    @Override
    public Long srem(String key, String... member) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.srem(key, member);
        jedis.close();
        return result;
    }

    @Override
    public String spop(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.spop(key);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> spop(String key, long count) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.spop(key, count);
        jedis.close();
        return result;
    }

    @Override
    public Long scard(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.scard(key);
        jedis.close();
        return result;
    }

    @Override
    public Boolean sismember(String key, String member) {
        Jedis jedis = jedisSentinelPool.getResource();
        Boolean result = jedis.sismember(key, member);
        jedis.close();
        return result;
    }

    @Override
    public String srandmember(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.srandmember(key);
        jedis.close();
        return result;
    }

    @Override
    public List<String> srandmember(String key, int count) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<String> result = jedis.srandmember(key, count);
        jedis.close();
        return result;
    }

    @Override
    public Long strlen(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.strlen(key);
        jedis.close();
        return result;
    }

    @Override
    public Long zadd(String key, double score, String member) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zadd(key, score, member);
        jedis.close();
        return result;
    }

    @Override
    public Long zadd(String key, double score, String member, ZAddParams params) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zadd(key, score, member, params);
        jedis.close();
        return result;
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zadd(key, scoreMembers);
        jedis.close();
        return result;
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zadd(key, scoreMembers, params);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> zrange(String key, long start, long stop) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.zrange(key, start, stop);
        jedis.close();
        return result;
    }

    @Override
    public Long zrem(String key, String... members) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zrem(key, members);
        jedis.close();
        return result;
    }

    @Override
    public Double zincrby(String key, double increment, String member) {
        Jedis jedis = jedisSentinelPool.getResource();
        Double result = jedis.zincrby(key, increment, member);
        jedis.close();
        return result;
    }

    @Override
    public Double zincrby(String key, double increment, String member, ZIncrByParams params) {
        Jedis jedis = jedisSentinelPool.getResource();
        Double result = jedis.zincrby(key, increment, member, params);
        jedis.close();
        return result;
    }

    @Override
    public Long zrank(String key, String member) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zrank(key, member);
        jedis.close();
        return result;
    }

    @Override
    public Long zrevrank(String key, String member) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zrevrank(key, member);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> zrevrange(String key, long start, long stop) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.zrevrange(key, start, stop);
        jedis.close();
        return result;
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long stop) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<Tuple> result = jedis.zrangeWithScores(key, start, stop);
        jedis.close();
        return result;
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String key, long start, long stop) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<Tuple> result = jedis.zrevrangeWithScores(key, start, stop);
        jedis.close();
        return result;
    }

    @Override
    public Long zcard(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zcard(key);
        jedis.close();
        return result;
    }

    @Override
    public Double zscore(String key, String member) {
        Jedis jedis = jedisSentinelPool.getResource();
        Double result = jedis.zscore(key, member);
        jedis.close();
        return result;
    }

    @Override
    public List<String> sort(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<String> result = jedis.sort(key);
        jedis.close();
        return result;
    }

    @Override
    public List<String> sort(String key, SortingParams sortingParameters) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<String> result = jedis.sort(key, sortingParameters);
        jedis.close();
        return result;
    }

    @Override
    public Long zcount(String key, double min, double max) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zcount(key, min, max);
        jedis.close();
        return result;
    }

    @Override
    public Long zcount(String key, String min, String max) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zcount(key, min, max);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.zrangeByScore(key, min, max);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.zrangeByScore(key, min, max);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.zrevrangeByScore(key, max, min);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.zrangeByScore(key, min, max, offset, count);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.zrevrangeByScore(key, max, min);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.zrangeByScore(key, min, max, offset, count);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.zrevrangeByScore(key, max, min, offset, count);
        jedis.close();
        return result;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<Tuple> result = jedis.zrangeByScoreWithScores(key, min, max);
        jedis.close();
        return result;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<Tuple> result = jedis.zrevrangeByScoreWithScores(key, max, min);
        jedis.close();
        return result;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<Tuple> result = jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.zrevrangeByScore(key, max, min, offset, count);
        jedis.close();
        return result;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<Tuple> result = jedis.zrangeByScoreWithScores(key, min, max);
        jedis.close();
        return result;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<Tuple> result = jedis.zrevrangeByScoreWithScores(key, max, min);
        jedis.close();
        return result;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<Tuple> result = jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        jedis.close();
        return result;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<Tuple> result = jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        jedis.close();
        return result;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<Tuple> result = jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        jedis.close();
        return result;
    }

    @Override
    public Long zremrangeByRank(String key, long start, long stop) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zremrangeByRank(key, start, stop);
        jedis.close();
        return result;
    }

    @Override
    public Long zremrangeByScore(String key, double min, double max) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zremrangeByScore(key, min, max);
        jedis.close();
        return result;
    }

    @Override
    public Long zremrangeByScore(String key, String min, String max) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zremrangeByScore(key, min, max);
        jedis.close();
        return result;
    }

    @Override
    public Long zlexcount(String key, String min, String max) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zlexcount(key, min, max);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.zrangeByLex(key, min, max);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.zrangeByLex(key, min, max, offset, count);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.zrevrangeByLex(key, max, min);
        jedis.close();
        return result;
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        Jedis jedis = jedisSentinelPool.getResource();
        Set<String> result = jedis.zrevrangeByLex(key, max, min, offset, count);
        jedis.close();
        return result;
    }

    @Override
    public Long zremrangeByLex(String key, String min, String max) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.zremrangeByLex(key, min, max);
        jedis.close();
        return result;
    }

    @Override
    public Long linsert(String key, ListPosition where, String pivot, String value) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.linsert(key, where, pivot, value);
        jedis.close();
        return result;
    }

    @Override
    public Long lpushx(String key, String... string) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.lpushx(key, string);
        jedis.close();
        return result;
    }

    @Override
    public Long rpushx(String key, String... string) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.rpushx(key, string);
        jedis.close();
        return result;
    }

    @Override
    public List<String> blpop(int timeout, String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<String> result = jedis.blpop(timeout, key);
        jedis.close();
        return result;
    }

    @Override
    public List<String> brpop(int timeout, String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<String> result = jedis.brpop(timeout, key);
        jedis.close();
        return result;
    }

    @Override
    public Long del(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }

    @Override
    public Long unlink(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.unlink(key);
        jedis.close();
        return result;
    }

    @Override
    public String echo(String string) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.echo(string);
        jedis.close();
        return result;
    }

    @Override
    public Long move(String key, int dbIndex) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.move(key, dbIndex);
        jedis.close();
        return result;
    }

    @Override
    public Long bitcount(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.bitcount(key);
        jedis.close();
        return result;
    }

    @Override
    public Long bitcount(String key, long start, long end) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.bitcount(key, start, end);
        jedis.close();
        return result;
    }

    @Override
    public Long bitpos(String key, boolean value) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.bitpos(key, value);
        jedis.close();
        return result;
    }

    @Override
    public Long bitpos(String key, boolean value, BitPosParams params) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.bitpos(key, value, params);
        jedis.close();
        return result;
    }

    @Override
    public ScanResult<Entry<String, String>> hscan(String key, String cursor) {
        Jedis jedis = jedisSentinelPool.getResource();
        ScanResult<Entry<String, String>> result = jedis.hscan(key, cursor);
        jedis.close();
        return result;
    }

    @Override
    public ScanResult<Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
        Jedis jedis = jedisSentinelPool.getResource();
        ScanResult<Entry<String, String>> result = jedis.hscan(key, cursor, params);
        jedis.close();
        return result;
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor) {
        Jedis jedis = jedisSentinelPool.getResource();
        ScanResult<String> result = jedis.sscan(key, cursor);
        jedis.close();
        return result;
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor) {
        Jedis jedis = jedisSentinelPool.getResource();
        ScanResult<Tuple> result = jedis.zscan(key, cursor);
        jedis.close();
        return result;
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
        Jedis jedis = jedisSentinelPool.getResource();
        ScanResult<Tuple> result = jedis.zscan(key, cursor, params);
        jedis.close();
        return result;
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
        Jedis jedis = jedisSentinelPool.getResource();
        ScanResult<String> result = jedis.sscan(key, cursor, params);
        jedis.close();
        return result;
    }

    @Override
    public Long pfadd(String key, String... elements) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.pfadd(key, elements);
        jedis.close();
        return result;
    }

    @Override
    public long pfcount(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        long result = jedis.pfcount(key);
        jedis.close();
        return result;
    }

    @Override
    public Long geoadd(String key, double longitude, double latitude, String member) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.geoadd(key, longitude, latitude, member);
        jedis.close();
        return result;
    }

    @Override
    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.geoadd(key, memberCoordinateMap);
        jedis.close();
        return result;
    }

    @Override
    public Double geodist(String key, String member1, String member2) {
        Jedis jedis = jedisSentinelPool.getResource();
        Double result = jedis.geodist(key, member1, member2);
        jedis.close();
        return result;
    }

    @Override
    public Double geodist(String key, String member1, String member2, GeoUnit unit) {
        Jedis jedis = jedisSentinelPool.getResource();
        Double result = jedis.geodist(key, member1, member2, unit);
        jedis.close();
        return result;
    }

    @Override
    public List<String> geohash(String key, String... members) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<String> result = jedis.geohash(key, members);
        jedis.close();
        return result;
    }

    @Override
    public List<GeoCoordinate> geopos(String key, String... members) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<GeoCoordinate> result = jedis.geopos(key, members);
        jedis.close();
        return result;
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius,
            GeoUnit unit) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<GeoRadiusResponse> result = jedis.georadius(key, longitude, latitude, radius, unit);
        jedis.close();
        return result;
    }

    @Override
    public List<GeoRadiusResponse> georadiusReadonly(String key, double longitude, double latitude, double radius,
            GeoUnit unit) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<GeoRadiusResponse> result = jedis.georadiusReadonly(key, longitude, latitude, radius, unit);
        jedis.close();
        return result;
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit,
            GeoRadiusParam param) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<GeoRadiusResponse> result = jedis.georadius(key, longitude, latitude, radius, unit, param);
        jedis.close();
        return result;
    }

    @Override
    public List<GeoRadiusResponse> georadiusReadonly(String key, double longitude, double latitude, double radius,
            GeoUnit unit, GeoRadiusParam param) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<GeoRadiusResponse> result = jedis.georadiusReadonly(key, longitude, latitude, radius, unit, param);
        jedis.close();
        return result;
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<GeoRadiusResponse> result = jedis.georadiusByMember(key, member, radius, unit);
        jedis.close();
        return result;
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMemberReadonly(String key, String member, double radius, GeoUnit unit) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<GeoRadiusResponse> result = jedis.georadiusByMemberReadonly(key, member, radius, unit);
        jedis.close();
        return result;
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit,
            GeoRadiusParam param) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<GeoRadiusResponse> result = jedis.georadiusByMember(key, member, radius, unit, param);
        jedis.close();
        return result;
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMemberReadonly(String key, String member, double radius, GeoUnit unit,
            GeoRadiusParam param) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<GeoRadiusResponse> result = jedis.georadiusByMemberReadonly(key, member, radius, unit, param);
        jedis.close();
        return result;
    }

    @Override
    public List<Long> bitfield(String key, String... arguments) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<Long> result = jedis.bitfield(key, arguments);
        jedis.close();
        return result;
    }

    @Override
    public Long hstrlen(String key, String field) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.hstrlen(key, field);
        jedis.close();
        return result;
    }

    @Override
    public StreamEntryID xadd(String key, StreamEntryID id, Map<String, String> hash) {
        Jedis jedis = jedisSentinelPool.getResource();
        StreamEntryID result = jedis.xadd(key, id, hash);
        jedis.close();
        return result;
    }

    @Override
    public StreamEntryID xadd(String key, StreamEntryID id, Map<String, String> hash, long maxLen,
            boolean approximateLength) {
        Jedis jedis = jedisSentinelPool.getResource();
        StreamEntryID result = jedis.xadd(key, id, hash, maxLen, approximateLength);
        jedis.close();
        return result;
    }

    @Override
    public Long xlen(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.xlen(key);
        jedis.close();
        return result;
    }

    @Override
    public List<StreamEntry> xrange(String key, StreamEntryID start, StreamEntryID end, int count) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<StreamEntry> result = jedis.xrange(key, start, end, count);
        jedis.close();
        return result;
    }

    @Override
    public List<StreamEntry> xrevrange(String key, StreamEntryID end, StreamEntryID start, int count) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<StreamEntry> result = jedis.xrevrange(key, end, start, count);
        jedis.close();
        return result;
    }

    @Override
    public long xack(String key, String group, StreamEntryID... ids) {
        Jedis jedis = jedisSentinelPool.getResource();
        long result = jedis.xack(key, group, ids);
        jedis.close();
        return result;
    }

    @Override
    public String xgroupCreate(String key, String groupname, StreamEntryID id, boolean makeStream) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.xgroupCreate(key, groupname, id, makeStream);
        jedis.close();
        return result;
    }

    @Override
    public String xgroupSetID(String key, String groupname, StreamEntryID id) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.xgroupSetID(key, groupname, id);
        jedis.close();
        return result;
    }

    @Override
    public long xgroupDestroy(String key, String groupname) {
        Jedis jedis = jedisSentinelPool.getResource();
        long result = jedis.xgroupDestroy(key, groupname);
        jedis.close();
        return result;
    }

    @Override
    public String xgroupDelConsumer(String key, String groupname, String consumername) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.xgroupDelConsumer(key, groupname, consumername);
        jedis.close();
        return result;
    }

    @Override
    public List<StreamPendingEntry> xpending(String key, String groupname, StreamEntryID start, StreamEntryID end,
            int count, String consumername) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<StreamPendingEntry> result = jedis.xpending(key, groupname, start, end, count, consumername);
        jedis.close();
        return result;
    }

    @Override
    public long xdel(String key, StreamEntryID... ids) {
        Jedis jedis = jedisSentinelPool.getResource();
        long result = jedis.xdel(key, ids);
        jedis.close();
        return result;
    }

    @Override
    public long xtrim(String key, long maxLen, boolean approximate) {
        Jedis jedis = jedisSentinelPool.getResource();
        long result = jedis.xtrim(key, maxLen, approximate);
        jedis.close();
        return result;
    }

    @Override
    public List<StreamEntry> xclaim(String key, String group, String consumername, long minIdleTime, long newIdleTime,
            int retries, boolean force, StreamEntryID... ids) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<StreamEntry> result = jedis.xclaim(key, group, consumername, minIdleTime, newIdleTime, retries, force, ids);
        jedis.close();
        return result;
    }
    
    @Override
    public List<Map<String, String>> sentinelMasters() {
        Jedis jedis = jedisSentinelPool.getResource();
        List<Map<String, String>> result = jedis.sentinelMasters();
        jedis.close();
        return result;
    }

    @Override
    public List<String> sentinelGetMasterAddrByName(String masterName) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<String> result = jedis.sentinelGetMasterAddrByName(masterName);
        jedis.close();
        return result;
    }

    @Override
    public Long sentinelReset(String pattern) {
        Jedis jedis = jedisSentinelPool.getResource();
        Long result = jedis.sentinelReset(pattern);
        jedis.close();
        return result;
    }

    @Override
    public List<Map<String, String>> sentinelSlaves(String masterName) {
        Jedis jedis = jedisSentinelPool.getResource();
        List<Map<String, String>> result = jedis.sentinelSlaves(masterName);
        jedis.close();
        return result;
    }

    @Override
    public String sentinelFailover(String masterName) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.sentinelFailover(masterName);
        jedis.close();
        return result;
    }

    @Override
    public String sentinelMonitor(String masterName, String ip, int port, int quorum) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.sentinelMonitor(masterName, ip, port, quorum);
        jedis.close();
        return result;
    }

    @Override
    public String sentinelRemove(String masterName) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.sentinelRemove(masterName);
        jedis.close();
        return result;
    }

    @Override
    public String sentinelSet(String masterName, Map<String, String> parameterMap) {
        Jedis jedis = jedisSentinelPool.getResource();
        String result = jedis.sentinelSet(masterName, parameterMap);
        jedis.close();
        return result;
    }
    
    public void setJedisSentinelPool(JedisSentinelPool jedisSentinelPool) {
        this.jedisSentinelPool = jedisSentinelPool;
    }
}
