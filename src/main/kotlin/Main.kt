fun main() {
    val field = StartField(50, 40).buildMatrix().let {
        for(sublist in it) {
            for (j in sublist.indices){
                print(sublist[j])
            }

            println()
        }
    }


}