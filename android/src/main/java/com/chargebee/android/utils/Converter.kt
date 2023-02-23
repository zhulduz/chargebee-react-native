package com.chargebee.android.utils

import com.chargebee.android.models.CBProduct
import com.chargebee.android.models.PurchaseResult
import com.chargebee.android.models.SubscriptionDetailsWrapper
import com.facebook.react.bridge.*


internal fun convertArrayToWritableArray(array: Array<Any?>): WritableArray {
  val writableArray: WritableArray = WritableNativeArray()
  for (item in array) {
    when (item) {
      null -> writableArray.pushNull()
      is String -> writableArray.pushString(item)
    }
  }
  return writableArray
}

internal fun convertReadableArray(readableArray: ReadableArray): ArrayList<String> {
  val items: ArrayList<String> = ArrayList()
  for (i in 0 until readableArray.size()) {
    items.add(readableArray.getString(i))
  }
  return items
}

internal fun convertListToWritableArray(array: List<CBProduct>): WritableArray {
  val writableArray: WritableArray = WritableNativeArray()
  for (item in array) {
    writableArray.pushMap(convertProductToDictionary(item))
  }
  return writableArray
}

internal fun convertProductToDictionary(product: CBProduct): WritableMap {
  val writableMap: WritableMap = WritableNativeMap()
  writableMap.putString("id", product.productId)
  writableMap.putString("title", product.productTitle)
  writableMap.putString("price", product.productPrice)
  writableMap.putString("currencyCode", product.skuDetails.priceCurrencyCode)
  return writableMap
}

internal fun convertPurchaseResultToDictionary(product: PurchaseResult, status: Boolean): WritableMap {
  val writableMap: WritableMap = WritableNativeMap()
  writableMap.putString("subscription_id", product.subscriptionId)
  writableMap.putString("plan_id", product.planId)
  writableMap.putBoolean("status", status)
  return writableMap
}

internal fun convertSubscriptionsToDictionary(subscriptions: List<SubscriptionDetailsWrapper>): WritableArray {
  val writableArray: WritableArray = WritableNativeArray()
  for (item in subscriptions) {
    writableArray.pushMap(convertSubscriptionToDictionary(item))
  }
  return writableArray
}

internal fun convertSubscriptionToDictionary(subscriptionDetailsWrapper: SubscriptionDetailsWrapper): WritableMap {
  val writableMap: WritableMap = WritableNativeMap()
  val subscriptionDetail = subscriptionDetailsWrapper.cb_subscription
  subscriptionDetail.activated_at?.toDouble()?.let { writableMap.putDouble("activatedAt", it) }
  subscriptionDetail.status?.let { writableMap.putString("status", it) }
  subscriptionDetail.plan_amount?.toDouble()?.let { writableMap.putDouble("planAmount", it) }
  subscriptionDetail.subscription_id?.let { writableMap.putString("id", it) }
  subscriptionDetail.customer_id?.let { writableMap.putString("customerId", it) }
  subscriptionDetail.current_term_end?.toDouble()?.let { writableMap.putDouble("currentTermEnd", it) }
  subscriptionDetail.current_term_start?.toDouble()?.let { writableMap.putDouble("currentTermStart", it) }
  subscriptionDetail.plan_id?.let { writableMap.putString("planId", it) }
  return writableMap
}

internal fun convertQueryParamsToArray(queryParams: ReadableMap): Array<String> {
  if (queryParams != null)
    return arrayOf(queryParams.getString("limit") ?: "")
  return arrayOf("")
}
