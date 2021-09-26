class NullSafety {

    fun smartCast(name: String?): String {
        return when (name) {
            null -> ""
            else -> name
        }
    }

    fun elvisOperator(name: String?): String {
        return name ?: "default"
    }

}