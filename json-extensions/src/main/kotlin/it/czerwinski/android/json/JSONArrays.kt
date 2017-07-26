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
