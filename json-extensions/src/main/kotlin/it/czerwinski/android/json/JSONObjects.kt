package it.czerwinski.android.json

import org.json.JSONObject
import kotlin.reflect.KProperty

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
 * Converts the JSON object to a map with its non-null values.
 *
 * @return Map of non-null name-value pairs.
 */
fun JSONObject.toMapNotNull(): Map<String, Any> =
		toMap().filterValues {
			it != null
		}.mapValues {
			it.value ?: throw NullPointerException()
		}

/**
 * Adds a name-value pair to the JSON object.
 *
 * @param pair New name-value pair.
 *
 * @return JSON object containing the new name-value pair.
 */
operator fun JSONObject.plus(pair: Pair<Any, Any>): JSONObject = copy().apply {
	put(pair.first.toString(), pair.second)
}

/**
 * Adds a map of name-value pair to the JSON object.
 *
 * @param map Map of new name-value pairs.
 *
 * @return JSON object containing the new name-value pairs.
 */
operator fun JSONObject.plus(map: Map<Any, Any>): JSONObject = copy().apply {
	map.forEach { (name, value) -> put(name.toString(), value) }
}

/**
 * Gets value of a delegated property from the JSON object.
 *
 * @param thisRef Reference to the object containing the property.
 * @param property Delegated property.
 *
 * @return Value of the property.
 */
inline operator fun <reified T> JSONObject.getValue(thisRef: Any, property: KProperty<*>): T = try {
	opt(property.name).convertTo<T>() as T
} catch (e: NullPointerException) {
	throw IllegalArgumentException(
			"Value \"${property.name}\" with type ${T::class.java.name} not found")
}
