[![Download](https://api.bintray.com/packages/sczerwinski/android/json-extensions/images/download.svg)](https://bintray.com/sczerwinski/android/json-extensions/_latestVersion)

# Extensions for standard Android JSON classes

## Gradle Build Configuration

```gradle
dependencies {
    compile 'it.czerwinski.android:json-extensions:0.1'
}
```

## Usage

### JSON Objects

#### Creating JSON objects

`JSONObject`s can be created from pairs or maps of values with `jsonOf()` function.

For example:

```kotlin
val user: JSONObject = jsonOf(
    "id" to 1,
    "username" to "jsmith")
```

corresponds the following JSON:

```json
{
  "id":1,
  "username":"jsmith"
}
```

#### Parsing Strings to JSON objects

An extension method `String.parseJson()` is introduced.

Example:

```kotlin
val user: JSONObject = "{\"id\":1,\"username\":\"jsmith\"}".parseJson()
```

#### Converting JSON objects to maps

There are two extension methods for conversion to maps:
* `JSONObject.toMap()` – which converts `JSONObject.NULL` to `null`,
* `JSONObject.toMapNotNull()` – which filters null values.

#### Adding JSON objects

A pair or even a map of values can be added to a `JSONObject`.

The original object is not changed by the operation.

Examples:

```kotlin
val userWithPassword: JSONObject = user + ("password" to "p4ssw0rd")
val userWithAttributes: JSONObject = user + attributesMap
```

#### Delegating properties to JSON objects

Read-only properties can be delegated to an instance `JSONObject`:

```kotlin
class User(json: JSONObject) {
    val id: Int by json
    val username: String by json
}

val jsonObject: JSONObject = "{\"id\":1,\"username\":\"jsmith\"}".parseJson()

val user: User = User(jsonObject)
```

### JSON Arrays

#### Creating JSON arrays

`JSONArray`s can be created with `jsonArrayOf()` function.

For example:

```kotlin
val jsonArray: JSONArray = jsonArrayOf("Hello", "World")
```

corresponds the following JSON:

```json
["Hello", "World"]
```

#### Parsing Strings to JSON arrays

An extension method `String.parseJsonArray()` is introduced.

Example:

```kotlin
val user: JSONArray = "[1, 2, 3]".parseJsonArray()
```

#### Converting JSON arrays to lists

There are three extension methods for conversion to lists:
* `JSONArray.toList()` – which converts `JSONObject.NULL` to `null`,
* `JSONArray.toListNotNull()` – which filters null values,
* `JSONArray.toListOf<T>()` – which converts all elements to the given type.

Examples:

```kotlin
val strings: List<String?> = jsonArrayOf("abc", "def", "ghi").toListOf<String>()
val ints: List<Int?> = jsonArrayOf("1", "2", "3").toListOf<Int>()
```

#### Lambda expressions for JSON arrays

The following lambdas are implemented for `JSONArray`s:
* `forEach()` – runs an action for each element of the JSON array,
* `map()` – applies a transformation to all elements of the JSON array, resulting in a `List`,
* `mapNotNull()` – the same as `map()`, but filters null values,
* `flatMap()` – maps each element of the JSON array to a new `JSONArray` and joins all the results.

#### Adding JSON arrays

A new element or a whole JSON array can be added to a `JSONArray` object.

The original array is not changed by the operation.

Examples:

```kotlin
val result1: JSONArray = jsonArray + "element"
val result2: JSONArray = jsonArray + jsonArray2
```

### Creating Complex Objects From JSON

It is possible to transform complex JSON into an object.

As an example, take a look at albums released by an artist:

```json
{
  "name":"Frank Sinatra",
  "albums":[
    {
      "name":"The Voice of Frank Sinatra",
      "year":1946
    },
    {
      "name":"Songs by Sinatra",
      "year":1947
    }
    // And many more…
  ]
}
```

First, create a class for a single album:

```kotlin
class Album(json: JSONObject) {
    val name: String by json
    val year: Int by json
}
```

For automated type conversion, implement a class that has a constructor
with a single `JSONArray` parameter, and can be treated as a list of albums:

```kotlin
class Albums(jsonArray: JSONArray) : List<Album?> by jsonArray.toListOf<Album>()
```

Finally, create a class for an artist:

```kotlin
class Artist(json: JSONObject) {
    val username: String by json
    val albums: Albums by json
}
```

Now, the JSON can be accessed as an object:

```kotlin
val artist: Artist = Artist(jsonObject)
artist.albums.forEach {
    println("Album: ${it?.name} (${it?.year})")
}
```
