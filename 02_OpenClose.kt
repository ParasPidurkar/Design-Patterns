// 1. THE CONTRACT (Interface)
// In Kotlin, we use properties (val) instead of getMethods().
interface Ability {
    val name: String
    val damage: Int
}

// 2. THE EXISTING ABILITIES (The Classes)
// Notice: No 'implements' keyword, we use ':'
class RazeGrenade : Ability {
    override val name = "Raze's Paint Shells"
    override val damage = 50
}

class SovaDart : Ability {
    override val name = "Sova's Shock Bolt"
    override val damage = 80
}

// 3. THE CALCULATOR (Closed for Modification)
// This logic stays the same regardless of how many agents we add.
class DamageCalculator {
    fun processHit(ability: Ability) {
        println("Processing hit from: ${ability.name}")
        println("Applying damage: ${ability.damage}")
        println("-------------------------------")
    }
}

// 4. THE EXTENSION (Open for Extension)
// Adding Gekko without touching the Calculator class
class GekkoMoshPit : Ability {
    override val name = "Gekko's Mosh Pit"
    override val damage = 100
}

// 5. MAIN EXECUTION
fun main() {
    // Create the calculator
    val calculator = DamageCalculator()

    // Create a list of abilities
    // Note: In Kotlin, we don't use the 'new' keyword
    val activeAbilities = listOf(
        RazeGrenade(),
        SovaDart(),
        GekkoMoshPit()
    )

    println("--- GAME START ---\n")

    // Loop through them
    for (ability in activeAbilities) {
        calculator.processHit(ability)
    }
}
