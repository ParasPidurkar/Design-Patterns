// 1. THE SINGLETON
// By using 'object' instead of 'class', Kotlin guarantees 
// only ONE instance of this exists in memory.
object MatchManager {
    var attackersScore = 0
    var defendersScore = 0
    
    init {
        println("\n[System]: MatchManager Singleton Initialized. Game Start!")
    }

    fun attackerWin() {
        attackersScore++
        println("[Match Manager]: Attackers won the round.")
    }

    fun defenderWin() {
        defendersScore++
        println("[Match Manager]: Defenders won the round.")
    }

    fun getScore(): String {
        return "$attackersScore - $defendersScore"
    }
    
    // Helper to show the memory address (The Proof)
    fun showMemoryAddress() {
        println("[System Check]: MatchManager Memory ID: ${System.identityHashCode(this)}")
    }
}

// 2. THE PLAYERS
class Player(val agentName: String) {
    
    fun winRound() {
        println("$agentName eliminated the enemy team!")
        // Accessing the Singleton directly without 'new'
        MatchManager.defenderWin()
    }
    
    fun checkTab() {
        // Accessing the same Singleton to read data
        println("$agentName checks Tab: Current Score is ${MatchManager.getScore()}")
        MatchManager.showMemoryAddress()
    }
}

// 3. MAIN EXECUTION
fun main() {
    println("--- SETUP ---")
    // We create two distinct players
    val myJett = Player("Jett")
    val mySage = Player("Sage")

    println("\n--- ROUND 1 ---")
    // Jett wins the round and updates the Singleton
    myJett.winRound()
    
    println("\n--- VERIFICATION ---")
    // Sage checks the score. 
    // If this wasn't a Singleton, Sage would see 0-0.
    // Because it IS a Singleton, Sage sees 0-1.
    mySage.checkTab()
    
    println("\n--- ROUND 2 ---")
    // Let's have the Attackers win via the Manager directly
    MatchManager.attackerWin()
    
    // Jett checks tab
    myJett.checkTab()
}
