fun main() {
    val field = StartField(50, 40).buildMatrix()
    val matrix = Passages(field).drawAnthill()

    for(sublist in matrix) {
        for (j in sublist.indices){
            print(sublist[j])
        }

        println()
    }
}