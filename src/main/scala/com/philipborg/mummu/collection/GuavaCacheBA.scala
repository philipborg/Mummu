package com.philipborg.mummu.collection

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import com.google.common.cache.RemovalListener
import com.google.common.cache.RemovalNotification

class GuavaCacheBA[T](cacheBuilder: CacheBuilder[Long, T], underlying: BigArray[T]) extends BigArray[T] {

  protected val cache: LoadingCache[Long, T] = {

    val removal = new RemovalListener[Long, T] {
      def onRemoval(removal: RemovalNotification[Long, T]): Unit = {
        underlying(removal.getKey) = removal.getValue;
      }
    }

    val loader = new CacheLoader[Long, T] {
      def load(index: Long): T = {
        return underlying(index);
      }
    }

    cacheBuilder.removalListener(removal).build(loader);
  }

  def close(): Unit = this.synchronized {
    cache.invalidateAll;
    cache.cleanUp;
    underlying.close;
  }

  def apply(index: Long): T = cache(index);
  val size: Long = underlying.size;
  def update(index: Long, value: T): Unit = cache.put(index, value);
}