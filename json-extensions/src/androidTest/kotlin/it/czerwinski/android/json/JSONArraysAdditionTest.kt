package it.czerwinski.android.json

import android.support.test.runner.AndroidJUnit4
import org.json.JSONArray
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JSONArraysAdditionTest {

	@Test
	@Throws(Exception::class)
	fun shouldAddElementToJSONArray() {
		// given:
		val jsonArray: JSONArray = jsonArrayOf(1, 2)
		val element: Any = 3
		// when:
		val result: JSONArray = jsonArray + element
		// then:
		assertEquals(3, result.length())
		assertEquals(1, result.getInt(0))
		assertEquals(2, result.getInt(1))
		assertEquals(3, result.getInt(2))
	}

	@Test
	@Throws(Exception::class)
	fun shouldNotChangeOriginalJSONArrayWhenAddingElement() {
		// given:
		val jsonArray: JSONArray = jsonArrayOf(1, 2)
		val element: Any = 3
		// when:
		val result: JSONArray = jsonArray + element
		// then:
		assertEquals(2, jsonArray.length())
		assertEquals(1, jsonArray.getInt(0))
		assertEquals(2, jsonArray.getInt(1))
	}

	@Test
	@Throws(Exception::class)
	fun shouldAddTwoJSONArrays() {
		// given:
		val jsonArray1: JSONArray = jsonArrayOf(1, 2)
		val jsonArray2: JSONArray = jsonArrayOf(3, 4)
		// when:
		val result: JSONArray = jsonArray1 + jsonArray2
		// then:
		assertEquals(4, result.length())
		assertEquals(1, result.getInt(0))
		assertEquals(2, result.getInt(1))
		assertEquals(3, result.getInt(2))
		assertEquals(4, result.getInt(3))
	}

	@Test
	@Throws(Exception::class)
	fun shouldNotChangeOriginalJSONArrayWhenAddingJSONArrays() {
		// given:
		val jsonArray1: JSONArray = jsonArrayOf(1, 2)
		val jsonArray2: JSONArray = jsonArrayOf(3, 4)
		// when:
		val result: JSONArray = jsonArray1 + jsonArray2
		// then:
		assertEquals(2, jsonArray1.length())
		assertEquals(1, jsonArray1.getInt(0))
		assertEquals(2, jsonArray1.getInt(1))
	}

	@Test
	@Throws(Exception::class)
	fun shouldNotChangeAddedJSONArrayWhenAddingJSONArrays() {
		// given:
		val jsonArray1: JSONArray = jsonArrayOf(1, 2)
		val jsonArray2: JSONArray = jsonArrayOf(3, 4)
		// when:
		val result: JSONArray = jsonArray1 + jsonArray2
		// then:
		assertEquals(2, jsonArray2.length())
		assertEquals(3, jsonArray2.getInt(0))
		assertEquals(4, jsonArray2.getInt(1))
	}
}
