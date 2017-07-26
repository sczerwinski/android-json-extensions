package it.czerwinski.android.json

import org.json.JSONArray
import org.json.JSONObject

/**
 * Creates an empty JSON array.
 *
 * @return Empty JSON array.
 */
fun jsonArrayOf(): JSONArray = JSONArray()

/**
 * Creates a JSON array with given element.
 *
 * @param element Element of the array.
 *
 * @return JSON array.
 */
fun jsonArrayOf(element: Any): JSONArray = JSONArray(listOf(element))

/**
 * Creates a JSON array with given elements.
 *
 * @param elements Elements of the array.
 *
 * @return JSON array.
 */
fun jsonArrayOf(vararg elements: Any): JSONArray = JSONArray(listOf(*elements))

/**
 * Parses the string to a JSON array.
 *
 * @return JSON array.
 */
fun String.parseJsonArray(): JSONArray = JSONArray(this)

/**
 * Creates a copy of the JSON array.
 *
 * @return A copy of the JSON array.
 */
fun JSONArray.copy(): JSONArray = jsonArrayOf(
		*toList().map {
			it ?: JSONObject.NULL
		}.toTypedArray())

/**
 * Last index of the JSON array.
 */
val JSONArray.lastIndex: Int
	get() = length() - 1

/**
 * Converts the JSON array to a list with nullable elements.
 *
 * @return List of elements of the JSON array.
 */
fun JSONArray.toList(): List<Any?> =
		(0..lastIndex).map {
			getNullable(it)
		}

private fun JSONArray.getNullable(index: Int): Any? =
		if (isNull(index)) null
		else get(index)

/**
 * Converts the JSON array to a list with non-null elements.
 *
 * @return List of non-null elements of the JSON array.
 */
fun JSONArray.toListNotNull(): List<Any> =
		toList().filterNotNull()

/**
 * Executes the action on each element of the JSON array.
 *
 * @param action Action to be executed.
 */
fun JSONArray.forEach(action: (Any?) -> Unit) {
	(0..lastIndex).forEach {
		action(getNullable(it))
	}
}

/**
 * Transforms nullable elements of JSON array.
 *
 * @param transform Mapping function.
 *
 * @return List of mapped elements.
 */
fun <T> JSONArray.map(transform: (Any?) -> T): List<T> =
		toList().map(transform)

/**
 * Transforms non-null elements of JSON array.
 *
 * @param transform Mapping function.
 *
 * @return List of mapped elements.
 */
fun <T> JSONArray.mapNotNull(transform: (Any) -> T): List<T> =
		toListNotNull().map(transform)

/**
 * Transforms elements of JSON array to JSON arrays and flattens the result.
 *
 * @param transform Mapping function.
 *
 * @return Flattened JSON array of mapped elements.
 */
fun JSONArray.flatMap(transform: (Any?) -> JSONArray): JSONArray =
		map(transform).reduce { accumulator, next ->
			accumulator + next
		}

/**
 * Adds an element to the JSON array.
 *
 * @param element New element.
 *
 * @return JSON array containing the new element.
 */
operator fun JSONArray.plus(element: Any): JSONArray =
		copy().apply {
			put(element)
		}

/**
 * Adds two JSON arrays.
 *
 * @param that Second JSON array.
 *
 * @return JSON array containing elements of both JSON arrays.
 */
operator fun JSONArray.plus(that: JSONArray): JSONArray =
		copy().apply {
			that.forEach { put(it) }
		}
