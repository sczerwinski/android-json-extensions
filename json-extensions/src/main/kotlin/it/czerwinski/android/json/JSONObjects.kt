package it.czerwinski.android.json

import org.json.JSONObject

/**
 * Creates an empty JSON object.
 *
 * @return Empty JSON object.
 */
fun jsonOf(): JSONObject = JSONObject()

/**
 * Creates a JSON object with given value.
 *
 * @param pair Name-value pair.
 *
 * @return JSON object.
 */
fun jsonOf(pair: Pair<Any, Any>): JSONObject = jsonOf(mapOf(pair))

/**
 * Creates a JSON object with given values.
 *
 * @param pairs Name-value pairs.
 *
 * @return JSON object.
 */
fun jsonOf(vararg pairs: Pair<*, *>): JSONObject = jsonOf(mapOf(*pairs))

/**
 * Creates a JSON object with given map of values.
 *
 * @param map Map of name-value pairs.
 *
 * @return JSON object.
 */
fun jsonOf(map: Map<*, *>): JSONObject = JSONObject(map)

/**
 * Parses the string to a JSON object.
 *
 * @return JSON object.
 */
fun String.parseJson(): JSONObject = JSONObject(this)
