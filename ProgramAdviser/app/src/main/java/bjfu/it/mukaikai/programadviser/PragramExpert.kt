package bjfu.it.mukaikai.programadviser

class PragramExpert {
    fun getLanguage(feature: String): String {
        var result: String
        when(feature) {
            "fast" -> result = "C/C++"
            "easy" -> result = "Python"
            "new" -> result = "Kotlin"
            "OO" -> result = "Java"
            else -> result = "You got me"
        }
        return result
    }
}