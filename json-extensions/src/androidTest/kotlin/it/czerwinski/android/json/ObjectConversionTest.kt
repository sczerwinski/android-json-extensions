package it.czerwinski.android.json

import android.support.test.runner.AndroidJUnit4
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.ArrayList

@RunWith(AndroidJUnit4::class)
class ObjectConversionTest {

	@Test
	@Throws(Exception::class)
	fun shouldReturnTheSameObjectWhenTypeIsTheSame() {
		// given:
		val text: String = "text"
		// when:
		val result = text.convertTo<String>()
		// then:
		assertSame(text, result)
	}

	@Test
	@Throws(Exception::class)
	fun shouldReturnNewObjectWhenMatchesConstructorParameterType() {
		// given:
		data class StringWrapper(val string: String)
		val text: String = "text"
		// when:
		val result = text.convertTo<StringWrapper>()
		// then:
		assertEquals(StringWrapper("text"), result)
	}

	@Test
	@Throws(Exception::class)
	fun shouldReturnNewObjectWhenIsAssignableToConstructorParameter() {
		// given:
		data class ListWrapper(val list: List<String>)
		val list: ArrayList<String> = arrayListOf("text")
		// when:
		val result = list.convertTo<ListWrapper>()
		// then:
		assertEquals(1, result?.list?.size)
		assertEquals("text", result?.list?.first())
	}

	@Test
	@Throws(Exception::class)
	fun shouldReturnNewObjectWhenMatchesParameterTypeOfAnyConstructor() {
		// given:
		val json: String = """{"id":13}"""
		// when:
		val result = json.convertTo<JSONObject>()
		// then:
		assertEquals(1, result?.length())
		assertEquals(13, result?.getInt("id"))
	}

	@Test
	@Throws(Exception::class)
	fun shouldReturnNullWhenNoSingleArgumentConstructorExists() {
		// given:
		data class Point(val x: Int, val y: Int)
		val number: Int = 13
		// when:
		val result = number.convertTo<Point>()
		// then:
		assertNull(result)
	}

	@Test
	@Throws(Exception::class)
	fun shouldReturnNullWhenConstructorParameterTypeDoesNotMatch() {
		// given:
		data class StringWrapper(val string: String)
		val number: Int = 13
		// when:
		val result = number.convertTo<StringWrapper>()
		// then:
		assertNull(result)
	}
}