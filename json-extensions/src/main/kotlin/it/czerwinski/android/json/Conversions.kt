package it.czerwinski.android.json

/**
 * Converts the object to the given type.
 *
 * @param T Output type.
 *
 * @return Object converted to the given type or `null`.
 */
inline fun <reified T> Any.convertTo(): T? =
		if (this is T) this
		else T::class.java.constructors.filter {
			it.parameterTypes.size == 1
		}.firstOrNull {
			it.parameterTypes.first().isAssignableFrom(this.javaClass)
		}?.newInstance(this) as T?
