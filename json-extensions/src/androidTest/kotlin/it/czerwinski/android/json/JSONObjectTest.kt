package it.czerwinski.android.json

import android.support.test.runner.AndroidJUnit4
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JSONObjectTest {

	@Test
	@Throws(Exception::class)
	fun shouldGetNamesOfJSONObjectValues() {
		// given:
		val jsonObject: JSONObject = jsonOf(
				"id" to 17,
				"text" to "Hello World",
				"numbers" to jsonArrayOf(1, 2, 3))
		// when:
		val names = jsonObject.names
		// then:
		assertEquals(listOf("id", "text", "numbers"), names)
	}

	@Test
	@Throws(Exception::class)
	fun shouldConvertJSONObjectToNullableMap() {
		// given:
		val jsonObject: JSONObject = jsonOf(
				"id" to 17,
				"text" to "Hello World",
				"numbers" to jsonArrayOf(1, 2, 3))
		// when:
		val map: Map<String, Any?> = jsonObject.toMap()
		// then:
		assertEquals(
				mapOf(
						"id" to 17,
						"text" to "Hello World",
						"numbers" to jsonArrayOf(1, 2, 3)),
				map)
	}

	@Test
	@Throws(Exception::class)
	fun shouldConvertJSONObjectWithNullsToNullableMap() {
		// given:
		val jsonObject: JSONObject = jsonOf(
				"id" to 17,
				"text" to "Hello World",
				"numbers" to JSONObject.NULL)
		// when:
		val map: Map<String, Any?> = jsonObject.toMap()
		// then:
		assertEquals(
				mapOf(
						"id" to 17,
						"text" to "Hello World",
						"numbers" to null),
				map)
	}

	@Test
	@Throws(Exception::class)
	fun shouldConvertJSONObjectToMap() {
		// given:
		val jsonObject: JSONObject = jsonOf(
				"id" to 17,
				"text" to "Hello World",
				"numbers" to jsonArrayOf(1, 2, 3))
		// when:
		val map: Map<String, Any> = jsonObject.toMapNotNull()
		// then:
		assertEquals(
				mapOf(
						"id" to 17,
						"text" to "Hello World",
						"numbers" to jsonArrayOf(1, 2, 3)),
				map)
	}

	@Test
	@Throws(Exception::class)
	fun shouldConvertJSONObjectWithNullsToMap() {
		// given:
		val jsonObject: JSONObject = jsonOf(
				"id" to 17,
				"text" to "Hello World",
				"numbers" to JSONObject.NULL)
		// when:
		val map: Map<String, Any> = jsonObject.toMapNotNull()
		// then:
		assertEquals(
				mapOf(
						"id" to 17,
						"text" to "Hello World"),
				map)
	}
}
