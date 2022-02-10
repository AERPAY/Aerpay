package com.client.aerpaymerchant.network.listeners

import java.util.*

/**
 * Enter key values pair in hashmap that is to be passed in all APIs,
 */
interface DefaultActionPerformer {
    fun onActionPerform(headers: HashMap<String, String>, params: HashMap<String, String>?)
}