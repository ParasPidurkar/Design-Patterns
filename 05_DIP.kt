// ==========================================
// DIP (Dependency Inversion Principle)
// Valorant-style example in Kotlin
// ==========================================

// ------------------------------
// 1) Abstraction (High-level depends on this)
// ------------------------------
interface Gun {
    val name: String
    fun shoot(): Int       // returns damage
    fun reload()
}

// ------------------------------
// 2) Low-level implementations (depend on abstraction)
// ------------------------------
class Vandal : Gun {
    override val name = "Vandal"
    private var bullets = 25

    override fun shoot(): Int {
        if (bullets <= 0) {
            println("❌ $name: Click! Out of bullets.")
            return 0
        }
        bullets--
        println("🔫 $name fired. Bullets left: $bullets")
        return 40
    }

    override fun reload() {
        bullets = 25
        println("🔄 $name reloaded. Bullets: $bullets")
    }
}

class Spectre : Gun {
    override val name = "Spectre"
    private var bullets = 30

    override fun shoot(): Int {
        if (bullets <= 0) {
            println("❌ $name: Click! Out of bullets.")
            return 0
        }
        bullets--
        println("🔫 $name sprayed. Bullets left: $bullets")
        return 26
    }

    override fun reload() {
        bullets = 30
        println("🔄 $name reloaded. Bullets: $bullets")
    }
}

// A test/dummy implementation (super useful for unit tests)
class TrainingGun : Gun {
    override val name = "TrainingGun"
    override fun shoot(): Int {
        println("🎯 $name: Pew! (no real damage)")
        return 1
    }
    override fun reload() = println("🔄 $name reloaded.")
}

// ------------------------------
// 3) High-level module (game logic)
// Depends ONLY on abstraction (Gun)
// ------------------------------
class DuelSimulator(private val gun: Gun) {

    fun startDuel() {
        println("\n=== Duel starts with ${gun.name} ===")

        var enemyHp = 100
        while (enemyHp > 0) {
            val damage = gun.shoot()

            if (damage == 0) {
                gun.reload()
                continue
            }

            enemyHp -= damage
            println("💥 Enemy HP: ${enemyHp.coerceAtLeast(0)}")

            if (enemyHp <= 0) {
                println("✅ Enemy eliminated using ${gun.name}!")
            }
        }
    }
}

// ------------------------------
// Main
// ------------------------------
fun main() {
    // High-level game logic doesn't care which gun you pass
    val vandalDuel = DuelSimulator(Vandal())
    vandalDuel.startDuel()

    val spectreDuel = DuelSimulator(Spectre())
    spectreDuel.startDuel()

    // Easy to test too
    val trainingDuel = DuelSimulator(TrainingGun())
    trainingDuel.startDuel()
}

