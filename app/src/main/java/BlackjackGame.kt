//Create a data class called Card that contains two members, a String for the pip and a Char
//for the stick
data class Card(val suit: Char, val pip: String)

fun main() {
    val deck = createDeck()
    val hand = dealHand(2, deck)
    val total = evaluateHand(hand)
    printResults(hand, total)
}

//We create the deck of cards
fun createDeck(): MutableSet<Card> {
    //We assign the different suit types with unicode characters
    val suits = listOf('\u2663','\u2660','\u2666','\u2665')
    println(suits)
    //Collection of pips
    val pips = listOf("1","2","3","4","5","6","7","8","9","10","J","Q","K")
    val deck: MutableSet<Card> = mutableSetOf()
    //Nested for loop to create a card from each point for each suit
    for(suit in suits) {
        for(pip in pips) {
            deck.add(Card(suit,pip))
        }
    }
    //Returns the MutableSet of cards
    return deck
}

//Function that can deal two cards from the deck.
/**This function receives MutableList<Card> of the deck as a parameter,
 * as well as the number of cards to place in the hand
 */
fun dealHand(number: Int, deck: MutableSet<Card>) : MutableSet<Card> {
    val hand: MutableSet<Card> = mutableSetOf()
    //Returns a collection containing the specified number of cards that we assigned above.
    for(i in 0 until number) {
        val card = deck.random()
        hand.add(card)
        deck.remove(card)
    }
    return hand
}

/**We deal two cards from the deck into a hand and evaluate that hand of cards
 * by finding the sum of the pips. We print the cards in the hand and the total
 * pips in the hand.
 * */

fun evaluateHand(hand: MutableSet<Card>) : Int {
    var total = 0
    //We use a conditional to find out the value of the card and add its value to a total.
    //This function must return the total.
    for(card in hand) {
        total += when (card.pip) {
            "K","Q","J","10" -> 10
            "A" -> 11
            else -> card.pip.toInt()
        }
    }
    return total
}

//This function receives the total and the hand as a parameter. As a result it prints
//the cards of the hand in a readable form, and then prints the total of the hand.
fun printResults(hand : Set<Card>, total: Int) {
    println("Your hand result was:")
    for(card in hand) {
        println("${card.pip}${card.suit}")
    }
    println("This adds up to a total of: $total")
}