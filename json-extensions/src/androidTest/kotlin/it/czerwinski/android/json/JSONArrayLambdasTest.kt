package it.czerwinski.android.json

import android.support.test.runner.AndroidJUnit4
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JSONArrayLambdasTest {

	@Test
	@Throws(Exception::class)
	fun shouldExecuteActionForEachElementOfJSONArray() {
		// given:
		val jsonArray = jsonArrayOf(1, 2, 3)
		// when:
		val executedElements = mutableListOf<Any?>()
		jsonArray.forEach {
			executedElements.add(it)
		}
		// then:
		assertEquals(listOf(1, 2, 3), executedElements)
	}

	@Test
	@Throws(Exception::class)
	fun shouldMapNullableJSONArrayElements() {
		// given:
		val jsonArray = jsonArrayOf(1, JSONObject.NULL, 3)
		// when:
		val result: List<String> = jsonArray.map {
			it?.toString() ?: "null"
		}
		// then:
		assertEquals(listOf("1", "null", "3"), result)
	}

	@Test
	@Throws(Exception::class)
	fun shouldMapJSONArrayElements() {
		// given:
		val jsonArray = jsonArrayOf(1, 2, 3)
		// when:
		val result: List<String> = jsonArray.mapNotNull {
			it.toString()
		}
		// then:
		assertEquals(listOf("1", "2", "3"), result)
	}

	@Test
	@Throws(Exception::class)
	fun shouldMapJSONArrayElementsWithNulls() {
		// given:
		val jsonArray = jsonArrayOf(1, JSONObject.NULL, 3)
		// when:
		val result: List<String> = jsonArray.mapNotNull {
			it.toString()
		}
		// then:
		assertEquals(listOf("1", "3"), result)
	}

	@Test
	@Throws(Exception::class)
	fun shouldFlatMapJSONArrayElements() {
		// given:
		val jsonArray = jsonArrayOf(1, 2, JSONObject.NULL, 3)
		// when:
		val result: JSONArray = jsonArray.flatMap { element ->
			val value = element?.toString()?.toInt() ?: 1
			jsonArrayOf(*(1..value).map { element?.toString() ?: "null" }.toTypedArray())
		}
		// then:
		assertEquals(jsonArrayOf("1", "2", "2", "null", "3", "3", "3"), result)
	}
}
