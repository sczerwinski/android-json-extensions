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

/**
 * Creates a copy of the JSON object.
 *
 * @return A copy of the JSON object.
 */
fun JSONObject.copy(): JSONObject = jsonOf(toMap())

/**
 * Names of JSON object values.
 */
val JSONObject.names: List<String>
	get() = keys().asSequence().toList()

/**
 * Converts the JSON object to a map with its nullable values.
 *
 * @return Map of name-value pairs.
 */
fun JSONObject.toMap(): Map<String, Any?> =
		mapOf(*names.map { it to getNullable(it) }.toTypedArray())

private fun JSONObject.getNullable(name: String): Any? =
		if (isNull(name)) null
		else get(name)

/**
 * Converts the JSON array to a list with non-null elements.
 *
 * @return List of non-null elements of the JSON array.
 */
fun JSONObject.toMapNotNull(): Map<String, Any> =
		toMap().filterValues { it != null }.mapValues { it.value ?: throw NullPointerException() }
