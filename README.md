#Reinforcement Learning in Tic-Tac-Toe

Using reinforcement learning to generate value function for playing Tic-Tac-Toe game is described
in Chapter 1 "Reinforcement Learning: An Introduction" by Sutton and Barto. In this project the
value function is trained via self-play. The initial game status value is determined as win -> 1,
draw -> 0, in process -> 0.5. The movement step can lead to either side winning the game is
considered as important. By default the agent is set to have 30% chance to take a random step
for exploratory, otherwise agent will choose maximum value by greedy search. The value
function will be updated after greedy search based the follow function:
V(s) <- V(s) + alpha * [ V(s') - V(s) ]

Sometimes, more than one cells value equal to current maximum value. Based on the game
knowledge, taking corner position will enlarge the winning possibility. Adding an expert
selecting step during the greedy move to ensure one of corner candidate positions will be
randomly selected when they have current maximum value.

#License

Created by Yu Fu, 21 Aug 2016

Code released under the GNU General Public License 

https://opensource.org/licenses
