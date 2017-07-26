package it.czerwinski.android.json

import android.support.test.runner.AndroidJUnit4
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JSONToCustomObjectConversionTest {

	@Test
	@Throws(Exception::class)
	fun shouldConvertJSONArrayToIntegers() {
		// given:
		class Integers(jsonArray: JSONArray) : List<Int?> by jsonArray.toListOf<Int>()
		val jsonArray: JSONArray = jsonArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
		// when:
		val integers = Integers(jsonArray)
		// then:
		assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8), integers)
	}

	@Test
	@Throws(Exception::class)
	fun shouldConvertJSONArrayToStrings() {
		// given:
		class Strings(jsonArray: JSONArray) : List<String?> by jsonArray.toListOf<String>()
		val jsonArray: JSONArray = jsonArrayOf("abc", "def", "ghi")
		// when:
		val strings = Strings(jsonArray)
		// then:
		assertEquals(listOf("abc", "def", "ghi"), strings)
	}

	@Test
	@Throws(Exception::class)
	fun shouldConvertJSONObjectToUser() {
		// given:
		class User(json: JSONObject) {
			val id: Int by json
			val username: String by json
		}
		val json = """
				|{
				|  "id": 456,
				|  "username": "johnny11"
				|}
				|""".trimMargin()
		// when:
		val user = User(json.parseJson())
		// then:
		assertEquals(456, user.id)
		assertEquals("johnny11", user.username)
	}

	@Test(expected = IllegalArgumentException::class)
	@Throws(Exception::class)
	fun shouldThrowExceptionWhenConvertingJSONObjectWithNullsToUser() {
		// given:
		class User(json: JSONObject) {
			val id: Int by json
			val username: String by json
		}
		val json = """
				|{
				|  "id": 456
				|}
				|""".trimMargin()
		// when:
		val user = User(json.parseJson())
		// then:
		assertEquals(456, user.id)
		assertEquals("johnny11", user.username)
	}

	@Test
	@Throws(Exception::class)
	fun shouldConvertJSONObjectWithNullsToUserWithNullableUsername() {
		// given:
		class User(json: JSONObject) {
			val id: Int by json
			val username: String? by json
		}
		val json = """
				|{
				|  "id": 456
				|}
				|""".trimMargin()
		// when:
		val user = User(json.parseJson())
		// then:
		assertEquals(456, user.id)
		assertNull(user.username)
	}

	@Test
	@Throws(Exception::class)
	fun shouldConvertComplexJSONObjectToCustomObject() {
		// given:
		class Integers(jsonArray: JSONArray) : List<Int?> by jsonArray.toListOf<Int>()
		class Item(json: JSONObject) {
			val key: Int by json
			val value: String by json
		}
		class Items(jsonArray: JSONArray) : List<Item?> by jsonArray.toListOf<Item>()
		class CustomObject(json: JSONObject) {
			val id: Int by json
			val text: String by json
			val numbers: Integers by json
			val items: Items by json
		}
		val json = """
				|{
				|  "id": 13,
				|  "text": "Hello World",
				|  "numbers": [1, 2, 3, 4],
				|  "items": [
				|    { "key": 1, "value": "first" },
				|    { "key": 2, "value": "second" },
				|    { "key": 3, "value": "third" }
				|  ]
				|}
				|""".trimMargin()
		// when:
		val result = CustomObject(json.parseJson())
		// then:
		assertEquals(13, result.id)
		assertEquals("Hello World", result.text)
		assertEquals(listOf(1, 2, 3, 4), result.numbers)
		assertEquals(3, result.items.size)
		assertEquals(listOf(1, 2, 3), result.items.map { it?.key })
		assertEquals(listOf("first", "second", "third"), result.items.map { it?.value })
	}
}
