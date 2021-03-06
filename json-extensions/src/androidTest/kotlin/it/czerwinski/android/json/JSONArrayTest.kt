package it.czerwinski.android.json

import android.support.test.runner.AndroidJUnit4
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JSONArrayTest {

	@Test
	@Throws(Exception::class)
	fun shouldGetLastIndexOfJSONArray() {
		// given:
		val jsonArray: JSONArray = jsonArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
		// when:
		val index = jsonArray.lastIndex
		// then:
		assertEquals(7, index)
	}

	@Test
	@Throws(Exception::class)
	fun shouldConvertJSONArrayToNullableList() {
		// given:
		val jsonArray: JSONArray = jsonArrayOf("abc", "def", "ghi")
		// when:
		val list: List<Any?> = jsonArray.toList()
		// then:
		assertEquals(listOf("abc", "def", "ghi"), list)
	}

	@Test
	@Throws(Exception::class)
	fun shouldConvertJSONArrayWithNullsToNullableList() {
		// given:
		val jsonArray: JSONArray = jsonArrayOf("abc", JSONObject.NULL, "ghi")
		// when:
		val list: List<Any?> = jsonArray.toList()
		// then:
		assertEquals(listOf("abc", null, "ghi"), list)
	}

	@Test
	@Throws(Exception::class)
	fun shouldConvertJSONArrayToList() {
		// given:
		val jsonArray: JSONArray = jsonArrayOf("abc", "def", "ghi")
		// when:
		val list: List<Any?> = jsonArray.toListNotNull()
		// then:
		assertEquals(listOf("abc", "def", "ghi"), list)
	}

	@Test
	@Throws(Exception::class)
	fun shouldConvertJSONArrayWithNullsToList() {
		// given:
		val jsonArray: JSONArray = jsonArrayOf("abc", JSONObject.NULL, "ghi")
		// when:
		val list: List<Any?> = jsonArray.toListNotNull()
		// then:
		assertEquals(listOf("abc", "ghi"), list)
	}

	@Test
	@Throws(Exception::class)
	fun shouldConvertJSONArrayToListOfStrings() {
		// given:
		val jsonArray: JSONArray = jsonArrayOf("abc", "def", "ghi")
		// when:
		val list: List<String?> = jsonArray.toListOf<String>()
		// then:
		assertEquals(listOf("abc", "def", "ghi"), list)
	}

	@Test
	@Throws(Exception::class)
	fun shouldConvertJSONArrayToListOfIntegers() {
		// given:
		val jsonArray: JSONArray = jsonArrayOf("1", "2", "3")
		// when:
		val list: List<Int?> = jsonArray.toListOf<Int>()
		// then:
		assertEquals(listOf(1, 2, 3), list)
	}

	@Test
	@Throws(Exception::class)
	fun shouldConvertJSONArrayToListOfCustomObjects() {
		// given:
		data class StringWrapper(val text: String)
		val jsonArray: JSONArray = jsonArrayOf("abc", "def", "ghi")
		// when:
		val list: List<StringWrapper?> = jsonArray.toListOf<StringWrapper>()
		// then:
		assertEquals(listOf(StringWrapper("abc"), StringWrapper("def"), StringWrapper("ghi")), list)
	}

	@Test
	@Throws(Exception::class)
	fun shouldReturnListOfNullsWhenConvertingJSONArrayToIncompatibleType() {
		// given:
		data class StringWrapper(val text: String)
		val jsonArray: JSONArray = jsonArrayOf(1, 2, 3)
		// when:
		val list: List<StringWrapper?> = jsonArray.toListOf<StringWrapper>()
		// then:
		assertEquals(listOf(null, null, null), list)
	}
}
