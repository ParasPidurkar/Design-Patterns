// ==========================================
// ISP (Interface Segregation Principle)
// Valorant-style example in Kotlin
// ==========================================

// ❌ BAD (fat interface) — don't do this
// Any agent would be forced to implement everything, even if it makes no sense.
// interface Agent {
//     fun move()
//     fun shoot()
//     fun healTeammate()
//     fun smokeArea()
//     fun flash()
// }

// ✅ GOOD (segregated interfaces) — ISP
interface Movable {
    fun move()
}

interface Shooter {
    fun shoot()
    fun reload()
}

interface Healer {
    fun healTeammate(target: String)
}

interface Smoker {
    fun smokeArea(location: String)
}

interface Flasher {
    fun flash()
}

// ==========================================
// Agents implement ONLY what they can do
// ==========================================

class Jett : Movable, Shooter {
    override fun move() = println("Jett dashes quickly ⚡")
    override fun shoot() = println("Jett fires her Vandal 🔫")
    override fun reload() = println("Jett reloads ✅")
}

class Sage : Movable, Healer {
    override fun move() = println("Sage rotates calmly 🧊")
    override fun healTeammate(target: String) = println("Sage heals $target ✨ (+HP)")
}

class Omen : Movable, Smoker {
    override fun move() = println("Omen teleports into position 🌑")
    override fun smokeArea(location: String) = println("Omen smokes $location ☁️")
}

class Phoenix : Movable, Shooter, Flasher {
    override fun move() = println("Phoenix pushes aggressively 🔥")
    override fun shoot() = println("Phoenix sprays bullets 🔫")
    override fun reload() = println("Phoenix reloads ✅")
    override fun flash() = println("Phoenix throws a curveball flash 💥")
}

// ==========================================
// "Game engine" uses only what it needs
// ==========================================

fun startRound(players: List<Movable>) {
    println("\n--- ROUND START: Everyone moves ---")
    players.forEach { it.move() }
}

fun executeGunFight(shooters: List<Shooter>) {
    println("\n--- GUNFIGHT: Only shooters involved ---")
    shooters.forEach {
        it.shoot()
        it.reload()
    }
}

fun doHeal(healers: List<Healer>) {
    println("\n--- SUPPORT: Only healers involved ---")
    healers.forEach { it.healTeammate("Jett") }
}

fun dropSmokes(smokers: List<Smoker>) {
    println("\n--- EXECUTE: Only smokers involved ---")
    smokers.forEach { it.smokeArea("A Site") }
}

fun popFlash(flashers: List<Flasher>) {
    println("\n--- ENTRY: Only flashers involved ---")
    flashers.forEach { it.flash() }
}

// ==========================================
// Main
// ==========================================

fun main() {
    val jett = Jett()
    val sage = Sage()
    val omen = Omen()
    val phoenix = Phoenix()

    // Everyone can move (Movable)
    startRound(listOf(jett, sage, omen, phoenix))

    // Only shooters can be passed here
    executeGunFight(listOf(jett, phoenix))

    // Only healers can be passed here
    doHeal(listOf(sage))

    // Only smokers can be passed here
    dropSmokes(listOf(omen))

    // Only flashers can be passed here
    popFlash(listOf(phoenix))

    // 🔥 If you try to put Sage into shooters, compiler stops you:
    // executeGunFight(listOf(sage)) // <- won't compile (Sage is not Shooter)
}

