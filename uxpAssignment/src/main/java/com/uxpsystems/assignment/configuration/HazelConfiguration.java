package com.uxpsystems.assignment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.ListConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;

@Configuration
public class HazelConfiguration {

	@Bean
	public Config hazelCastConfig() {
		Config config = new Config();
        config.setInstanceName("hazelcast-instance")        // hazel case instance name
                .addMapConfig(
                        new MapConfig()                     // create map
                                .setName("configuration")
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(-1))
                .addListConfig(
                		new ListConfig()
                		.setName("userList")
                		.setMaxSize(20)
                		);     // cache will be available until it will remove manually. less then 0 means never expired.
        return config;

	}
}
