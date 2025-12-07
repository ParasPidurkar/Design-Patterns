// ==========================================
// 1. THE PARENT INTERFACES
// ==========================================

// Level 1: The General Parent
// Every item in the game follows these rules.
interface Weapon {
    val name: String
    fun equip()
}

// Level 2: The Specialized Parent
// Only things that shoot bullets implement this.
// Notice: It inherits from Weapon, so it's also a Weapon.
interface Firearm : Weapon {
    fun shoot()
    fun reload()
}

// ==========================================
// 2. THE CLASSES (Implementations)
// ==========================================

// VANDAL: It is a Firearm (and also a Weapon)
class Vandal : Firearm {
    override val name = "Vandal"

    override fun equip() {
        println(">> Vandal equipped. Ready to tap heads.")
    }

    override fun shoot() {
        println("   [BANG] Vandal fired a bullet!")
    }

    override fun reload() {
        println("   [CLICK-CLACK] Reloading Vandal...")
    }
}

// KNIFE: It is a Weapon, but NOT a Firearm
// This ensures we never accidentally ask it to shoot.
class Knife : Weapon {
    override val name = "Tactical Knife"

    override fun equip() {
        println(">> Knife in hand. Run faster!")
    }
    
    // Notice: There is NO shoot() method here. 
    // We physically cannot write code that breaks the game.
}

// ==========================================
// 3. THE "GAME ENGINE" (Main Logic)
// ==========================================

fun main() {
    println("--- PART 1: GENERIC ACTIONS (Safe for everyone) ---")
    // We can treat both Vandal and Knife as generic "Weapons"
    val inventory: List<Weapon> = listOf(Vandal(), Knife())

    for (item in inventory) {
        // Liskov Principle: Both Vandal and Knife can successfully 'equip'
        // without crashing the game.
        item.equip() 
    }

    println("\n--- PART 2: SHOOTING ACTIONS (Safe for guns only) ---")
    // We create a list specifically for Firearms
    val shooters: List<Firearm> = listOf(
        Vandal()
        // Knife() // <--- UNCOMMENT THIS LINE TO SEE THE MAGIC
        // If you try to put Knife() here, the Compiler turns RED.
        // It prevents the crash BEFORE you even run the game.
    )

    for (gun in shooters) {
        gun.shoot()
        gun.reload()
    }
}
