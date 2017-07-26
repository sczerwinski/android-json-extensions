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
			if (isNull(it)) null
			else get(it)
		}

/**
 * Converts the JSON array to a list with non-null elements.
 *
 * @return List of elements of the JSON array.
 */
fun JSONArray.toList(): List<Any> =
		toNullableList().filterNotNull()
