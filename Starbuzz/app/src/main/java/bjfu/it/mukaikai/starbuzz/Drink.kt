package bjfu.it.mukaikai.starbuzz

class Drink(var name: String, var description: String, var imageResourceId: Int) {

    override fun toString(): String {
        return name.toString()
    }

    companion object {
        val drinks = arrayOf(
            Drink(
                "Latte", "A couple of espresso shots with steamed milk",
                R.drawable.latte
            ),
            Drink(
                "Cappuccino", "Espresso, hot milk, and  steamed milk foam",
                R.drawable.cappuccino
            ),
            Drink(
                "Filter", "Highest quality beans roasted and brewed fresh",
                R.drawable.filter
            )
        )
    }
}
