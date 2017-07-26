package it.czerwinski.android.json

import org.json.JSONArray

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
fun JSONArray.copy(): JSONArray = jsonArrayOf(*toList().toTypedArray())

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
fun JSONArray.toNullableList(): List<Any?> =
		(0..lastIndex).map {
			getNullable(it)
		}

private fun JSONArray.getNullable(it: Int): Any? =
		if (isNull(it)) null
		else get(it)

/**
 * Converts the JSON array to a list with non-null elements.
 *
 * @return List of elements of the JSON array.
 */
fun JSONArray.toList(): List<Any> =
		toNullableList().filterNotNull()

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
fun <T> JSONArray.mapNullable(transform: (Any?) -> T): List<T> =
		toNullableList().map(transform)

/**
 * Transforms non-null elements of JSON array.
 *
 * @param transform Mapping function.
 *
 * @return List of mapped elements.
 */
fun <T> JSONArray.map(transform: (Any) -> T): List<T> =
		toList().map(transform)
