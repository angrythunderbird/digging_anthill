class Passages(private val field: MutableList<MutableList<String>>) {
    private val startPoint: Int = field[0].size / 2

    init {
        println(startPoint + 1)
    }
    private val barriers = mutableSetOf<List<Int>>()
//    private val barriers = mutableSetOf<Int>()

    data class StartEntries(var x: Int, var y: Int)

    init {
        addBarriers(x = startPoint , y = 0, lastTurn =  TurnNaming.FORWARD)
        println("init $barriers")
    }

    enum class TurnNaming {
        FORWARD,
        LEFT,
        RIGHT,
    }

    private val passages: MutableSet<List<Int>> = mutableSetOf(listOf(startPoint, 0))

    private fun addBarriers(x: Int, y: Int, lastTurn: TurnNaming) {
        when(lastTurn) {
            TurnNaming.FORWARD -> {
                if (y != 0) {
                    barriers.add(listOf(x - 1, y - 1))
                    barriers.add(listOf(x + 1, y - 1))
                    barriers.add(listOf(x, y))
                } else {
                    barriers.add(listOf(x - 1, y))
                    barriers.add(listOf(x + 1, y))
                }
            }
            TurnNaming.LEFT -> {
                barriers.add(listOf(x - 1, y - 1))
                barriers.add(listOf(x + 1, y + 1))
                barriers.add(listOf(x + 1, y))
                barriers.add(listOf(x + 2, y))
            }
            TurnNaming.RIGHT -> {
                barriers.add(listOf(x - 1, y + 1))
                barriers.add(listOf(x + 1, y - 1))
                barriers.add(listOf(x - 1, y))
                barriers.add(listOf(x - 2, y))
            }
        }
    }

    private fun checkBarriers(x: Int, y: Int): Boolean {
        return barriers.contains(listOf(x, y))
    }

    private fun step(x: Int, y: Int): Pair<Int, Int> {
        var currentX = x
        var currentY = y
        field[y][x] = " ██ "
            when(TurnNaming.entries.random()) {
                TurnNaming.LEFT -> {
                    currentX -= 1
                    if (checkBarriers(currentX + 0, currentY + 0)) {
                        currentX += 1
                        currentY += 1
                        addBarriers(x = currentX, y = currentY, lastTurn =  TurnNaming.FORWARD)
                    } else {
                        addBarriers(x = currentX, y = currentY, lastTurn = TurnNaming.LEFT)
                    }
                }
                TurnNaming.FORWARD -> {
                    currentY += 1
                    addBarriers(x = currentX, y = currentY, lastTurn =  TurnNaming.FORWARD)
                }
                TurnNaming.RIGHT -> {
                    currentX += 1
                    if (checkBarriers(currentX + 0, currentY + 0)) {
                        currentX -= 1
                        currentY +=1
                        addBarriers(x = currentX, y = currentY, lastTurn = TurnNaming.FORWARD)
                    } else {
                        addBarriers(x = currentX, y = currentY, lastTurn =  TurnNaming.RIGHT)
                    }
                }
//            }
        }

        return currentX to currentY
    }

    fun drawAnthill(): MutableList<MutableList<String>> {
        while (passages.size > 0) {
            passages.forEach {el -> println(el)}
//            passages.forEach {el -> println("PASSAGE: $el")}
            passages.forEach {
                var isNext = true
                val ddd = StartEntries(x = it[0], y = it[1])
                var guaranteedSteps = 0

                while (guaranteedSteps < 10 && ddd.y in 0..field.size - 1) {
                    println("${ddd.x} ${ddd.y}")
                    val (currXX, currYY) = step(x = ddd.x, y = ddd.y)
                    ddd.x = currXX
                    ddd.y = currYY
                    guaranteedSteps += 1
                }
                passages.remove(it)
                passages.add(listOf(ddd.x, ddd.y + 0))
                if (!checkBarriers(ddd.x + 1, ddd.y + 0)) {
                    passages.add(listOf(ddd.x + 1, ddd.y + 0))
                }
                if (!checkBarriers(ddd.x - 1, ddd.y + 0)) {
                    passages.add(listOf(ddd.x- 1, ddd.y + 0))
                }
                passages.forEach {el -> println("PASSAGES: $el")}
            }
        }
        return field
    }
}