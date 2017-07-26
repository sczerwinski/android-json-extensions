package it.czerwinski.android.json

import android.support.test.runner.AndroidJUnit4
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotSame
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JSONObjectsCreationTest {

	@Test
	@Throws(Exception::class)
	fun shouldCreateEmptyJSONObject() {
		// when:
		val jsonObject: JSONObject = jsonOf()
		// then:
		assertEquals(0, jsonObject.length())
	}

	@Test
	@Throws(Exception::class)
	fun shouldCreateJSONObjectWithSingleIntValue() {
		// given:
		val pair = "id" to 17
		// when:
		val jsonObject: JSONObject = jsonOf(pair)
		// then:
		assertEquals(1, jsonObject.length())
		assertEquals(17, jsonObject.getInt("id"))
	}

	@Test
	@Throws(Exception::class)
	fun shouldCreateJSONObjectWithSingleStringValue() {
		// given:
		val pair = "text" to "Hello World"
		// when:
		val jsonObject: JSONObject = jsonOf(pair)
		// then:
		assertEquals(1, jsonObject.length())
		assertEquals("Hello World", jsonObject.getString("text"))
	}

	@Test
	@Throws(Exception::class)
	fun shouldCreateJSONObjectWithMultipleValues() {
		// given:
		val idPair = "id" to 17
		val textPair = "text" to "Hello World"
		// when:
		val jsonObject: JSONObject = jsonOf(idPair, textPair)
		// then:
		assertEquals(2, jsonObject.length())
		assertEquals(17, jsonObject.getInt("id"))
		assertEquals("Hello World", jsonObject.getString("text"))
	}

	@Test
	@Throws(Exception::class)
	fun shouldCreateJSONObjectFromMap() {
		// given:
		val map = mapOf("id" to 17, "text" to "Hello World")
		// when:
		val jsonObject: JSONObject = jsonOf(map)
		// then:
		assertEquals(2, jsonObject.length())
		assertEquals(17, jsonObject.getInt("id"))
		assertEquals("Hello World", jsonObject.getString("text"))
	}

	@Test
	@Throws(Exception::class)
	fun shouldParseStringToJSONObject() {
		// given:
		val json = """
				|{
				|  "id": 17,
				|  "text": "Hello World"
				|}
				|""".trimMargin()
		// when:
		val jsonObject: JSONObject = json.parseJson()
		// then:
		assertEquals(2, jsonObject.length())
		assertEquals(17, jsonObject.getInt("id"))
		assertEquals("Hello World", jsonObject.getString("text"))
	}

	@Test
	@Throws(Exception::class)
	fun shouldCreateACopyOfJSONObject() {
		// given:
		val jsonObject = jsonOf(
				"id" to 17,
				"text" to "Hello World",
				"numbers" to jsonArrayOf(1, 2, 3))
		// when:
		val copy: JSONObject = jsonObject.copy()
		// then:
		assertEquals(3, copy.length())
		assertEquals(17, copy.getInt("id"))
		assertEquals("Hello World", copy.getString("text"))
		assertEquals(jsonArrayOf(1, 2, 3), copy.getJSONArray("numbers"))
		assertNotSame(jsonObject, copy)
	}

	@Test
	@Throws(Exception::class)
	fun shouldCreateACopyOfJSONObjectWithNulls() {
		// given:
		val jsonObject = jsonOf(
				"id" to 17,
				"text" to "Hello World",
				"numbers" to JSONObject.NULL)
		// when:
		val copy: JSONObject = jsonObject.copy()
		// then:
		assertEquals(3, copy.length())
		assertEquals(17, copy.getInt("id"))
		assertEquals("Hello World", copy.getString("text"))
		assertEquals(JSONObject.NULL, copy.get("numbers"))
		assertNotSame(jsonObject, copy)
	}
}
