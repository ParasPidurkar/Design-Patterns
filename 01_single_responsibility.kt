class AgentSelector {
    fun select(agentName: String) {
        println("🎮 Agent selected: $agentName")
    }
}

// 2️⃣ Responsibility: Agent Persistence (Database / Storage)
class AgentRepository {
    fun save(agentName: String) {
        println("💾 Saving agent '$agentName' to database")
    }
}

// 3️⃣ Responsibility: Analytics Tracking
class AgentAnalytics {
    fun track(agentName: String) {
        println("📊 Tracking analytics for agent '$agentName'")
    }
}

// 4️⃣ Responsibility: Orchestration ONLY
// (Coordinates other classes, but does not own their logic)
class ValorantAgentController(
    private val selector: AgentSelector,
    private val repository: AgentRepository,
    private val analytics: AgentAnalytics
) {
    fun onAgentSelected(agentName: String) {
        selector.select(agentName)
        repository.save(agentName)
        analytics.track(agentName)
    }
}

// -------------------------------
// Program Entry Point
// ------------------------------
fun main() {

    // Create dependencies
    val agentSelector = AgentSelector()
    val agentRepository = AgentRepository()
    val agentAnalytics = AgentAnalytics()

    // Inject dependencies
    val controller = ValorantAgentController(
        agentSelector,
        agentRepository,
        agentAnalytics
    )

    // Simulate Valorant gameplay
    controller.onAgentSelected("Jett")
    println("----")
    controller.onAgentSelected("Phoenix")
}
