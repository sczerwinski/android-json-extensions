package it.czerwinski.android.json

import android.support.test.runner.AndroidJUnit4
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JSONObjectsAdditionTest {

	@Test
	@Throws(Exception::class)
	fun shouldAddValueToJSONObject() {
		// given:
		val jsonObject: JSONObject = jsonOf("id" to 13, "text" to "Hello")
		val pair: Pair<Any, Any> = "bool" to true
		// when:
		val result: JSONObject = jsonObject + pair
		// then:
		assertEquals(3, result.length())
		assertEquals(13, result.getInt("id"))
		assertEquals("Hello", result.getString("text"))
		assertEquals(true, result.getBoolean("bool"))
	}

	@Test
	@Throws(Exception::class)
	fun shouldAddNullValueToJSONObject() {
		// given:
		val jsonObject: JSONObject = jsonOf("id" to 13, "text" to "Hello")
		val pair: Pair<Any, Any> = "number" to JSONObject.NULL
		// when:
		val result: JSONObject = jsonObject + pair
		// then:
		assertEquals(3, result.length())
		assertEquals(13, result.getInt("id"))
		assertEquals("Hello", result.getString("text"))
		assertEquals(JSONObject.NULL, result.get("number"))
	}

	@Test
	@Throws(Exception::class)
	fun shouldNotChangeOriginalJSONObjectWhenAddingValue() {
		// given:
		val jsonObject: JSONObject = jsonOf("id" to 13, "text" to "Hello")
		val pair: Pair<Any, Any> = "bool" to true
		// when:
		val result: JSONObject = jsonObject + pair
		// then:
		assertEquals(2, jsonObject.length())
		assertEquals(13, jsonObject.getInt("id"))
		assertEquals("Hello", jsonObject.getString("text"))
	}

	@Test
	@Throws(Exception::class)
	fun shouldAddMapToJSONObject() {
		// given:
		val jsonObject: JSONObject = jsonOf("id" to 13, "text" to "Hello")
		val map: Map<Any, Any> = mapOf("bool" to true, "number" to 75639L)
		// when:
		val result: JSONObject = jsonObject + map
		// then:
		assertEquals(4, result.length())
		assertEquals(13, result.getInt("id"))
		assertEquals("Hello", result.getString("text"))
		assertEquals(true, result.getBoolean("bool"))
		assertEquals(75639L, result.getLong("number"))
	}

	@Test
	@Throws(Exception::class)
	fun shouldAddMapWithNullsToJSONObject() {
		// given:
		val jsonObject: JSONObject = jsonOf("id" to 13, "text" to "Hello")
		val map: Map<Any, Any> = mapOf("bool" to true, "number" to JSONObject.NULL)
		// when:
		val result: JSONObject = jsonObject + map
		// then:
		assertEquals(4, result.length())
		assertEquals(13, result.getInt("id"))
		assertEquals("Hello", result.getString("text"))
		assertEquals(true, result.getBoolean("bool"))
		assertEquals(JSONObject.NULL, result.get("number"))
	}

	@Test
	@Throws(Exception::class)
	fun shouldNotChangeOriginalJSONObjectWhenAddingMap() {
		// given:
		val jsonObject: JSONObject = jsonOf("id" to 13, "text" to "Hello")
		val map: Map<Any, Any> = mapOf("bool" to true, "number" to 75639L)
		// when:
		val result: JSONObject = jsonObject + map
		// then:
		assertEquals(2, jsonObject.length())
		assertEquals(13, jsonObject.getInt("id"))
		assertEquals("Hello", jsonObject.getString("text"))
	}
}
