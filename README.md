The Singleton Design Principle is a rule in coding that says: "There can be only one of this thing in the entire game, and everyone needs to be able to find it easily."

1. The Concept: "The Spike"
Think of the Singleton class as The Spike.

Rule 1: Single Instance (Only One Exists) In a standard round, there is only one Spike. Imagine if every Attacker spawned with their own Spike. You could plant at A, B, and C simultaneously. The Defenders wouldn't know which one to defuse, and the game would break.

Code Translation: The class restricts itself so you can never type new Spike() twice.

Rule 2: Global Access (Everyone Can Find It) You don't need to ask permission to see the Spike. If it's dropped on the ground, anyone can see it on the minimap. If it's planted, everyone hears the timer. It is a globally shared object.

Code Translation: You can access it from anywhere in your code using Spike.getInstance().

The Definition
"Software entities should be OPEN for Extension, but CLOSED for Modification."

Open for Extension: You can add new features (like a new Agent or Gun).

Closed for Modification: You don't have to touch/break the old code that is already working to add those new features.




Singleton Design Principal
https://medium.com/@paraspidurkar97/singleton-design-pattern-understanding-with-valorant-b21cc4a75637
