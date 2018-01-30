package com.dubion.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache("users", jcacheConfiguration);
            cm.createCache(com.dubion.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.dubion.domain.SocialUserConnection.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.Band.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.Band.class.getName() + ".genres", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Band.class.getName() + ".ratings", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Band.class.getName() + ".favourites", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Band.class.getName() + ".artists", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Artist.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.Artist.class.getName() + ".bands", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Artist.class.getName() + ".instruments", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Artist.class.getName() + ".ratings", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Album.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.Album.class.getName() + ".genres", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Album.class.getName() + ".ratings", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Album.class.getName() + ".favourites", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Album.class.getName() + ".songs", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Song.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.Song.class.getName() + ".albums", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Song.class.getName() + ".ratings", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Song.class.getName() + ".favourites", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Review.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.Label.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.Country.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.Genre.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.Genre.class.getName() + ".bands", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Genre.class.getName() + ".albums", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Sex.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.Sex.class.getName() + ".users", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Social.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.Instrument.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.Instrument.class.getName() + ".artists", jcacheConfiguration);
            cm.createCache(com.dubion.domain.RatingArtist.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.RatingAlbum.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.RatingSong.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.RatingBand.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.FavouriteBand.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.FavouriteAlbum.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.FavouriteSong.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.UserExt.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.BandPrueba.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.Beta.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.Alpha.class.getName(), jcacheConfiguration);
            cm.createCache(com.dubion.domain.Alpha.class.getName() + ".betas", jcacheConfiguration);
            cm.createCache(com.dubion.domain.Gamma.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
