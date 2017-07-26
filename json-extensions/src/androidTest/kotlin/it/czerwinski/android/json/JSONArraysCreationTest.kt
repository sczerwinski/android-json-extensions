package it.czerwinski.android.json

import android.support.test.runner.AndroidJUnit4
import org.json.JSONArray
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotSame
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JSONArraysCreationTest {

	@Test
	@Throws(Exception::class)
	fun shouldCreateEmptyJSONArray() {
		// when:
		val jsonArray: JSONArray = jsonArrayOf()
		// then:
		assertEquals(0, jsonArray.length())
	}

	@Test
	@Throws(Exception::class)
	fun shouldCreateJSONArrayWithSingleElement() {
		// given:
		val element = "Hello World"
		// when:
		val jsonArray: JSONArray = jsonArrayOf(element)
		// then:
		assertEquals(1, jsonArray.length())
		assertEquals("Hello World", jsonArray.getString(0))
	}

	@Test
	@Throws(Exception::class)
	fun shouldCreateJSONArrayWithMultipleElements() {
		// given:
		val element1 = "Hello"
		val element2 = "World"
		// when:
		val jsonArray: JSONArray = jsonArrayOf(element1, element2)
		// then:
		assertEquals(2, jsonArray.length())
		assertEquals("Hello", jsonArray.getString(0))
		assertEquals("World", jsonArray.getString(1))
	}

	@Test
	@Throws(Exception::class)
	fun shouldParseStringToJSONArray() {
		// given:
		val json = "[1,2,3]"
		// when:
		val jsonArray: JSONArray = json.parseJsonArray()
		// then:
		assertEquals(3, jsonArray.length())
		assertEquals(1, jsonArray.getInt(0))
		assertEquals(2, jsonArray.getInt(1))
		assertEquals(3, jsonArray.getInt(2))
	}

	@Test
	@Throws(Exception::class)
	fun shouldCreateACopyOfJSONArray() {
		// given:
		val jsonArray = jsonArrayOf(1, 2, 3)
		// when:
		val copy: JSONArray = jsonArray.copy()
		// then:
		assertEquals(jsonArray, copy)
		assertNotSame(jsonArray, copy)
	}
}
